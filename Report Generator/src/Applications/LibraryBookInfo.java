package Applications;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class LibraryBookInfo extends javax.swing.JFrame {

    Connection DBCon;

    /* Creates new form Library */
    public LibraryBookInfo() throws SQLException {
        initComponents();

        //Sets whether this frame is resizable by the user.
        this.setResizable(false);

        //Call Driver
        DBCon = DatabaseConnection.ClsDatabaseConnection.methodDatabaseConnection();

        autoBookID();
        restoreClass();  //Restore data from database
    }

    public void autoBookID() throws SQLException {
        String SQLStr = "SELECT COALESCE(MAX(BookID), 0) AS BookID FROM book_info";
        PreparedStatement PreSta = DBCon.prepareStatement(SQLStr);
        ResultSet RS = PreSta.executeQuery();
        while (RS.next()) {
            String ID = RS.getString("BookID");
            int IID = Integer.parseInt(ID) + 1;
            String BID = Integer.toString(IID);
            jTextFieldBookID.setText(BID);
        }
    }

    public void restoreClass() throws SQLException {
        String SQLStr = "SELECT DISTINCT(Class) AS Class FROM student_info";
        PreparedStatement PreSta = DBCon.prepareStatement(SQLStr);
        ResultSet resultQuery = PreSta.executeQuery();
        while (resultQuery.next()) {
            jComboBoxClass.addItem(resultQuery.getString("Class"));
        }
    }

    public void StoreMethod() throws SQLException {
        if ("".equals(jTextFieldBookID.getText())) {
            JOptionPane.showMessageDialog(this, "Miss Book ID");
        } else if ("".equals(jTextFieldBookTitle.getText())) {
            JOptionPane.showMessageDialog(this, "Miss Book Title");
        } else if ("<Select>".equals(jComboBoxClass.getSelectedItem())) {
            JOptionPane.showMessageDialog(this, "Miss Class");
        } else if ("<Select>".equals(jComboBoxEdition.getSelectedItem())) {
            JOptionPane.showMessageDialog(this, "Miss Edition");
        } else if ("".equals(jTextFieldAuthor.getText())) {
            JOptionPane.showMessageDialog(this, "Miss Author");
        } else if ("".equals(jTextFieldPublication.getText())) {
            JOptionPane.showMessageDialog(this, "Miss Publication");
        } else if ("".equals(jTextFieldISBN.getText())) {
            JOptionPane.showMessageDialog(this, "Miss ISBN");
        } else if ("".equals(jTextFieldEntryDate.getText())) {
            JOptionPane.showMessageDialog(this, "Miss Date of Entry");
        } else {
            String str = "INSERT INTO book_info(BookID,BookTitle,Class,Edition,Author,Publication,ISBN,EntryDate) VALUES(?,?,?,?,?,?,?,?)";
            PreparedStatement PS = DBCon.prepareStatement(str);
            PS.setString(1, jTextFieldBookID.getText());
            PS.setString(2, jTextFieldBookTitle.getText());
            PS.setString(3, (String) jComboBoxClass.getSelectedItem());
            PS.setString(4, (String) jComboBoxEdition.getSelectedItem());
            PS.setString(5, jTextFieldAuthor.getText());
            PS.setString(6, jTextFieldPublication.getText());
            PS.setString(7, jTextFieldISBN.getText());
            PS.setString(8, jTextFieldEntryDate.getText());

            int i = PS.executeUpdate();
            if (i != 0) {
                JOptionPane.showMessageDialog(this, "Information Stored");
            } else {
                JOptionPane.showMessageDialog(this, "Problem to Store !!!");
            }
        }
    }

    public void SearchMethod() throws SQLException {
        String str = "SELECT * FROM book_info WHERE BookID = ?";
        PreparedStatement PS = DBCon.prepareStatement(str);
        PS.setString(1, jTextFieldSearch.getText());

        ResultSet RS = PS.executeQuery();
        while (RS.next()) {
            jTextFieldBookID.setText(RS.getString("BookID"));
            jTextFieldBookTitle.setText(RS.getString("BookTitle"));
            jComboBoxClass.setSelectedItem(RS.getString("Class"));
            jComboBoxEdition.setSelectedItem(RS.getString("Edition"));
            jTextFieldAuthor.setText(RS.getString("Author"));
            jTextFieldPublication.setText(RS.getString("Publication"));
            jTextFieldISBN.setText(RS.getString("ISBN"));
            jTextFieldEntryDate.setText(RS.getString("EntryDate"));
        }
    }

    public void UpdateMethod() throws SQLException {
        if ("".equals(jTextFieldBookID.getText())) {
            JOptionPane.showMessageDialog(this, "Miss Book ID");
        } else if ("".equals(jTextFieldBookTitle.getText())) {
            JOptionPane.showMessageDialog(this, "Miss Book Title");
        } else if ("<Select>".equals(jComboBoxClass.getSelectedItem())) {
            JOptionPane.showMessageDialog(this, "Miss Class");
        } else if ("<Select>".equals(jComboBoxEdition.getSelectedItem())) {
            JOptionPane.showMessageDialog(this, "Miss Edition");
        } else if ("".equals(jTextFieldAuthor.getText())) {
            JOptionPane.showMessageDialog(this, "Miss Author");
        } else if ("".equals(jTextFieldPublication.getText())) {
            JOptionPane.showMessageDialog(this, "Miss Publication");
        } else if ("".equals(jTextFieldISBN.getText())) {
            JOptionPane.showMessageDialog(this, "Miss ISBN");
        } else if ("".equals(jTextFieldEntryDate.getText())) {
            JOptionPane.showMessageDialog(this, "Miss Date of Entry");
        } else {
            String str = "UPDATE book_info SET BookID=?,BookTitle=?,Class=?,Edition=?,Author=?,Publication=?,ISBN=?,EntryDate=?";
            PreparedStatement PS = DBCon.prepareStatement(str);
            PS.setString(1, jTextFieldBookID.getText());
            PS.setString(2, jTextFieldBookTitle.getText());
            PS.setString(3, (String) jComboBoxClass.getSelectedItem());
            PS.setString(4, (String) jComboBoxEdition.getSelectedItem());
            PS.setString(5, jTextFieldAuthor.getText());
            PS.setString(6, jTextFieldPublication.getText());
            PS.setString(7, jTextFieldISBN.getText());
            PS.setString(8, jTextFieldEntryDate.getText());

            int i = PS.executeUpdate();
            if (i != 0) {
                JOptionPane.showMessageDialog(this, "Update Complete ...");
            } else {
                JOptionPane.showMessageDialog(this, "Problem In Updating !!!");
            }
        }
    }

    public void DeleteMethod() throws SQLException {
        if ("".equals(jTextFieldBookID.getText())) {
            JOptionPane.showMessageDialog(this, "Miss Book ID");
        } else if ("".equals(jTextFieldBookTitle.getText())) {
            JOptionPane.showMessageDialog(this, "Miss Book Title");
        } else if ("<Select>".equals(jComboBoxClass.getSelectedItem())) {
            JOptionPane.showMessageDialog(this, "Miss Class");
        } else if ("<Select>".equals(jComboBoxEdition.getSelectedItem())) {
            JOptionPane.showMessageDialog(this, "Miss Edition");
        } else if ("".equals(jTextFieldAuthor.getText())) {
            JOptionPane.showMessageDialog(this, "Miss Author");
        } else if ("".equals(jTextFieldPublication.getText())) {
            JOptionPane.showMessageDialog(this, "Miss Publication");
        } else if ("".equals(jTextFieldISBN.getText())) {
            JOptionPane.showMessageDialog(this, "Miss ISBN");
        } else if ("".equals(jTextFieldEntryDate.getText())) {
            JOptionPane.showMessageDialog(this, "Miss Date of Entry");
        } else {
            String str = "DELETE FROM book_info WHERE BookID=?";
            PreparedStatement PS = DBCon.prepareStatement(str);
            PS.setString(1, jTextFieldSearch.getText());

            int i = PS.executeUpdate();
            if (i != 0) {
                JOptionPane.showMessageDialog(this, "Delete Completed.");
            } else {
                JOptionPane.showMessageDialog(this, "Problem Ocuur :(");
            }
        }
    }

    public void RefreshMethod() {
        jTextFieldBookID.setText("");
        jTextFieldBookTitle.setText(null);
        jComboBoxClass.setSelectedItem("<Select>");
        jComboBoxEdition.setSelectedItem("<Select>");
        jTextFieldAuthor.setText("");
        jTextFieldPublication.setText(null);
        jTextFieldISBN.setText("");
        jTextFieldSearch.setText(null);
        jTextFieldEntryDate.setText(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelBookInfo = new javax.swing.JPanel();
        jLabelBookInfo = new javax.swing.JLabel();
        jPanelInfo = new javax.swing.JPanel();
        jTextFieldISBN = new javax.swing.JTextField();
        jTextFieldPublication = new javax.swing.JTextField();
        jTextFieldAuthor = new javax.swing.JTextField();
        jTextFieldBookID = new javax.swing.JTextField();
        jLabelAuthor = new javax.swing.JLabel();
        jLabelISBN = new javax.swing.JLabel();
        jLabelEdition = new javax.swing.JLabel();
        jTextFieldBookTitle = new javax.swing.JTextField();
        jLabelBookTitle = new javax.swing.JLabel();
        jLabelBookID = new javax.swing.JLabel();
        jLabelPublication = new javax.swing.JLabel();
        jComboBoxEdition = new javax.swing.JComboBox<>();
        jLabelClass = new javax.swing.JLabel();
        jComboBoxClass = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldEntryDate = new javax.swing.JTextField();
        jPanelControl = new javax.swing.JPanel();
        jButtonStore = new javax.swing.JButton();
        jButtonRefresh = new javax.swing.JButton();
        jButtonDelete = new javax.swing.JButton();
        jButtonUpdate = new javax.swing.JButton();
        jPanelSearchBox = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jTextFieldSearch = new javax.swing.JTextField();
        jButtonSearch = new javax.swing.JButton();
        jButtonExit = new javax.swing.JButton();
        jButtonBorrowInfo = new javax.swing.JButton();
        jButtonPurchaseInfo = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanelBookInfo.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder()));

        jLabelBookInfo.setFont(new java.awt.Font("Lucida Calligraphy", 1, 24)); // NOI18N
        jLabelBookInfo.setForeground(new java.awt.Color(192, 0, 0));
        jLabelBookInfo.setText("Book Information");

        javax.swing.GroupLayout jPanelBookInfoLayout = new javax.swing.GroupLayout(jPanelBookInfo);
        jPanelBookInfo.setLayout(jPanelBookInfoLayout);
        jPanelBookInfoLayout.setHorizontalGroup(
            jPanelBookInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBookInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelBookInfo)
                .addContainerGap())
        );
        jPanelBookInfoLayout.setVerticalGroup(
            jPanelBookInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBookInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelBookInfo)
                .addContainerGap())
        );

        jPanelInfo.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Info Collection", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Console", 0, 12))); // NOI18N
        jPanelInfo.setToolTipText("Information");

        jTextFieldISBN.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N

        jTextFieldPublication.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N

        jTextFieldAuthor.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N

        jTextFieldBookID.setEditable(false);
        jTextFieldBookID.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N

        jLabelAuthor.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jLabelAuthor.setText("Author");

        jLabelISBN.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jLabelISBN.setText("ISBN");

        jLabelEdition.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jLabelEdition.setText("Edition");

        jTextFieldBookTitle.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N

        jLabelBookTitle.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jLabelBookTitle.setText("Book Title");

        jLabelBookID.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jLabelBookID.setText("Book ID");

        jLabelPublication.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jLabelPublication.setText("Publication");

        jComboBoxEdition.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jComboBoxEdition.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "<Select>", "1st", "2nd", "3rd", "4th", "5th", "6th", "7th", "8th", "9th", "10th", "11th", "12th", "13th", "14th", "15th", "16th", "17th", "18th", "19th", "20th", "21th", "22th", "23th", "24th", "25th", "26th", "27th", "28th", "29th", "30th", "31th", "32th", "33th", "34th", "35th", "36th", "37th", "38th", "39th", "40th", "41th", "42th", "43th", "44th", "45th", "46th", "47th", "48th", "49th", "50th" }));

        jLabelClass.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jLabelClass.setText("Class");

        jComboBoxClass.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jComboBoxClass.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "<Select>" }));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jLabel1.setText("Entry Data (yyyy/mm/dd)");

        jTextFieldEntryDate.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N

        javax.swing.GroupLayout jPanelInfoLayout = new javax.swing.GroupLayout(jPanelInfo);
        jPanelInfo.setLayout(jPanelInfoLayout);
        jPanelInfoLayout.setHorizontalGroup(
            jPanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelBookID)
                    .addComponent(jLabelBookTitle)
                    .addComponent(jLabelEdition)
                    .addComponent(jLabelAuthor)
                    .addComponent(jLabelPublication)
                    .addComponent(jLabelISBN)
                    .addComponent(jLabelClass)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextFieldBookID)
                    .addComponent(jTextFieldBookTitle)
                    .addComponent(jTextFieldPublication)
                    .addComponent(jTextFieldISBN)
                    .addComponent(jComboBoxEdition, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextFieldAuthor)
                    .addComponent(jComboBoxClass, 0, 256, Short.MAX_VALUE)
                    .addComponent(jTextFieldEntryDate))
                .addGap(19, 19, 19))
        );
        jPanelInfoLayout.setVerticalGroup(
            jPanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabelBookID)
                    .addComponent(jTextFieldBookID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabelBookTitle)
                    .addComponent(jTextFieldBookTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabelClass)
                    .addComponent(jComboBoxClass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabelEdition)
                    .addComponent(jComboBoxEdition, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabelAuthor)
                    .addComponent(jTextFieldAuthor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabelPublication)
                    .addComponent(jTextFieldPublication, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabelISBN)
                    .addComponent(jTextFieldISBN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldEntryDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelControl.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Control", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Console", 0, 12))); // NOI18N
        jPanelControl.setToolTipText("Control");

        jButtonStore.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jButtonStore.setText("Store");
        jButtonStore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonStoreActionPerformed(evt);
            }
        });

        jButtonRefresh.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jButtonRefresh.setText("Refresh");
        jButtonRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRefreshActionPerformed(evt);
            }
        });

        jButtonDelete.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jButtonDelete.setText("Delete");
        jButtonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteActionPerformed(evt);
            }
        });

        jButtonUpdate.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jButtonUpdate.setText("Update");
        jButtonUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUpdateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelControlLayout = new javax.swing.GroupLayout(jPanelControl);
        jPanelControl.setLayout(jPanelControlLayout);
        jPanelControlLayout.setHorizontalGroup(
            jPanelControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelControlLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonStore, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jButtonUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(jButtonDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jButtonRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanelControlLayout.setVerticalGroup(
            jPanelControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelControlLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonStore)
                    .addComponent(jButtonUpdate)
                    .addComponent(jButtonDelete)
                    .addComponent(jButtonRefresh))
                .addContainerGap())
        );

        jPanelSearchBox.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Search", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Console", 0, 12))); // NOI18N
        jPanelSearchBox.setToolTipText("Search Box");

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder()));

        jLabel8.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jLabel8.setText("Book ID");

        jTextFieldSearch.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jTextFieldSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldSearchKeyReleased(evt);
            }
        });

        jButtonSearch.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jButtonSearch.setText("Search");
        jButtonSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel8)
                    .addComponent(jTextFieldSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addComponent(jButtonSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextFieldSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonSearch)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanelSearchBoxLayout = new javax.swing.GroupLayout(jPanelSearchBox);
        jPanelSearchBox.setLayout(jPanelSearchBoxLayout);
        jPanelSearchBoxLayout.setHorizontalGroup(
            jPanelSearchBoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSearchBoxLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelSearchBoxLayout.setVerticalGroup(
            jPanelSearchBoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSearchBoxLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButtonExit.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jButtonExit.setText("E X I T");
        jButtonExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExitActionPerformed(evt);
            }
        });

        jButtonBorrowInfo.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        jButtonBorrowInfo.setText("Book Borrow Info");
        jButtonBorrowInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBorrowInfoActionPerformed(evt);
            }
        });

        jButtonPurchaseInfo.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        jButtonPurchaseInfo.setText("Book Purchase Info");
        jButtonPurchaseInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPurchaseInfoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanelControl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanelInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jPanelSearchBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jButtonPurchaseInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButtonBorrowInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(3, 3, 3)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jButtonExit)
                                .addGap(109, 109, 109))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(273, 273, 273)
                        .addComponent(jPanelBookInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jPanelBookInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanelInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanelSearchBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)
                        .addComponent(jButtonExit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButtonBorrowInfo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonPurchaseInfo))
                    .addComponent(jPanelControl, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // Open "LibraryBookInfo" form in center.
        this.setLocationRelativeTo(null);
    }//GEN-LAST:event_formWindowOpened

    private void jButtonExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExitActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButtonExitActionPerformed

    private void jButtonRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRefreshActionPerformed
        try {
            RefreshMethod();
            autoBookID();
        } catch (SQLException ex) {
            Logger.getLogger(LibraryBookInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonRefreshActionPerformed

    private void jButtonStoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonStoreActionPerformed
        try {
            StoreMethod();  //Call method
        } catch (SQLException ex) {
            Logger.getLogger(LibraryBookInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonStoreActionPerformed

    private void jButtonSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSearchActionPerformed
        try {
            SearchMethod();
        } catch (SQLException ex) {
            Logger.getLogger(LibraryBookInfo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButtonSearchActionPerformed

    private void jButtonUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUpdateActionPerformed
        try {
            UpdateMethod();  //Call method
        } catch (SQLException ex) {
            Logger.getLogger(LibraryBookInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonUpdateActionPerformed

    private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteActionPerformed
        try {
            DeleteMethod();  //Call method
        } catch (SQLException ex) {
            Logger.getLogger(LibraryBookInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonDeleteActionPerformed

    private void jButtonBorrowInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBorrowInfoActionPerformed
        try {
            LibraryBookBorrow LBB = new LibraryBookBorrow();
            LBB.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(LibraryBookInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonBorrowInfoActionPerformed

    private void jButtonPurchaseInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPurchaseInfoActionPerformed
        try {
            LibraryBookPurchase LBP = new LibraryBookPurchase();
            LBP.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(LibraryBookInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonPurchaseInfoActionPerformed

    private void jTextFieldSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldSearchKeyReleased
        try {
            SearchMethod();
        } catch (SQLException ex) {
            Logger.getLogger(LibraryBookInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jTextFieldSearchKeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LibraryBookInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LibraryBookInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LibraryBookInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LibraryBookInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new LibraryBookInfo().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(LibraryBookInfo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonBorrowInfo;
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JButton jButtonExit;
    private javax.swing.JButton jButtonPurchaseInfo;
    private javax.swing.JButton jButtonRefresh;
    private javax.swing.JButton jButtonSearch;
    private javax.swing.JButton jButtonStore;
    private javax.swing.JButton jButtonUpdate;
    private javax.swing.JComboBox<String> jComboBoxClass;
    private javax.swing.JComboBox<String> jComboBoxEdition;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabelAuthor;
    private javax.swing.JLabel jLabelBookID;
    private javax.swing.JLabel jLabelBookInfo;
    private javax.swing.JLabel jLabelBookTitle;
    private javax.swing.JLabel jLabelClass;
    private javax.swing.JLabel jLabelEdition;
    private javax.swing.JLabel jLabelISBN;
    private javax.swing.JLabel jLabelPublication;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanelBookInfo;
    private javax.swing.JPanel jPanelControl;
    private javax.swing.JPanel jPanelInfo;
    private javax.swing.JPanel jPanelSearchBox;
    private javax.swing.JTextField jTextFieldAuthor;
    private javax.swing.JTextField jTextFieldBookID;
    private javax.swing.JTextField jTextFieldBookTitle;
    private javax.swing.JTextField jTextFieldEntryDate;
    private javax.swing.JTextField jTextFieldISBN;
    private javax.swing.JTextField jTextFieldPublication;
    private javax.swing.JTextField jTextFieldSearch;
    // End of variables declaration//GEN-END:variables
}
