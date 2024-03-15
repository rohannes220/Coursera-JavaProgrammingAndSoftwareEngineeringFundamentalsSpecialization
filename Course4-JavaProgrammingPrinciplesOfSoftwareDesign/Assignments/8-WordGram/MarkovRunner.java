import edu.duke.*;

public class MarkovRunner {
    public void runModel(IMarkovModel markov, String text, int size) {
        markov.setTraining(text);
        System.out.println("running with " + markov);
        for (int k = 0; k < 3; k++) {
            String st = markov.getRandomText(size);
            printOut(st);
        }
    }

    public void runModel(IMarkovModel markov, String text, int size, int seed) {
        markov.setTraining(text);
        markov.setRandom(seed);
        System.out.println("running with " + markov);
        for (int k = 0; k < 3; k++) {
            String st = markov.getRandomText(size);
            printOut(st);
        }
    }

    public void runMarkov() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        // MarkovWord markovWord = new MarkovWord(5);
        EfficientMarkovWord orderThree = new EfficientMarkovWord(2);
        runModel(orderThree, st, 60, 65);
    }

    private void printOut(String s) {
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for (int k = 0; k < words.length; k++) {
            System.out.print(words[k] + " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println();
                psize = 0;
            }
        }
        System.out.println("\n----------------------------------");
    }

    public void testHashMap() {
        EfficientMarkovWord orderTwo = new EfficientMarkovWord(3);
        runModel(orderTwo, "this is a test yes this is really a test yes a test this is wow", 50, 42);
    }

    public void compareMethods() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        MarkovWord markovWord = new MarkovWord(2);
        long start = System.nanoTime();
        runModel(markovWord, st, 100, 42);
        long end = System.nanoTime();
        double time = (end - start) / 1000000000.0;
        System.out.println("The final time for markovWord in seconds: " + time);

        EfficientMarkovWord orderTwo = new EfficientMarkovWord(2);
        start = System.nanoTime();
        runModel(orderTwo, st, 100, 42);
        end = System.nanoTime();
        time = (end - start) / 1000000000.0;
        System.out.println("The final time for efficientmarkovWord in seconds: " + time);
    }
}
