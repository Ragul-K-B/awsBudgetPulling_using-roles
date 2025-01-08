package org.example;
import software.amazon.awssdk.auth.credentials.AwsSessionCredentials;
import software.amazon.awssdk.regions.Region;

public class Main {
    public static void main(String[] args) {
        // Define constants
        Region friendRegion = Region.AP_SOUTH_1;
        String roleArn = "arn:aws:iam::122610524556:role/Budget_role2";  // Friend's role ARN
        String roleSessionName = "BudgetSession";
        String accountId = "122610524556";  // Friend's account ID

        // Step 1: Assume the role
        AwsSessionCredentials temporaryCredentials = STSService.assumeRole(roleArn, roleSessionName, friendRegion);

        // Step 2: Fetch and print budget details
        BudgetService.fetchAndPrintBudgets(temporaryCredentials, accountId, friendRegion);
    }
}
