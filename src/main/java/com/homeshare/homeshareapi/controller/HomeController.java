package com.homeshare.homeshareapi.controller;

import com.homeshare.homeshareapi.model.Home;
import com.homeshare.homeshareapi.repository.HomeRepository;
import com.homeshare.homeshareapi.service.HomeService;
import lombok.AllArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/home")
@AllArgsConstructor
public class HomeController {

    private final HomeService homeService;
    private final HomeRepository homeRepository;

    @PostMapping("/create")
    public void createHome(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("image1") MultipartFile image1,
            @RequestParam("image2") MultipartFile image2,
            @RequestParam("image3") MultipartFile image3,
            @RequestParam("startRent") String startRent,
            @RequestParam("endRent") String endRent
    ) throws IOException {
        Home home = new Home();
        home.setTitle(title);
        home.setDescription(description);
        home.setStartRent(startRent);
        home.setEndRent(endRent);

        // Generate unique file names for the images
        String image1FileName = UUID.randomUUID().toString() + ".png";
        String image2FileName = UUID.randomUUID().toString() + ".png";
        String image3FileName = UUID.randomUUID().toString() + ".png";
// "http://localhost:8080/home/image"+UUID.randomUUID().toString() + ".png";
        // Save the images to the server
        saveImage(image1, image1FileName);
        saveImage(image2, image2FileName);
        saveImage(image3, image3FileName);

        String image1Path = "http://localhost:8080/home/image/"+image1FileName;
        String image2Path = "http://localhost:8080/home/image/"+image2FileName;
        String image3Path = "http://localhost:8080/home/image/"+image3FileName;

        // Set the image file names in the home object
        home.setImage1(image1Path);
        home.setImage2(image2Path);
        home.setImage3(image3Path);

        // Save the home object to the database
        homeRepository.save(home);
    }

    private void saveImage(MultipartFile image, String fileName) throws IOException {
        // Convert the image to bytes
        byte[] imageData = IOUtils.toByteArray(image.getInputStream());

        // Save the image to the server
        FileOutputStream fos = new FileOutputStream("src/main/resources/" + fileName);
        fos.write(imageData);
        fos.close();
    }


    @GetMapping("/image/{fileName}")
    public ResponseEntity<byte[]> getImage(@PathVariable String fileName) throws IOException {
        ClassPathResource resource = new ClassPathResource(fileName);
        byte[] imageBytes = IOUtils.toByteArray(resource.getInputStream());

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(imageBytes);
    }


    @GetMapping("/read")

    public List<Home> read(){
        return homeService.read();
    }

    @PutMapping("/update/{id}")
    public Home update(@PathVariable UUID id, @RequestBody Home home) {
        return homeService.modify(id, home);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable UUID id) {
        return homeService.delete(id);
    }

    @GetMapping("/search")
    public List<Home> searchByTitle(@RequestParam String criteria){
        return homeService.searchByCriteria(criteria);
    }

}
