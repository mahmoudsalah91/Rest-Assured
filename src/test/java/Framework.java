/*
Rest Assured : is Api/library through which can automate rest Apis
-steps to build framework :-
1- read functional requirement specification (static test) or if there are swagger documentation
note swagger is like commendation contain what are the prerequisite ,request and response
------------------------
2- design
- prerequisites to build framework
-prepare dependencies and plugins
1-Java +9 & IDE(intellij)
2-TestNg
3-Maven
- dependencies need to add in pox.xml file
1-rest-assured
2-json-path
3-json
4-gson
5-TestNg
6-scribe java-apis  (generate random data)
7-json-schema-validator
8-xml-schema-validator
9-extentreports

-write testcase manual
--------------------------------
3-development testcases in script
1-create routes class (urls)
2-create endpoint class (CRUD methods implementation)
note:CRUD are Create ,Retrieve , Update ,Delete methods
-pass in the variable of this method attributes and request body that create by pojo class  ex:(User)

3-create payload(request body)
-can do by ex:(pojo class) after create import in endpoints class

4-create testcases
-validation statements
---------------------------------
5-utilities
data driven test(data provider)>> repeat the same test methods with different set of data without use looping by:
-prepare Excel Sheet with data
-create ExcelUtility class >> read the data from Excel sheet
-create DataProvider class >> get data from Excel sheet and store it in 2 dimensions array and pass it in test methods
----------------------------------
6-Add Extent Report to project
-create ExtentReportUtility class under utilities package
-add captureScreen() method in BaseClass
-add ExtentReportUtility (Listener class) entry in TestNg Xml file ex:TestRunner.xml
-make sure WebDriver is static in BaseClass as we refer same instance in ExtentReportUtility class
--------------------------------
7-Read from properties file
-create routes.properties file under resources package and create key and value like Post_url=http//www.google.com
-loading config properties file in routes class ex:UserEndPointsWithPropertiesFile by two ways

  1-by FileReader
        public static Properties propertyFile;
        FileReader file = new FileReader(System.getProperty("user.dir")+"/src/test/resources/routes.properties");
        propertyFile = new Properties();
        propertyFile.load(file);

  2-by method getting url from properties file in resource package

    static ResourceBundle getUrl()
    {
        ResourceBundle routes =ResourceBundle.getBundle("routes");
        return routes;
    }

-pass key in the testClass like in TestsWithPropertiesFile class ex: Post_url-Get_url-...
----------------------------------

4-execute testcases by testNg xml file
---------------------------------------

5-execute testcases by maven

---execution remotely---
to execution testcases remotely we need:
1-build tool (maven)
2-pipeline tool (jenkins)

1-build tool (maven) : maven is type of project contain pom.xml file
 -note build here different than development concept it mean here execute testcases in automation test project
-through pom.xml we can execution the testcases remotely
--pom.xml contain:-
1-dependencies: by downloading required dependency jar file for project
2-plugins: to compile and run the project
--plugins types:-
1-maven compile plugin >>> compile the project
2-maven surefire plugin >>> run the project
---run through pom.xml file Directly-----
1-through these two plugins can execute test suits through pom.xml file inside the compiler (Intellij , Eclipse)
2-add two plugins in pom.xml file through official websites of maven compile plugin & maven surefire plugin
3-add maven compile plugin & maven surefire plugin under  build tag > pluginManagement > plugins  in pom.xml file
4-to run testcase you should add under maven surefire plugin:-
<configuration>
      <suiteXmlFiles>
         <suiteXmlFile>TestRunner</suiteXmlFile>
      </suiteXmlFiles>
</configuration>
-note:where TestRunner is testNg Xml file contain testsuite you want to run
and should TestRunner.xml locate in the level of project no inside package or folder
5-click M in right up corner in IntelliJ and in lifecycle and click test
-note when BUILD SUCCESS appear in run log mean execution is successful done

---run through pom.xml file through terminal command-----
-as default maven project build in when create java project by any compiler like (Intellij , Eclipse) so can run project through compiler
-but in case you want to run pom.xml file outside compiler like (Intellij , Eclipse) through terminal command need to install maven
--to install maven in your device os linux:-
-in terminal write >> sudo apt install maven ,  enter Y to confirm the installation.
-then to verify that install >> mvn -version
-then you ready to run project through terminal by two methods:-
1-(manually) go to location of your project want to run > open terminal > run command mvn test or mvn clean test
2- (by file.sh)  crate sh file and inside it but these two command:
cd location of project want run  ex: cd /home/mahmoudsalah/IdeaProjects/Seleniumwebdriver
mvn test
then can open file and run
-note in windows os file extension become file.bat

------CI/CD---------
steps for CI/CD :-
after finish from execute test suites through pom.xml file you should flow this steps to apply CI/CD:
git >>> github >> jenkins
-git: is local repository that store your code in your machine
-git hub : is remote repository need to make account in github website and create repository and catch url of this repo
--the flow of the project in git and git hub:
work directory           staging area          git repository           github repository
                  add                  commit                  push
untracked file ---------> track file ----------> commit file ----------> remote file

-installation of git in your machine
-can make local repository by write command in terminal or by IDE you use
-by command:
- go to location of your project open bash git (terminal of git appear after installation of git) and write
1-create a new local repository (one time) >> git init
2-provide user info to git repo (one time) >> git config --global user.name "username" & git config --global user.email "email"
3-add files/folders to staging/indexing area:
git add A: add all files and folder to staging
git add file name : add specific file
git add *.jave : add all file end with same extension ex:.jave
4-commit the code into local(git) repository >> git commit -m "commit message"
5-connect local repo with remote repo (one time) >> git remote add origin "repo url in github"
6-push the code to remote repo >> git push origin master
note:require token or account of github
7-after that any change (add or modify) in your code you just need >> git add -A , git commit -m "message" , git push origin master
8-pull file from remote repo to his local >> git pull "remote repo url"
9-clone remote repository to his local repo >> git clone "remote repo url"
note: difference between git pull and git:-
if we already has local repo use pull  & if we not have local repo use clone

note: every body work on project push his code by this flow:
local branch -----> remote branch -----> merge to master branch
merge conflict : more than one body change in the same line of code and merge two change to master branch at one shot
-if merge conflict happen github can resolve it by display all changes cause this conflict and you chose which push

--execute through jenkins----
jenkins : is devops CI/CD tool use to pull project code automatically from git repository and execute on remote environment
CI:mean development process(code- push - build (by maven)-packaging) and testing(write testcases-code-push) process parallel done
then jenkins pull both build for development and automation script for testing and execute automation script
then if every thing well it certification build
note: pipelines configure by jenkins :- build -----> packaging ----> automation test ----> certification build

---install jenkins---
go to jenkins web site there are two option to download
1-by  jenkins.war : use for learning as it provide UI during execution automation script
2-by  installation file : this real life execution in dev oops environment as it automation script execute headless
 you not need install locally you just take access to enter to jenkins by dev oops team
-we use war file as this staging for learning by
1-download jenkins.war
2-open terminal at location of jenkins.war run command java -jar jenkins.war
3-capture password generate in terminal then minimize it not close to keep it run
4-open url "http://localhost:8080"
5-put password you capture from terminal
6-wait for install just finish it redirect to create credential for login in jenkins
7-when finish it redirect to url "http://localhost:8080/"
8-there is plugins required to install some in install by default ex:(git ,github) and other required install manual ex(maven integration)
9-install (maven integration plugin ) by go to mange jenkins ---> available plugins ----> search for(maven integration) ---> install
10-get path of (java - maven - git) to jenkins by direct to mange jenkins ---> tools ----> add paths
ex: java:/usr/java/jdk-12.0.2    maven:/home/"user"/apache-maven-3.9.9   git:/usr/bin/git
11-click on add New Item ----> Enter an item name -----> Select an item type:maven----->Source Code Management git and put repo url
----->Build Goals and options:test(like mvn test to run pom.xml)----> save (apply)
12-in project dashboard click run (jenkins pull project from github repo and execute pom.xml)


 */
public class Framework {
}
