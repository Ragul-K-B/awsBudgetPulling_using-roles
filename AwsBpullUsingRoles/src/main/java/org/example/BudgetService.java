package org.example;

import software.amazon.awssdk.auth.credentials.AwsSessionCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.services.budgets.BudgetsClient;
import software.amazon.awssdk.services.budgets.model.DescribeBudgetsRequest;
import software.amazon.awssdk.services.budgets.model.DescribeBudgetsResponse;
import software.amazon.awssdk.regions.Region;

public class BudgetService {
    public static void fetchAndPrintBudgets(AwsSessionCredentials credentials, String accountId, Region region) {
        // Create a Budgets client using the temporary credentials
        BudgetsClient budgetsClient = BudgetsClient.builder()
                .region(region)
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .build();

        // Prepare the DescribeBudgets request
        DescribeBudgetsRequest request = DescribeBudgetsRequest.builder()
                .accountId(accountId)
                .maxResults(10)
                .build();

        // Send the request and get the response
        DescribeBudgetsResponse response = budgetsClient.describeBudgets(request);

        // Print budget details
        response.budgets().forEach(budget -> {
            System.out.println(budget);
            //System.out.println("Budget Name: " + budget.budgetName());
            //System.out.println("Budget Type: " + budget.budgetType());
            //System.out.println("Budget Limit: " + budget.budgetLimit().amount() + " " + budget.budgetLimit().unit());
            //System.out.println("Budget Time Unit: " + budget.timeUnit());
            //System.out.println("Start Date: " + budget.timePeriod().start());
            //System.out.println("End Date: " + budget.timePeriod().end());
        });
    }
}
