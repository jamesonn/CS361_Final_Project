import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;

public class UserInterface extends JFrame{

	private static final long serialVersionUID = 1L;
	private ChronoTimer cTimer;
    private String[] command = new String[3];
    private StringBuilder enteredNumber = new StringBuilder();
    private int finalNumber;
    private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    private Calendar calendar = Calendar.getInstance();
    private double totalTime;
    private String sysTime;
    private String swap1;
    private String selectedMenuOption;
    private JTextField console;
    private JTextField numberSelectionField;
	private JList functionMenu;
    private Container cp = getContentPane();
    private boolean functionMenuIsOpen;
    private boolean selectingNumber;
    private int functionMenuIndex;

    public UserInterface(ChronoTimer cTimer){

		this.cTimer = cTimer;
		cp.setLayout(null);

		JButton power = new JButton("Power");
		power.setBounds(30, 20, 100, 30);  // (x, y, width, height)
		power.addActionListener(e -> {
            //assuming the system is on if the ui is open
            command[0] = "OFF";
		}); 
		cp.add(power);

		JButton function = new JButton("FUNCTION");
		function.setBounds(30, 250, 100, 30);
		function.addActionListener(e -> refreshConsole());
		cp.add(function);

		BasicArrowButton left = new BasicArrowButton(BasicArrowButton.WEST);
		left.setBounds(30, 300, 30, 30);
		left.addActionListener(e -> {
            if(selectingNumber){
                selectingNumber = false;
                functionMenuIsOpen = true;
                numberSelectionField.setVisible(false);
                functionMenu.setVisible(true);
                revalidate();
            }else if(functionMenuIsOpen){
                functionMenuIsOpen = false;
                console.setVisible(true);
                functionMenu.setVisible(false);
                revalidate();
            }
		}); 
		cp.add(left);

		BasicArrowButton right = new BasicArrowButton(BasicArrowButton.EAST);
		right.setBounds(70, 300, 30, 30);
		right.addActionListener(e -> {
            if(functionMenuIsOpen) {
                selectedMenuOption = functionMenu.getSelectedValue().toString();
                if (selectedMenuOption.equals("Add Racer") || selectedMenuOption.equals("Clear") || selectedMenuOption.equals("Event") || selectedMenuOption.equals("Print")) {
                    selectingNumber = true;
                    functionMenuIsOpen = false;
                    functionMenu.setVisible(false);
                    numberSelectionField.setVisible(true);
                }
            }else if(selectingNumber){
                selectingNumber = false;
                command[0] = selectedMenuOption;
                //command[1] = finalNumber;
                updateTime();
                cTimer.executeCommand(command,totalTime,sysTime);
            }
		}); 
		cp.add(right);

		BasicArrowButton down = new BasicArrowButton(BasicArrowButton.SOUTH);
		down.setBounds(110, 300, 30, 30);
		down.addActionListener(e -> {
            if(functionMenuIsOpen) {
                if (functionMenuIndex < 9) {
                    functionMenuIndex++;
                    functionMenu.setSelectedIndex(functionMenuIndex);
                }
            }
		}); 
		cp.add(down);

		BasicArrowButton up = new BasicArrowButton(BasicArrowButton.NORTH);
		up.setBounds(150, 300, 30, 30);
		up.addActionListener(e -> {
            if(functionMenuIsOpen) {
                if (functionMenuIndex > 0) {
                    functionMenuIndex--;
                    functionMenu.setSelectedIndex(functionMenuIndex);
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
            updateTime();
            cTimer.executeCommand(command,totalTime,sysTime);
		}); 

		JButton startFive = new JButton();
		startFive.setBounds(405, 70, 20, 20);
		startFive.addActionListener(e -> {
            command[0] = CommandConstants.trigger;
            command[1] = "5";
            updateTime();
            cTimer.executeCommand(command,totalTime,sysTime);
		}); 
		cp.add(startFive);

		JButton startSeven = new JButton();
		startSeven.setBounds(435, 70, 20, 20);
		startSeven.addActionListener(e -> {
            command[0] = CommandConstants.trigger;
            command[1] = "7";
            updateTime();
            cTimer.executeCommand(command,totalTime,sysTime);
		}); 
		cp.add(startSeven);

		JRadioButton toggleOne = new JRadioButton();
		toggleOne.setBounds(341, 100, 22, 22);
		toggleOne.addActionListener(e -> {
            command[0] = CommandConstants.toggle;
            command[1] = "1";
            updateTime();
            cTimer.executeCommand(command,totalTime,sysTime);
		}); 
		cp.add(toggleOne);

		JRadioButton toggleThree = new JRadioButton();
		toggleThree.setBounds(371, 100, 22, 22);
		toggleThree.addActionListener(e -> {
            command[0] = CommandConstants.toggle;
            command[1] = "3";
            updateTime();
            cTimer.executeCommand(command,totalTime,sysTime);
		}); 
		cp.add(toggleThree);

		JRadioButton toggleFive = new JRadioButton();
		toggleFive.setBounds(401, 100, 22, 22);
		toggleFive.addActionListener(e -> {
            command[0] = CommandConstants.toggle;
            command[1] = "5";
            updateTime();
            cTimer.executeCommand(command,totalTime,sysTime);
		}); 
		cp.add(toggleFive);

		JRadioButton toggleSeven = new JRadioButton();
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
		}); 
		cp.add(finishTwo);

		JButton finishFour = new JButton();
		finishFour.setBounds(375, 160, 20, 20);
		finishFour.addActionListener(e -> {
            command[0] = CommandConstants.trigger;
            command[1] = "4";
            updateTime();
            cTimer.executeCommand(command,totalTime,sysTime);
		}); 
		cp.add(finishFour);

		JButton finishSix = new JButton();
		finishSix.setBounds(405, 160, 20, 20);
		finishSix.addActionListener(e -> {
            command[0] = CommandConstants.trigger;
            command[1] = "6";
            updateTime();
            cTimer.executeCommand(command,totalTime,sysTime);
		}); 
		cp.add(finishSix);

		JButton finishEight = new JButton();
		finishEight.setBounds(435, 160, 20, 20);
		finishEight.addActionListener(e -> {
            command[0] = CommandConstants.trigger;
            command[1] = "8";
            updateTime();
            cTimer.executeCommand(command,totalTime,sysTime);
		}); 
		cp.add(finishEight);

		JRadioButton toggleTwo = new JRadioButton();
		toggleTwo.setBounds(341, 190, 22, 22);
		toggleTwo.addActionListener(e -> {
            command[0] = CommandConstants.toggle;
            command[1] = "2";
            updateTime();
            cTimer.executeCommand(command,totalTime,sysTime);
		}); 
		cp.add(toggleTwo);

		JRadioButton toggleFour = new JRadioButton();
		toggleFour.setBounds(371, 190, 22, 22);
		toggleFour.addActionListener(e -> {
            command[0] = CommandConstants.toggle;
            command[1] = "4";
            updateTime();
            cTimer.executeCommand(command,totalTime,sysTime);
		}); 
		cp.add(toggleFour);

		JRadioButton toggleSix = new JRadioButton();
		toggleSix.setBounds(401, 190, 22, 22);
		toggleSix.addActionListener(e -> {
            command[0] = CommandConstants.toggle;
            command[1] = "6";
            updateTime();
            cTimer.executeCommand(command,totalTime,sysTime);
		}); 
		cp.add(toggleSix);

		JRadioButton toggleEight = new JRadioButton();
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
		consoleLabel.setBounds(300,460,200,20);
		cp.add(consoleLabel);

		JButton printerPower = new JButton("Printer Pwr");
		printerPower.setBounds(600, 20, 100, 30);
		printerPower.addActionListener(e -> {
				//TODO does this just print?
            command[0] = CommandConstants.print;
            updateTime();
            cTimer.executeCommand(command,totalTime,sysTime);
		}); 
		cp.add(printerPower);

		JTextField printer = new JTextField();
		printer.setEditable(false);
		printer.setBounds(575, 60, 150, 150);
		printer.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		printer.setBackground(Color.white);
		cp.add(printer);

		JPanel keypad = new JPanel(new GridLayout(4,3));
		keypad.setBounds(575, 250, 150, 200);

		JButton b1 = new JButton("1");
		b1.addActionListener(e -> enteredNumber.append("1"));

		JButton b2 = new JButton("2");
		b2.addActionListener(e -> enteredNumber.append("2"));

		JButton b3 = new JButton("3");
		b3.addActionListener(e -> enteredNumber.append("3"));

		JButton b4 = new JButton("4");
		b4.addActionListener(e -> enteredNumber.append("4"));

		JButton b5 = new JButton("5");
		b5.addActionListener(e -> enteredNumber.append("5"));

		JButton b6 = new JButton("6");
		b6.addActionListener(e -> enteredNumber.append("6"));

		JButton b7 = new JButton("7");
		b7.addActionListener(e -> enteredNumber.append("7"));

		JButton b8 = new JButton("8");
		b8.addActionListener(e -> enteredNumber.append("8"));

		JButton b9 = new JButton("9");
		b9.addActionListener(e -> enteredNumber.append("9"));

        //TODO wtf is * supposed to do? im just guessing
		JButton ba = new JButton("*");
		ba.addActionListener(e -> enteredNumber.append("*"));

		JButton b0 = new JButton("0");
		b0.addActionListener(e -> enteredNumber.append("0"));

		JButton bp = new JButton("#");
		bp.addActionListener(e -> {
            //TODO i think conn/disc/swap are the only ones where the number isn't in the second position
            if(command[0].equals(CommandConstants.connect) || command[0].equals(CommandConstants.disconnect)) {
                command[2] = enteredNumber.toString();
                updateTime();
                cTimer.executeCommand(command,totalTime,sysTime);
            }else if(command[0].equals(CommandConstants.swap)){
                if(swap1 == null) {
                    swap1 = enteredNumber.toString();
                }else{
                    command[1] = swap1;
                    command[2] = enteredNumber.toString();
                    swap1 = null;
                    updateTime();
                    cTimer.executeCommand(command,totalTime,sysTime);
                }
            }else{
                command[1] = enteredNumber.toString();
                updateTime();
                cTimer.executeCommand(command,totalTime,sysTime);
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
		JComboBox sensorType = new JComboBox(sensorStrings);
		sensorType.setBounds(210, 20, 100, 100);
		back.add(sensorType);
		sensorType.setVisible(false);

		JRadioButton toggleBackOne = new JRadioButton();
		toggleBackOne.setBounds(91, 40, 22, 22);
		toggleBackOne.addItemListener(ie -> {
            if (ie.getStateChange() == ItemEvent.SELECTED) {
                command[0] = CommandConstants.connect;
                sensorType.addActionListener(e ->{
                    command[1] = (String) sensorType.getSelectedItem();
                    sensorType.setVisible(false);
                    command[2] = "1";
                    updateTime();
                    cTimer.executeCommand(command, totalTime, sysTime);
                });
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

		JRadioButton toggleBackThree = new JRadioButton();
		toggleBackThree.setBounds(121, 40, 22, 22);
		toggleBackThree.addItemListener(ie -> {
            if (ie.getStateChange() == ItemEvent.SELECTED) {
                command[0] = CommandConstants.connect;
                sensorType.addActionListener(e ->{
                    command[1] = (String) sensorType.getSelectedItem();
                    sensorType.setVisible(false);
                    command[2] = "3";
                    updateTime();
                    cTimer.executeCommand(command, totalTime, sysTime);
                });
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

		JRadioButton toggleBackFive = new JRadioButton();
		toggleBackFive.setBounds(151, 40, 22, 22);
		toggleBackFive.addItemListener(ie -> {
            if (ie.getStateChange() == ItemEvent.SELECTED) {
                command[0] = CommandConstants.connect;
                sensorType.addActionListener(e ->{
                    command[1] = (String) sensorType.getSelectedItem();
                    sensorType.setVisible(false);
                    command[2] = "5";
                    updateTime();
                    cTimer.executeCommand(command, totalTime, sysTime);
                });
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

		JRadioButton toggleBackSeven = new JRadioButton();
		toggleBackSeven.setBounds(181, 40, 22, 22);
		toggleBackSeven.addItemListener(ie -> {
            if (ie.getStateChange() == ItemEvent.SELECTED) {
                command[0] = CommandConstants.connect;
                sensorType.addActionListener(e ->{
                    command[1] = (String) sensorType.getSelectedItem();
                    sensorType.setVisible(false);
                    command[2] = "7";
                    updateTime();
                    cTimer.executeCommand(command, totalTime, sysTime);
                });
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

		JRadioButton toggleBackTwo = new JRadioButton();
		toggleBackTwo.setBounds(91, 90, 22, 22);
		toggleBackTwo.addItemListener(ie -> {
            if (ie.getStateChange() == ItemEvent.SELECTED) {
                command[0] = CommandConstants.connect;
                sensorType.addActionListener(e ->{
                    command[1] = (String) sensorType.getSelectedItem();
                    sensorType.setVisible(false);
                    command[2] = "2";
                    updateTime();
                    cTimer.executeCommand(command, totalTime, sysTime);
                });
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

		JRadioButton toggleBackFour = new JRadioButton();
		toggleBackFour.setBounds(121, 90, 22, 22);
		toggleBackFour.addItemListener(ie -> {
            if (ie.getStateChange() == ItemEvent.SELECTED) {
                command[0] = CommandConstants.connect;
                sensorType.addActionListener(e ->{
                    command[1] = (String) sensorType.getSelectedItem();
                    sensorType.setVisible(false);
                    command[2] = "4";
                    updateTime();
                    cTimer.executeCommand(command, totalTime, sysTime);
                });
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

		JRadioButton toggleBackSix = new JRadioButton();
		toggleBackSix.setBounds(151, 90, 22, 22);
		toggleBackSix.addItemListener(ie -> {
            if (ie.getStateChange() == ItemEvent.SELECTED) {
                command[0] = CommandConstants.connect;
                sensorType.addActionListener(e ->{
                    command[1] = (String) sensorType.getSelectedItem();
                    sensorType.setVisible(false);
                    command[2] = "6";
                    updateTime();
                    cTimer.executeCommand(command, totalTime, sysTime);
                });
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

		JRadioButton toggleBackEight = new JRadioButton();
		toggleBackEight.setBounds(181, 90, 22, 22);
		toggleBackEight.addItemListener(ie -> {
            if (ie.getStateChange() == ItemEvent.SELECTED) {
                command[0] = CommandConstants.connect;
                sensorType.addActionListener(e ->{
                    command[1] = (String) sensorType.getSelectedItem();
                    sensorType.setVisible(false);
                    command[2] = "8";
                    updateTime();
                    cTimer.executeCommand(command, totalTime, sysTime);
                });
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

		JButton USB = new JButton();
		USB.setBounds(320, 60, 60, 15);
		USB.addActionListener(e -> {
				//TODO
				//CONN/DISC USB (enter a filename??)
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
	
	private void updateTime(){
		calendar.getInstance();
		totalTime = calendar.get(Calendar.HOUR)*3600 + calendar.get(Calendar.MINUTE)*60 + calendar.get(Calendar.SECOND);
		sysTime = sdf.format(new Date());
	}

    private void refreshConsole(){
        if(console == null){
            console = new JTextField();
            console.setEditable(false);
            console.setBounds(280, 250, 220, 200);
            console.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            console.setBackground(Color.white);
            cp.add(console);

            //Add racer, event, clear, print need a number
            String functions[] = {"Add Racer","Event","Reset","Did Not Finish","Clear","Print","NewRun","EndRun","Exit"};
            functionMenuIndex = 0;
            functionMenu = new JList(functions);
            functionMenu.setBounds(280, 250, 220, 200);
            functionMenu.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            functionMenu.setBackground(Color.white);
			functionMenu.setSelectedIndex(functionMenuIndex);
            cp.add(functionMenu);
            functionMenuIsOpen = false;

            numberSelectionField = new JTextField();
            numberSelectionField.setEditable(false);
            numberSelectionField.setBounds(280, 250, 220, 200);
            numberSelectionField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            numberSelectionField.setBackground(Color.white);
            cp.add(numberSelectionField);
            selectingNumber = false;
        }else if(!functionMenuIsOpen && !selectingNumber) {
            functionMenu.setVisible(true);
            console.setVisible(false);
            functionMenuIsOpen = true;
            revalidate();
        }else if(!functionMenuIsOpen && selectingNumber){
            console.setVisible(true);
            functionMenu.setVisible(false);
            functionMenuIsOpen = false;
            selectingNumber = false;
            revalidate();
        }else if(functionMenuIsOpen){
            console.setVisible(true);
            functionMenu.setVisible(false);
            functionMenuIsOpen = false;
            revalidate();
        }
    }
}
