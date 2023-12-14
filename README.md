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

### Import CSV into MySQL

To import CSV data into the MySQL database:

1. Create a table in MySQL with the structure matching your CSV file.
   ```sql
   CREATE TABLE your_table_name (
       column1 datatype,
       column2 datatype,
       ...
   );
   ```

2. Use the MySQL `LOAD DATA` statement to import CSV data.
   ```sql
   LOAD DATA LOCAL INFILE 'path/to/your/file.csv'
   INTO TABLE your_table_name
   FIELDS TERMINATED BY ','
   ENCLOSED BY '"'
   LINES TERMINATED BY '\n'
   IGNORE 1 ROWS;
   ```

Replace placeholders (`your_table_name`, `path/to/your/file.csv`, etc.) with your actual table name and file path.

Now, your CSV data should be successfully imported into the MySQL database.

### Explore the HRM Tool

The HRM Tool offers features like leave management, file handling, and more. Visit the [GitHub Repository](https://github.com/moxhadeel571/HRMTOOL_SPRINGBOOT) for detailed information.

Feel free to explore and enhance the HRM Tool according to your HR management needs!
