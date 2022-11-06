package uz.spring.oson_shop_uz.security.base;

import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.nio.file.Path;
import java.util.stream.Stream;

public interface FileStorage {
    public void init();
    public void save(MultipartFile file);


    public void deleteAll();

    public Stream<Path> loadAll();
}
