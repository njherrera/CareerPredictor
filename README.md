# CareerPredictor
Predicting an NBA prospect's future value by comparing their performance in each season, growth across seaons, and physical measurements to historical players.

Initially for this project, I designed and implemented a custom MySQL database in MySQL Workbench using Omri Goldstein's CSV files (https://www.kaggle.com/drgilermo/nba-players-stats), which track every NBA player and season played from 1950-2017. Because the data set did not include recent seasons (the seasons of prospects the program is designed to project), I scraped statistics for prospects from Basketball Reference. However, shortly after the last commit in October, Basketball Reference started sending me 403 errors whenever I tried to access it through the code. As a result, I am currently re-designing my SQL database from the ground up with data sets that cover up until the present day. After the new database has been implemented, I will remove BasketballReferenceScraper and also re-work the existing JDBC/SQL code. Ultimately this will be a good thing, as I'll have all my data in one place to make queries to and can update the database with each season.

Players are compared in the following areas: performance in each season (calculated by comparing performance in each statistical category for each season), growth/regression across seasons (calculated by comparing changes in each statistical category across seasons), and physical measurements (height and weight). The scores for each of the three categories are then compiled into an aggregate similarity score, with each category weighted by 33%.

Project Roadmap:

1. Re-work the SQL database

2. Finish work on the comparison algorithms

3. Start work on phase 3 of the project, actually projecting the careers of prospects using their most similar historical players
