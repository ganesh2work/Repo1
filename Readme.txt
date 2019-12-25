The main aim is to develop automated test cases for the collections endpoint of the given Zomato API. 
This framework is designed to test the given API where the test cases are written in an excel sheet and these test cases are input to this project.

the test cases in excel sheet contains the columns as Test Case Id, input parameters(city id, latitude, longitude, count) & expected response code. 
For each test case, I am constructing an url based on the input parameters and quering the API.
I am using REST ASSURED to query api and to  validate ResponseCode. 
Since I have concentrated only on the collections api to test, i am considering the fields of that api only. 
There can be more generalisation to the code, but as it will become a project all together, I am concentrating on the sole purpose of testing an API with a generic method to validate

