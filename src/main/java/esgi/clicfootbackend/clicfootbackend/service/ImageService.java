package esgi.clicfootbackend.clicfootbackend.service;

import esgi.clicfootbackend.clicfootbackend.controller.UserController;
import esgi.clicfootbackend.clicfootbackend.error.ImageAlreadyExistException;
import esgi.clicfootbackend.clicfootbackend.repository.ImageRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Optional;

import static esgi.clicfootbackend.clicfootbackend.Image.ImageController.compressBytes;
import static esgi.clicfootbackend.clicfootbackend.Image.ImageController.decompressBytes;
import esgi.clicfootbackend.clicfootbackend.Image.ImageModel;

@Service
@Slf4j
public class ImageService {
    private final ImageRepository imageRepository;

    @Autowired
    public ImageService(ImageRepository imageRepository){
        this.imageRepository = imageRepository;
    }

    private static Logger logger;
    static {
        try {
            // you need to do something like below instaed of Logger.getLogger(....);
            logger = LogManager.getLogger(UserController.class);
        } catch (Throwable th) {
        }
    }

    @Transactional
    public ImageModel deleteImage(ImageModel img){
        logger.info("processing to delete image from the dataBase ...");
        imageRepository.deleteByName(img.getName());
        return img;
    }

    public void TriggerSaveImage(MultipartFile file) throws ImageAlreadyExistException, IOException
    {
//        if(imageRepository.findByName(file.getOriginalFilename()) != null){
//            throw new ImageAlreadyExistException(
//                    "can't create image because an image with the same name already exist: " + file.getOriginalFilename()
//            );
//        }

        ImageModel img = new ImageModel(file.getOriginalFilename(), file.getContentType(),
                compressBytes(file.getBytes()));
        logger.info("processing to save image in the dataBase ...");
        imageRepository.save(img);
    }

    public ImageModel fetshAndGet(String imageName) {
        logger.info("processing to retrieve image from the dataBase ...");
        final Optional<ImageModel> retrievedImage = imageRepository.findByName(imageName);
        if (retrievedImage.isPresent()) {
            ImageModel img = new ImageModel(retrievedImage.get().getName(), retrievedImage.get().getType(),
                    decompressBytes(retrievedImage.get().getPicByte()));
            return img;
        }
        return new ImageModel();
    }

}
