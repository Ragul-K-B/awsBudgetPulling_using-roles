package org.example;

import software.amazon.awssdk.auth.credentials.AwsSessionCredentials;
import software.amazon.awssdk.services.sts.StsClient;
import software.amazon.awssdk.services.sts.model.AssumeRoleRequest;
import software.amazon.awssdk.services.sts.model.AssumeRoleResponse;
import software.amazon.awssdk.regions.Region;

public class STSService {
    public static AwsSessionCredentials assumeRole(String roleArn, String sessionName, Region region) {
        // Create an STS client
        StsClient stsClient = StsClient.builder()
                .region(region)
                .build();

        // Prepare the AssumeRole request
        AssumeRoleRequest assumeRoleRequest = AssumeRoleRequest.builder()
                .roleArn(roleArn)
                .roleSessionName(sessionName)
                .build();

        // Send the request and get the response
        AssumeRoleResponse assumeRoleResponse = stsClient.assumeRole(assumeRoleRequest);

        // Extract and return temporary credentials
        return AwsSessionCredentials.create(
                assumeRoleResponse.credentials().accessKeyId(),
                assumeRoleResponse.credentials().secretAccessKey(),
                assumeRoleResponse.credentials().sessionToken()
        );
    }
}
