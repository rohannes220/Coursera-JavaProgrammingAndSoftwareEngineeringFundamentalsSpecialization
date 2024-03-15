import java.util.*;
import edu.duke.*;

public class LogAnalyzer {
    private ArrayList<LogEntry> records;

    public LogAnalyzer() {
        records = new ArrayList<LogEntry>();
    }

    public void readFile(String filename) {
        records.clear();
        FileResource fr = new FileResource(filename);
        for (String entry : fr.lines()) {
            LogEntry logEntry = WebLogParser.parseEntry(entry);
            records.add(logEntry);
        }
    }

    public void printAllHigherThanNum(int num) {
        for (LogEntry le : records) {
            int statusCode = le.getStatusCode();
            if (statusCode > num) {
                System.out.println(le);
            }
        }
    }

    public int countUniqueIps() {
        ArrayList<String> uniqueIPS = new ArrayList<String>();
        for (LogEntry le : records) {
            String ip = le.getIpAddress();
            if (!uniqueIPS.contains(ip)) {
                uniqueIPS.add(ip);
            }
        }
        return uniqueIPS.size();
    }

    public int countUniqueIPsInRange(int low, int high) {
        ArrayList<String> uniqueIPS = new ArrayList<String>();
        for (LogEntry le : records) {
            int range = le.getStatusCode();
            if (range >= low && range <= high) {
                String address = le.getIpAddress();
                if (!uniqueIPS.contains(address)) {
                    uniqueIPS.add(address);
                }
            }
        }
        return uniqueIPS.size();
    }

    public ArrayList<String> uniqueIPVisitsOneDay(String someday) {
        ArrayList<String> uniqueIPS = new ArrayList<String>();
        for (LogEntry le : records) {
            String date = le.getAccessTime().toString();
            if (date.contains(someday)) {
                String address = le.getIpAddress();
                if (!uniqueIPS.contains(address)) {
                    uniqueIPS.add(address);
                }
            }
        }
        return uniqueIPS;
    }

    public void printAll() {
        for (LogEntry le : records) {
            System.out.println(le);
        }
    }

    public HashMap<String, Integer> countVisitsPerIp() {
        HashMap<String, Integer> answers = new HashMap<String, Integer>();
        for (LogEntry le : records) {
            String ip = le.getIpAddress();
            if (!answers.containsKey(ip)) {
                answers.put(ip, 1);
            } else {
                answers.put(ip, answers.get(ip) + 1);
            }
        }
        return answers;
    }

    public int mostNumberVisitsByIP(HashMap<String, Integer> input) {
        int maxValue = 0;
        for (String key : input.keySet()) {
            int count = input.get(key);
            if (count > maxValue) {
                maxValue = count;
            }
        }
        return maxValue;
    }

    public HashMap<String, ArrayList<String>> iPsForDays() {
        HashMap<String, ArrayList<String>> visits = new HashMap<String, ArrayList<String>>();
        for (LogEntry le : records) {
            String date = le.getAccessTime().toString().substring(4, 10);
            String ip = le.getIpAddress();
            if (!visits.containsKey(date)) {
                ArrayList<String> ips = new ArrayList<String>();
                ips.add(ip);
                visits.put(date, ips);
            } else {
                ArrayList<String> currentIPS = visits.get(date);
                currentIPS.add(ip);
                visits.put(date, currentIPS);
            }
        }
        return visits;
    }

    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> input) {
        int maxSize = 0;
        String maxDate = null;
        for (String date : input.keySet()) {
            int currentSize = input.get(date).size();
            if (currentSize > maxSize) {
                maxSize = currentSize;
                maxDate = date;
            }
        }
        return maxDate;
    }

    public ArrayList<String> iPsMostVisits(HashMap<String, Integer> ipCounts) {
        ArrayList<String> ipsWithMostVists = new ArrayList<String>();
        int maxVisits = mostNumberVisitsByIP(ipCounts);
        for (String ip : ipCounts.keySet()) {
            int currentCount = ipCounts.get(ip);
            if (currentCount == maxVisits) {
                ipsWithMostVists.add(ip);
            }
        }
        return ipsWithMostVists;
    }

    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> input, String date) {
        ArrayList<String> ipsVisitedOnDate = input.get(date);
        HashMap<String, Integer> ipCounts = new HashMap<String, Integer>();
        for (String ip : ipsVisitedOnDate) {
            if (!ipCounts.containsKey(ip)) {
                ipCounts.put(ip, 1);
            } else {
                ipCounts.put(ip, ipCounts.get(ip) + 1);
            }
        }
        return iPsMostVisits(ipCounts);
    }
}
