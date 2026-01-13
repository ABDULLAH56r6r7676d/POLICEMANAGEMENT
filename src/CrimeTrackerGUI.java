import javax.swing.*;
import java.awt.*;

public class CrimeTrackerGUI {

    private CaseManager manager;

    public CrimeTrackerGUI() {

        manager = new CaseManager();
        manager.loadFromFile();

        JFrame frame = new JFrame("SZABIST POLICE");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(8, 1));

        JButton addCaseBtn = new JButton("Add Case");
        JButton addDetectiveBtn = new JButton("Add Detective");
        JButton viewCasesBtn = new JButton("View Cases");
        JButton assignBtn = new JButton("Assign Detective");
        JButton updateBtn = new JButton("Update Status");
        JButton sortIdBtn = new JButton("Sort by Case ID");
        JButton sortStatusBtn = new JButton("Sort by Status");
        JButton saveBtn = new JButton("Save & Exit");

        panel.add(addCaseBtn);
        panel.add(addDetectiveBtn);
        panel.add(viewCasesBtn);
        panel.add(assignBtn);
        panel.add(updateBtn);
        panel.add(sortIdBtn);
        panel.add(sortStatusBtn);
        panel.add(saveBtn);

        frame.add(panel);
        frame.setVisible(true);

        // ---------------- BUTTON ACTIONS ----------------
        addCaseBtn.addActionListener(e -> {
            try {
                int id = Integer.parseInt(
                        JOptionPane.showInputDialog("Enter Case ID:")
                );
                String title = JOptionPane.showInputDialog("Case Title:");
                String desc = JOptionPane.showInputDialog("Description:");

                if (title != null && desc != null) {
                    boolean added = manager.addCase(id, title, desc);
                    if (!added) {
                        JOptionPane.showMessageDialog(null,
                                "Case ID already exists!");
                    }
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null,
                        "Invalid Case ID!");
            }
        });

        addDetectiveBtn.addActionListener(e -> {
            String name = JOptionPane.showInputDialog("Detective Name:");
            String badge = JOptionPane.showInputDialog("Badge Number:");
            if (name != null && badge != null) {
                manager.addDetective(name, badge);
            }
        });

        viewCasesBtn.addActionListener(e -> {
            StringBuilder sb = new StringBuilder();
            for (CrimeCase c : manager.getCases()) {
                sb.append(c).append("\n\n");
            }
            JTextArea area = new JTextArea(sb.toString());
            area.setEditable(false);
            JOptionPane.showMessageDialog(null,
                    new JScrollPane(area),
                    "All Cases",
                    JOptionPane.INFORMATION_MESSAGE);
        });

        assignBtn.addActionListener(e -> {
            try {
                int id = Integer.parseInt(
                        JOptionPane.showInputDialog("Case ID:")
                );
                String badge = JOptionPane.showInputDialog("Detective Badge:");
                manager.assignDetective(id, badge);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null,
                        "Invalid Case ID!");
            }
        });

        updateBtn.addActionListener(e -> {
            try {
                int id = Integer.parseInt(
                        JOptionPane.showInputDialog("Case ID:")
                );
                String status = JOptionPane.showInputDialog("New Status:");
                manager.updateStatus(id, status);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null,
                        "Invalid Case ID!");
            }
        });

        sortIdBtn.addActionListener(e -> manager.bubbleSortCasesById());
        sortStatusBtn.addActionListener(e -> manager.bubbleSortCasesByStatus());

        saveBtn.addActionListener(e -> {
            manager.saveToFile();
            System.exit(0);
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CrimeTrackerGUI::new);
    }
}
