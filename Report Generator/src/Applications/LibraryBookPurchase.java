package Applications;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class LibraryBookPurchase extends javax.swing.JFrame {

    Connection DBCon;

    /* Creates new form LibraryBookPurchase */
    public LibraryBookPurchase() throws SQLException {
        initComponents();
        this.setResizable(false);  //Sets whether this frame is resizable by the user

        DBCon = DatabaseConnection.ClsDatabaseConnection.methodDatabaseConnection();
        autoPurID();
        retriveBookTitle();
    }

    public void autoPurID() throws SQLException {
        String sqlSTR = "SELECT COALESCE(MAX(purID), 0) AS purID FROM book_purchase";
        PreparedStatement PS = DBCon.prepareStatement(sqlSTR);
        ResultSet RS = PS.executeQuery();
        while (RS.next()) {
            String ID = RS.getString("purID");
            int IID = Integer.parseInt(ID) + 1;
            String PID = Integer.toString(IID);
            jTextFieldPurID.setText(PID);
        }
    }

    public void retriveBookTitle() throws SQLException {
        String sqlSTR = "SELECT DISTINCT(BookTitle) AS BookTitle FROM book_info";
        PreparedStatement PS = DBCon.prepareStatement(sqlSTR);
        ResultSet RS = PS.executeQuery();
        while (RS.next()) {
            jComboBoxBookTitle.addItem(RS.getString("BookTitle"));
        }
    }

    public void retriveEdition() throws SQLException {
        String sqlSTR = "SELECT DISTINCT(Edition) AS E FROM book_info WHERE BookTitle=?";
        PreparedStatement PS = DBCon.prepareStatement(sqlSTR);
        PS.setString(1, jComboBoxBookTitle.getSelectedItem().toString());
        ResultSet RS = PS.executeQuery();
        while (RS.next()) {
            jComboBoxEdition.addItem(RS.getString("E"));
        }
    }

    public void retriveAuthorPublicISBN() throws SQLException {
        String sqlSTR = "SELECT Author, Publication, ISBN FROM book_info WHERE BookTitle=? AND Edition=?";
        PreparedStatement PS = DBCon.prepareStatement(sqlSTR);
        PS.setString(1, jComboBoxBookTitle.getSelectedItem().toString());
        PS.setString(2, jComboBoxEdition.getSelectedItem().toString());
        ResultSet RS = PS.executeQuery();
        while (RS.next()) {
            jTextFieldAuthor.setText(RS.getString("Author"));
            jTextFieldPublication.setText(RS.getString("Publication"));
            jTextFieldISBN.setText(RS.getString("ISBN"));
        }
    }

    public void SaveMethod() throws SQLException {
        if ("".equals(jTextFieldPurID.getText())) {
            JOptionPane.showMessageDialog(this, "Missing Purchase ID!!!");
        } else if ("<Select>".equals(jComboBoxBookTitle.getSelectedItem().toString())) {
            JOptionPane.showMessageDialog(this, "Missing Book Title!!!");
        } else if ("<Select>".equals(jComboBoxEdition.getSelectedItem().toString())) {
            JOptionPane.showMessageDialog(this, "Missing Book Edition!!!");
        } else if ("".equals(jTextFieldAuthor.getText())) {
            JOptionPane.showMessageDialog(this, "Missing Book Author!!!");
        } else if ("".equals(jTextFieldPublication.getText())) {
            JOptionPane.showMessageDialog(this, "Missing Publication Name!!!");
        } else if ("".equals(jTextFieldISBN.getText())) {
            JOptionPane.showMessageDialog(this, "Missing ISBN Number!!!");
        } else if ("".equals(jTextFieldPrice.getText())) {
            JOptionPane.showMessageDialog(this, "Missing Book Price!!!");
        } else if ("".equals(jTextFieldvat.getText())) {
            JOptionPane.showMessageDialog(this, "Missing Vat!!!");
        } else if ("".equals(jTextFieldDiscount.getText())) {
            JOptionPane.showMessageDialog(this, "Missing Discount!!!");
        } else if ("".equals(jTextFieldNoItems.getText())) {
            JOptionPane.showMessageDialog(this, "Missing no. of items!!!");
        } else if ("".equals(jTextFieldNetAmnt.getText())) {
            JOptionPane.showMessageDialog(this, "Missing Net Amount!!!");
        } else if ("".equals(jTextFieldPurDate.getText())) {
            JOptionPane.showMessageDialog(this, "Missing Purchase Date!!!");
        } else {
            String SQLStr = "INSERT INTO book_purchase(purID,BookTitle,Edition,Author,Publication,ISBN,price,vat,discount,itemNum,NetAmount,PurchaseDate) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement PreSta = DBCon.prepareStatement(SQLStr);
            PreSta.setString(1, jTextFieldPurID.getText());
            PreSta.setString(2, (String) jComboBoxBookTitle.getSelectedItem());
            PreSta.setString(3, (String) jComboBoxEdition.getSelectedItem());
            PreSta.setString(4, jTextFieldAuthor.getText());
            PreSta.setString(5, jTextFieldPublication.getText());
            PreSta.setString(6, jTextFieldISBN.getText());
            PreSta.setString(7, jTextFieldPrice.getText());
            PreSta.setString(8, jTextFieldvat.getText());
            PreSta.setString(9, jTextFieldDiscount.getText());
            PreSta.setString(10, jTextFieldNoItems.getText());
            PreSta.setString(11, jTextFieldNetAmnt.getText());
            PreSta.setString(12, jTextFieldPurDate.getText());

            int i = PreSta.executeUpdate();
            if (i != 0) {
                JOptionPane.showMessageDialog(this, "Saved :)");
            } else {
                JOptionPane.showMessageDialog(this, "Not Saved :(");
            }
        }
    }

    public void SearchMethod() throws SQLException {
        String SQLStr = "SELECT * FROM book_purchase WHERE purID=?";
        PreparedStatement PreSta = DBCon.prepareStatement(SQLStr);
        PreSta.setString(1, jTextFieldSearchPurID.getText());

        ResultSet resultQuery = PreSta.executeQuery();
        while (resultQuery.next()) {
            jTextFieldPurID.setText(resultQuery.getString("purID"));
            jComboBoxBookTitle.setSelectedItem(resultQuery.getString("BookTitle"));
            jComboBoxEdition.setSelectedItem(resultQuery.getString("Edition"));
            jTextFieldAuthor.setText(resultQuery.getString("Author"));
            jTextFieldPublication.setText(resultQuery.getString("Publication"));
            jTextFieldISBN.setText(resultQuery.getString("ISBN"));
            jTextFieldPrice.setText(resultQuery.getString("price"));
            jTextFieldvat.setText(resultQuery.getString("vat"));
            jTextFieldDiscount.setText(resultQuery.getString("discount"));
            jTextFieldNoItems.setText(resultQuery.getString("itemNum"));
            jTextFieldNetAmnt.setText(resultQuery.getString("NetAmount"));
            jTextFieldPurDate.setText(resultQuery.getString("PurchaseDate"));
        }
    }

    public void UpdateMethod() throws SQLException {
        if ("".equals(jTextFieldPurID.getText())) {
            JOptionPane.showMessageDialog(this, "Missing Purchase ID!!!");
        } else if ("<Select>".equals(jComboBoxBookTitle.getSelectedItem().toString())) {
            JOptionPane.showMessageDialog(this, "Missing Book Title!!!");
        } else if ("<Select>".equals(jComboBoxEdition.getSelectedItem().toString())) {
            JOptionPane.showMessageDialog(this, "Missing Book Edition!!!");
        } else if ("".equals(jTextFieldAuthor.getText())) {
            JOptionPane.showMessageDialog(this, "Missing Book Author!!!");
        } else if ("".equals(jTextFieldPublication.getText())) {
            JOptionPane.showMessageDialog(this, "Missing Publication Name!!!");
        } else if ("".equals(jTextFieldISBN.getText())) {
            JOptionPane.showMessageDialog(this, "Missing ISBN Number!!!");
        } else if ("".equals(jTextFieldPrice.getText())) {
            JOptionPane.showMessageDialog(this, "Missing Book Price!!!");
        } else if ("".equals(jTextFieldvat.getText())) {
            JOptionPane.showMessageDialog(this, "Missing Vat!!!");
        } else if ("".equals(jTextFieldDiscount.getText())) {
            JOptionPane.showMessageDialog(this, "Missing Discount!!!");
        } else if ("".equals(jTextFieldNoItems.getText())) {
            JOptionPane.showMessageDialog(this, "Missing no. of items!!!");
        } else if ("".equals(jTextFieldNetAmnt.getText())) {
            JOptionPane.showMessageDialog(this, "Missing Net Amount!!!");
        } else if ("".equals(jTextFieldPurDate.getText())) {
            JOptionPane.showMessageDialog(this, "Missing Purchase Date!!!");
        } else {
            String SQLStr = "UPDATE book_purchase SET purID=?,BookTitle=?,Edition=?,Author=?,Publication=?,ISBN=?,price=?,vat=?,discount=?,itemNum=?,NetAmount=?,PurchaseDate=? WHERE purID=?";
            PreparedStatement PreSta = DBCon.prepareStatement(SQLStr);
            PreSta.setString(1, jTextFieldPurID.getText());
            PreSta.setString(2, (String) jComboBoxBookTitle.getSelectedItem());
            PreSta.setString(3, (String) jComboBoxEdition.getSelectedItem());
            PreSta.setString(4, jTextFieldAuthor.getText());
            PreSta.setString(5, jTextFieldPublication.getText());
            PreSta.setString(6, jTextFieldISBN.getText());
            PreSta.setString(7, jTextFieldPrice.getText());
            PreSta.setString(8, jTextFieldvat.getText());
            PreSta.setString(9, jTextFieldDiscount.getText());
            PreSta.setString(10, jTextFieldNoItems.getText());
            PreSta.setString(11, jTextFieldNetAmnt.getText());
            PreSta.setString(12, jTextFieldPurDate.getText());
            PreSta.setString(13, jTextFieldSearchPurID.getText());

            int i = PreSta.executeUpdate();
            if (i != 0) {
                JOptionPane.showMessageDialog(this, "Update Complete.");
            } else {
                JOptionPane.showMessageDialog(this, "Problem in updating.");
            }
        }
    }

    public void DeleteMethod() throws SQLException {
        if ("".equals(jTextFieldPurID.getText())) {
            JOptionPane.showMessageDialog(this, "Missing Purchase ID!!!");
        } else if ("<Select>".equals(jComboBoxBookTitle.getSelectedItem().toString())) {
            JOptionPane.showMessageDialog(this, "Missing Book Title!!!");
        } else if ("<Select>".equals(jComboBoxEdition.getSelectedItem().toString())) {
            JOptionPane.showMessageDialog(this, "Missing Book Edition!!!");
        } else if ("".equals(jTextFieldAuthor.getText())) {
            JOptionPane.showMessageDialog(this, "Missing Book Author!!!");
        } else if ("".equals(jTextFieldPublication.getText())) {
            JOptionPane.showMessageDialog(this, "Missing Publication Name!!!");
        } else if ("".equals(jTextFieldISBN.getText())) {
            JOptionPane.showMessageDialog(this, "Missing ISBN Number!!!");
        } else if ("".equals(jTextFieldPrice.getText())) {
            JOptionPane.showMessageDialog(this, "Missing Book Price!!!");
        } else if ("".equals(jTextFieldvat.getText())) {
            JOptionPane.showMessageDialog(this, "Missing Vat!!!");
        } else if ("".equals(jTextFieldDiscount.getText())) {
            JOptionPane.showMessageDialog(this, "Missing Discount!!!");
        } else if ("".equals(jTextFieldNoItems.getText())) {
            JOptionPane.showMessageDialog(this, "Missing no. of items!!!");
        } else if ("".equals(jTextFieldNetAmnt.getText())) {
            JOptionPane.showMessageDialog(this, "Missing Net Amount!!!");
        } else if ("".equals(jTextFieldPurDate.getText())) {
            JOptionPane.showMessageDialog(this, "Missing Purchase Date!!!");
        } else {
            String SQLStr = "DELETE FROM book_purchase WHERE purID=?";
            PreparedStatement PreSta = DBCon.prepareStatement(SQLStr);
            PreSta.setString(1, jTextFieldSearchPurID.getText());

            int i = PreSta.executeUpdate();
            if (i != 0) {
                JOptionPane.showMessageDialog(this, "Delete Complete.");
            } else {
                JOptionPane.showMessageDialog(this, "Problem in deleting.");
            }
        }
    }

    public void RefreshMethod() throws SQLException {
        jTextFieldPurID.setText("");
        jComboBoxBookTitle.setSelectedItem("<Select>");
        jComboBoxEdition.removeAllItems();
        jComboBoxEdition.addItem("<Select>");
        jTextFieldAuthor.setText("");
        jTextFieldPublication.setText("");
        jTextFieldISBN.setText("");
        jTextFieldPrice.setText("");
        jTextFieldvat.setText("");
        jTextFieldDiscount.setText("");
        jTextFieldNoItems.setText("");
        jTextFieldNetAmnt.setText("");
        jTextFieldPurDate.setText("");
        autoPurID();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jTextFieldSearchPurID = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jTextFieldAuthor = new javax.swing.JTextField();
        jTextFieldISBN = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldPurID = new javax.swing.JTextField();
        jTextFieldPublication = new javax.swing.JTextField();
        jTextFieldPrice = new javax.swing.JTextField();
        jLabelEdition = new javax.swing.JLabel();
        jLabelAuthor = new javax.swing.JLabel();
        jLabelPublication = new javax.swing.JLabel();
        jLabelISBN = new javax.swing.JLabel();
        jComboBoxEdition = new javax.swing.JComboBox<>();
        jLabelISBN1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldvat = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldDiscount = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldNetAmnt = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextFieldPurDate = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextFieldNoItems = new javax.swing.JTextField();
        jComboBoxBookTitle = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jButtonSave = new javax.swing.JButton();
        jButtonRefresh = new javax.swing.JButton();
        jButtonDelete = new javax.swing.JButton();
        jButtonUpdate = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder()));

        jLabel1.setFont(new java.awt.Font("Lucida Calligraphy", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(192, 0, 0));
        jLabel1.setText("Purchase Information (book)");

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

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Search", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Console", 0, 12))); // NOI18N

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder()));

        jLabel8.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jLabel8.setText("Serial No");

        jTextFieldSearchPurID.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jTextFieldSearchPurID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldSearchPurIDKeyReleased(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jButton1.setText("Search");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
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
                    .addComponent(jTextFieldSearchPurID, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextFieldSearchPurID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
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

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Purchase Info", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Console", 0, 12))); // NOI18N

        jTextFieldAuthor.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N

        jTextFieldISBN.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jTextFieldISBN.setToolTipText("dd/mm/yy");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jLabel3.setText("Book Title");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jLabel2.setText("Purchase ID");

        jTextFieldPurID.setEditable(false);
        jTextFieldPurID.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N

        jTextFieldPublication.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jTextFieldPublication.setToolTipText("dd/mm/yy");

        jTextFieldPrice.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N

        jLabelEdition.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jLabelEdition.setText("Edition");

        jLabelAuthor.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jLabelAuthor.setText("Author");

        jLabelPublication.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jLabelPublication.setText("Publication");

        jLabelISBN.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jLabelISBN.setText("ISBN");

        jComboBoxEdition.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jComboBoxEdition.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "<Select>" }));
        jComboBoxEdition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxEditionActionPerformed(evt);
            }
        });

        jLabelISBN1.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jLabelISBN1.setText("Price (taka)");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jLabel4.setText("VAT (per item) (%)");

        jTextFieldvat.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jLabel5.setText("Discount (per item) (%)");

        jTextFieldDiscount.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jLabel6.setText("Net Amount");

        jTextFieldNetAmnt.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jLabel7.setText("Purchase Date (yyyy/mm/dd)");

        jTextFieldPurDate.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jTextFieldPurDate.setToolTipText("dd/mm/yy");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jLabel9.setText("No. of items");

        jTextFieldNoItems.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jTextFieldNoItems.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldNoItemsActionPerformed(evt);
            }
        });
        jTextFieldNoItems.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldNoItemsKeyReleased(evt);
            }
        });

        jComboBoxBookTitle.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jComboBoxBookTitle.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "<Select>" }));
        jComboBoxBookTitle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxBookTitleActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabelEdition)
                    .addComponent(jLabelAuthor)
                    .addComponent(jLabelPublication)
                    .addComponent(jLabelISBN)
                    .addComponent(jLabelISBN1)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextFieldPurID)
                    .addComponent(jTextFieldAuthor)
                    .addComponent(jTextFieldISBN)
                    .addComponent(jTextFieldPublication)
                    .addComponent(jTextFieldPrice)
                    .addComponent(jComboBoxEdition, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextFieldvat)
                    .addComponent(jTextFieldDiscount)
                    .addComponent(jTextFieldNetAmnt)
                    .addComponent(jTextFieldPurDate)
                    .addComponent(jTextFieldNoItems)
                    .addComponent(jComboBoxBookTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldPurID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jComboBoxBookTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabelEdition)
                    .addComponent(jComboBoxEdition, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabelAuthor)
                    .addComponent(jTextFieldAuthor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabelPublication)
                    .addComponent(jTextFieldPublication, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabelISBN)
                    .addComponent(jTextFieldISBN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabelISBN1)
                    .addComponent(jTextFieldPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel4)
                    .addComponent(jTextFieldvat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextFieldDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jTextFieldNoItems, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel6)
                    .addComponent(jTextFieldNetAmnt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel7)
                    .addComponent(jTextFieldPurDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jButton2.setFont(new java.awt.Font("Lucida Calligraphy", 1, 24)); // NOI18N
        jButton2.setText("e x i t");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Control", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Console", 0, 12))); // NOI18N

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
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jButtonDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jButtonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonSave)
                    .addComponent(jButtonUpdate))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonDelete)
                    .addComponent(jButtonRefresh))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(113, 113, 113)
                                .addComponent(jButton2))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(48, 48, 48)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(204, 204, 204)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(72, 72, 72)
                        .addComponent(jButton2)))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // Open this form in center
        this.setLocationRelativeTo(null);
    }//GEN-LAST:event_formWindowOpened

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // "e x i t" Button
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed
        try {
            SaveMethod();
        } catch (SQLException ex) {
            Logger.getLogger(LibraryBookPurchase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonSaveActionPerformed

    private void jComboBoxBookTitleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxBookTitleActionPerformed
        try {
            retriveEdition();
        } catch (SQLException ex) {
            Logger.getLogger(LibraryBookPurchase.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jComboBoxBookTitleActionPerformed

    private void jComboBoxEditionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxEditionActionPerformed
        try {
            retriveAuthorPublicISBN();
        } catch (SQLException ex) {
            Logger.getLogger(LibraryBookPurchase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jComboBoxEditionActionPerformed

    private void jButtonRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRefreshActionPerformed
        try {
            RefreshMethod();
            autoPurID();
        } catch (SQLException ex) {
            Logger.getLogger(LibraryBookPurchase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonRefreshActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            SearchMethod();
        } catch (SQLException ex) {
            Logger.getLogger(LibraryBookPurchase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButtonUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUpdateActionPerformed
        try {
            UpdateMethod();
        } catch (SQLException ex) {
            Logger.getLogger(LibraryBookPurchase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonUpdateActionPerformed

    private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteActionPerformed
        try {
            DeleteMethod();
        } catch (SQLException ex) {
            Logger.getLogger(LibraryBookPurchase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonDeleteActionPerformed

    private void jTextFieldSearchPurIDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldSearchPurIDKeyReleased
        try {
            SearchMethod();
        } catch (SQLException ex) {
            Logger.getLogger(LibraryBookPurchase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jTextFieldSearchPurIDKeyReleased

    private void jTextFieldNoItemsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNoItemsActionPerformed
    }//GEN-LAST:event_jTextFieldNoItemsActionPerformed

    private void jTextFieldNoItemsKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldNoItemsKeyReleased
        String price = jTextFieldPrice.getText();
        float Price = Integer.parseInt(price);
        String vat = jTextFieldvat.getText();
        float Vat = Integer.parseInt(vat);
        String discount = jTextFieldDiscount.getText();
        float Discount = Integer.parseInt(discount);
        String items = jTextFieldNoItems.getText();
        float NumOFItems = Integer.parseInt(items);
        
        float AfterDiscount = (Price*Discount)/100;
        float AfterVat = (Price*Vat)/100;
        float Total = (Price - AfterDiscount) + AfterVat;
        Total = Total*NumOFItems;
        
        String NetAmount = Float.toString(Total);
        jTextFieldNetAmnt.setText(NetAmount);
    }//GEN-LAST:event_jTextFieldNoItemsKeyReleased

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
            java.util.logging.Logger.getLogger(LibraryBookPurchase.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LibraryBookPurchase.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LibraryBookPurchase.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LibraryBookPurchase.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new LibraryBookPurchase().setVisible(true);

                } catch (SQLException ex) {
                    Logger.getLogger(LibraryBookPurchase.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JButton jButtonRefresh;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JButton jButtonUpdate;
    private javax.swing.JComboBox<String> jComboBoxBookTitle;
    private javax.swing.JComboBox<String> jComboBoxEdition;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelAuthor;
    private javax.swing.JLabel jLabelEdition;
    private javax.swing.JLabel jLabelISBN;
    private javax.swing.JLabel jLabelISBN1;
    private javax.swing.JLabel jLabelPublication;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JTextField jTextFieldAuthor;
    private javax.swing.JTextField jTextFieldDiscount;
    private javax.swing.JTextField jTextFieldISBN;
    private javax.swing.JTextField jTextFieldNetAmnt;
    private javax.swing.JTextField jTextFieldNoItems;
    private javax.swing.JTextField jTextFieldPrice;
    private javax.swing.JTextField jTextFieldPublication;
    private javax.swing.JTextField jTextFieldPurDate;
    private javax.swing.JTextField jTextFieldPurID;
    private javax.swing.JTextField jTextFieldSearchPurID;
    private javax.swing.JTextField jTextFieldvat;
    // End of variables declaration//GEN-END:variables
}
