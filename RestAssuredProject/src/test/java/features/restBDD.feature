Feature: Validate the place APIs

  Scenario: Verify user can add a new place using addPlaceAPI
    Given Add place payload
    When user calls "addPlaceAPI" with POST http request
    Then status code is "200"
    And "status" in response body is "OK"
    And "scope" in response body is "APP"