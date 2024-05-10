# Webshop Customer and Payment Management
This simple Java application is designed to manage customers and their payments for two online webshops. It provides functionality to read customer and payment data from CSV files, make sure about no invalid data, generate reports in CSV files.

## Features
- ReadCSV: Reads customer and payment data from CSV files and stores them in memory, and validates them at the same time.
- Error Handling: Detects and logs erroneous rows from the input files to application.log (no number in names, no same ClientID in the same webshop, proper form of card and transfer numbers, CSV rows with missing data do not throw error).
- Customer Spending Report: Generates a report summarizing the total spending of each customer and saves it into a CSV file.
- Top Spenders Report: Identifies the top two spenders and saves their details in a CSV file.
- Webshop Revenue Report: Produces a report summarizing the revenue for each webshop, categorized by payment method and saves their details in a CSV file.

## File Structure
### data:
- customer.csv: Contains fields for webshop ID, customer ID, customer name, and address.
- payments.csv: Contains fields for webshop ID, customer ID, payment method, amount, bank account number (for transfers), card number (for card payments), and payment date.
- application.log: Logs error messages for invalid rows encountered during data processing.
- top.csv: Saves the details of the top two spenders.
- report01.csv: Stores the customer spending report.
- report02.csv: Holds the webshop revenue report.

### com.otp.webshop:
- .data: Contains the stored data that was read from the CSV files in 2 lists, and many methods that help reaching the necessary entities. Follows the Singleton pattern.
- .entity: Contains the entities.
- .exception: Contains one NotFoundException.
- .file: Contains the file manipulating packages: writing, reading, logging.
- .formatter: Contains a currency formatter to make a nice currency string from an integer.
- .myapplication: Contains the main method.

## How to Use
1. Clone the Repository: Clone this repository to your local machine.
2. Compile the Code: Compile the Java files using your preferred Java compiler.
3. Prepare Input Files: Ensure you have valid customer.csv and payments.csv files in the project directory.
4. Run the Application: Execute the compiled Java application.
5. View Results: Check the generated CSV files (report01.csv, report02.csv top.csv) and LOG file (application.log) for the respective reports.
