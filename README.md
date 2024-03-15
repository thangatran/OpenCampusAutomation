# Open Campus API Automation Test

## Requirements
 * Maven version 3.9.6 or above
 * Java version 20 or above

## Run using CLI 
 * `mvn test -Dcucumber.filter.tags='@api_regression' -Dtest=ApiTestRunner`
   
   - Dcucumber.filter.tags: tests with the tags will be run, for multiple tags, can do "@test1 or @test2 or @test3".
   - Dtest: Test runner, default is ApiTestRunner
     
 * After tests are run, raw test report will be generated under target/allure-results
 * To generate a enriched report (readable) report, run `mvn allure:report`
 * The enriched report will be generated under target/site/allure-maven-plugin/index.html
 * To read the report, open the html file in a web browser
   ![image](https://github.com/thangatran/OpenCampusAutomation/assets/5816644/cb113f32-01f1-47fe-a0f7-a47b3d8afc0e)


 * Alternately, can open the enriched report directly by running `mvn allure:serve`

## Run using IDE (IntelliJ) 
 * Import source to IntelliJ
 * Add TestNg as run configuration
 * Run the ApiTestRunner configuration
   - Tag is @api_regression by default, can update it in ApiTestRunner
   - Can add break points for debugging

## Technologies used: 
   Java, Cucumber, TestNg, RestAssurer API, Allure Report

## Cucumber Scenarios
 * Test scenarios are defined under src/test/java/features
 * They are written in natural language and can be reviewed by others
      
