Feature: Validating place APIs

Scenario: Verify if Place is being successfull added using AddPlaceAPI
	Given Add placePayload
	When user calls "AddPlaceAPI" with post http request
	Then the API call got success with status code "200"
	And "status" in response body is "OK"
	And "scope" in response body is "APP"