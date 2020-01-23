package calcswing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Window extends JFrame implements ActionListener {
    public static Window window = null;
    private JPanel topPanel;
    private JPanel midPanel;
    private JPanel bottomPanel;
    protected JLabel display;
    private JButton b1,b2,b3,b4,b5,b6,b7,b8,b9,b0,bplus,bminus,bmultiply,
            bdivide,bequal,bCE,bpoint;
    private boolean start = true;
    private boolean operTrigger = false;
    private float number1=0;
    private float number2=0;
    private float result=0;
    private String operator="";
  
    private Window () {
        this.setTitle("Calculator");
        setSize(500,700);
        this.setLayout(new BorderLayout(10,10));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        topPanel = new JPanel();
        display = new JLabel("0",null,JLabel.CENTER);
        display.setFont(new Font("Verdana", Font.PLAIN, 35));
  
        midPanel = new JPanel();
        bottomPanel = new JPanel();
        
        topPanel.setLayout(new BorderLayout());
        topPanel.add(display, BorderLayout.CENTER);
        add(topPanel,BorderLayout.NORTH);
        add(midPanel,BorderLayout.CENTER);
        add(bottomPanel,BorderLayout.SOUTH);
        
        midPanel.setLayout(new GridLayout(4,4,15,15));
        JButton buttonArray[] = new JButton[16];
        String butText[] = {"7","8","9","+","4","5","6","-","1","2","3","*","CE","0",".","/"};
        Font butFont = new Font("TimesRoman", Font.BOLD, 30);
                
        for (int i=0; i<buttonArray.length; i++) {
            midPanel.add(buttonArray[i] = new JButton(butText[i]));
            buttonArray[i].setFont(butFont);
            buttonArray[i].addActionListener(this);
        } 
        
        b7=buttonArray[0];
        b8=buttonArray[1];
        b9=buttonArray[2];
        bplus=buttonArray[3];
        b4=buttonArray[4];
        b5=buttonArray[5];
        b6=buttonArray[6];
        bminus=buttonArray[7];
        b1=buttonArray[8];
        b2=buttonArray[9];
        b3=buttonArray[10];
        bmultiply=buttonArray[11];
        bCE=buttonArray[12];
        b0=buttonArray[13];
        bpoint=buttonArray[14];
        bdivide=buttonArray[15];
                
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.add(bequal = new JButton("="),BorderLayout.CENTER);
        bequal.setFont(butFont);
        bequal.addActionListener(this);
        bequal.setSize((int)(this.getWidth()*0.8),(int)(this.getHeight()*0.2));
        setVisible(true);
        
    }
    
    public static Window getInstance () {
        if(window !=null) return window;
        else return new Window();
    }
   
    @Override
    public void actionPerformed(ActionEvent ae) {
        Object x=ae.getSource();
        String butName = ae.getActionCommand();
        if (start) { number1=0;}
        if (operTrigger) {
            display.setText("");
            operTrigger=false;
        }
        if (x==b0 || x==b1 || x==b2 || x==b3 || x==b4 || x==b5 || x==b6 || x==b7
                || x==b8 || x==b9 || x==bpoint) {
            if (display.getText().equals("0")) {
                display.setText("");
            }
            display.setText(display.getText()+butName);
            start=false;
        } else {
            if (x==bequal) {
                number2=Float.valueOf(display.getText());
                result=number2;
                switch (operator) {
                    case "+": result=number1+number2; break;
                    case "-": result=number1-number2; break;
                    case "*": result=number1*number2; break;
                    case "/": result=number1/number2; break;
                }
                display.setText(Float.toString(result));
            } else {
                if (x==bCE) {
                    display.setText("0");
                    start=true;
                } else {
                operator=butName;
                number1=Float.valueOf(display.getText());
                operTrigger=true;
                }
            }       
        }

    }
}
    
