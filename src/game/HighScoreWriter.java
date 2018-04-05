package game;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Writes high scores to a text file.
 */
public class HighScoreWriter {

    private String fileName;

    public HighScoreWriter(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Write the high-score data to the high-score file.
     */
    public void writeHighScore(String name, int score) throws IOException {
        boolean append = true;
        FileWriter writer = null;
        try {
            User user = new User(name, score);
            writer = new FileWriter(fileName, append);
            writer.write(name + "," + score + "\n");
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    /**
     * Clear the high-score file.
     */
    public void clear() throws IOException {
        boolean append = false;
        FileWriter writer = null;
        try {
            writer = new FileWriter(fileName, append);
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        HighScoreWriter hsWriter = new HighScoreWriter("sample.hs");
        for (int i = 0; i < args.length; i += 2) {
            String name = args[i];
            int score = Integer.parseInt(args[i + 1]);
            hsWriter.writeHighScore(name, score);
        }
    }
}
