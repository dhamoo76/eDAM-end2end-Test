Feature: Home Page Verifications 

Scenario: Check Brand Link Displayed 
	Given User in HomePage
	And User Clicks "Rejuvenation" Icon
	Then User navigate to Corresponding brand page
