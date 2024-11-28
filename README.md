
**Before testing make sure that you have already set JAVA_HOME**
**Make sure that all required packages are installed!**
**Make sure that allure is installed - npm install -g allure-commandline**

## RestApi testing

1. Create RestApi tests in specific directory. 
2. Add test classes to restApi.xml file.
3. Open terminal and go to the project directory
4. Run command 

`mvn clean -Dsuite=restApi.xml test`

## Reports
To generate Allure Reports run command:

`allure serve target/allure-results`

or 

`allure generate target/allure-results'
'allure open`
