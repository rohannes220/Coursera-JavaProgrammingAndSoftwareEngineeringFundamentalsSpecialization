import java.util.*;

public class Tester {
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }

    public void testLogAnalyzer() {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile("data/short-test_log.txt");
        logAnalyzer.printAll();
    }

    public void testUniqueIP() {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile("weblog2_log");
        int count = logAnalyzer.countUniqueIps();
        System.out.println(count);
    }

    public void testUniqueIPVisits() {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile("weblog2_log");
        ArrayList<String> uniqueIPS = logAnalyzer.uniqueIPVisitsOneDay("Sep 24");
        System.out.println(uniqueIPS.size());
    }

    public void testRange() {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile("weblog2_log");
        int total = logAnalyzer.countUniqueIPsInRange(200, 299);
        System.out.println("200 to 299:" + total);
        total = logAnalyzer.countUniqueIPsInRange(400, 499);
        System.out.println("400 to 499:" + total);
    }

    public void testAllNumbers() {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile("weblog1_log");
        logAnalyzer.printAllHigherThanNum(400);
    }

    public void testCountVisitsPerIP() {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile("weblog2_log");
        System.out.println(logAnalyzer.countVisitsPerIp());
    }

    public void testMostNumberVisitsByIP() {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile("weblog2_log");
        HashMap<String, Integer> counts = logAnalyzer.countVisitsPerIp();
        int answer = logAnalyzer.mostNumberVisitsByIP(counts);
        System.out.println(answer);
    }

    public void testiPsMostVisits() {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile("weblog2_log");
        HashMap<String, Integer> counts = logAnalyzer.countVisitsPerIp();
        ArrayList<String> answer = logAnalyzer.iPsMostVisits(counts);
        System.out.println(answer);
    }

    public void testiPsForDays() {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile("weblog2_log");
        HashMap<String, ArrayList<String>> answer = logAnalyzer.iPsForDays();
        System.out.println(answer);
    }

    public void testdayWithMostIPVisits() {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile("weblog1_log");
        HashMap<String, ArrayList<String>> answer = logAnalyzer.iPsForDays();
        String finalDay = logAnalyzer.dayWithMostIPVisits(answer);
        System.out.println(finalDay);
    }

    public void testiPsWithMostVisitsOnDay() {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile("weblog2_log");
        HashMap<String, ArrayList<String>> answer = logAnalyzer.iPsForDays();
        ArrayList<String> ipsWithMostVists = logAnalyzer.iPsWithMostVisitsOnDay(answer, "Sep 30");
        System.out.println(ipsWithMostVists);
    }
}
