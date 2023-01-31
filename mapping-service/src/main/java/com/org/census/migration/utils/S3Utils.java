package com.org.census.migration.utils;

import com.amazonaws.AmazonClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferManagerBuilder;
import com.amazonaws.services.s3.transfer.Upload;
import com.org.census.migration.exception.ServiceException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class S3Utils {

    public static boolean uploadFile(AmazonS3 s3Client, String s3BucketName,String filePath, String fileName, MultipartFile file) {
        try {
            if(null == file || file.isEmpty()) {
                throw new ServiceException(" File is not uploaded");
            }
            TransferManager transferManager = TransferManagerBuilder.standard().withS3Client(s3Client).build();
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(file.getSize());
            Upload upload = transferManager.upload(s3BucketName, filePath + "/" + fileName, file.getInputStream(), objectMetadata);
            upload.waitForCompletion();
            return true;
        } catch(AmazonClientException | IOException e) {
            throw new ServiceException(String.format("Upload not completed in bucket %s",
                                                     s3BucketName), e);
        } catch(InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ServiceException(String.format("Upload not completed in bucket %s",
                                                     s3BucketName), e);
        }
    }
}
