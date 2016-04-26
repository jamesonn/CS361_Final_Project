import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChooseInputUI extends JFrame{

    public boolean UI;
    public boolean TestData;
    public String[] TestFiles;

    public ChooseInputUI() {
        createContents();
        setSize(300, 150);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void createContents() {
        String[] TestFiles = {"Sprint1TestData.txt","Sprint2TestData.txt","Sprint3TestData.txt","Sprint4TestData.txt"};
        JPanel p = new JPanel(new GridBagLayout());
        setLayout(new GridBagLayout());


        // UI Button
        p = new JPanel();
        p.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        JButton ui = new JButton("UI");
        ui.addActionListener(e -> {
            UI = true;
            Client client = new Client(true, null);
            setVisible(false);
        });
        p.add(ui);
        add(p, gbc);

        // TestData Button
        p = new JPanel();
        p.setLayout(new GridBagLayout());
        gbc.gridx = 1;
        gbc.gridy = 0;
        JButton test1 = new JButton("TestData1");
        test1.addActionListener(e -> {
            try {
                TestData = true;
                Client client = new Client(false,TestFiles[0]);
                setVisible(false);
            } catch (IndexOutOfBoundsException e1) {

            }
        });
        p.add(test1);
        add(p, gbc);

        p = new JPanel();
        p.setLayout(new GridBagLayout());
        gbc.gridx = 1;
        gbc.gridy = 1;
        JButton test2 = new JButton("TestData2");
        test2.addActionListener(e -> {
            try {
                TestData = true;
                Client client = new Client(false,TestFiles[1]);
                setVisible(false);
            } catch (IndexOutOfBoundsException e1) {

            }
        });
        p.add(test2);
        add(p, gbc);

        p = new JPanel();
        p.setLayout(new GridBagLayout());
        gbc.gridx = 1;
        gbc.gridy = 2;
        JButton test3 = new JButton("TestData3");
        test3.addActionListener(e -> {
            try {
                TestData = true;
                Client client = new Client(false,TestFiles[2]);
                setVisible(false);
            } catch (IndexOutOfBoundsException e1) {

            }
        });
        p.add(test3);
        add(p, gbc);

        p = new JPanel();
        p.setLayout(new GridBagLayout());
        gbc.gridx = 1;
        gbc.gridy = 3;
        JButton test4 = new JButton("TestData4");
        test4.addActionListener(e -> {
            try {
                TestData = true;
                Client client = new Client(false,TestFiles[3]);
                setVisible(false);
            } catch (IndexOutOfBoundsException e1) {

            }
        });
        p.add(test4);
        add(p, gbc);
    }
}
