package com.org.census.migration.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Configuration
@Profile({"local"})
public class AwsS3LocalstackConfig {

    @Value("${aws.localstack.s3.url:http://localhost:14572}")
    private String serviceEndpoint;

    @Value("${com.org.census.migration.s3.bucketName:source-files}")
    private String s3BucketForSourceFileUpload;

    public static final Regions DEFAULT_REGION = Regions.US_WEST_2;
    public static final String TEST_ACCESS_KEY = "test";
    public static final String TEST_SECRET_KEY = "test";
    public static final AWSCredentials TEST_CREDENTIALS = new BasicAWSCredentials(TEST_ACCESS_KEY, TEST_SECRET_KEY);

    @Primary
    @Bean
    public AmazonS3 s3Client() {
        AmazonS3 s3Client = AmazonS3ClientBuilder
                                    .standard()
                                    .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(serviceEndpoint, DEFAULT_REGION.getName()))
                                    .withCredentials(new AWSStaticCredentialsProvider(TEST_CREDENTIALS))
                                    .withForceGlobalBucketAccessEnabled(true)
                                    .enableForceGlobalBucketAccess()
                                    .withPathStyleAccessEnabled(true)
                                    .build();
        log.info("aws s3 from local stack");

        //bug in aws api: you need to set aws region by setting AWS_REGION env var
        //otherwise the code above fails. adding withRegion results in an error too

        Objects.requireNonNull(s3Client, "Null AmazonS3 returned");
        List<Bucket>  bucketList = s3Client.listBuckets();

        Optional<Bucket> bucket = bucketList.stream().filter(bket -> bket.getName().equals(s3BucketForSourceFileUpload)).findFirst();
        if(bucket.isEmpty()) s3Client.createBucket(s3BucketForSourceFileUpload);
        return s3Client;
    }

}

