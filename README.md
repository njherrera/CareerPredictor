# CareerPredictor
Predicting an NBA prospect's future value by comparing their performance in each season, growth across seaons, and physical measurements to historical players.

For this project, I designed and implemented a custom MySQL database in MySQL Workbench using Omri Goldstein's CSV files (https://www.kaggle.com/drgilermo/nba-players-stats), which track every NBA player and season played from 1950-2017. I also plan on adding support for seasons since 2017 in the future. While the CSV files contain data on every player since 1950, for my project I only include players and seasons since 1980 (when the three-point line was introduced).

Player's are compared in the following areas: performance in each season (calculated by comparing performance in each statistical category for each season), growth/regression across seasons (calculated by comparing changes in each statistical category across seasons), and physical measurements (height and weight). The scores for each of the three categories will then be compiled into an aggregate similarity score, with each category weighted by 33%.

Once I have written the code for comparing two players, I will then move on to using these comparisons to project the career outcomes of young NBA players, taking inspiration from 538's NBA Player Projections.
