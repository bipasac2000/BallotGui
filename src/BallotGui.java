
import java.awt.*;
import java.awt.event.*;
//import java.io.Serializable;
import javax.swing.*;


public class BallotGui extends JPanel implements ActionListener{
	 
    private JRadioButton[] radioButton;
    private JButton[] jButton; 
    private JFrame[] popUp; 
    private JButton submitButton; 
    private int numCandidates; 
    private LocalBallot lb; 
    private String[] cName; 
    private String voterId; 

    public BallotGui(String name, String[] inputNames, String[] cInfo) {
        super(new GridBagLayout());
        
        cName = inputNames;
        numCandidates = cName.length; 
        radioButton = new JRadioButton[numCandidates]; 
        jButton = new JButton[numCandidates];
        popUp = new JFrame[numCandidates]; 
        		
        for(int i =0; i<numCandidates; i++){
        	// Initialize radio buttons 
        	radioButton[i] = new JRadioButton(cName[i], false);
        	radioButton[i].addActionListener(this);
        	// Initialize information buttons
        	jButton[i] = new JButton("Get Information");
        	jButton[i].addActionListener(this); 
        	// Initialize popup frames
        	popUp[i] = new JFrame(cInfo[i]); 
        	popUp[i].setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        	popUp[i].setVisible(false); 
        }
        submitButton = new JButton("Submit"); 
        submitButton.addActionListener(this); 
        

        //Add Components to this panel.
        //GridBagLayout gridBag = new GridBagLayout(); 
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.fill = GridBagConstraints.BOTH;
        c.gridheight = cName.length; 
        c.gridwidth = 2; 
        
        c.weightx = 1;
        c.weighty = 1;
        
        for (int i = 0; i<numCandidates; i++){
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
        
    	for (int i=0; i<numCandidates; i++){
    		if (jButton[i] == evt.getSource())
    			popUp[i].setVisible(true); 
    	}
    	
    	for (int i = 0; i<numCandidates; i++){
        	if (radioButton[i] == evt.getSource()){
        		for (int j = 0; j<numCandidates; j++){
        			if (j!=i)
        				radioButton[j].setSelected(false); 
        			else
        				radioButton[j].setSelected(true); 
        				
        		}
        	}
        }
        
        if(submitButton == evt.getSource()){
        	
        	for (int i = 0; i<numCandidates; i++){
        		if (radioButton[i].isSelected())
        			lb.updateTable(voterId, cName[i]); 
        		radioButton[i].setSelected(false); 
        	}
        }
        	
    }

    
    private void createAndShowGUI() { 
        //Create and set up the window.
        JFrame frame = new JFrame("Ballot");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add contents to the window.
        frame.add(this);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public void create(LocalBallot inputLb) {
        
    	  lb = inputLb; 
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}