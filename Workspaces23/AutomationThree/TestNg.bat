set projectLocation=D:\Sandeep\Selenium\Workspaces23\AutomationThree
cd %projectLocation%
set classpath=%projectLocation%\bin;%projectLocation%\lib\*;C:\Users\sandeep.vadrale\.m2\repository\*;
java  org.testng.TestNG %projectLocation%\testng.xml
pause