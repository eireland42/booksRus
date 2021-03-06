//BooksRUs Software

import java.sql.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;


//#########################################################
public class SearchJDialog extends JDialog
                           implements ActionListener,
                                      DocumentListener
{

Queries listOfQueries;

StoreFrame pointerToStoreFrame;

JButton searchButton;
JTextField searchTextField;
JComboBox searchComboBox;

    //=====================================================
    public SearchJDialog(StoreFrame pointerToStoreFrame)
    {
    this.pointerToStoreFrame = pointerToStoreFrame;

    Container cp;
    Toolkit tk;
    Dimension d;

    listOfQueries = new Queries();

    JButton cancelButton;

    JLabel searchComboBoxLabel;
    JLabel searchTextFieldLabel;

    JPanel mainPanel;
    JPanel searchComboBoxAndLabelPanel;
    JPanel searchTextFieldAndLabelPanel;
    JPanel buttonPanel;

    searchButton = new JButton("Search");
    searchButton.setActionCommand("SEARCH");
    searchButton.addActionListener(this);
    searchButton.setToolTipText("Begin the search.");
    searchButton.setEnabled(false);

    cancelButton = new JButton("Cancel");
    cancelButton.setActionCommand("CANCEL");
    cancelButton.addActionListener(this);
    cancelButton.setToolTipText("Close the search window.");

    buttonPanel = new JPanel();
    buttonPanel.add(searchButton);
    buttonPanel.add(cancelButton);

    searchComboBoxLabel = new JLabel("Search By:");

    searchTextFieldLabel = new JLabel("Keywords:");

    searchComboBox = new JComboBox();
    searchComboBox.addItem("DVD Title");
    searchComboBox.addItem("Director Name");
    searchComboBox.addItem("Cast Member Name");
    searchComboBox.addItem("Genre");
    searchComboBox.addItem("DVD Keyword");
    searchComboBox.addItem("Sequel");
    searchComboBox.addItem("Book Title");
    searchComboBox.addItem("Author Name");
    searchComboBox.addItem("Publisher Name");
    searchComboBox.addItem("Book Catagory");
    searchComboBox.addItem("Book Keyword");

    searchTextField = new JTextField(30);
    searchTextField.getDocument().addDocumentListener(this);

    searchComboBoxAndLabelPanel = new JPanel(new BorderLayout());
    searchComboBoxAndLabelPanel.add(searchComboBoxLabel, BorderLayout.CENTER);
    searchComboBoxAndLabelPanel.add(searchComboBox, BorderLayout.SOUTH);

    searchTextFieldAndLabelPanel = new JPanel(new BorderLayout());
    searchTextFieldAndLabelPanel.add(searchTextFieldLabel, BorderLayout.CENTER);
    searchTextFieldAndLabelPanel.add(searchTextField, BorderLayout.SOUTH);

    mainPanel = new JPanel();
    mainPanel.add(searchTextFieldAndLabelPanel);
    mainPanel.add(searchComboBoxAndLabelPanel);

    cp = getContentPane();
    cp.add(mainPanel, BorderLayout.CENTER);
    cp.add(buttonPanel, BorderLayout.SOUTH);

    setTitle("Search Panel");
    setModalityType(Dialog.ModalityType.APPLICATION_MODAL);

    tk = Toolkit.getDefaultToolkit();
    d = tk.getScreenSize();
    setSize(d.width/4, d.height/8);
    setLocation(d.width/3, d.height/3);

    d.setSize(500, 100);
    setMinimumSize(d);

    setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

    getRootPane().setDefaultButton(searchButton);
    searchTextField.requestFocus();

    setVisible(true);

    }
    //=====================================================
    public void actionPerformed(ActionEvent e)
    {

    if (e.getActionCommand().equals("CANCEL"))
        {
        dispose();
        }
    else if (e.getActionCommand().equals("SEARCH"))
        {
        PreparedStatement preparedStatement;
        Object comboObject;
        String searchString;
        Connection connection;
        ResultSet resultSet;

        connection = pointerToStoreFrame.connection;

        searchString = searchTextField.getText().trim();    //get what the user wants to search for

        comboObject = searchComboBox.getSelectedItem();     //get what they want to search in from the combo box
                                                            //and relate it to the Queries data members
        resultSet = null;
        try
            {

            if (comboObject.toString().equals("DVD Title"))
                {
                preparedStatement = connection.prepareStatement((String)listOfQueries.title_DVDs_Search);
                preparedStatement.clearParameters();
                preparedStatement.setString(1, searchString);
                resultSet = preparedStatement.executeQuery();
                }
            else if (comboObject.toString().equals("Director Name"))
                {
                preparedStatement = connection.prepareStatement((String)listOfQueries.director_Search);
                preparedStatement.clearParameters();
                preparedStatement.setString(1, searchString);
                resultSet = preparedStatement.executeQuery();
                }
            else if (comboObject.toString().equals("Cast Member Name"))
                {
                preparedStatement = connection.prepareStatement((String)listOfQueries.cast_Search);
                preparedStatement.clearParameters();
                preparedStatement.setString(1, searchString);
                resultSet = preparedStatement.executeQuery();
                }
            else if (comboObject.toString().equals("Genre"))
                {
                preparedStatement = connection.prepareStatement((String)listOfQueries.genre_Search);
                preparedStatement.clearParameters();
                preparedStatement.setString(1, searchString);
                resultSet = preparedStatement.executeQuery();
                }
            else if (comboObject.toString().equals("DVD Keyword"))
                {
                preparedStatement = connection.prepareStatement((String)listOfQueries.keyword_DVDs_Search);
                preparedStatement.clearParameters();
                preparedStatement.setString(1, searchString);
                resultSet = preparedStatement.executeQuery();
                }
            else if (comboObject.toString().equals("Sequel"))
                {
                preparedStatement = connection.prepareStatement((String)listOfQueries.sequel_Search);
                preparedStatement.clearParameters();
                preparedStatement.setString(1, searchString);
                resultSet = preparedStatement.executeQuery();
                }
            else if (comboObject.toString().equals("Book Title"))
                {
                preparedStatement = connection.prepareStatement((String)listOfQueries.title_Books_Search);
                preparedStatement.clearParameters();
                preparedStatement.setString(1, searchString);
                resultSet = preparedStatement.executeQuery();
                }
            else if (comboObject.toString().equals("Author Name"))
                {
                preparedStatement = connection.prepareStatement((String)listOfQueries.author_Search);
                preparedStatement.clearParameters();
                preparedStatement.setString(1, searchString);
                resultSet = preparedStatement.executeQuery();
                }
            else if (comboObject.toString().equals("Publisher Name"))
                {
                preparedStatement = connection.prepareStatement((String)listOfQueries.publisher_Search);
                preparedStatement.clearParameters();
                preparedStatement.setString(1, searchString);
                resultSet = preparedStatement.executeQuery();
                }
            else if (comboObject.toString().equals("Book Category"))
                {
                preparedStatement = connection.prepareStatement((String)listOfQueries.subject_Cate_Search);
                preparedStatement.clearParameters();
                preparedStatement.setString(1, searchString);
                resultSet = preparedStatement.executeQuery();
                }
            else if (comboObject.toString().equals("Book Keyword"))
                {
                preparedStatement = connection.prepareStatement((String)listOfQueries.keyword_Books_Search);
                preparedStatement.clearParameters();
                preparedStatement.setString(1, searchString);
                resultSet = preparedStatement.executeQuery();
                }

            if (resultSet != null)
                {
                pointerToStoreFrame.updateResultTable(resultSet);   //sending the resultSet to StoreFrame to be displayed
                }
            }
        catch (SQLException sqle)
            {
            System.out.println("SQLException in SearchJDialog actionPerformed");

            sqle.printStackTrace();
            }
        }

    }
    //=====================================================
    public void changedUpdate(DocumentEvent e)
    {
    //do nothing
    }
    //=====================================================
    public void removeUpdate(DocumentEvent e)
    {
    if (searchTextField.getText().trim().equals(""))
        {
        searchButton.setEnabled(false);
        }
    else
        {
        searchButton.setEnabled(true);
        }
    }
    //=====================================================
    public void insertUpdate(DocumentEvent e)
    {
    if (searchTextField.getText().trim().equals(""))
        {
        searchButton.setEnabled(false);
        }
    else
        {
        searchButton.setEnabled(true);
        }
    }
    //=====================================================

}
//#########################################################