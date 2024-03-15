import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class BabyBirths {
    // find the totalBirths in a file
    public void totalBirths(FileResource fr) {

        // initialize the counts
        int totalMales = 0;
        int totalFemales = 0;
        int totalMaleNames = 0;
        int totalFemaleNames = 0;

        // loop over each record in the parser
        CSVParser parser = fr.getCSVParser(false);
        for (CSVRecord record : parser) {

            // find gender and number born
            String gender = record.get(1);
            int numberBorn = Integer.parseInt(record.get(2));

            // if gender is male or female add numberBorn to totalMales/Females, but only
            // add 1 to name count b/c for everyname there are so many births
            if (gender.equals("M")) {
                totalMales += numberBorn;
                totalMaleNames += 1;
            } else {
                totalFemales += numberBorn;
                totalFemaleNames += 1;
            }
        }

        // tally totalbirths by adding the totals for each gender and display results
        int totalBirths = totalMales + totalFemales;
        System.out.println("Total males: " + totalMales);
        System.out.println("Total male names: " + totalMaleNames);
        System.out.println("Total females: " + totalFemales);
        System.out.println("Total female names: " + totalFemaleNames);
        System.out.println("Total births: " + totalBirths);
    }

    public void testTotalBirths() {
        FileResource fr = new FileResource("data/yob1900.csv");
        totalBirths(fr);
        FileResource fri = new FileResource("data/yob1905.csv");
        totalBirths(fri);
    }

    // given a year,name,and gender find the names rank in terms of males/females
    // born
    public int getRank(int year, String name, String gender) {
        // open FileResource using getFileName function to select files
        // CSV parserr is false because there are no rows/columns
        FileResource fr = new FileResource(getFileName(year));
        CSVParser parser = fr.getCSVParser(false);

        // file has ordered all names based on gender and rank
        // initially rank is 0
        int rank = 0;

        for (CSVRecord record : parser) {
            // find the gender
            String currentGender = record.get(1);

            // if the current gender is valid add 1 to the rank because we are on the first
            // rank
            if (currentGender.equals(gender)) {
                rank += 1;
                // see if the name corresponds to input
                String currentName = record.get(0);
                // if so return the current rank
                if (currentName.equals(name)) {
                    return rank;
                }
            }

        }
        // if name is not there return -1
        return -1;
    }

    public String getFileName(int year) {
        // return "testing/yob"+year+"short.csv";
        return "data/yob" + year + ".csv";
    }

    public void testGetRank() {
        int answer = getRank(2012, "Sophia", "F");
        if (answer != 1) {
            System.out.print("Test case failed for 2012,Sophia,F");
        }
        answer = getRank(2012, "Jacob", "F");
        if (answer != -1) {
            System.out.print("Test case failed for 2012,Jacob,F");
        }
        answer = getRank(2012, "Isabella", "F");
        if (answer != 3) {
            System.out.print("Test case failed for 2012,Isabella,F");
        }
        answer = getRank(2012, "Ava", "F");
        if (answer != 5) {
            System.out.print("Test case failed for 2012,Ava,F");
        }
        answer = getRank(2012, "Jacob", "M");
        if (answer != 1) {
            System.out.print("Test case failed for 2012,Jacob,M");
        }
        answer = getRank(2012, "Noah", "M");
        if (answer != 4) {
            System.out.print("Test case failed for 2012,Noah,M");
        }
        answer = getRank(2012, "Mason", "M");
        if (answer != 2) {
            System.out.print("Test case failed for 2012,Mason,M");
        }
        answer = getRank(2012, "Emma", "M");
        if (answer != -1) {
            System.out.print("Test case failed for 2012,Emma,M");
        }
        answer = getRank(2012, "Rohan", "M");
        if (answer != -1) {
            System.out.print("Test case failed for 2012,Rohan,M");
        }
        System.out.println("Testing done");
    }

    // given a year,rank,and gender find the name
    public String getName(int year, int rank, String gender) {
        FileResource fr = new FileResource(getFileName(year));
        CSVParser parser = fr.getCSVParser(false);

        // set a count for currentRank to see the each records rank while looping over
        // the data
        int currentRank = 0;

        // loop over the data
        for (CSVRecord record : parser) {

            // find the gender of the record and see if its matches with input
            String currentGender = record.get(1);

            // if currentGender equals input gender; otherwise skip
            if (currentGender.equals(gender)) {
                // add 1 to the rank since we start from the name with the most births
                currentRank += 1;

                // find the name
                String currentName = record.get(0);

                // if the current rank matches with the input rank then return that name
                if (currentRank == rank) {
                    return currentName;
                }
            }
        }

        // if that name doesnt exist then return no name, this usually happens when you
        // put a guys name for a girls data or vice versa
        return "NO NAME";
    }

    public void testGetName() {
        // 2013,1,f--> Sophia
        // 2013,0,f --> NO NAME
        // 2013,5,f-- Ava
        // 2013,4,f --> Isabella
        // 2013,25,f --> NO NAME
        // 2012,2,m --> Mason
        // 2012,1,m --> Jacob
        // 2012,0,m--> NO NAME
        // 2012,4,m --> Noah
        // 2012,6,m --> NO NAME
        // 2012,5,m --> William
        int[] years = {
                2013, 2013, 2013, 2013, 2013, 2012, 2012, 2012, 2012, 2012, 2012
        };
        int[] ranks = {
                1, 0, 5, 4, 25, 2, 1, 0, 4, 6, 5
        };

        String[] genders = { "F", "F", "F", "F", "F", "M", "M", "M", "M", "M", "M" };
        String[] expectedOutputs = { "Sophia", "NO NAME", "Ava", "Isabella", "NO NAME", "Mason", "Jacob", "NO NAME",
                "Noah", "NO NAME", "William" };
        int totalTestCases = years.length;
        int failedCases = 0;
        for (int i = 0; i < totalTestCases; i++) {
            int year = years[i];
            int rank = ranks[i];
            String gender = genders[i];
            String expectedOutput = expectedOutputs[i];
            String actualOutput = getName(year, rank, gender);
            if (!expectedOutput.equals(actualOutput)) {
                System.out.println("Failed Test Case: ");
                System.out.println("Year: " + year);
                System.out.println("Rank: " + rank);
                System.out.println("Gender: " + gender);
                System.out.println("Expected Output: " + expectedOutput);
                System.out.println("Actual Output: " + actualOutput);
                failedCases += 1;
                System.out.println();
            }
        }
        System.out.println("Testing of getName Complete!");
        System.out.println("Success rate: " + (totalTestCases - failedCases) + "/" + totalTestCases);
    }

    // given a name in one year return newName in newYear based on original name's
    // ranking in original year
    public void whatIsNameInYear(String name, int year, int newYear, String gender) {
        // set pronoun to null and set it to he/she based on gender
        String pronoun = null;
        if (gender.equals("F")) {
            pronoun = "she";
        } else {
            pronoun = "he";
        }

        // call getRank function for original name and store that rank
        int rank = getRank(year, name, gender);

        // use that rank to find the newName in the same gender
        String newName = getName(newYear, rank, gender);

        // print out the output
        System.out.println(
                name + " born in " + year + " would be " + newName + " if " + pronoun + " was born in " + newYear);
    }

    public void testWhatIsNameInYear() {
        whatIsNameInYear("Isabella", 2012, 2014, "F");
        whatIsNameInYear("Jacob", 2012, 2013, "M");
        whatIsNameInYear("Liam", 2014, 2012, "M");
    }

    // given a name and gender find the year with the highes rank
    public int yearOfHighestRank(String name, String gender) {
        // use DR to hold multiple files
        DirectoryResource dr = new DirectoryResource();

        // highestYear = -1 b/c it is easier to return the year if the name isnt there
        int highestYear = -1;

        // store the currentHighesRank has negative infinity and always compare this in
        // every instance
        int currentHighestRank = Integer.MAX_VALUE;

        // loop over each file
        for (File f : dr.selectedFiles()) {
            // get the file Name
            String fileName = f.getName();

            // find the year because it is needed for the getRank function
            int year = Integer.parseInt(fileName.substring(3, 7));

            // call the getRank function and store it in currentRank
            int currentRank = getRank(year, name, gender);

            // if name isnt there getRank will return -1, function should go the next file
            if (currentRank == -1) {
                continue;
            }
            // if name is there see if currentRank is hihher than currentHighest and update
            // the year and rank
            if (currentRank < currentHighestRank) {
                currentHighestRank = currentRank;
                highestYear = year;
            }
        }

        // when everything is done return the highestYear, if function returns -1 that
        // means the name isnt there
        return highestYear;
    }

    public void testYearOfHighestRank() {
        int answer = yearOfHighestRank("Mason", "M");
        System.out.println(answer);
    }

    // Find the averageRank for a name and gender in a series of files
    public double getAverageRank(String name, String gender) {
        // hold multiple giles
        DirectoryResource dr = new DirectoryResource();
        // to get the average we need total of all the rankings over the instances
        double totalRankings = 0;
        double totalInstances = 0;

        // loop over the files
        for (File f : dr.selectedFiles()) {
            // find the fileName and year
            String fileName = f.getName();
            int year = Integer.parseInt(fileName.substring(3, 7));

            // apply it to the getRank function
            double currentRank = getRank(year, name, gender);

            // if the names doesnt exist, then we move to the next file
            if (currentRank == -1) {
                continue;
            }
            // but if the name exists add the rank to the totalRankings and 1 to
            // totalInstances
            totalInstances += 1;
            totalRankings += currentRank;
        }

        // if at the end of the loop the ranking is 0 that means the name doesnt exist
        // in any files so we return -1
        if (totalRankings == 0) {
            return -1.0;
        }

        // but if the ranking is greater than 0 we find the average and return it
        double totalAverage = totalRankings / totalInstances;
        return totalAverage;
    }

    // find the names who have births higher than current gender and name
    public int getTotalBirthsRankedHigher(int year, String name, String gender) {
        // totalBirths for the other ames is set to 0
        int totalBirths = 0;

        // use fileResource and parser to hold the data
        FileResource fr = new FileResource(getFileName(year));
        CSVParser parser = fr.getCSVParser(false);

        // for every record in the parser
        for (CSVRecord record : parser) {
            // if the currentGender is not aligned with the input then move on
            String currentGender = record.get(1);
            if (!currentGender.equals(gender)) {
                continue;
            }
            // find the currentName if the currentName is found then return the totalBirths
            // of all the other names combined
            String currentName = record.get(0);
            if (currentName.equals(name)) {
                return totalBirths;
            }
            // if the names dont match then find the births and add it to totalBirths
            int births = Integer.parseInt(record.get(2));
            totalBirths += births;
        }
        // you will return negative one if you have assigned the wrong gender for the
        // wrong name --> Michael, Female
        return -1;
    }

    public void testgetTotalBirthsRankedHigher() {
        // 2013,Sophia,F--> 0
        // 2013,Isabella,F --> 26
        // 2013,Ava,F-- 33
        // 2013,Rohan,F --> -1
        // 2013,Mason,M --> 29
        // 2012,William,M --> 28
        int[] years = {
                2013, 2013, 2013, 2013, 2013, 2012
        };
        String[] names = {
                "Sophia", "Isabella", "Ava", "Rohan", "Mason", "William"
        };
        String[] genders = { "F", "F", "F", "F", "M", "M" };
        int[] expectedOutputs = { 0, 26, 33, -1, 29, 28 };
        int totalTestCases = years.length;
        int failedCases = 0;
        for (int i = 0; i < totalTestCases; i++) {
            int year = years[i];
            String name = names[i];
            String gender = genders[i];
            int expectedOutput = expectedOutputs[i];
            int actualOutput = getTotalBirthsRankedHigher(year, name, gender);
            if (expectedOutput != actualOutput) {
                System.out.println("Failed Test Case: ");
                System.out.println("Year: " + year);
                System.out.println("Name: " + name);
                System.out.println("Gender: " + gender);
                System.out.println("Expected Output: " + expectedOutput);
                System.out.println("Actual Output: " + actualOutput);
                failedCases += 1;
                System.out.println();
            }
        }
        System.out.println("Testing of getTotalBirthsRankedHigher Complete!");
        System.out.println("Success rate: " + (totalTestCases - failedCases) + "/" + totalTestCases);
    }

    // What about finding a list of all the names that were used for fewer than 10
    // children in a particular year?
    public int getMedianRank(String name, String gender) {
        DirectoryResource dr = new DirectoryResource();
        int totalRanking = 0;
        int totalEntries = 0;
        String currentName = name;
        String currentGender = gender;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource();
            CSVParser parser = fr.getCSVParser(false);
            String fileName = f.getName();
            int year = Integer.parseInt(fileName.substring(3, 7));
            for (CSVRecord record : parser) {
                int rank = getRank(year, currentName, currentGender);
                totalRanking += rank;
                totalEntries += 1;
            }
        }
        int average = totalRanking / totalEntries;
        return average;
    }
}
