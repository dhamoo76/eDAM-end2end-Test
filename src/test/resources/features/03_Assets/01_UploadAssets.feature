Feature Upload Assets

Scenarios: Verify that user able to upload single asset
Given User is on Edam assert Page
When User click Create > files Button and User Select ‘capture’ to upload
And User clicks the upload button
Then Uploaded assets should be available in the system
