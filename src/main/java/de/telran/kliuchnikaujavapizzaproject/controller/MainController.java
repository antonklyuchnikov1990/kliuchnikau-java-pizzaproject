package de.telran.kliuchnikaujavapizzaproject.controller;

import de.telran.kliuchnikaujavapizzaproject.model.Cafe;
import de.telran.kliuchnikaujavapizzaproject.model.Pizza;
import de.telran.kliuchnikaujavapizzaproject.repository.CafeRepository;
import de.telran.kliuchnikaujavapizzaproject.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MainController {

    @Value("${images.dir}")
    private String imagesDir;

    @GetMapping("/image/{filename}")
    public ResponseEntity<byte[]> downloadImage(@PathVariable String filename) throws IOException {
        byte[] image = Files.readAllBytes(new File(imagesDir + "/" + filename).toPath());
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(image);
    }

}
