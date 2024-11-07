# ASQA - Assignment 02
## TecRoot LK Test Automation
---
## Project Setup Instructions

Follow these steps to set up and run the project.

### Prerequisites
Ensure that you have the following installed:
- [Java Development Kit (JDK) 11 or higher](https://www.oracle.com/java/technologies/javase-downloads.html)
- [Maven](https://maven.apache.org/install.html)
- [Git](https://git-scm.com/)

### Project Setup

1. **Clone the repository**  
   Run the following command to clone the project repository:
   ```bash
   git clone https://github.com/DinithKumudika/SQA-POM.git
   
2. **Install dependencies**
   Navigate to the project directory and use Maven to install dependencies specified in the `pom.xml` file:
   ```bash
   mvn clean install

3. **Configure project settings**
   Open the config.properties file and set the necessary configurations, such as the base URL of the System Under Test (SUT).
   Example:
   ```bash
   baseUrl=https://example.com

4. **Run the test suite**
   Execute the test suite by running testng.xml
