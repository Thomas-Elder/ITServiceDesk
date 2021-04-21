Welcome to Cinco IT Service Desk!

Cinco IT Service Desk is an application that will help you organise tickets. This document will guide you through the process of compiling and running the application.

First make sure the location you've saved the ITServiceDesk folder is local on the machine.

1 - Click on the start menu and type 'openJDK CMD' and click to run. 
2 - You'll then need to navigate to the location you saved the ITServiceDesk project. If you're not sure how to do this in the cmdline, the easiest way might be to open a File Explorer and navigate to the extracted folders. Shift right click on the ITServiceDesk folder and select 'copy as path'.
    Return to the openJDK CMD window, and type 'cd ', paste the path you copied and press enter. 
3 - At this point you need to compile the project using the following command:
    javac -source 1.8 -target 1.8 -d .\target\. .\src\main\java\*.java
4 - Next step is starting the application with the following command:
    java -cp .\target\. App
5 - Then follow the prompts in the application to create accounts, log in, and manage tickets.

Note that the Technician's Accounts have been hard coded into the system, their log in details are as follows:
Harry Style: email- harry.style@cinco.com password- 1234
Niall Horan: email- niall.horan@cinco.com password- 5678
Liam Payne: email- liam.payne@cinco.com password- 9123
Louis Tomlinson: email- louis.tomlinson@cinco.com password- 4567
Zayn Malik: email- zayn.malik@cinco.com password- 8912

Enjoy.