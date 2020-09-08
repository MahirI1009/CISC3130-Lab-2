This readme file will explain my java code which produces a report containing the list of artists that appear on Spotify's Top 200 list for the week of August 27, 2020 in alphabetical order, which is what the description of Lab 2 asked for.
There are also two other files in the repository aside from the java file which contains the code, the java file is titled "Lab2.java". 
The other two files are titled "regional-us-weekly-latest.txt" and "Artists-WeekOf08272020.txt"
The file titled "regional-us-weekly-latest.txt" is the input file, it is a csv file that I downloaded from Spotify and saved as a txt file, it contains the data for the Top 200 list for the week of August 27, 2020 in the US.
The file titled "Artists-WeekOf08272020.txt" is the final output file, after all the code executes and it prints the processed data using a printwriter to print it to an output file. It is titled based on the recommended format in the Lab 2 description. 
The "Lab2.java" contains all the code to process the data in the csv file and produce the desired file. I will now proceed to briefly explain the steps and structure of the code.
First, in the main method, I set up a Scanner object to read the csv file and then a PrintWriter object which will eventually get the output printed on to an output file.
The first 2 lines of the data file are irrelevant since they are just headers, so I skip the first two lines using two strings.
I initially tried using 2D arrays, but I found a nested ArrayList would work better for me, this I created a nested ArrayList of strings and an ArrayList to hold the 200 names that appear on the list.
Then I use a while loop which reads in line by line using the scanner. Then a split method which seperates each line by commas and addes each value to an array of Strings.
Now, I just needed the third value in every line as it contains the artists' names and is the only relevant bit of data.
I initially tried to get the third element of the array that stores the data separated by commas in each line, but there was a problem, which was that some values had commas themselves. Since I knew it was the third column out of the five columns that had the names within it, it follows that the 3rd to last column would have the data I needed. Thus, I got the 3rd to last element of the array.
I then assigned every name to a String variable which then added it to my arraylist containing the top 200 names.
Once that was over I sorted the arraylist alphabetically using a nested for loop and then added the arraylist to the nested arraylist.
Then I created another arraylist which would store the amount of times a name appeared but in a string value.
I then used another nested for loop to check each with the next time and get rid of the duplicates and then added 1 to the element in the arraylist that contained the amount of times it had appeared so far.
After the duplicates were removed and all the appearances were counted, I added the arraylist containing the appearance counts of each artist to the nested arraylist.
Lastly, I used a for loop and the printwriter object to print the contents of the nested arraylist to an output file to produce the desired report.
