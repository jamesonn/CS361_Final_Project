import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;

/**
 *
 */
public class UserInterface extends JFrame{

	private static final long serialVersionUID = 1L;
    private ChronoTimer cTimer;
    private String[] command = new String[3];
    private String sysTime;
    private String selectedMenuOption;
    private String consoleText = "";
    private String printerText = "";
    private String previousConsoleLine = "";
    private StringBuilder enteredNumber = new StringBuilder();
    private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    private Calendar calendar = Calendar.getInstance();
    private double totalTime;
    private JLabel eventLabel;
    private JTextArea console;
    private JTextArea numberSelectionField;
    private JTextArea printer;
    private JScrollPane printerScrollPane;
    private JScrollPane consoleScroll;
	private JList<String> functionMenu;
    private JList<String> eventTypes;
    private Container cp = getContentPane();
    private JRadioButton toggleBackOne;
    private JRadioButton toggleBackTwo;
    private JRadioButton toggleBackThree;
    private JRadioButton toggleBackFour;
    private JRadioButton toggleBackFive;
    private JRadioButton toggleBackSix;
    private JRadioButton toggleBackSeven;
    private JRadioButton toggleBackEight;
    private JRadioButton toggleOne;
    private JRadioButton toggleTwo;
    private JRadioButton toggleThree;
    private JRadioButton toggleFour;
    private JRadioButton toggleFive;
    private JRadioButton toggleSix;
    private JRadioButton toggleSeven;
    private JRadioButton toggleEight;
    private JButton function;
    private JButton USB;
    private JButton printerPower;
    private boolean functionMenuIsOpen;
    private boolean selectingNumber;
    private boolean isExportMenuOpen;
    private boolean isEventListOpen;
    private boolean printCalled;
    private int functionMenuIndex;
    private int eventIndex;

    public UserInterface(ChronoTimer cTimer, Log log){
        this.cTimer = cTimer;
		cp.setLayout(null);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width/4-this.getSize().width/4, dim.height/5-this.getSize().height/5);

		JButton power = new JButton("Power");
		power.setBackground(Color.GREEN);
		power.setOpaque(true);
		power.setBounds(30, 20, 100, 30);  // (x, y, width, height)
		power.addActionListener(e -> {
            if(cTimer.getSystemStatus()) {
                command[0] = "OFF";
                updateTime();
                setUIDefaultState();
                lockConnections();
                cTimer.executeCommand(command,totalTime,sysTime);
                power.setBackground(Color.RED);
            }else{
                command[0] = "ON";
                updateTime();
                cTimer.executeCommand(command,totalTime,sysTime);
                unlockConnections();
                power.setBackground(Color.GREEN);
            }
		}); 
		cp.add(power);

		function = new JButton("FUNCTION");
		function.setBounds(30, 250, 100, 30);
		function.addActionListener(e -> refreshConsole());
		cp.add(function);

		BasicArrowButton left = new BasicArrowButton(BasicArrowButton.WEST);
		left.setBounds(30, 325, 30, 30);
		left.addActionListener(e -> {
            if(selectingNumber && enteredNumber.toString().equals("")){
                selectingNumber = false;
                functionMenuIsOpen = true;
                numberSelectionField.setVisible(false);
                functionMenu.setVisible(true);
                enteredNumber =  new StringBuilder();
                revalidate();
            }else if(selectingNumber && !enteredNumber.toString().equals("")){
                enteredNumber.setLength(enteredNumber.length() - 1);
                numberSelectionField.setText(selectedMenuOption + " " + enteredNumber.toString());
                revalidate();
            } else if(functionMenuIsOpen){
                functionMenuIsOpen = false;
                isEventListOpen = false;
                consoleScroll.setVisible(true);
                console.setVisible(true);
                eventTypes.setVisible(false);
                functionMenu.setVisible(false);
                revalidate();
            }
		}); 
		cp.add(left);

		BasicArrowButton right = new BasicArrowButton(BasicArrowButton.EAST);
		right.setBounds(110, 325, 30, 30);
		right.addActionListener(e -> {
            if(isEventListOpen){
                selectedMenuOption = eventTypes.getSelectedValue();
                switch(selectedMenuOption) {
                    case "IND":
                        command[1] = "IND";
                        updateTime();
                        cTimer.executeCommand(command, totalTime, sysTime);
                        eventLabel.setText("Current Event: Individual");
                        break;
                    case "PARIND":
                        command[1] = "PARIND";
                        updateTime();
                        cTimer.executeCommand(command, totalTime, sysTime);
                        eventLabel.setText("Current Event: Parallel Individual");
                        break;
                    case "GRP":
                        command[1] = "GRP";
                        updateTime();
                        cTimer.executeCommand(command, totalTime, sysTime);
                        eventLabel.setText("Current Event: Group");
                        break;
                    case "PARGRP":
                        command[1] = "PARGRP";
                        updateTime();
                        cTimer.executeCommand(command, totalTime, sysTime);
                        eventLabel.setText("Current Event: Group Parallel");
                        break;
                }
                consoleScroll.setVisible(true);
                eventTypes.setVisible(false);
                isEventListOpen = false;
            }else if(functionMenuIsOpen) {
                selectedMenuOption = functionMenu.getSelectedValue();
                if (selectedMenuOption.equals("NUM") || selectedMenuOption.equals("CLR") || selectedMenuOption.equals("PRINT")) {
                    if(selectedMenuOption.equals("PRINT")){
                        printCalled = true;
                    }
                    selectingNumber = true;
                    functionMenuIsOpen = false;

                    functionMenu.setVisible(false);
                    eventTypes.setVisible(false);
                    numberSelectionField.setVisible(true);
                    numberSelectionField.setText(selectedMenuOption + " ");
                }else if(selectedMenuOption.equals("EVENT")){
                    command[0] = selectedMenuOption;
                    isEventListOpen = true;
                    functionMenuIsOpen = false;
                    eventTypes.setVisible(true);
                    functionMenu.setVisible(false);
                }else{
                    command[0] = selectedMenuOption;
                    updateTime();
                    cTimer.executeCommand(command,totalTime,sysTime);

                    if(selectedMenuOption.equals("RESET")){
                        setUIDefaultState();
                    }

                    if(cTimer.getEventStatus()) {
                        if (selectedMenuOption.equals("ENDRUN") && cTimer.getPrinterStatus()) {
                            printerText += log.getRun();
                            printer.setText(printerText);
                            revalidate();
                        }
                    }
                    consoleScroll.setVisible(true);
                    functionMenu.setVisible(false);
                    functionMenuIsOpen = false;
                }
            }else if(selectingNumber){
                selectingNumber = false;
                command[0] = selectedMenuOption;
                command[1] = enteredNumber.toString();
                consoleScroll.setVisible(true);
                numberSelectionField.setVisible(false);
                enteredNumber = new StringBuilder();
                updateTime();
                cTimer.executeCommand(command,totalTime,sysTime);
                if(printCalled){
                    printCalled = false;
                    printerText += log.getRun(Integer.parseInt(command[1]));
                    printer.setText(printerText);
                    revalidate();
                }

                if(!previousConsoleLine.equals(log.getLatestLine())) {
                    previousConsoleLine = log.getLatestLine();
                    consoleText += previousConsoleLine + "\n";
                    console.setText(consoleText);
                }
            }
		}); 
		cp.add(right);

		BasicArrowButton down = new BasicArrowButton(BasicArrowButton.SOUTH);
		down.setBounds(70, 360, 30, 30);
		down.addActionListener(e -> {
            if(functionMenuIsOpen) {
                if (functionMenuIndex < 9) {
                    functionMenuIndex++;
                    functionMenu.setSelectedIndex(functionMenuIndex);
                }
            }else if(isEventListOpen){
                if(eventIndex < 4){
                    eventIndex++;
                    eventTypes.setSelectedIndex(eventIndex);
                }
            }
		}); 
		cp.add(down);

		BasicArrowButton up = new BasicArrowButton(BasicArrowButton.NORTH);
		up.setBounds(70, 290, 30, 30);
		up.addActionListener(e -> {
            if(functionMenuIsOpen) {
                if (functionMenuIndex > 0) {
                    functionMenuIndex--;
                    functionMenu.setSelectedIndex(functionMenuIndex);
                }
            }else if(isEventListOpen){
                if(eventIndex > 0){
                    eventIndex--;
                    eventTypes.setSelectedIndex(eventIndex);
                }
            }
		}); 
		cp.add(up);

		JButton swap = new JButton("SWAP");
		swap.setBounds(30,400, 100, 30);
		swap.addActionListener(e -> {
            command[0] = CommandConstants.swap;
            updateTime();
            cTimer.executeCommand(command, totalTime, sysTime);
		}); 
		cp.add(swap);

		JLabel title = new JLabel("CHRONOTIMER 1009");
		title.setFont(new Font("Dialog", Font.BOLD, 14));
		title.setBounds(300,20,200,20);
		cp.add(title);

		JLabel start = new JLabel("Start");
		start.setBounds(300,70,50,20);
		cp.add(start);

		JLabel enableDisable1 = new JLabel("Enable/Disable");
		enableDisable1.setBounds(233,100,100,20);
		cp.add(enableDisable1);

		JLabel one = new JLabel("1");
		one.setBounds(350, 50, 10, 10);
		cp.add(one);

		JLabel three = new JLabel("3");
		three.setBounds(380, 50, 10, 10);
		cp.add(three);

		JLabel five = new JLabel("5");
		five.setBounds(410, 50, 10, 10);
		cp.add(five);

		JLabel seven = new JLabel("7");
		seven.setBounds(440, 50, 10, 10);
		cp.add(seven);

		JButton startOne = new JButton();
		startOne.setBounds(345, 70, 20, 20);
		startOne.addActionListener(e -> {
            command[0] = CommandConstants.trigger;
            command[1] = "1";

            consoleText += "\n";
            console.setText(consoleText);

            updateTime();
            cTimer.executeCommand(command,totalTime,sysTime);
		}); 
		cp.add(startOne);

		JButton startThree = new JButton();
		startThree.setBounds(375, 70, 20, 20);
		cp.add(startThree);
		startThree.addActionListener(e -> {
            command[0] = CommandConstants.trigger;
            command[1] = "3";

            consoleText += "\n";
            console.setText(consoleText);

            updateTime();
            cTimer.executeCommand(command,totalTime,sysTime);
		}); 

		JButton startFive = new JButton();
		startFive.setBounds(405, 70, 20, 20);
		startFive.addActionListener(e -> {
            command[0] = CommandConstants.trigger;
            command[1] = "5";

            consoleText += "\n";
            console.setText(consoleText);

            updateTime();
            cTimer.executeCommand(command,totalTime,sysTime);
		}); 
		cp.add(startFive);

		JButton startSeven = new JButton();
		startSeven.setBounds(435, 70, 20, 20);
		startSeven.addActionListener(e -> {
            command[0] = CommandConstants.trigger;
            command[1] = "7";

            consoleText += "\n";
            console.setText(consoleText);

            updateTime();
            cTimer.executeCommand(command,totalTime,sysTime);
		}); 
		cp.add(startSeven);

		toggleOne = new JRadioButton();
		toggleOne.setBounds(341, 100, 22, 22);
		toggleOne.addActionListener(e -> {
            command[0] = CommandConstants.toggle;
            command[1] = "1";
            updateTime();
            cTimer.executeCommand(command,totalTime,sysTime);
		}); 
		cp.add(toggleOne);

		toggleThree = new JRadioButton();
		toggleThree.setBounds(371, 100, 22, 22);
		toggleThree.addActionListener(e -> {
            command[0] = CommandConstants.toggle;
            command[1] = "3";
            updateTime();
            cTimer.executeCommand(command,totalTime,sysTime);
		}); 
		cp.add(toggleThree);

		toggleFive = new JRadioButton();
		toggleFive.setBounds(401, 100, 22, 22);
		toggleFive.addActionListener(e -> {
            command[0] = CommandConstants.toggle;
            command[1] = "5";
            updateTime();
            cTimer.executeCommand(command,totalTime,sysTime);
		}); 
		cp.add(toggleFive);

		toggleSeven = new JRadioButton();
		toggleSeven.setBounds(431, 100, 22, 22);
		toggleSeven.addActionListener(e -> {
            command[0] = CommandConstants.toggle;
            command[1] = "7";
            updateTime();
            cTimer.executeCommand(command,totalTime,sysTime);
		}); 
		cp.add(toggleSeven);

		JLabel finish = new JLabel("Finish");
		finish.setBounds(290,160,50,20);
		cp.add(finish);

		JLabel enableDisable2 = new JLabel("Enable/Disable");
		enableDisable2.setBounds(233,190,100,20);
		cp.add(enableDisable2);

		JLabel two = new JLabel("2");
		two.setBounds(350, 140, 10, 10);
		cp.add(two);

		JLabel four = new JLabel("4");
		four.setBounds(380, 140, 10, 10);
		cp.add(four);

		JLabel six = new JLabel("6");
		six.setBounds(410, 140, 10, 10);
		cp.add(six);

		JLabel eight = new JLabel("8");
		eight.setBounds(440, 140, 10, 10);
		cp.add(eight);

		JButton finishTwo = new JButton();
		finishTwo.setBounds(345, 160, 20, 20);
		finishTwo.addActionListener(e -> {
            command[0] = CommandConstants.trigger;
            command[1] = "2";

            updateTime();
            cTimer.executeCommand(command,totalTime,sysTime);

            if(!previousConsoleLine.equals(log.getLatestLine())) {
                previousConsoleLine = log.getLatestLine();
                consoleText += previousConsoleLine + "\n";
                console.setText(consoleText);
            }
		}); 
		cp.add(finishTwo);

		JButton finishFour = new JButton();
		finishFour.setBounds(375, 160, 20, 20);
		finishFour.addActionListener(e -> {
            command[0] = CommandConstants.trigger;
            command[1] = "4";

            updateTime();
            cTimer.executeCommand(command,totalTime,sysTime);

            if(!previousConsoleLine.equals(log.getLatestLine())) {
                previousConsoleLine = log.getLatestLine();
                consoleText += previousConsoleLine + "\n";
                console.setText(consoleText);
            }
		}); 
		cp.add(finishFour);

		JButton finishSix = new JButton();
		finishSix.setBounds(405, 160, 20, 20);
		finishSix.addActionListener(e -> {
            command[0] = CommandConstants.trigger;
            command[1] = "6";

            updateTime();
            cTimer.executeCommand(command,totalTime,sysTime);

            if(!previousConsoleLine.equals(log.getLatestLine())) {
                previousConsoleLine = log.getLatestLine();
                consoleText += previousConsoleLine + "\n";
                console.setText(consoleText);
            }
		}); 
		cp.add(finishSix);

		JButton finishEight = new JButton();
		finishEight.setBounds(435, 160, 20, 20);
		finishEight.addActionListener(e -> {
            command[0] = CommandConstants.trigger;
            command[1] = "8";

            updateTime();
            cTimer.executeCommand(command,totalTime,sysTime);

            if(!previousConsoleLine.equals(log.getLatestLine())) {
                previousConsoleLine = log.getLatestLine();
                consoleText += previousConsoleLine + "\n";
                console.setText(consoleText);
            }
		}); 
		cp.add(finishEight);

		toggleTwo = new JRadioButton();
		toggleTwo.setBounds(341, 190, 22, 22);
		toggleTwo.addActionListener(e -> {
            command[0] = CommandConstants.toggle;
            command[1] = "2";
            updateTime();
            cTimer.executeCommand(command,totalTime,sysTime);
		}); 
		cp.add(toggleTwo);

		toggleFour = new JRadioButton();
		toggleFour.setBounds(371, 190, 22, 22);
		toggleFour.addActionListener(e -> {
            command[0] = CommandConstants.toggle;
            command[1] = "4";
            updateTime();
            cTimer.executeCommand(command,totalTime,sysTime);
		}); 
		cp.add(toggleFour);

		toggleSix = new JRadioButton();
		toggleSix.setBounds(401, 190, 22, 22);
		toggleSix.addActionListener(e -> {
            command[0] = CommandConstants.toggle;
            command[1] = "6";
            updateTime();
            cTimer.executeCommand(command,totalTime,sysTime);
		}); 
		cp.add(toggleSix);

		toggleEight = new JRadioButton();
		toggleEight.setBounds(431, 190, 22, 22);
		toggleEight.addActionListener(e -> {
            command[0] = CommandConstants.toggle;
            command[1] = "8";
            updateTime();
            cTimer.executeCommand(command,totalTime,sysTime);
		});
		cp.add(toggleEight);

        //This is how the console is now created/refreshed
        refreshConsole();

		JLabel consoleLabel = new JLabel("Queue/Running/Final Time");
		consoleLabel.setBounds(315,463,200,20);
		cp.add(consoleLabel);

		printerPower = new JButton("Printer Pwr");
		printerPower.setBounds(600, 20, 100, 30);
        printerPower.setBackground(Color.RED);
        printerPower.setOpaque(true);  // needed for OSX color display ????
		printerPower.addActionListener(e -> {
            if(cTimer.getSystemStatus()) {
                command[0] = CommandConstants.printPWR;
                updateTime();
                cTimer.executeCommand(command, totalTime, sysTime);
                if (cTimer.getPrinterStatus()) {
                    printerPower.setBackground(Color.GREEN);
                    revalidate();
                } else {
                    printerPower.setBackground(Color.RED);
                    revalidate();
                }
            }
		}); 
		cp.add(printerPower);

		
		
		printer = new JTextArea();
		printer.setEditable(false);
		printer.setBackground(Color.white);
		
		printerScrollPane = new JScrollPane(printer);
		printerScrollPane.setBounds(550, 60, 200, 150);
		printerScrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		printerScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		cp.add(printerScrollPane);
		
		JPanel keypad = new JPanel(new GridLayout(4,3));
		keypad.setBounds(575, 250, 150, 200);

		JButton b1 = new JButton("1");
		b1.addActionListener(e -> {
            if(selectingNumber) {
                enteredNumber.append("1");
                numberSelectionField.setText(selectedMenuOption + " " + enteredNumber.toString());
                revalidate();
            }
        });

		JButton b2 = new JButton("2");
		b2.addActionListener(e -> {
            if(selectingNumber) {
                enteredNumber.append("2");
                numberSelectionField.setText(selectedMenuOption + " " + enteredNumber.toString());
                revalidate();
            }
        });

		JButton b3 = new JButton("3");
		b3.addActionListener(e -> {
            if(selectingNumber) {
                enteredNumber.append("3");
                numberSelectionField.setText(selectedMenuOption + " " + enteredNumber.toString());
                revalidate();
            }
        });

		JButton b4 = new JButton("4");
		b4.addActionListener(e -> {
            if(selectingNumber) {
                enteredNumber.append("4");
                numberSelectionField.setText(selectedMenuOption + " " + enteredNumber.toString());
                revalidate();
            }
        });

		JButton b5 = new JButton("5");
		b5.addActionListener(e -> {
            if(selectingNumber) {
                enteredNumber.append("5");
                numberSelectionField.setText(selectedMenuOption + " " + enteredNumber.toString());
                revalidate();
            }
        });

		JButton b6 = new JButton("6");
		b6.addActionListener(e -> {
            if(selectingNumber) {
                enteredNumber.append("6");
                numberSelectionField.setText(selectedMenuOption + " " + enteredNumber.toString());
                revalidate();
            }
        });

		JButton b7 = new JButton("7");
		b7.addActionListener(e -> {
            if(selectingNumber) {
                enteredNumber.append("7");
                numberSelectionField.setText(selectedMenuOption + " " + enteredNumber.toString());
                revalidate();
            }
        });

		JButton b8 = new JButton("8");
		b8.addActionListener(e -> {
            if(selectingNumber) {
                enteredNumber.append("8");
                numberSelectionField.setText(selectedMenuOption + " " + enteredNumber.toString());
                revalidate();
            }
        });

		JButton b9 = new JButton("9");
		b9.addActionListener(e -> {
            if(selectingNumber) {
                enteredNumber.append("9");
                numberSelectionField.setText(selectedMenuOption + " " + enteredNumber.toString());
                revalidate();
            }
        });

		JButton ba = new JButton("*");
		ba.addActionListener(e -> {
            if(selectingNumber) {
                enteredNumber.append("*");
                numberSelectionField.setText(selectedMenuOption + " " + enteredNumber.toString());
                revalidate();
            }
        });

		JButton b0 = new JButton("0");
		b0.addActionListener(e -> {
            if(selectingNumber) {
                enteredNumber.append("0");
                numberSelectionField.setText(selectedMenuOption + " " + enteredNumber.toString());
                revalidate();
            }
        });

		JButton bp = new JButton("#");
		bp.addActionListener(e -> {
            if(selectingNumber) {
                enteredNumber.append("#");
                numberSelectionField.setText(selectedMenuOption + " " + enteredNumber.toString());
                revalidate();
            }
        });

		keypad.add(b1);
		keypad.add(b2);
		keypad.add(b3);
		keypad.add(b4);
		keypad.add(b5);
		keypad.add(b6);
		keypad.add(b7);
		keypad.add(b8);
		keypad.add(b9);
		keypad.add(ba);
		keypad.add(b0);
		keypad.add(bp);
		cp.add(keypad);

		JPanel back = new JPanel();
		back.setLayout(null);
		back.setBounds(0,500, 800, 170);
		back.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		cp.add(back);

		JLabel channel = new JLabel("CHAN");
		channel.setBounds(20, 20, 100, 20);
		back.add(channel);

		JLabel backOne = new JLabel("1");
		backOne.setBounds(100, 20, 10, 10);
		back.add(backOne);

		JLabel backThree = new JLabel("3");
		backThree.setBounds(130, 20, 10, 10);
		back.add(backThree);

		JLabel backFive = new JLabel("5");
		backFive.setBounds(160, 20, 10, 10);
		back.add(backFive);

		JLabel backSeven = new JLabel("7");
		backSeven.setBounds(190, 20, 10, 10);
		back.add(backSeven);

		String[] sensorStrings = {"GATE", "PAD", "EYE"};
		JComboBox<String> sensorType = new JComboBox<>(sensorStrings);
		sensorType.setBounds(210, 20, 100, 100);
		sensorType.addActionListener(e ->{
            command[1] = (String) sensorType.getSelectedItem();
            sensorType.setVisible(false);
            updateTime();
            cTimer.executeCommand(command, totalTime, sysTime);
            unlockConnections();
        });
		back.add(sensorType);
		sensorType.setVisible(false);

		toggleBackOne = new JRadioButton();
		toggleBackOne.setBounds(91, 40, 22, 22);
		toggleBackOne.addItemListener(ie -> {
			if (ie.getStateChange() == ItemEvent.SELECTED) {
				lockConnections();
                command[0] = CommandConstants.connect;
                command[2] = "1";
                sensorType.setVisible(true);
            }
            else if (ie.getStateChange() == ItemEvent.DESELECTED) {
                command[0] = CommandConstants.disconnect;
                command[1] = "1";
                updateTime();
                cTimer.executeCommand(command, totalTime, sysTime);
            }
		}); 
		back.add(toggleBackOne);

		toggleBackThree = new JRadioButton();
		toggleBackThree.setBounds(121, 40, 22, 22);
		toggleBackThree.addItemListener(ie -> {
			if (ie.getStateChange() == ItemEvent.SELECTED) {
				lockConnections();
                command[0] = CommandConstants.connect;
                command[2] = "3";
                sensorType.setVisible(true);
            }
            else if (ie.getStateChange() == ItemEvent.DESELECTED) {
                command[0] = CommandConstants.disconnect;
                command[1] = "3";
                updateTime();
                cTimer.executeCommand(command, totalTime, sysTime);
            }
		}); 
		back.add(toggleBackThree);

		toggleBackFive = new JRadioButton();
		toggleBackFive.setBounds(151, 40, 22, 22);
		toggleBackFive.addItemListener(ie -> {
			if (ie.getStateChange() == ItemEvent.SELECTED) {
				lockConnections();
                command[0] = CommandConstants.connect;
                command[2] = "5";
                sensorType.setVisible(true);
            }
            else if (ie.getStateChange() == ItemEvent.DESELECTED) {
                command[0] = CommandConstants.disconnect;
                command[1] = "5";
                updateTime();
                cTimer.executeCommand(command, totalTime, sysTime);
            }
		}); 
		back.add(toggleBackFive);

		toggleBackSeven = new JRadioButton();
		toggleBackSeven.setBounds(181, 40, 22, 22);
		toggleBackSeven.addItemListener(ie -> {
			if (ie.getStateChange() == ItemEvent.SELECTED) {
				lockConnections();
                command[0] = CommandConstants.connect;
                command[2] = "7";
                sensorType.setVisible(true);
            }
            else if (ie.getStateChange() == ItemEvent.DESELECTED) {
                command[0] = CommandConstants.disconnect;
                command[1] = "7";
                updateTime();
                cTimer.executeCommand(command, totalTime, sysTime);
            }
		}); 
		back.add(toggleBackSeven);

		JLabel backTwo = new JLabel("2");
		backTwo.setBounds(100, 70, 10, 10);
		back.add(backTwo);

		JLabel backFour = new JLabel("4");
		backFour.setBounds(130, 70, 10, 10);
		back.add(backFour);

		JLabel backSix = new JLabel("6");
		backSix.setBounds(160, 70, 10, 10);
		back.add(backSix);

		JLabel backEight = new JLabel("8");
		backEight.setBounds(190, 70, 10, 10);
		back.add(backEight);

		toggleBackTwo = new JRadioButton();
		toggleBackTwo.setBounds(91, 90, 22, 22);
		toggleBackTwo.addItemListener(ie -> {
			if (ie.getStateChange() == ItemEvent.SELECTED) {
				lockConnections();
                command[0] = CommandConstants.connect;
                command[2] = "2";
                sensorType.setVisible(true);
            }
            else if (ie.getStateChange() == ItemEvent.DESELECTED) {
                command[0] = CommandConstants.disconnect;
                command[1] = "2";
                updateTime();
                cTimer.executeCommand(command, totalTime, sysTime);
            }
		}); 
		back.add(toggleBackTwo);

		toggleBackFour = new JRadioButton();
		toggleBackFour.setBounds(121, 90, 22, 22);
		toggleBackFour.addItemListener(ie -> {
			if (ie.getStateChange() == ItemEvent.SELECTED) {
				lockConnections();
                command[2] = "4";
                command[0] = CommandConstants.connect;
                sensorType.setVisible(true);
            }
            else if (ie.getStateChange() == ItemEvent.DESELECTED) {
                command[0] = CommandConstants.disconnect;
                command[1] = "4";
                updateTime();
                cTimer.executeCommand(command, totalTime, sysTime);
            }
		}); 
		back.add(toggleBackFour);

		toggleBackSix = new JRadioButton();
		toggleBackSix.setBounds(151, 90, 22, 22);
		toggleBackSix.addItemListener(ie -> {
			if (ie.getStateChange() == ItemEvent.SELECTED) {
				lockConnections();
                command[0] = CommandConstants.connect;
                command[2] = "6";
                sensorType.setVisible(true);
            }
            else if (ie.getStateChange() == ItemEvent.DESELECTED) {
                command[0] = CommandConstants.disconnect;
                command[1] = "6";
                updateTime();
                cTimer.executeCommand(command, totalTime, sysTime);
            }
		}); 
		back.add(toggleBackSix);

		toggleBackEight = new JRadioButton();
		toggleBackEight.setBounds(181, 90, 22, 22);
		toggleBackEight.addItemListener(ie -> {
			if (ie.getStateChange() == ItemEvent.SELECTED) {
				lockConnections();
                command[0] = CommandConstants.connect;
                command[2] = "8";
                sensorType.setVisible(true);
            }
            else if (ie.getStateChange() == ItemEvent.DESELECTED) {
                command[0] = CommandConstants.disconnect;
                command[1] = "8";
                updateTime();
                cTimer.executeCommand(command, totalTime, sysTime);
            }
		}); 
		back.add(toggleBackEight);

        //Handles the Export button
		USB = new JButton();
		USB.setBounds(320, 60, 60, 15);
		USB.addActionListener(e -> {
            if(!isExportMenuOpen) {
                enteredNumber = new StringBuilder();
                selectingNumber = true;
                isExportMenuOpen = true;
                numberSelectionField.setVisible(true);
                console.setVisible(false);
                eventTypes.setVisible(false);
                consoleScroll.setVisible(false);
                functionMenu.setVisible(false);
                String exportMenuText = "Export Menu\nEnter run number\nHit the right arrow to enter\nHit export again to cancel.\nExport Run: ";
                numberSelectionField.setText(exportMenuText);
                selectedMenuOption = "Export";
                revalidate();
            }else{
                isExportMenuOpen = false;
                selectingNumber = false;
                numberSelectionField.setVisible(false);
                enteredNumber = new StringBuilder();
                console.setVisible(true);
                consoleScroll.setVisible(true);
                revalidate();
            }
		}); 
		back.add(USB);

		JLabel USBLabel = new JLabel("USB PORT");
		USBLabel.setBounds(390, 60, 60, 15);
		back.add(USBLabel);

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Chrono Timer");
		setSize(800, 700);
        setResizable(false);
		setVisible(true);
	}

    /**
     * Updates system time, called prior to executeCommand being called.
     */
	private void updateTime(){
		calendar = Calendar.getInstance();
		double milli = calendar.get(Calendar.MILLISECOND);
		milli = milli / 1000;  //shift right three places
		milli = Math.round( milli * 10.0 ) / 10.0;  //round to one decimal place
		totalTime = calendar.get(Calendar.HOUR)*3600 + calendar.get(Calendar.MINUTE)*60 
				+ calendar.get(Calendar.SECOND) + milli;
		sysTime = sdf.format(new Date());
	}

    /**
     * Handles the state changes of the console window. Three States being default console, function menu, export screen,
     * and number selection screen
     */
    private void refreshConsole(){
        if(console == null){
            eventLabel = new JLabel("Current Event: Individual");
            eventLabel.setBounds(280,230,200,20);
            cp.add(eventLabel);

            console = new JTextArea();
            console.setEditable(false);
            consoleScroll = new JScrollPane(console);
            consoleScroll.setBounds(280, 250, 220, 200);
            consoleScroll.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            consoleScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
            consoleScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            consoleScroll.setBackground(Color.white);
            consoleScroll.setVisible(true);
            cp.add(consoleScroll);

            String functions[] = {"NUM","EVENT","RESET","DNF","CLR","PRINT","NEWRUN","ENDRUN","EXIT"};
            functionMenuIndex = 0;
            functionMenu = new JList<>(functions);
            functionMenu.setBounds(280, 250, 220, 200);
            functionMenu.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            functionMenu.setBackground(Color.white);
			functionMenu.setSelectedIndex(functionMenuIndex);
            cp.add(functionMenu);
            functionMenuIsOpen = false;

            String eventList[] = {"IND","PARIND","GRP","PARGRP"};
            eventIndex = 0;
            eventTypes = new JList<>(eventList);
            eventTypes.setBounds(280, 250, 220, 200);
            eventTypes.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            eventTypes.setBackground(Color.white);
            eventTypes.setSelectedIndex(eventIndex);
            cp.add(eventTypes);
            isEventListOpen = false;

            numberSelectionField = new JTextArea();
            numberSelectionField.setEditable(false);
            numberSelectionField.setBounds(280, 250, 220, 200);
            numberSelectionField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            numberSelectionField.setBackground(Color.white);
            cp.add(numberSelectionField);
            selectingNumber = false;
        }else if(!functionMenuIsOpen && !selectingNumber) {
            functionMenu.setVisible(true);
            consoleScroll.setVisible(false);
            functionMenuIsOpen = true;
            revalidate();
        }else if(!functionMenuIsOpen && selectingNumber){
            if(isExportMenuOpen){
                numberSelectionField.setText("");
                isExportMenuOpen = false;
            }
            consoleScroll.setVisible(true);
            numberSelectionField.setVisible(false);
            enteredNumber = new StringBuilder();
            functionMenuIsOpen = false;
            selectingNumber = false;
            revalidate();
        }else if(functionMenuIsOpen){
            consoleScroll.setVisible(true);
            functionMenu.setVisible(false);
            functionMenuIsOpen = false;
            revalidate();
        }
    }
    /**
     * locks button functionality
     */
	private void lockConnections(){
    	toggleBackOne.setEnabled(false);
    	toggleBackTwo.setEnabled(false);
    	toggleBackThree.setEnabled(false);
    	toggleBackFour.setEnabled(false);
    	toggleBackFive.setEnabled(false);
    	toggleBackSix.setEnabled(false);
    	toggleBackSeven.setEnabled(false);
    	toggleBackEight.setEnabled(false);
        toggleOne.setEnabled(false);
        toggleTwo.setEnabled(false);
        toggleThree.setEnabled(false);
        toggleFour.setEnabled(false);
        toggleFive.setEnabled(false);
        toggleSix.setEnabled(false);
        toggleSeven.setEnabled(false);
        toggleEight.setEnabled(false);
        function.setEnabled(false);
        USB.setEnabled(false);
    }
    
	/**
	 * unlocks button functionality
	 */
    private void unlockConnections(){
    	toggleBackOne.setEnabled(true);
    	toggleBackTwo.setEnabled(true);
    	toggleBackThree.setEnabled(true);
    	toggleBackFour.setEnabled(true);
    	toggleBackFive.setEnabled(true);
    	toggleBackSix.setEnabled(true);
    	toggleBackSeven.setEnabled(true);
    	toggleBackEight.setEnabled(true);
        toggleOne.setEnabled(true);
        toggleTwo.setEnabled(true);
        toggleThree.setEnabled(true);
        toggleFour.setEnabled(true);
        toggleFive.setEnabled(true);
        toggleSix.setEnabled(true);
        toggleSeven.setEnabled(true);
        toggleEight.setEnabled(true);
        function.setEnabled(true);
        USB.setEnabled(true);
    }

    private void setUIDefaultState(){
        toggleOne.setSelected(false);
        toggleTwo.setSelected(false);
        toggleThree.setSelected(false);
        toggleFour.setSelected(false);
        toggleFive.setSelected(false);
        toggleSix.setSelected(false);
        toggleSeven.setSelected(false);
        toggleEight.setSelected(false);

        toggleBackOne.setSelected(false);
        toggleBackTwo.setSelected(false);
        toggleBackThree.setSelected(false);
        toggleBackFour.setSelected(false);
        toggleBackFive.setSelected(false);
        toggleBackSix.setSelected(false);
        toggleBackSeven.setSelected(false);
        toggleBackEight.setSelected(false);

        if(cTimer.getPrinterStatus()){
            printerPower.setBackground(Color.RED);
            revalidate();
        }
    }
}
