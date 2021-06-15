Feature: Validate the place APIs

  @addPlace
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

  @deletePlace
  Scenario: Verify if delete place functionality is working
    Given Add place payload
    When user calls "AddPlaceAPI" with "POST" http request
    Then status code is "200"
    And "status" in response body is "OK"
    Given Delete Place Payload
    When user calls "DeletePlaceAPI" with "DELETE" http request
    Then status code is "200"
    And "status" in response body is "OK"
