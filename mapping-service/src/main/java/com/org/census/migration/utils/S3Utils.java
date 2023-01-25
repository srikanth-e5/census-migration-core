package com.org.census.migration.utils;

import com.amazonaws.AmazonClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferManagerBuilder;
import com.amazonaws.services.s3.transfer.Upload;
import com.org.census.migration.exception.ServiceException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Component
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class S3Utils {

    public static boolean uploadFile(AmazonS3 s3Client, String s3BucketName, List<MultipartFile> files) {
        try {
            for(MultipartFile file: files){
                if(null == file || file.isEmpty()) {
                    throw new ServiceException(String.format(" File is not uploaded"));
                }
                TransferManager transferManager = TransferManagerBuilder.standard().withS3Client(s3Client).build();
                Upload upload = transferManager.upload(s3BucketName, file.getName(), file.getInputStream(),null);
                upload.waitForCompletion();
            }
            return true;
        } catch(AmazonClientException e) {
            throw new ServiceException(String.format("Upload not completed in bucket %s",
                                                     s3BucketName), e);
        } catch(InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ServiceException(String.format("Upload not completed in bucket %s",
                                                     s3BucketName), e);
        } catch (IOException exception) {
            throw new ServiceException(String.format("Upload not completed in bucket %s",
                                                     s3BucketName), exception);
        }
    }
}
