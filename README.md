# My Personal Project

## *Stock Investment Tool*
This application will be a tool that allows the user to 
keep track of their current and potential/future stock 
investments. Users will be able to have important information 
about multiple stocks gathered in the same place. This application
is useful for anyone interested in investing in stocks. 

This project is of particular interest to me because I'm 
personally interested in investing in the stock market,
and would find it very helpful to have an application like this. 

## *User stories for this application:*
- As a user, I want to be able to add a stock to my list of stocks
- As a user, I want to be able to remove a stock from my list of stocks
- As a user, I want to be able to mark which stocks I have already invested in
- As a user, I want to be able to see which stocks I have invested in and which I haven't
- As a user, I want to be able to record the cost of 1 share in a particular stock
- As a user, I want to be able to save my stock list to file (if I so choose)
- As a user, I want to be able to load my stock list from file (if I so choose)

# Instructions for Grader
- You can generate the first required action, which is adding stocks to the stocklist, by filling out the fields at the bottom of the window and clicking the button labeled "Add a stock"
- You can generate the second required action, which is only viewing stocks you have invested in, by clicking the button labeled "View invested stocks"
- You can locate my visual component by looking at the icons that I have added to all the buttons, and to the fields at the bottom of the window
- You can save the state of my application by clicking the button labeled "Save Stocks To File"
- You can reload the state of my application by clicking the button labeled "Load Stocks From File"

## Phase 4: Task 2
Added stock Apple to list!
Viewed invested stocks
Added stock Samsung to list!

## Phase 4: Task 3
In order to improve the design of my program, I would refactor things in my GUI class. An important change I would make is reducing
duplication and repetition in my code, particularly in my viewInvestedStocks() and viewAllStocks() methods. These methods
both use a for each loop to iterate through a given list and display out information in the same format
about the stocks in the list. I would change this code by making a new method that takes in a list as a parameter, and then does the same
thing that the two view methods do (iterate through the list, and display stock information). I could then remove most of the code from the
viewInvestedStocks() method and viewAllStocks() method and instead just have them both pass a specific list to my new method.  

