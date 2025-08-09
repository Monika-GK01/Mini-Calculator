import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.border.LineBorder;
public class Calculator {

    int borderwidth=360;
    int borderheight=540;
    Color customLightGray = new Color(249,206,231);
    Color customDarkGray = new Color(238,161,205);
    Color customBlack= new Color(154,160,163);
    Color customOrange = new Color(230,139,190);

    String[] buttonValues={
        "AC" , "+/-" ,"%" ,"÷",
        "7" , "8" , "9" , "×" ,
        "4" , "5" , "6" , "-" ,
        "1" , "2" , "3" , "+" ,
        "0" , "." , "√" , "=" 
    };
    String[] rightSymbols={"÷" , "×" , "-" , "+" , "="};
    String[] topSymbols={"AC" , "+/-" , "%"};

    JFrame frame=new JFrame("Calculator");
    JLabel displayLabel=new JLabel();
    JPanel displayPanel=new JPanel();
    JPanel buttonsPanel=new JPanel();
    //a+b a-b a*b a/b
    String A="0";
    String operator=null;
    String B=null;
    Calculator()
    {
       //frame.setVisible(true);//see windw
        frame.setSize(borderwidth,borderheight);//size
        frame.setLocationRelativeTo(null);//frame will center
        frame.setResizable(false);//so user doesnt have access to change /drag
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//automatically close 
        frame.setLayout(new BorderLayout());//place compnents within window

        displayLabel.setBackground(customBlack);
        displayLabel.setForeground(Color.white);
        displayLabel.setFont(new Font("arial",Font.PLAIN,80));
        displayLabel.setHorizontalAlignment(JLabel.RIGHT);//calculate at right
        displayLabel.setText("0");//default
        displayLabel.setOpaque(true);

        displayPanel.setLayout(new BorderLayout());
        displayPanel.add(displayLabel);//label added into panel
        frame.add(displayPanel,BorderLayout.NORTH);//put panel into windows

        buttonsPanel.setLayout(new GridLayout(5,4)); //no.ofrows&col
        buttonsPanel.setBackground(customBlack);
        frame.add(buttonsPanel);

        for(int i=0;i<buttonValues.length;i++)
        {
            JButton button=new JButton(); //new button
            String buttonValue=buttonValues[i]; //
            button.setFont(new Font("Arial",Font.PLAIN,30));//properties
            button.setText(buttonValue); //add cntent
            button.setFocusable(false); //sqaure appears around toremove that
            button.setBorder(new LineBorder(customBlack));
            if(Arrays.asList(topSymbols).contains(buttonValue)) //if sym=topsymb
            {
                button.setBackground(customLightGray);
                button.setForeground(customBlack);
            }
            else if(Arrays.asList(rightSymbols).contains(buttonValue))//if sym=rightsymb
            {
                button.setBackground(customOrange);
                button.setForeground(Color.white);

            }
            else{
                button.setBackground(customDarkGray); //elseevyrywhere
                button.setForeground(Color.white);
            }

            buttonsPanel.add(button);
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e)
                {
                    //buttons when touched 
                    JButton button=(JButton) e.getSource(); //e is event & source gets e
                    String buttonValue=button.getText(); //to idnetify button
            
            if(Arrays.asList(rightSymbols).contains(buttonValue)) //if sym=topsymb
            {
                if (buttonValue.equals("=")) {
                            if (A != null) {//get A and B in console
                                B = displayLabel.getText(); 
                                double numA = Double.parseDouble(A);
                                double numB = Double.parseDouble(B);

                                if (operator.equals("-")) {
                                    displayLabel.setText(removeZeroDecimal(numA-numB));
                                }
                                else if (operator.equals("+")) {
                                    displayLabel.setText(removeZeroDecimal(numA+numB));
                                }
                                else if (operator.equals("×")) {
                                    displayLabel.setText(removeZeroDecimal(numA*numB));
                                }
                                else if (operator.equals("÷")) {
                                    displayLabel.setText(removeZeroDecimal(numA/numB));
                                }
                                clearAll();
                            }
                        }
                    else if("+-×÷".contains(buttonValue))
                    {
                        if(operator==null)
                        {
                            A=displayLabel.getText();
                            displayLabel.setText("0");
                            B="0";
                        }
                        operator=buttonValue;//user can calculate twice like + and -
                    }
                
            }
            else if(Arrays.asList(topSymbols).contains(buttonValue))
            {
                if(buttonValue.equals("AC"))//toclear
                {
                    clearAll();
                    displayLabel.setText("0");


























                    
                }
                else if(buttonValue=="+/-")//nums in negativetiggles so making it positive
                {
                    double numDisplay=Double.parseDouble(displayLabel.getText());
                    numDisplay*=-1;
                    displayLabel.setText(removeZeroDecimal(numDisplay));
                }
                else if(buttonValue=="%")
                {
                    double numDisplay = Double.parseDouble(displayLabel.getText());
                        numDisplay /= 100;
                        displayLabel.setText(removeZeroDecimal(numDisplay));
                }
            }
            else{ //digits or
                if(buttonValue==".")
                {
                    if(!displayLabel.getText().contains(buttonValue))
                    {
                        displayLabel.setText(displayLabel.getText()+buttonValue);
                    }
                }
                else if("012346789".contains(buttonValue));
                {
                    if(displayLabel.getText().equals("0"))
                    {
                        displayLabel.setText(buttonValue);//0 and 5 gives 5 not 05
                    }
                    else{
                        displayLabel.setText(displayLabel.getText()+buttonValue);
                    }
                }
            }   
        }
    });
    frame.setVisible(true);
}   
}
void clearAll(){
        A="0";
        operator=null;
        B=null;
}
String removeZeroDecimal(double numDisplay)
{
    if(numDisplay%1==0)
    {
        return Integer.toString((int)numDisplay);
    }
    return Double.toString(numDisplay);
}

}
