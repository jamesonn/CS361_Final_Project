import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class UserInterface extends JFrame{
	
	private static final long serialVersionUID = 1L;

	public UserInterface(){
		Container cp = getContentPane();
	    cp.setLayout(null);
	    
	    JButton power = new JButton("Power");
	    power.setBounds(30, 20, 100, 30);  // (x, y, width, height)
	    cp.add(power);
	    
	    JButton function = new JButton("FUNCTION");
	    function.setBounds(30, 250, 100, 30);
	    cp.add(function);
	    
	    //add arrow buttons
	    
	    JButton swap = new JButton("SWAP");
	    swap.setBounds(30,400, 100, 30);
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
	    startOne.setBackground(Color.GREEN);
	    startOne.setOpaque(true);
	    startOne.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	    startOne.setBounds(345, 70, 20, 20);
	    cp.add(startOne);
	    
	    JButton startThree = new JButton();
	    startThree.setBackground(Color.GREEN);
	    startThree.setOpaque(true);
	    startThree.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	    startThree.setBounds(375, 70, 20, 20);
	    cp.add(startThree);
	    
	    JButton startFive = new JButton();
	    startFive.setBackground(Color.GREEN);
	    startFive.setOpaque(true);
	    startFive.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	    startFive.setBounds(405, 70, 20, 20);
	    cp.add(startFive);
	    
	    JButton startSeven = new JButton();
	    startSeven.setBackground(Color.GREEN);
	    startSeven.setOpaque(true);
	    startSeven.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	    startSeven.setBounds(435, 70, 20, 20);
	    cp.add(startSeven);
	    
	    JRadioButton toggleOne = new JRadioButton();
	    toggleOne.setBounds(341, 100, 22, 22);
	    cp.add(toggleOne);
	    
	    JRadioButton toggleThree = new JRadioButton();
	    toggleThree.setBounds(371, 100, 22, 22);
	    cp.add(toggleThree);
	    
	    JRadioButton toggleFive = new JRadioButton();
	    toggleFive.setBounds(401, 100, 22, 22);
	    cp.add(toggleFive);
	    
	    JRadioButton toggleSeven = new JRadioButton();
	    toggleSeven.setBounds(431, 100, 22, 22);
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
	    
	    JButton startTwo = new JButton();
	    startTwo.setBackground(Color.RED);
	    startTwo.setOpaque(true);
	    startTwo.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	    startTwo.setBounds(345, 160, 20, 20);
	    cp.add(startTwo);
	    
	    JButton startFour = new JButton();
	    startFour.setBackground(Color.RED);
	    startFour.setOpaque(true);
	    startFour.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	    startFour.setBounds(375, 160, 20, 20);
	    cp.add(startFour);
	    
	    JButton startSix = new JButton();
	    startSix.setBackground(Color.RED);
	    startSix.setOpaque(true);
	    startSix.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	    startSix.setBounds(405, 160, 20, 20);
	    cp.add(startSix);
	    
	    JButton startEight = new JButton();
	    startEight.setBackground(Color.RED);
	    startEight.setOpaque(true);
	    startEight.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	    startEight.setBounds(435, 160, 20, 20);
	    cp.add(startEight);
	    
	    JRadioButton toggleTwo = new JRadioButton();
	    toggleTwo.setBounds(341, 190, 22, 22);
	    cp.add(toggleTwo);
	    
	    JRadioButton toggleFour = new JRadioButton();
	    toggleFour.setBounds(371, 190, 22, 22);
	    cp.add(toggleFour);
	    
	    JRadioButton toggleSix = new JRadioButton();
	    toggleSix.setBounds(401, 190, 22, 22);
	    cp.add(toggleSix);
	    
	    JRadioButton toggleEight = new JRadioButton();
	    toggleEight.setBounds(431, 190, 22, 22);
	    cp.add(toggleEight);
	    
	    //add console display
	    
	    JButton printerPower = new JButton("Printer Pwr");
	    printerPower.setBounds(600, 20, 100, 30);
	    cp.add(printerPower);
	    
	    //add printer display
	    
	    //add numeric keypad
		
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
	    
	    JRadioButton toggleBackOne = new JRadioButton();
	    toggleBackOne.setBounds(91, 40, 22, 22);
	    back.add(toggleBackOne);
	    
	    JRadioButton toggleBackThree = new JRadioButton();
	    toggleBackThree.setBounds(121, 40, 22, 22);
	    back.add(toggleBackThree);
	    
	    JRadioButton toggleBackFive = new JRadioButton();
	    toggleBackFive.setBounds(151, 40, 22, 22);
	    back.add(toggleBackFive);
	    
	    JRadioButton toggleBackSeven = new JRadioButton();
	    toggleBackSeven.setBounds(181, 40, 22, 22);
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
	    back.add(toggleBackTwo);
	    
	    JRadioButton toggleBackFour = new JRadioButton();
	    toggleBackFour.setBounds(121, 90, 22, 22);
	    back.add(toggleBackFour);
	    
	    JRadioButton toggleBackSix = new JRadioButton();
	    toggleBackSix.setBounds(151, 90, 22, 22);
	    back.add(toggleBackSix);
	    
	    JRadioButton toggleBackEight = new JRadioButton();
	    toggleBackEight.setBounds(181, 90, 22, 22);
	    back.add(toggleBackEight);
	   
	    //add USB port
	    
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Chrono Timer");
		setSize(800, 700);
		setVisible(true);
		
		
	}
	
	public static void main(String[] args) {
	      SwingUtilities.invokeLater(new Runnable() {
	         @Override
	         public void run() {
	            new UserInterface();
	         }
	      });
	   }
	
}
