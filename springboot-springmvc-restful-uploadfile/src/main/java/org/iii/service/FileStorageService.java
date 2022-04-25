package org.iii.service;

import lombok.extern.slf4j.Slf4j;
import org.iii.exception.FileStorageException;
import org.iii.exception.MyFileNotFoundException;
import org.iii.misc.FileStorageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Slf4j
@Service
public class FileStorageService {

    private final Path fileStorageLocation;


    // 由建構子手動注入，由於Path本身沒有被Spring接管所以才會這樣子去寫
    @Autowired
    public FileStorageService(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("無法創建檔案儲存資料夾", ex);
        }
    }

    public String storeFile(MultipartFile file) {
        // 取得檔案名稱
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // 確認檔案有無不合法字母
            if(fileName.contains("..")) {
                throw new FileStorageException("檔名不合法 " + fileName);
            }

            // 寫入目的地 (如果重複就覆寫)
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException ex) {
            throw new FileStorageException("無法儲存檔案:" + fileName, ex);
        }
    }

    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new MyFileNotFoundException("找不到檔案" + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new MyFileNotFoundException("找不到檔案" + fileName, ex);
        }
    }

}
