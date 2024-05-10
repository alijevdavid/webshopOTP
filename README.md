# Webshop Customer and Payment Management
This Java application is designed to manage customers and their payments for two online webshops. It provides functionality to read customer and payment data from CSV files, perform data validation, generate reports, and store results in CSV files.

## Features
- Data Import: Reads customer and payment data from CSV files and stores them in memory.
- Error Handling: Detects and logs erroneous rows from the input files to application.log, ensuring they are not processed further.
- Customer Spending Report: Generates a report summarizing the total spending of each customer.
- Top Spenders Report: Identifies the top two spenders and saves their details in a CSV file.
- Webshop Revenue Report: Produces a report summarizing the revenue for each webshop, categorized by payment method.

## File Structure
- customer.csv: Contains fields for webshop ID, customer ID, customer name, and address.
- payments.csv: Contains fields for webshop ID, customer ID, payment method, amount, bank account number (for transfers), card number (for card payments), and payment date.
- application.log: Logs error messages for invalid rows encountered during data processing.
- top.csv: Saves the details of the top two spenders.
- report01.csv: Stores the customer spending report.
- report02.csv: Holds the webshop revenue report.

## How to Use
1. Clone the Repository: Clone this repository to your local machine.
2. Compile the Code: Compile the Java files using your preferred Java compiler.
3. Prepare Input Files: Ensure you have valid customer.csv and payments.csv files in the project directory.
4. Run the Application: Execute the compiled Java application.
5. View Results: Check the generated CSV files (report01.csv, report02.csv top.csv) and LOG file (application.log) for the respective reports.
