# ITServiceDesk
Assignment 2

## Compiling the Application
In the root directory of the appication run the following command if using UNIX:
```
javac -d ./target ./src/main/java/*.java
```

Or the following if using Windows PowerShell:
```
javac -d .\target\. .\src\main\java\*.java 
```

## Running the Compiled Applicaiton from the CLI
In the root directory of the application run the following command if using UNIX:
```
java -cp ./target App
```

Or the following if using Windows PowerShell:
```
java -cp .\target\. App
```

# Notes
## App directory Structure
This project is using the Maven directory structure as this is used as the impromptu standard for all Java applications, even if they aren't using Maven. More information can be found [here](http://maven.apache.org/guides/introduction/introduction-to-the-standard-directory-layout.html)