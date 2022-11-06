package uz.spring.oson_shop_uz.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.spring.oson_shop_uz.entity.Attachment;
import uz.spring.oson_shop_uz.admin.repository.AttachmentContentRepository;
import uz.spring.oson_shop_uz.admin.repository.AttachmentRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class AttachmentService {
    private static final String uploadDirectory = "yuklanganlar";


    private final AttachmentRepository attachmentRepository;
    private final AttachmentContentRepository attachmentContentRepository;

    @Autowired
    public AttachmentService(AttachmentRepository attachmentRepository, AttachmentContentRepository attachmentContentRepository) {
        this.attachmentRepository = attachmentRepository;
        this.attachmentContentRepository = attachmentContentRepository;
    }

    public MultipartFile uploadFileToSystemImg(MultipartFile file) throws IOException {

        if (file != null) {
            String originalFilename = file.getOriginalFilename();
            Attachment attachment = new Attachment();
            attachment.setFileOriginalName(originalFilename);
            attachment.setSize(file.getSize());
            attachment.setContentType(file.getContentType());
            String[] split = originalFilename.split("\\.");
            String name = UUID.randomUUID().toString() + "." + split[split.length - 1];
            attachment.setName(name);
            attachmentRepository.save(attachment);
            Path path = Paths.get(uploadDirectory + "/" + name);
            Files.copy(file.getInputStream(), path);
            return file;
        }
        return null;
    }



}
