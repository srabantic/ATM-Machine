package gui;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * A panel of the ATM containing the help button.
 */
public class HelpButtonScreen extends ParentPanel {

    /** The help button. */
    JButton helpButton;

    /**
     * Constructs a new HelpButton screen.
     */
    public HelpButtonScreen() {
        super();
        this.setSize(450, 325);
        this.setLayout(null);
        helpButton = new JButton(new ImageIcon("phase2/src/gui/HelpFiles/help.png"));
        add(helpButton);
    }

    /**
     * Returns the contents of a text file as a String.
     *
     * @param fileName The name of the text file (String)
     * @return String - The contents of the text file
     */
    String getMessage(String fileName) {
        StringBuilder message = new StringBuilder();
        Path path = Paths.get("phase2/src/gui/HelpFiles/"+fileName);
        try (BufferedReader fileInput = Files.newBufferedReader(path)) {
            String line = fileInput.readLine();
            while (line != null) {
                message.append(line);
                message.append("\n");
                line = fileInput.readLine();
            }
        } catch (IOException e) {
            message.append("Error");
        }
        return message.toString();
    }

    /**
     * Sets the properties (i.e. the font, position, and dimensions) for the widgets of this screen.
     */
    @Override
    void setUp() {
        super.setUp();
        helpButton.setBounds(390, 10, 30, 30);
    }
}
