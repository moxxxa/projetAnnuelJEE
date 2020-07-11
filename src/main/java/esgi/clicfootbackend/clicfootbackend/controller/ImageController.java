package esgi.clicfootbackend.clicfootbackend.Image;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import esgi.clicfootbackend.clicfootbackend.controller.UserController;
import esgi.clicfootbackend.clicfootbackend.repositoryDao.ImageRepository;
import esgi.clicfootbackend.clicfootbackend.service.ImageService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import esgi.clicfootbackend.clicfootbackend.Model.Image.ImageModel;


@RestController
@RequestMapping(path = "image")
@CrossOrigin
public class ImageController {

    private static Logger logger;
    static {
        try {
            // you need to do something like below instaed of Logger.getLogger(....);
            logger = LogManager.getLogger(UserController.class);
        } catch (Throwable th) {
        }
    }

    @Autowired
    ImageRepository imageRepository;

    @Autowired
    private ImageService imageService;

    @PostMapping("/upload")
    public ImageModel uplaodImage(@RequestParam("imageFile") MultipartFile file) throws IOException {
        logger.info("Original Image Byte Size - " + file.getBytes().length + " - fileName - " + file.getOriginalFilename());
        imageService.TriggerSaveImage(file);
        logger.info("Image save successfully");
        return imageService.fetshAndGet(file.getOriginalFilename());
    }

    @GetMapping(path = { "/get/{imageName}" })
    public ImageModel getImage(@PathVariable("imageName") String imageName) throws IOException {
        logger.info("request to retreive image : " + imageName);
        return imageService.fetshAndGet(imageName);
    }

    @DeleteMapping("/delete/{imageName}")
    public ResponseEntity<ImageModel> deleteUser(@PathVariable("imageName") String imageName){
        logger.info("request to delete image : " + imageName);
        final Optional<ImageModel> retrievedImage = imageRepository.findByName(imageName);
        logger.info("retrevied image =" + retrievedImage.toString());
        if (retrievedImage.isPresent()) {
            ImageModel img = new ImageModel(retrievedImage.get().getName(), retrievedImage.get().getType(),
                    decompressBytes(retrievedImage.get().getPicByte()));
            logger.info("the image was deleted successfully");
            return new ResponseEntity<ImageModel>(imageService.deleteImage(img),HttpStatus.OK);
        }
        return new ResponseEntity<ImageModel>(new ImageModel(),HttpStatus.OK);
    }

    // compress the image bytes before storing it in the database
    public static byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
        }
        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);

        return outputStream.toByteArray();
    }

    // uncompress the image bytes before returning it to the angular application
    public static byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException ioe) {
        } catch (DataFormatException e) {
        }
        return outputStream.toByteArray();
    }
}