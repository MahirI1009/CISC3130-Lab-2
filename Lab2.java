import java.io.*;
import java.util.*;
public class Lab2 {
	/*-The purpose of this lab is to produce a report of which artists are top streamed music artists during certain weeks.
	  -I have decided to choose the Top (200) Artists of the week of August 27, 2020 in the Unites States.
	  -The report is to produce which artists are the top artists for that week and and how many times each artist appears
	   on the list.
	  -So the plan is to read in the data from the CSV I downloaded from Spotify and feed them into (as the assignment reco-
	   mmended, a 2D Array) and then the output will be the contents of the 2D Array.
	  -The output will probably look like a list of rows with 2 columns each, the first column being the Artist's name and 
	   the second column would be the amount of times said artist appeared on the Top 200 list. (This means an artist will
	   appear multiple times in the data file and I'll need to work a way around that). 
	  -Extra Challenge: 1) Alphabetize the list 2) Return list of Artists produced (whatever that means)*/
	
		//so I tried many times with a 2D array, but it was too hard and too messy to get the task done with an 2D array
		//I opted for a nested arraylist instead, much cleaner and easier to work with
	
	public static void main(String[] args) throws Exception {
		
		String csv = "regional-us-weekly-latest.txt";
		//String to hold file name
		Scanner sc = new Scanner(new File(csv));
		//Scanner object to read the csv file
		PrintWriter outputFile = new PrintWriter("Artists-WeekOf08272020.txt");
		//printwriter object to print the report to an output file		

		String header = sc.nextLine(); //gets header
		String artistName = ""; //string for holding artist name
		ArrayList<String> top200 = new ArrayList<>(); 
		//arraylist for all the top 200 names, in the order they're in from the csv file
		ArrayList<ArrayList<String>> nameList = new ArrayList<ArrayList<String>>(); 
		//nested arraylist, one for names in report and another for the amount of times each appears
		
		String line = sc.nextLine(); //gets the first line after header before the while loop starts since it is irrelevant data
		/*this while loop gets all the names from the csv file and ignores the rest of the data as it is unneeded for the report
		it then adds all the names to the top200 arraylist in the order that they were in in the csv file (including duplicates)*/
        while(sc.hasNext()) { //loop continues to the end of the file
        	line = sc.nextLine(); //reads in an entire line
            String [] name = line.split(","); //splits lines by commas as the data values are separated by commas
            artistName = name[name.length-3];
            /*originally I wanted to assign values[2] to artist because the names were the third value of the data (the element is
            2 due to arrays starting at 0), but there was a problem, some values had commas within them. So, I improvised, no matter
            what the column with the names will always be the 3rd to last column, so instead I made the array element be the length 
            of the array minus 3 to ensure I always get the artist's name.*/
            top200.add(artistName); //adds artist name to the top200 arraylist
        } //end of while loop
        
        
        /*this loop sorts the top200 names in alphabetical order, the first character of an element is compared to the first character 
        of the next element, then it swaps the two names if the name after it starts with a letter that comes before it in the alphabet*/
        for (int i = 1; i < top200.size(); i++) {
        	String temp = "";
        	for (int j = 0; j < top200.size()-1; j++) {
        		if(top200.get(i).charAt(0) < top200.get(j).charAt(0)) { //compares first letter of strings
        			temp = top200.get(i); 
        			top200.set(i, top200.get(j));
        			top200.set(j, temp);
        		}
        	} //end of second for loop
        } //end of for loop
        
        nameList.add(top200); //this adds the list of names to the nested arraylist 
        
        ArrayList<String> appearanceCount = new ArrayList<>(); 
        //arraylist to be parallel to the names and will say how many times the name appeared on the top 200 list
          
        /*the job of this for loop is to go through the first loop in the nested arraylist, which is currently the only one, and it
        looks through it for duplicates, by comparing every single element with every element after it, when it finds a duplicate 
        it deletes it, then it adds 1 to the corresponding element value on the appearanceCount arraylist, otherwise it will add "1" to 
        the appearanceCount arraylist as it didnt have a duplicate for the corresponding element value, thus it will only appear once*/
        for (int i = 0; i < nameList.get(0).size(); i++) { // this loop goes through the arraylist containing the list of 200 names 1 by 1
        	int cnt = 1; //this is the default count, if a name appears once on the list, its corresponding array will hold the string "1"
        	String stringNum = ""; //this String is there to hold the count of appearances, it will hold the int when converted to a string
        	for (int j = i+1; j < nameList.get(0).size(); j++) { //this loop will compare the current element with the rest of the array
        		if(nameList.get(0).get(i).equals(nameList.get(0).get(j))) { //this condition checks for duplicates
        			nameList.get(0).remove(j); //when a duplicate is found it will be removed
        			j--; //j is decreased by 1, since an element was removed the arraylist is shorter now, so the loop wont throw it out of bounds
        			cnt++; //when a duplicate is found, cnt is incremented 
        			stringNum = Integer.toString(cnt); //the int value of cnt is converted to a string
                    appearanceCount.set(i, stringNum); //this places the amount of times the artist appears in the parallel appearanceCount arraylist
        		}//end of if
        		else appearanceCount.add("1"); //if there is no duplicate, that means it appears only once, so "1" is added to appearanceCount
        	}//end of second for loop
        } //end of for loop
        
        /*when the loop ends, appearanceCount should be full and have all the amount of times an artist appeared on the list and its elements
        will correspond to the values of the arraylist containing all the names*/
        nameList.add(appearanceCount); //then the appearance count arraylist is added to the nested nameList arrayList
        
        //prints report for header of the report to the output file
        outputFile.println("This is a report of the list of artists that appear on Spotify's");
        outputFile.println("Top 200 list in the United States for the week of August 27, 2020.");
        outputFile.println("The following names in the report are in alphabetical order.\n");
        
        int cnt = 0; //this is to count how many names are on the list altogether
        //this for loop is to print out the report to the output file
        for (int i = 0; i < nameList.get(0).size(); i++) {
        	int a = Integer.parseInt(nameList.get(1).get(i)); //this converts the appearance count of every name into an int 
        	if (a > 1) //this if will check if the appearance count is greater than 1
        		outputFile.println(nameList.get(0).get(i) + " appears " + nameList.get(1).get(i) + " times on the list.");
        		//if it is greater than 1, than to be grammatically correct it will print "appears i times", (times being plural)
        	else outputFile.println(nameList.get(0).get(i) + " appears " + nameList.get(1).get(i) + " time on the list.");
        		//otherwise, if its not bigger than 1, then it will say "appears 1 time", (time being singular)
        	cnt++; //increments the count of names
        } //end of for loop
        
        outputFile.println("\nThere are " + cnt + " artists on the list.");
        
        sc.close();
        outputFile.close();
        
	} //end of main method
} //end of class