Feature: Test main page Banco Central

Scenario: User uses Banco Central main page to see the value of the coins 
Given user open Chrome browser
When user search main page of Banco Central
And number of h1 tags is validated
And number of p tags is validated
And site title is validated
Then user can view the value of the coins