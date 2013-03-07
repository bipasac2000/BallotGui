import java.awt.*;
import java.awt.event.*;
//import java.io.Serializable;
import javax.swing.*;


public class BallotGui extends JPanel implements ActionListener{
     
    private JRadioButton[] radioButton;
    private JButton[] jButton; 
    private JFrame[] popUp; 
    private JFrame ballotFrame;
    private JFrame authenticate;
    private JButton submitButton; 
    private JButton loginButton; 
    private JButton quitButton; 
    private int numCandidates; 
    private LocalBallot lb; 
    private String[] cName; 
    private String[] cInfo;
    private String voterId; 
    private JTextField typeField; 
    private JTextField passField; 
    private JLabel typeLabel;
    private JLabel passLabel;
    private JLabel loginError;
    private JLabel voteError;
    public GridBagConstraints c;

    public BallotGui(String name, String[] inputNames, String[] inputInfo) {
        super(new GridBagLayout());
        cName = inputNames;
        cInfo = inputInfo;
        numCandidates = cName.length; 
        
        ballotFrame = new JFrame("Ballot");
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
        voteError = new JLabel("     ");
        submitButton = new JButton("Submit"); 
        submitButton.addActionListener(this); 
        
        //initiate authentication components
        authenticate = new JFrame("Sign In");
        authenticate.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        typeField = new JTextField("", 20);  
        passField = new JTextField("",20);
        typeLabel = new JLabel("Enter your Voter ID: ");
        passLabel = new JLabel("Enter your Password: ");
        loginError = new JLabel("     ");
        loginButton = new JButton("Log In");
        loginButton.addActionListener(this);
        quitButton = new JButton("Exit and Tally Votes");
        quitButton.addActionListener(this);
        
        
    }
 
    public void actionPerformed(ActionEvent evt) {
        
	for (int i=0; i<numCandidates; i++){
	    if (jButton[i] == evt.getSource()) {
		//show candidate info
		popUp[i].add(new JLabel("Candidate " + (i+1) + " Info"));
		popUp[i].pack();
		popUp[i].setVisible(true);     
	    }
	}
	
	for (int i = 0; i<numCandidates; i++){
	    if (radioButton[i] == evt.getSource()){
		//select a candidate to vote for
		for (int j = 0; j<numCandidates; j++){
		    if (j!=i)
			radioButton[j].setSelected(false); 
		    else
			radioButton[j].setSelected(true); 
		    
		}
	    }
        }
        
        if(submitButton == evt.getSource()){
	    //make sure a candidate is actually selected
	    boolean checkSelected = false;
	    for (int i = 0; i<numCandidates; i++){
		if (radioButton[i].isSelected()) {
		    checkSelected = true;
		}
	    }
	    if (checkSelected) {
		//cast the vote, go back to the authentication form
		voterId = typeField.getText(); 
		for (int i = 0; i<numCandidates; i++){
		    if (radioButton[i].isSelected())
			lb.updateTable(voterId, cName[i]); 
		    radioButton[i].setSelected(false); 
		}
		typeField.setText("");
		passField.setText("");
		loginError.setText("   ");
		showAuthenticationForm();
	    } else {
		voteError.setText("Select a candidate");
	    }
        }
        
        if (loginButton == evt.getSource()) {
	    //make sure a voter ID has been entered
	    if (!typeField.getText().equals("")) {
		//voter ID has been entered, go to voting form
		authenticate.setVisible(false);
		voteError.setText("   ");
		showVotingForm();
	    } else {
		//a voter ID has not been entered, display error label
		loginError.setText("Voter ID field is blank");
		showAuthenticationForm();
	    }
        }
        if (quitButton == evt.getSource()) {
	    //quit the program and print the winner of the local election
	    lb.getResults();
	    System.exit(0);
        }
        
    }

    private void showVotingForm() {
	authenticate.setVisible(false);
	this.removeAll();
        //Add Components to this panel.
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
        }
        c.gridy++; 
        add(voteError, c);
        c.gridy++; 
        add(submitButton, c); 
        
        
        ballotFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add contents to the window.
        ballotFrame.add(this);

        //Display the window.
        ballotFrame.pack();
        ballotFrame.setVisible(true);
        
    }
    private void showAuthenticationForm() {
	ballotFrame.setVisible(false);
	this.removeAll();
	//Voter authentication
        GridBagConstraints ac = new GridBagConstraints();
        ac.gridheight = 2; 
        ac.gridwidth = 2; 
        
        ac.weightx = 1;
        ac.weighty = 1;
        ac.anchor = GridBagConstraints.EAST; 
	ac.gridx = 0; 
	ac.gridy = 0; 
	ac.gridwidth = 1; 
	ac.gridheight = 1; 
        add(typeLabel,ac);
        ac.gridx = 1; 
	ac.gridy = 0; 
	ac.gridwidth = 2; 
	ac.gridheight = 1; 
        add(typeField,ac);
        ac.gridx = 0; 
	ac.gridy = 1; 
	ac.gridwidth = 1; 
	ac.gridheight = 1; 
        add(passLabel,ac);
        ac.gridx = 1; 
	ac.gridy = 1; 
	ac.gridwidth = 2; 
	ac.gridheight = 1;
        add(passField,ac);
        ac.gridx = 0;
        ac.gridwidth = 1;
        ac.gridy++;
        add(loginError,ac);
        ac.gridy++;
        add(loginButton,ac);
        ac.gridx = 1; 
        add(quitButton,ac);
        authenticate.add(this);
        authenticate.pack();
        authenticate.validate();
        authenticate.setVisible(true);
    
    }
    private void createAndShowGUI() { 
        //Create and set up the window.
	showAuthenticationForm();
    }

    public void create(LocalBallot inputLb) {
        
	//create a voting form for a given local election
	lb = inputLb; 
	javax.swing.SwingUtilities.invokeLater(new Runnable() {
		public void run() {
		    createAndShowGUI();
		}
	    });
    }
}