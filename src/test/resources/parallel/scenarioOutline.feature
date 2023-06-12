Feature: Login Test case

Scenario Outline: Check Login with credentials
Given launch the browser 
When User enters the <email> and  <password>
Then User should see the login 

Examples:
|email             |password|
|vijiraja@gmail.com|viji123|  
|viji1@gmail.com|viji123|

