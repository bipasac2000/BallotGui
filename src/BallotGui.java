
import java.awt.*;
import java.awt.event.*;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.swing.*;


public class BallotGui extends JPanel implements ActionListener, Serializable {
		/**
	 * 
	 */
	  private static final long serialVersionUID = 1L;
		protected JTextField textField;
    protected JTextField topicField; 
    protected JTextArea textArea;
    protected JTextArea typeArea; 
    protected JRadioButton[] radioButton;
    protected JButton[] jButton; 
    protected JButton submitButton; 
    protected JComboBox comboBox; 
    protected Timer timer; 
    protected String prevText; 
    protected String userName; 
    private final static String newline = "\n";

    public BallotGui(String name, String[] cName) {
        super(new GridBagLayout());
        
        /*userName = name; 
        textField = new JTextField("Enter msg here", 20);
        textField.addActionListener(this); 
        prevText = textField.getText(); 
        
        topicField = new JTextField("Receit a poem",20); 
        topicField.addActionListener(this); 

        textArea = new JTextArea(20, 20);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        */
        
        radioButton = new JRadioButton[cName.length]; 
        jButton = new JButton[cName.length];
        for(int i =0; i<cName.length; i++){
        	radioButton[i] = new JRadioButton(cName[i], true);
        	jButton[i] = new JButton("Get Information"); 
        }
        submitButton = new JButton("Submit"); 
        /*jButton1 = new J
        radioButton2 = new JRadioButton("Candidate2", false); 
        radioButton3 = new JRadioButton("Candidate3", false); 
        */
        /*typeArea = new JTextArea("Not entered text", 1, 20); 
        typeArea.setEditable(false); */
        
        /*String[] status = { "Available", "Away", "Busy", "Idle" };
        comboBox = new JComboBox(status);
        comboBox.setSelectedIndex(0);
        comboBox.addActionListener(this);*/
        
        /*timer = new Timer(1000, this); 
        timer.start(); */

        //Add Components to this panel.
        GridBagLayout gridBag = new GridBagLayout(); 
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.fill = GridBagConstraints.BOTH;
        c.gridheight = cName.length; 
        c.gridwidth = 2; 
        
        c.weightx = 1;
        c.weighty = 1;
        
        /*add(topicField, c);
        add(scrollPane, c);
        //add(typeArea, c);
        //add(textField, c); 
        add(comboBox, c); 
        add(radioButton1, c); 
        add(radioButton2, c);
        add(radioButton3, c);*/
        
        for (int i = 0; i<cName.length; i++){
        	c.anchor = GridBagConstraints.EAST; 
        	c.gridx = 0; 
        	c.gridy = i; 
        	c.gridwidth = 1; 
        	c.gridheight = 1; 
        	add(radioButton[i], c);
        	c.anchor = GridBagConstraints.WEST;
        	c.gridx = 1; 
        	c.gridy = i; 
        	c.gridwidth = 2; 
        	c.gridheight = 1; 
          add(jButton[i], c); 
          //c.gridwidth = GridBagConstraints.REMAINDER;
        }
        c.gridy++; 
        add(submitButton, c); 
        
    }
    
    

    public void actionPerformed(ActionEvent evt) {
    	//System.out.println(evt.getSource()); 
    	  String returnText = null; 
    		if (timer == evt.getSource()){
    			String newText = textField.getText(); 
    			//System.out.println("This is the text " + text);
    			//System.out.println("This is the text " + prevText); 
    			if (newText.equals(prevText)){
    				if (prevText.equals("") || prevText.equals("Enter msg here"))
    					typeArea.setText("Not entered text"); 
    				else 
    					typeArea.setText("Entered text but not typing");	
    			}
    			else 
    				typeArea.setText("Typing");
    			prevText = newText; 
    			returnText = typeArea.getText();  
    		}
    		
    		if (textField == evt.getSource()){
    			String text = textField.getText();
        	textArea.append(text + newline);
        	textField.setText("");
        	//typeArea.setText("Not entered text");
        	returnText = text; 
        }
    		
    		if (comboBox == evt.getSource()){ //if (!("comboBoxChanged".equals(evt.getActionCommand()))){ 
    			String text = (String) comboBox.getSelectedItem();   
    			textArea.append("User is " + text + newline); 
    			returnText = text; 
    		}

        //Make sure the new text is visible, even if there
        //was a selection in the text area.
        textArea.setCaretPosition(textArea.getDocument().getLength()); 
    }
    

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    private void createAndShowGUI() { 
        //Create and set up the window.
        JFrame frame = new JFrame(userName);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add contents to the window.
        frame.add(this);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public void create() {
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}