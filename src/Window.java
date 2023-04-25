import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window extends JFrame implements ActionListener {


    JButton registerButton;
    JButton addBookButton;

    JButton loanButton;

    JButton returnButton;

    public Window(){

        //Adding buttons for the GUI menu
        registerButton = new JButton();
        registerButton.setBounds(200,400, 150,50);
        registerButton.addActionListener(this);
        registerButton.setText("Register member");

        addBookButton = new JButton();
        addBookButton.setBounds(200,300, 150,50);
        addBookButton.addActionListener(this);
        addBookButton.setText("Add book");

        loanButton = new JButton();
        loanButton.setBounds(200,200, 150,50);
        loanButton.addActionListener(this);
        loanButton.setText("Loan book");

        returnButton = new JButton();
        returnButton.setBounds(200,100, 150,50);
        returnButton.addActionListener(this);
        returnButton.setText("Return book");

        this.setTitle("Library");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(500,500);
        this.setVisible(true);
        this.add(registerButton);
        this.add(addBookButton);
        this.add(loanButton);
        this.add(returnButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == registerButton){
            Library.addMember();
        }
        else if(e.getSource() == addBookButton){
            Library.addBook();
        }
        else if(e.getSource() == loanButton){

        }
        else if(e.getSource() == returnButton){

        }
    }
}