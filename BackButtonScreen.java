package gui;

import javax.swing.*;
import java.util.EmptyStackException;

/**
 * A panel of the ATM containing the back button.
 */
public class BackButtonScreen extends ParentPanel {

    /** The back button. */
    private JButton backButton;

    /**
     * Constructs a new BackButton panel
     */
    public BackButtonScreen() {
        super();

        backButton = new JButton("Back");
        add(backButton);
    }

    /**
     * Changes the current text on the back button to whatever is passed in.
     *
     * @param newText The new text on the back button (String)
     */
    void setButtonText( String newText ) {
        backButton.setText(newText);
    }

    /**
     * Returns to the previous ATM screen and logs the user out, if needed.
     */
    private void backEvent() {
        JPanel temp;
        try {
            temp = guiController.popScreen();
        } catch (EmptyStackException e) {
            return;
        } try {
            guiController.setContentPane(guiController.peekScreen());
            if (guiController.peekScreen() instanceof Clearable) {
                ((Clearable) guiController.peekScreen()).clear();
            }
        } catch (EmptyStackException e) {
            guiController.pushScreen(temp);
        }
    }

    /**
     * Sets the properties (i.e. the font, position, and dimensions) for the widgets of this screen.
     */
    @Override
    void setUp() {
        super.setUp();
        backButton.addActionListener(e -> backEvent());
        backButton.setFont(defaultFont);
        backButton.setBounds(320, 230, 100, 35);
    }
}
