package gui;

import javax.swing.*;
import java.awt.Font;
import bankSystem.MainBankSystem;

/**
 * A parent class for all the ATM panels that keeps track of default values, and stores the GuiStarter instance along
 * with various methods.
 */
public class ParentPanel extends JPanel {

    /** The default font for the widgets. */
    Font defaultFont;

    /** The instance of GuiController controlling all the current screens. */
    static GuiController guiController;

    /**
     * Constructs a new ParentPanel.
     */
    public ParentPanel() {
        defaultFont = new Font("Arial", Font.PLAIN, 20);
        this.setSize(450, 325);
        this.setLayout(null);
    }

    /**
     * Sets the properties (i.e. the font, position, and dimensions) for the widgets of this screen.
     */
    void setUp() {
        guiController = MainBankSystem.getGuiController();
    }
}