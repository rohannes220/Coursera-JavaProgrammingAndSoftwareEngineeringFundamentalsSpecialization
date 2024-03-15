import edu.duke.*;

public class MarkovRunnerWithInterface {
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
        int size = 200;
        int seed = 531;

        EfficientMarkovModel eM6 = new EfficientMarkovModel(5);
        runModel(eM6, st, size, seed);
        /*
         * MarkovZero mz = new MarkovZero();
         * runModel(mz, st, size,seed);
         * 
         * MarkovOne mOne = new MarkovOne();
         * runModel(mOne, st, size,seed);
         * 
         * MarkovModel mThree = new MarkovModel(3);
         * runModel(mThree, st, size,seed);
         * 
         * MarkovFour mFour = new MarkovFour();
         * runModel(mFour, st, size,seed);
         */
    }

    public void testHashMap() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        EfficientMarkovModel eMM5 = new EfficientMarkovModel(5);
        runModel(eMM5, st, 100, 615);
        // sizeTwo.printHashMapInfo();
    }

    public void compareMethods() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');

        EfficientMarkovModel emTwo = new EfficientMarkovModel(2);
        long start = System.nanoTime();
        runModel(emTwo, st, 1000, 42);
        long end = System.nanoTime();
        System.out.println("Time taken for efficient: " + (end - start) + "ms");

        MarkovModel mmTwo = new MarkovModel(2);
        start = System.nanoTime();
        runModel(mmTwo, st, 1000, 42);
        end = System.nanoTime();
        System.out.println("Time taken for inefficient: " + (end - start) + "ms");
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

}
