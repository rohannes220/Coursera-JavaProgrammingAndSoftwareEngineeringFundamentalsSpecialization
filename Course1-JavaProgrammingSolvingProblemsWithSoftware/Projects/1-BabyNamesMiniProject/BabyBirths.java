import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class BabyBirths {
    public void totalBirths(FileResource fr) {
        int totalMales = 0;
        int totalFemales = 0;
        int totalMaleNames = 0;
        int totalFemaleNames = 0;
        CSVParser parser = fr.getCSVParser(false);
        for (CSVRecord record : parser) {
            String gender = record.get(1);
            int numberBorn = Integer.parseInt(record.get(2));
            if (gender.equals("M")) {
                totalMales += numberBorn;
                totalMaleNames += 1;
            } else {
                totalFemales += numberBorn;
                totalFemaleNames += 1;
            }
        }
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

    public String getFileName(int year) {
        // return "testing/yob"+year+"short.csv";
        return "data/yob" + year + ".csv";
    }

    public int getRank(int year, String name, String gender) {
        FileResource fr = new FileResource(getFileName(year));
        CSVParser parser = fr.getCSVParser(false);
        int rank = 0;
        for (CSVRecord record : parser) {
            String currentGender = record.get(1);
            if (currentGender.equals(gender)) {
                rank += 1;
                String currentName = record.get(0);
                if (currentName.equals(name)) {
                    return rank;
                }
            }

        }
        return -1;
    }

    public String getName(int year, int rank, String gender) {
        FileResource fr = new FileResource(getFileName(year));
        CSVParser parser = fr.getCSVParser(false);
        int currentRank = 0;
        for (CSVRecord record : parser) {
            String currentGender = record.get(1);
            if (currentGender.equals(gender)) {
                currentRank += 1;
                String currentName = record.get(0);
                if (currentRank == rank) {
                    return currentName;
                }
            }
        }
        return "NO NAME";
    }

    public void whatIsNameInYear(String name, int year, int newYear, String gender) {
        String pronoun = null;
        if (gender.equals("F")) {
            pronoun = "she";
        } else {
            pronoun = "he";
        }
        int rank = getRank(year, name, gender);
        String newName = getName(newYear, rank, gender);
        System.out.println(
                name + " born in " + year + " would be " + newName + " if " + pronoun + " was born in " + newYear);
    }

    public void testWhatIsNameInYear() {
        whatIsNameInYear("Isabella", 2012, 2014, "F");
        whatIsNameInYear("Jacob", 2012, 2013, "M");
        whatIsNameInYear("Liam", 2014, 2012, "M");
    }

    public int yearOfHighestRank(String name, String gender) {
        DirectoryResource dr = new DirectoryResource();
        int highestYear = -1;
        int currentHighestRank = Integer.MAX_VALUE;
        // highestYear = -1 b/c it is easier to return the year if the name isnt there
        // in any file
        for (File f : dr.selectedFiles()) {
            String fileName = f.getName();
            int year = Integer.parseInt(fileName.substring(3, 7));
            int currentRank = getRank(year, name, gender);
            if (currentRank == -1) {
                continue;
            }
            if (currentRank < currentHighestRank) {
                currentHighestRank = currentRank;
                highestYear = year;
            }
        }
        return highestYear;
    }

    public void testYearOfHighestRank() {
        int answer = yearOfHighestRank("Mason", "M");
        System.out.println(answer);
    }

    public double getAverageRank(String name, String gender) {
        DirectoryResource dr = new DirectoryResource();
        double totalRankings = 0;
        double totalInstances = 0;
        for (File f : dr.selectedFiles()) {
            String fileName = f.getName();
            int year = Integer.parseInt(fileName.substring(3, 7));
            double currentRank = getRank(year, name, gender);
            if (currentRank == -1) {
                continue;
            }
            totalInstances += 1;
            totalRankings += currentRank;
        }
        if (totalRankings == 0) {
            return -1.0;
        }
        double totalAverage = totalRankings / totalInstances;
        return totalAverage;
    }

    public int getTotalBirthsRankedHigher(int year, String name, String gender) {
        int totalBirths = 0;
        FileResource fr = new FileResource(getFileName(year));
        CSVParser parser = fr.getCSVParser(false);
        for (CSVRecord record : parser) {
            String currentGender = record.get(1);
            if (!currentGender.equals(gender)) {
                continue;
            }
            String currentName = record.get(0);
            if (currentName.equals(name)) {
                return totalBirths;
            }
            int births = Integer.parseInt(record.get(2));
            totalBirths += births;
        }
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
