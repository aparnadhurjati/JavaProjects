# ExcelUtility

A Java utility application that converts Excel files to JSON format using Apache POI library.

## Overview

ExcelUtility is a Maven-based Java project that provides functionality to read Excel files (.xlsx format) and convert them to JSON format. The application processes Excel sheets row by row, extracting headers from the first row and converting subsequent data rows into JSON objects.

## Features

- **Excel to JSON Conversion**: Converts Excel files to JSON format
- **Multi-sheet Support**: Processes all sheets in an Excel workbook
- **Header Mapping**: Uses the first row as headers for JSON object keys
- **Numeric Data Handling**: Supports numeric cell values
- **Logging**: Integrated logging with Log4j2
- **Maven Build**: Easy dependency management and build process

## Prerequisites

- Java 8 or higher
- Maven 3.6 or higher

## Dependencies

The project uses the following key dependencies:

- **Apache POI 5.2.5**: For Excel file processing
- **Gson 2.10.1**: For JSON serialization
- **Log4j2 2.17.2**: For logging functionality
- **Lombok 1.18.8**: For reducing boilerplate code

## Project Structure

```
ExcelUtility/
├── pom.xml                          # Maven configuration
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/ad/java/practice/
│   │   │       └── App.java         # Main application class
│   │   └── resources/
│   │       ├── ExampleSheet.xlsx    # Sample Excel file
│   │       └── log4j2.xml          # Logging configuration
│   └── test/
│       ├── java/                    # Test source directory
│       └── resources/               # Test resources
└── target/                          # Compiled output
```

## Installation

1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd ExcelUtility
   ```

2. Build the project:
   ```bash
   mvn clean compile
   ```

## Usage

### Running the Application

1. **Compile and Run**:
   ```bash
   mvn clean compile exec:java -Dexec.mainClass="com.ad.java.practice.App"
   ```

2. **Or run the JAR file** (after building):
   ```bash
   mvn clean package
   java -jar target/ExcelUtility-1.0-SNAPSHOT.jar
   ```

### How It Works

The application performs the following steps:

1. **Loads Excel File**: Reads the `ExampleSheet.xlsx` file from the resources directory
2. **Processes Each Sheet**: Iterates through all sheets in the workbook
3. **Extracts Headers**: Uses the first row of each sheet as column headers
4. **Converts Data**: Converts each subsequent row into a JSON object using the headers as keys
5. **Outputs JSON**: Prints the resulting JSON array to the console

### Example Output

For an Excel file with the following structure:
```
| Name    | Age | Salary |
|---------|-----|--------|
| John    | 25  | 50000  |
| Jane    | 30  | 60000  |
```

The application will output:
```json
[
  {"Name": "John", "Age": 25.0, "Salary": 50000.0},
  {"Name": "Jane", "Age": 30.0, "Salary": 60000.0}
]
```

## Configuration

### Logging

The application uses Log4j2 for logging. The configuration is defined in `src/main/resources/log4j2.xml`:

- **Log Level**: INFO
- **Output**: Console
- **Pattern**: `yyyy-MM-dd HH:mm:ss LEVEL CLASS - MESSAGE`

### Excel File

Place your Excel files in the `src/main/resources/` directory and update the file path in the `loadExcelToJSON()` method call in `App.java`.

## Development

### Adding New Features

1. **Support for Different Data Types**: Currently, the application only handles numeric values. You can extend it to support:
   - String values
   - Date values
   - Boolean values

2. **File Output**: Instead of printing to console, you could:
   - Save JSON to a file
   - Send to a web service
   - Store in a database

3. **Error Handling**: Add more robust error handling for:
   - Missing files
   - Invalid Excel formats
   - Empty sheets

### Testing

The project includes a test directory structure. You can add unit tests using JUnit or TestNG:

```bash
mvn test
```

## Troubleshooting

### Common Issues

1. **File Not Found**: Ensure the Excel file is in the `src/main/resources/` directory
2. **Build Errors**: Make sure all dependencies are properly downloaded:
   ```bash
   mvn dependency:resolve
   ```
3. **Memory Issues**: For large Excel files, consider increasing JVM memory:
   ```bash
   java -Xmx2g -jar target/ExcelUtility-1.0-SNAPSHOT.jar
   ```

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests if applicable
5. Submit a pull request

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Version History

- **1.0-SNAPSHOT**: Initial release with basic Excel to JSON conversion functionality

## Support

For issues and questions, please create an issue in the project repository. 