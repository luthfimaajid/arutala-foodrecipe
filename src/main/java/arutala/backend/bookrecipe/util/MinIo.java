package arutala.backend.bookrecipe.util;

import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class MinIo {
    @Value("${minio.url}")
    private String URL;

    @Value("${minio.username}")
    private String USERNAME;

    @Value("${minio.password}")
    private String PASSWORD;

    @Value("${minio.bucket}")
    private String BUCKET;

    private MinioClient minioClient;

    @PostConstruct
    void init(){
        this.minioClient = MinioClient.builder()
                .endpoint(URL)
                .credentials(USERNAME, PASSWORD)
                .build();
    }

    public void upload(String fileName, MultipartFile file) {
        try {
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(BUCKET)
                            .object(fileName)
                            .contentType(file.getContentType())
                            .stream(file.getInputStream(), file.getSize(), -1)
                            .build()
            ).etag();
        } catch (Exception e) {
            throw new RuntimeException("error");
        }

    }


}




