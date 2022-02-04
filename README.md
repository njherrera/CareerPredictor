# CareerPredictor
Predicting an NBA prospect's future value by comparing their performance in each season, growth across seaons, and physical measurements to historical players.

For this project, I designed and implemented a custom MySQL database in MySQL Workbench using Omri Goldstein's CSV files (https://www.kaggle.com/drgilermo/nba-players-stats), which track every NBA player and season played from 1950-2017. I also plan on adding support for seasons since 2017 in the future. While the CSV files contain data on every player since 1950, for my project I only include players and seasons since 1980 (when the three-point line was introduced). For this project I also implemented a web scraper, which (given the name of a prospect) takes that player's physicals and statistics from their Basketball Reference page.

Players are compared in the following areas: performance in each season (calculated by comparing performance in each statistical category for each season), growth/regression across seasons (calculated by comparing changes in each statistical category across seasons), and physical measurements (height and weight). The scores for each of the three categories are then compiled into an aggregate similarity score, with each category weighted by 33%.

Project Roadmap:

Add WAR/some other statistic that reflects overall value of player to increase accuracy of comparisons 

Write code for projecting future WAR and trajectory of prospect
