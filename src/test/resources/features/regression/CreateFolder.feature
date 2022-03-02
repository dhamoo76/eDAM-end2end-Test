Feature: Create new folder
  @RegressionTest @End2End
  Scenario: Create new folder in EDAM
    Given User in HomePage
    And User click on selected brand folder
    When User click on create button
    And click any option from the list
      | OptionsList |
      | folder      |
    Then user enters data in the form and save
      | OptionsList | Title                        | Name                         |
      | folder      | Test-Automation-CreateFolder | Test-Automation-CreateFolder |
    And user delete selected folder or asset
      | OptionsList | Title                        |
      | folder      | Test-Automation-CreateFolder |

