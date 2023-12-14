**HRM Tool - Spring Boot**

### Overview

Welcome to the HRM Tool, a Spring Boot project designed to streamline Human Resource Management processes. This tool covers various aspects, including leave management, file handling, and more.

### Getting Started

1. **Clone the Repository:**
   ```bash
   git clone https://github.com/moxhadeel571/HRMTOOL_SPRINGBOOT.git
   cd HRMTOOL_SPRINGBOOT
   ```

2. **Database Configuration:**
   - Configure your MySQL database settings in `src/main/resources/application.properties`.
   - Create a database named `hrm_tool`.

3. **Run the Application:**
   ```bash
   ./gradlew bootRun
   ```

   The application will start at `http://localhost:8080`.

### Import sql into MySQL
If your exported dataset is in a ZIP file containing SQL files and you want to import it into your MySQL database, follow these steps:

### Prerequisites

1. **MySQL Database:**
   - Ensure you have a MySQL database set up.
   - Make note of the database name.

### Steps to Import SQL Dataset from ZIP File

1. **Database Configuration:**
   - Verify the MySQL database connection settings in `src/main/resources/application.properties`.
   - Ensure your database exists; you noted the name earlier.

2. **Unzip the Dataset:**
   - Extract the contents of your ZIP file to a directory on your local machine.

3. **Run SQL Scripts:**
   - Open your MySQL command-line interface or any MySQL database management tool.

4. **Navigate to SQL Files:**
   - Navigate to the directory where your extracted SQL dataset files are located.

5. **Import SQL Scripts:**
   - Run the following command for each SQL script in the directory:

     ```sql
     source your_dataset_file.sql;
     ```

----------------------------------------------------------------------------------
**Video Tutorial**


https://github.com/moxhadeel571/Food_ordering/assets/60618158/531e586c-6349-4132-ac90-30aa751a7d6a


----------------------------------------------------------------------------------
   Replace `your_dataset_file.sql` with the actual name of your SQL script.

6. **Running the Application:**
   - Start the Spring Boot application using the following command:

     ```bash
     ./gradlew bootRun
     ```

   The application will start at `http://localhost:8080`.

7. **Explore the HRM Tool:**
   - Visit the [HRM Tool](https://github.com/moxhadeel571/HRMTOOL_SPRINGBOOT) for features such as leave management, file handling, and more.

Now, your MySQL database should be populated with the imported dataset from the ZIP file. Feel free to explore the HRM Tool using the imported data.

For detailed instructions on importing data, refer to the MySQL documentation or visit [MySQL LOAD DATA](https://dev.mysql.com/doc/refman/8.0/en/load-data.html).
