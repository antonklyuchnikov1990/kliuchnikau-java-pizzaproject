package de.telran.kliuchnikaujavapizzaproject.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.telran.kliuchnikaujavapizzaproject.model.Cafe;
import de.telran.kliuchnikaujavapizzaproject.repository.CafeRepository;
import jakarta.annotation.Resource;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CafeRestController.class)
public class RestControllerTest {

    @MockBean
    CafeRepository cafeRepository;

    @Resource
    MockMvc mockMvc;

    @Test
    @WithMockUser(username = "tt@sc.de", roles = {"USER", "ADMIN"})
    public void testFindAllCafes() throws Exception {
        Cafe cafe = new Cafe();
        cafe.setName("Test Cafe");
        Mockito.when(cafeRepository.findAll()).thenReturn(List.of(cafe));
        mockMvc.perform(get("/api/cafes").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].name", Matchers.is("Test Cafe")));
    }

    @Test
    @WithMockUser(username = "tt@sc.de", roles = {"USER", "ADMIN"})
//    @WithUserDetails("admin")
//    @WithUserDetails("user")
//    @WithAnonymousUser
    public void testCreateCafe() throws Exception {
        Cafe cafe = new Cafe();
        cafe.setName("Test Cafe");
        cafe.setAddress("Berlin");

        Cafe cafe2 = new Cafe();
        cafe2.setName("Test Cafe");
        cafe2.setId("1");
        cafe2.setAddress("Berlin");

        Mockito.when(cafeRepository.save(any(Cafe.class))).thenReturn(cafe2);

        mockMvc.perform(post("/api/cafe")
                        .with(csrf())
                        .content(asJsonString(cafe))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value("Test Cafe"));
    }

    private String asJsonString(Cafe cafe) {
        try {
            return new ObjectMapper().writeValueAsString(cafe);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @WithMockUser(username = "tt@sc.de", roles = {"USER", "ADMIN"})
//    @WithUserDetails("admin")
//    @WithUserDetails("user")
//    @WithAnonymousUser
    public void testUpdateCafe() throws Exception {
        Cafe cafe = new Cafe();
        cafe.setName("Test Cafe");

        Cafe cafe2 = new Cafe();
        cafe2.setName("Test Cafe2");
        cafe2.setId("1");

        Mockito.when(cafeRepository.save(any(Cafe.class))).thenReturn(cafe2);

        mockMvc.perform(post("/api/cafe")
                        .with(csrf())
                        .content(asJsonString(cafe))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value("Test Cafe2"));
    }

    @Test
    @WithMockUser(username = "tt@sc.de", roles = {"USER", "ADMIN"})
//    @WithUserDetails("admin")
//    @WithUserDetails("user")
//    @WithAnonymousUser
    public void testDeleteCafe() throws Exception {
        Cafe cafe = new Cafe();
        cafe.setName("Test Cafe");
        cafe.setId("4cbd813d-110d-4047-b095-24c931f7d9d3");

        Mockito.when(cafeRepository.findById("4cbd813d-110d-4047-b095-24c931f7d9d3"))
                .thenReturn(Optional.of(cafe));

        cafeRepository.delete(cafe);

        mockMvc.perform(delete("/api/cafe/4cbd813d-110d-4047-b095-24c931f7d9d3")
                        .with(csrf())
                        .content(asJsonString(cafe))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());

        Mockito.verify(cafeRepository, Mockito.times(1)).delete(cafe);
    }
}
