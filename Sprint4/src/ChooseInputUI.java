import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChooseInputUI extends JFrame{

    public boolean UI;
    public boolean TestData;

    public ChooseInputUI() {
        createContents();
        setSize(300, 150);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void createContents() {
        JPanel p = new JPanel(new GridBagLayout());

        // UI Button
        p = new JPanel();
        p.setLayout(new GridBagLayout());
        JButton prev = new JButton("UI");
        prev.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UI = true;
                Client client = new Client(true);
                setVisible(false);
            }
        });
        p.add(prev);
        add(p, BorderLayout.WEST);

        // TestData Button
        p = new JPanel();
        p.setLayout(new GridBagLayout());
        JButton next = new JButton("TestData");
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    TestData = true;
                    Client client = new Client(false);
                    setVisible(false);
                } catch (IndexOutOfBoundsException e1) {

                }
            }
        });
        p.add(next);
        add(p, BorderLayout.EAST);
    }
}
