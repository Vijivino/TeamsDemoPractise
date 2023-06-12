Feature: DemoShop Registration DataTable

Scenario: Registration with multiple data with headers
Given User is on demoshop registration page
When User enters following details with headers
		| firstname | lastname | email | password | confpass |
		| viji2 | a | viji2@123.com | viji@123| viji@123 |
		| laxmi | V | Vlaxmi@abc.com | laxmi123 | laxmi123 |
Then User can see registration is successful
