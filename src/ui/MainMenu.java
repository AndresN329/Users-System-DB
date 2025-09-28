package ui;

import controller.CoderController;
import utils.JOptionPaneUtils;

import javax.swing.*;

public class MainMenu {

    public static void start() {
        String option = "";

        do {
            option = JOptionPane.showInputDialog("""
                    ---------- Menu ----------
                      1. Create Coder
                      2. Display All Coders
                      3. Update Coder
                      4. Delete Coder
                      5. Exit
                    """);

            switch (option) {
                case "1":
                    CoderController.createCoder();
                    break;
                case "2":
                    CoderController.dispalyCoders();
                    break;
                case "3":
                    CoderController.UpdateCoder();
                    break;
                case "4":
                    CoderController.DeleteCoder();
                    break;
                case "5":
                    JOptionPane.showMessageDialog(null, "Thanks for use the program :)");
                    break;

                default:
                    JOptionPane.showMessageDialog(null,"Invalid option, please enter a valid option.");
            }
        } while (!option.equals("5"));
    }
}
