Questions
1. Are the id, manager_id, and salary fields always positive numbers without any special characters (e.g., currency symbols)?
2. Can we assume the following about the CSV data?
   a. Employee IDs are unique
   b. All required fields are present
   c. All manager_id values refer to valid employees within the same file
If not, what should be the expected behaviour in the following scenarios?
    • Missing fields
    • Duplicate employee IDs
    • manager_id values that do not match any employee in the csv

Assuming
1. Field Validation: The id, manager_id, and salary fields must contain only positive numeric values. If any of these fields contain characters or special symbols (e.g., currency symbols), the record will be skipped after logging an error.
2. Unique Employee IDs: Each employee ID must be unique. In the event of duplicate IDs, only the first occurrence will be processed; subsequent duplicates will be ignored with an appropriate error logged.
3. Required Fields: Every record in the CSV must contain all required fields (id, manager_id, fullName and salary). Records with missing fields will be logged as errors and skipped.
4. CEO Uniqueness: The company is assumed to have exactly one CEO (i.e., an employee with no manager). If multiple CEO entries are found (i.e., multiple employees with manager_id as null or empty), only the first will be considered valid. All other conflicting records will be treated as invalid and excluded from processing.
5. Manager-Employee Relationship: It is assumed that every manager_id in the csv corresponds to a valid employee id
