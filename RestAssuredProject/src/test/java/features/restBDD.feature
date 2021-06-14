Feature: Validate the place APIs

  Scenario Outline: Verify user can add a new place using addPlaceAPI
    Given Add place payload with "<name>" "<language>" "<address>"
    When user calls "AddPlaceAPI" with "POST" http request
    Then status code is "200"
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify place_id created maps to "<name>" using "GetPlaceAPI"

    Examples:
      | name  | language | address |  |
      | aaaaa | EN       | bbbbbb  |  |
     # | bbbbb | ES       | cccccc  |  |


  Scenario: Verify if delete place functionality is working
    Given Delete Place Payload
    When user calls "DeletePlaceAPI" with "POST" http request
    Then status code is "200"
    And "status" in response body is "OK"
