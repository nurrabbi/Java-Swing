package Applications;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class LibraryBookBorrow extends javax.swing.JFrame {

    Connection DBCon;

    /* Creates new form ClassInformation */
    public LibraryBookBorrow() throws SQLException {
        initComponents();
        DBCon = DatabaseConnection.ClsDatabaseConnection.methodDatabaseConnection();

        //Sets whether this frame is resizable by the user.
        this.setResizable(false);
        
        AutoBrorrowID();
    }
    
    public void AutoBrorrowID() throws SQLException {
        String SQLStr = "SELECT COALESCE(MAX(BookID), 0) AS BookID FROM book_borrow";
        PreparedStatement PreSta = DBCon.prepareStatement(SQLStr);
        ResultSet RS = PreSta.executeQuery();
        while(RS.next()) {
            String ID = RS.getString("BookID");
            int IID = Integer.parseInt(ID)+1;
            String BBID = Integer.toString(IID);
            jTextFieldBorrowID.setText(BBID);
        }
    }
    
    public void RestoreStuName() throws SQLException {
        String str = "SELECT StuName from student_info WHERE StuID = ?";
        PreparedStatement PreSta = DBCon.prepareStatement(str);
        PreSta.setString(1, jTextFieldStuID.getText());
        
        ResultSet resultQuery = PreSta.executeQuery();
        while(resultQuery.next()) {
            jTextFieldStuName.setText(resultQuery.getString("StuName"));
        }
    }

    public void SaveMethod() throws SQLException {
        String str = "INSERT INTO book_borrow(BookID,BookTitle,StuID,StuName,BorrowDate,ReturnDate,NumTimes) VALUES(?,?,?,?,?,?,?)";
        PreparedStatement PS = DBCon.prepareStatement(str);
        PS.setString(1, jTextFieldBorrowID.getText());
        PS.setString(2, jTextFieldBookTitle.getText());
        PS.setString(3, jTextFieldStuID.getText());
        PS.setString(4, jTextFieldStuName.getText());
        PS.setString(5, jTextFieldBorrowDate.getText());
        PS.setString(6, jTextFieldReturnDate.getText());
        PS.setString(7, jTextFieldNoOFTimes.getText());

        int i = PS.executeUpdate();
        if (i != 0) {
            JOptionPane.showMessageDialog(this, "Saved ...");
        } else {
            JOptionPane.showMessageDialog(this, "Not Saved");
        }
    }
    
    public void SearchMethod() throws SQLException {
        String str = "SELECT * FROM book_borrow WHERE BookID = ?";
            PreparedStatement PS = DBCon.prepareStatement(str);
            PS.setString(1, jTextFieldSearch.getText());

            ResultSet RS = PS.executeQuery();
            while (RS.next()) {
                jTextFieldBorrowID.setText(RS.getString("BookID"));
                jTextFieldBookTitle.setText(RS.getString("BookTitle"));
                jTextFieldStuID.setText(RS.getString("StuID"));
                jTextFieldStuName.setText(RS.getString("StuName"));
                jTextFieldBorrowDate.setText(RS.getString("BorrowDate"));
                jTextFieldReturnDate.setText(RS.getString("ReturnDate"));
                jTextFieldNoOFTimes.setText(RS.getString("NumTimes"));
            }
    }

    public void UpdateMethod() throws SQLException {
        String str = "UPDATE book_borrow SET BookID=?,BookTitle=?,StuID=?,StuName=?,BorrowDate=?,ReturnDate=?,NumTimes=?";
        PreparedStatement PS = DBCon.prepareStatement(str);
        PS.setString(1, jTextFieldBorrowID.getText());
        PS.setString(2, jTextFieldBookTitle.getText());
        PS.setString(3, jTextFieldStuID.getText());
        PS.setString(4, jTextFieldStuName.getText());
        PS.setString(5, jTextFieldBorrowDate.getText());
        PS.setString(6, jTextFieldReturnDate.getText());
        PS.setString(7, jTextFieldNoOFTimes.getText());

        int i = PS.executeUpdate();
        if (i != 0) {
            JOptionPane.showMessageDialog(this, "Updated ...");
        } else {
            JOptionPane.showMessageDialog(this, "Not Updated !!!");
        }
    }

    public void DeleteMethod() {
        try {
            String str = "DELETE FROM book_borrow WHERE BookID = ?";
            PreparedStatement PS = DBCon.prepareStatement(str);
            PS.setString(1, jTextFieldSearch.getText());

            int i = PS.executeUpdate();
            if (i != 0) {
                JOptionPane.showMessageDialog(this, "Delete Completed.");
            } else {
                JOptionPane.showMessageDialog(this, "Problem Ocuur :(");
            }
        } catch (SQLException ex) {
            Logger.getLogger(LibraryBookInfo.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Problem Ocuurance");
        }
    }
    
    public void RefreshMethod() {
        jTextFieldBookTitle.setText("");
        jTextFieldStuID.setText("");
        jTextFieldStuName.setText("");
        jTextFieldBorrowDate.setText("");
        jTextFieldReturnDate.setText("");
        jTextFieldNoOFTimes.setText("");
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jTextFieldStuName = new javax.swing.JTextField();
        jTextFieldReturnDate = new javax.swing.JTextField();
        jLabelReturn_Date = new javax.swing.JLabel();
        jLabelBook_Title = new javax.swing.JLabel();
        jLabelStudent_Name = new javax.swing.JLabel();
        jLabelBook_ID = new javax.swing.JLabel();
        jTextFieldBorrowID = new javax.swing.JTextField();
        jTextFieldStuID = new javax.swing.JTextField();
        jLabelStudent_ID = new javax.swing.JLabel();
        jTextFieldBorrowDate = new javax.swing.JTextField();
        jLabelBorrow_Date = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldNoOFTimes = new javax.swing.JTextField();
        jTextFieldBookTitle = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jButtonSave = new javax.swing.JButton();
        jButtonRefresh = new javax.swing.JButton();
        jButtonDelete = new javax.swing.JButton();
        jButtonUpdate = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jTextFieldSearch = new javax.swing.JTextField();
        jButtonSearch = new javax.swing.JButton();
        jButtonExit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder()));

        jLabel1.setFont(new java.awt.Font("Lucida Calligraphy", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(192, 0, 0));
        jLabel1.setText("Borrow Information (Book)");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Borrow By", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Console", 0, 12))); // NOI18N
        jPanel2.setToolTipText("Borrow Info");

        jTextFieldStuName.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N

        jTextFieldReturnDate.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jTextFieldReturnDate.setToolTipText("dd/mm/yy");

        jLabelReturn_Date.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jLabelReturn_Date.setText("Return Date (yyyy-mm-dd)");

        jLabelBook_Title.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jLabelBook_Title.setText("Book Title");

        jLabelStudent_Name.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jLabelStudent_Name.setText("Student Name");

        jLabelBook_ID.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jLabelBook_ID.setText("Borrow ID");

        jTextFieldBorrowID.setEditable(false);
        jTextFieldBorrowID.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N

        jTextFieldStuID.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jTextFieldStuID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldStuIDKeyReleased(evt);
            }
        });

        jLabelStudent_ID.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jLabelStudent_ID.setText("Student ID");

        jTextFieldBorrowDate.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jTextFieldBorrowDate.setToolTipText("dd/mm/yy");

        jLabelBorrow_Date.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jLabelBorrow_Date.setText("Borrow Date (yyyy-mm-dd)");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jLabel6.setText("No. of times");

        jTextFieldNoOFTimes.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N

        jTextFieldBookTitle.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jTextFieldBookTitle.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldBookTitleKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelBook_ID)
                    .addComponent(jLabelBook_Title)
                    .addComponent(jLabelStudent_ID)
                    .addComponent(jLabelStudent_Name)
                    .addComponent(jLabelBorrow_Date)
                    .addComponent(jLabelReturn_Date)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextFieldBorrowID)
                    .addComponent(jTextFieldStuID)
                    .addComponent(jTextFieldStuName)
                    .addComponent(jTextFieldReturnDate)
                    .addComponent(jTextFieldBorrowDate)
                    .addComponent(jTextFieldNoOFTimes, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
                    .addComponent(jTextFieldBookTitle))
                .addGap(20, 20, 20))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabelBook_ID)
                    .addComponent(jTextFieldBorrowID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelBook_Title)
                    .addComponent(jTextFieldBookTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabelStudent_ID)
                    .addComponent(jTextFieldStuID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabelStudent_Name)
                    .addComponent(jTextFieldStuName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabelBorrow_Date)
                    .addComponent(jTextFieldBorrowDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabelReturn_Date)
                    .addComponent(jTextFieldReturnDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel6)
                    .addComponent(jTextFieldNoOFTimes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Control", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Console", 0, 12))); // NOI18N
        jPanel3.setToolTipText("Control");

        jButtonSave.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jButtonSave.setText("Save");
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(jButtonUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addComponent(jButtonDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(jButtonRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonSave)
                    .addComponent(jButtonUpdate)
                    .addComponent(jButtonDelete)
                    .addComponent(jButtonRefresh))
                .addContainerGap())
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Search", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Console", 0, 12))); // NOI18N
        jPanel7.setToolTipText("Search Box");

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

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButtonExit.setFont(new java.awt.Font("Lucida Calligraphy", 1, 24)); // NOI18N
        jButtonExit.setText("e x i t");
        jButtonExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jButtonExit)
                                .addGap(91, 91, 91))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(217, 217, 217)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56)
                        .addComponent(jButtonExit)))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExitActionPerformed
        // "e x i t" Button
        this.dispose();
    }//GEN-LAST:event_jButtonExitActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // Open this form in center
        this.setLocationRelativeTo(null);
    }//GEN-LAST:event_formWindowOpened

    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed
        try {
            SaveMethod();
        } catch (SQLException ex) {
            Logger.getLogger(LibraryBookBorrow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonSaveActionPerformed

    private void jButtonSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSearchActionPerformed
        try {
            SearchMethod();
        } catch (SQLException ex) {
            Logger.getLogger(LibraryBookBorrow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonSearchActionPerformed

    private void jButtonUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUpdateActionPerformed
        try {
            UpdateMethod();
        } catch (SQLException ex) {
            Logger.getLogger(LibraryBookBorrow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonUpdateActionPerformed

    private void jTextFieldBookTitleKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldBookTitleKeyReleased
    }//GEN-LAST:event_jTextFieldBookTitleKeyReleased

    private void jTextFieldStuIDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldStuIDKeyReleased
        try {
            RestoreStuName();
        } catch (SQLException ex) {
            Logger.getLogger(LibraryBookBorrow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jTextFieldStuIDKeyReleased

    private void jButtonRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRefreshActionPerformed
        try {
            RefreshMethod();
            AutoBrorrowID();
        } catch (SQLException ex) {
            Logger.getLogger(LibraryBookBorrow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonRefreshActionPerformed

    private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteActionPerformed
        DeleteMethod();
    }//GEN-LAST:event_jButtonDeleteActionPerformed

    private void jTextFieldSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldSearchKeyReleased
        try {
            SearchMethod();
        } catch (SQLException ex) {
            Logger.getLogger(LibraryBookBorrow.class.getName()).log(Level.SEVERE, null, ex);
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
            java.util.logging.Logger.getLogger(LibraryBookBorrow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LibraryBookBorrow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LibraryBookBorrow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LibraryBookBorrow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new LibraryBookBorrow().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(LibraryBookBorrow.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JButton jButtonExit;
    private javax.swing.JButton jButtonRefresh;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JButton jButtonSearch;
    private javax.swing.JButton jButtonUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabelBook_ID;
    private javax.swing.JLabel jLabelBook_Title;
    private javax.swing.JLabel jLabelBorrow_Date;
    private javax.swing.JLabel jLabelReturn_Date;
    private javax.swing.JLabel jLabelStudent_ID;
    private javax.swing.JLabel jLabelStudent_Name;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JTextField jTextFieldBookTitle;
    private javax.swing.JTextField jTextFieldBorrowDate;
    private javax.swing.JTextField jTextFieldBorrowID;
    private javax.swing.JTextField jTextFieldNoOFTimes;
    private javax.swing.JTextField jTextFieldReturnDate;
    private javax.swing.JTextField jTextFieldSearch;
    private javax.swing.JTextField jTextFieldStuID;
    private javax.swing.JTextField jTextFieldStuName;
    // End of variables declaration//GEN-END:variables
}
