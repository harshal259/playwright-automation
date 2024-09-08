Feature: Login Page feature

  @test
  Scenario Outline: Verify User is able to login to Book Store with correct credentials
    Given user navigates to "url"
    When user searches for "<SearchText>"
    Then verify search results are displayed and contain "<SearchText>"
#    Then verify that user is logged in and navigated to Profile page

  Examples:

    |SearchText|
    |Docker Playwright|