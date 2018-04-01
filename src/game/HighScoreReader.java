package game;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JPanel;

/**
 * Demonstrates how high-score data can be read from a text file and printed to
 * the terminal.
 */
public class HighScoreReader {

    private int pos;
    private String fileName;
    private GameOver gameOver;
    ArrayList<Integer> scores = new ArrayList<Integer>();
    ArrayList<String> names = new ArrayList<String>();

    /**
     * Initialise a new HighScoreReader
     *
     * @param fileName the name of the high-score file
     */
    public HighScoreReader(String fileName) {
        this.fileName = fileName;
    }

    public void setGameOver(GameOver gameOver) {
        this.gameOver = gameOver;
    }

    /**
     * Read the high-score data from the high-score file and print it to the
     * terminal window.
     */
    public void readScores() throws IOException {
        FileReader fr = null;
        BufferedReader reader = null;
        try {
            System.out.println("Reading " + fileName + " ...");
            fr = new FileReader(fileName);
            reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null) {
                // file is assumed to contain one name, score pair per line
                String[] tokens = line.split(",");
                String name = tokens[0];
                names.add(name);

                int score = Integer.parseInt(tokens[1]);
                scores.add(score);

                line = reader.readLine();
            }

            pos = biggestPosition();

            //print the highscore
            gameOver.getTextArea().append("High score" + "\n");
            gameOver.getTextArea().append("Name: " + names.get(pos)
                    + ", Score: " + scores.get(pos) + "\n");

            //print the top 10 scores
            gameOver.getTextArea().append("\n");
            gameOver.getTextArea().append("Top 10" + "\n");
            topTen();

            System.out.println("...done.");
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (fr != null) {
                fr.close();
            }
        }
    }

    //finds the position of the biggest score in the list.
    public int biggestPosition() {
        int pos = 0;
        int biggest = scores.get(pos);
        int current;

        for (int i = 0; i < scores.size(); i++) {
            current = scores.get(i);
            if (current > biggest) {
                pos = i;
                biggest = current;
            }
        }
        return pos;
    }

    public void topTen() {
        Collections.sort(scores);
        Collections.reverse(scores);
        for (int i = 0; i < 10; i++) {
            gameOver.getTextArea().append(i+1 + ") Name: " + names.get(i) + 
                    ", Score: " + scores.get(i) + "\n");
        }
    }

    public static void main(String[] args) throws IOException {
        HighScoreReader demo = new HighScoreReader(args[0]);
        demo.readScores();
    }
}
