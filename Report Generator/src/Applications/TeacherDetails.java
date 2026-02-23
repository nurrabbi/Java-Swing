package Applications;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class TeacherDetails extends javax.swing.JFrame {

    Connection DBCon;

    /* Creates new form Teachers */
    public TeacherDetails() throws SQLException {
        initComponents();
        //Sets whether this frame is resizable by the user.
        this.setResizable(false);

        DBCon = DatabaseConnection.ClsDatabaseConnection.methodDatabaseConnection();

        autoTeaID();
    }

    public void autoTeaID() {
        try {
            String queryStr = "SELECT MAX(TeacherID) FROM teacher_info";
            PreparedStatement PS = DBCon.prepareStatement(queryStr);
            ResultSet resultQuery = PS.executeQuery();
            while (resultQuery.next()) {
                String ID = resultQuery.getString("MAX(TeacherID)");
                if (ID == null) {
                    ID = "T-0";
                }
                int IID = Integer.parseInt(ID.substring(2));
                IID = IID + 1;
                String TID = Integer.toString(IID);
                jTextFieldTeaID.setText("T-" + TID);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TeacherDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void SaveMethod() throws SQLException {
        if ("".equals(jTextFieldTeaName.getText())) {
            JOptionPane.showMessageDialog(this, "Missing Name");
        } else if (jRadioButtonFaculty.isSelected() == false && jRadioButtonAdjoin.isSelected() == false && jRadioButtonHead.isSelected() == false) {
            JOptionPane.showMessageDialog(this, "Missing Member");
        } else if ("<Select>".equals(jComboBoxDepartment.getSelectedItem().toString())) {
            JOptionPane.showMessageDialog(this, "Missing Department");
        } else if ("".equals(jTextAreaDegree.getText())) {
            JOptionPane.showMessageDialog(this, "Missing Degree");
        } else if ("".equals(jTextAreaParmAddr.getText())) {
            JOptionPane.showMessageDialog(this, "Missing Parmanent Address");
        } else if ("".equals(jTextAreaTempAddr.getText())) {
            JOptionPane.showMessageDialog(this, "Missing Temporary Address");
        } else if (jCheckBoxMale.isSelected() == false && jCheckBoxFemale.isSelected() == false) {
            JOptionPane.showMessageDialog(this, "Missing Gender");
        } else if ("<Select>".equals(jComboBoxReligion.getSelectedItem().toString())) {
            JOptionPane.showMessageDialog(this, "Missing Religion");
        } else if ("".equals(jTextFieldNationality.getText())) {
            JOptionPane.showMessageDialog(this, "Missing Nationality");
        } else if ("".equals(jTextFieldNationalID.getText())) {
            JOptionPane.showMessageDialog(this, "Missing National ID");
        } else {
            String SQLStr = "INSERT INTO teacher_info(TeacherID,TeacherName,Member,Department,Degree,ParmAddr,TempAddr,Gender,Religion,Nationality,NationalID) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement PreSta = DBCon.prepareStatement(SQLStr);
            PreSta.setString(1, jTextFieldTeaID.getText());
            PreSta.setString(2, jTextFieldTeaName.getText());
            if (jRadioButtonFaculty.isSelected()) {
                PreSta.setString(3, "Faculty");
            } else if (jRadioButtonAdjoin.isSelected()) {
                PreSta.setString(3, "Adjoin");
            } else {
                PreSta.setString(3, "Head");
            }
            PreSta.setString(4, (String) jComboBoxDepartment.getSelectedItem());
            PreSta.setString(5, jTextAreaDegree.getText());
            PreSta.setString(6, jTextAreaParmAddr.getText());
            PreSta.setString(7, jTextAreaTempAddr.getText());
            if (jCheckBoxMale.isSelected()) {
                PreSta.setString(8, "Male");
            } else {
                PreSta.setString(8, "Female");
            }
            PreSta.setString(9, (String) jComboBoxReligion.getSelectedItem());
            PreSta.setString(10, jTextFieldNationality.getText());
            PreSta.setString(11, jTextFieldNationalID.getText());

            int i = PreSta.executeUpdate();
            if (i != 0) {
                JOptionPane.showMessageDialog(this, "Saved :)");
            } else {
                JOptionPane.showMessageDialog(this, "Not Saved :(");
            }
        }
    }

    public void SearchMethod() throws SQLException {
        String SQLStr = "SELECT * FROM teacher_info WHERE TeacherID=?";
        PreparedStatement PreSta = DBCon.prepareStatement(SQLStr);
        PreSta.setString(1, jTextFieldSearch.getText());

        ResultSet resultQuery = PreSta.executeQuery();
        while (resultQuery.next()) {
            jTextFieldTeaID.setText(resultQuery.getString("TeacherID"));
            jTextFieldTeaName.setText(resultQuery.getString("TeacherName"));
            if ("Faculty".equals(resultQuery.getString("Member"))) {
                jRadioButtonFaculty.setSelected(true);
            } else if ("Adjoin".equals(resultQuery.getString("Member"))) {
                jRadioButtonAdjoin.setSelected(true);
            } else if ("Head".equals(resultQuery.getString("Member"))) {
                jRadioButtonHead.setSelected(true);
            }
            jComboBoxDepartment.setSelectedItem(resultQuery.getString("Department"));
            jTextAreaDegree.setText(resultQuery.getString("Degree"));
            jTextAreaParmAddr.setText(resultQuery.getString("ParmAddr"));
            jTextAreaTempAddr.setText(resultQuery.getString("TempAddr"));
            if ("Male".equals(resultQuery.getString("Gender"))) {
                jCheckBoxMale.setSelected(true);
            } else {
                jCheckBoxFemale.setSelected(true);
            }
            jComboBoxReligion.setSelectedItem(resultQuery.getString("Religion"));
            jTextFieldNationality.setText(resultQuery.getString("Nationality"));
            jTextFieldNationalID.setText(resultQuery.getString("NationalID"));
        }
    }

    public void UpdateMethod() throws SQLException {
        if ("".equals(jTextFieldTeaName.getText())) {
            JOptionPane.showMessageDialog(this, "Missing Name");
        } else if (jRadioButtonFaculty.isSelected() == false && jRadioButtonAdjoin.isSelected() == false && jRadioButtonHead.isSelected() == false) {
            JOptionPane.showMessageDialog(this, "Missing Member");
        } else if ("<Select>".equals(jComboBoxDepartment.getSelectedItem().toString())) {
            JOptionPane.showMessageDialog(this, "Missing Department");
        } else if ("".equals(jTextAreaDegree.getText())) {
            JOptionPane.showMessageDialog(this, "Missing Degree");
        } else if ("".equals(jTextAreaParmAddr.getText())) {
            JOptionPane.showMessageDialog(this, "Missing Parmanent Address");
        } else if ("".equals(jTextAreaTempAddr.getText())) {
            JOptionPane.showMessageDialog(this, "Missing Temporary Address");
        } else if (jCheckBoxMale.isSelected() == false && jCheckBoxFemale.isSelected() == false) {
            JOptionPane.showMessageDialog(this, "Missing Gender");
        } else if ("<Select>".equals(jComboBoxReligion.getSelectedItem().toString())) {
            JOptionPane.showMessageDialog(this, "Missing Religion");
        } else if ("".equals(jTextFieldNationality.getText())) {
            JOptionPane.showMessageDialog(this, "Missing Nationality");
        } else if ("".equals(jTextFieldNationalID.getText())) {
            JOptionPane.showMessageDialog(this, "Missing National ID");
        } else {
            String SQLStr = "UPDATE teacher_info SET TeacherID=?, TeacherName=?, Member=?, Department=?, Degree=?, ParmAddr=?, TempAddr=?, Gender=?, Religion=?, Nationality=?, NationalID=? WHERE TeacherID=? ";
            PreparedStatement PreSta = DBCon.prepareStatement(SQLStr);
            PreSta.setString(1, jTextFieldTeaID.getText());
            PreSta.setString(2, jTextFieldTeaName.getText());
            if (jRadioButtonFaculty.isSelected()) {
                PreSta.setString(3, "Faculty");
            } else if (jRadioButtonAdjoin.isSelected()) {
                PreSta.setString(3, "Adjoin");
            } else {
                PreSta.setString(3, "Head");
            }
            PreSta.setString(4, (String) jComboBoxDepartment.getSelectedItem());
            PreSta.setString(5, jTextAreaDegree.getText());
            PreSta.setString(6, jTextAreaParmAddr.getText());
            PreSta.setString(7, jTextAreaTempAddr.getText());
            if (jCheckBoxMale.isSelected()) {
                PreSta.setString(8, "Male");
            } else {
                PreSta.setString(8, "Female");
            }
            PreSta.setString(9, (String) jComboBoxReligion.getSelectedItem());
            PreSta.setString(10, jTextFieldNationality.getText());
            PreSta.setString(11, jTextFieldNationalID.getText());

            int i = PreSta.executeUpdate();
            if (i != 0) {
                JOptionPane.showMessageDialog(this, "Update Complete.");
            } else {
                JOptionPane.showMessageDialog(this, "Problem in updating.");
            }
        }
    }

    public void DeleteMethod() throws SQLException {
        if ("".equals(jTextFieldTeaName.getText())) {
            JOptionPane.showMessageDialog(this, "(Teacher Name) Null value not accept !!!");
        } else if (jRadioButtonFaculty.isSelected() == false && jRadioButtonAdjoin.isSelected() == false && jRadioButtonHead.isSelected() == false) {
            JOptionPane.showMessageDialog(this, "(Department) Null value not accept !!!");
        } else if ("<Select>".equals(jComboBoxDepartment.getSelectedItem().toString())) {
            JOptionPane.showMessageDialog(this, "(Religion) Null value not accept !!!");
        } else if ("".equals(jTextAreaDegree.getText())) {
            JOptionPane.showMessageDialog(this, "(Degree) Null value not accept !!!");
        } else if ("".equals(jTextAreaParmAddr.getText())) {
            JOptionPane.showMessageDialog(this, "(Parmanent Address) Null value not accept !!!");
        } else if ("".equals(jTextAreaTempAddr.getText())) {
            JOptionPane.showMessageDialog(this, "(Temporary Address) Null value not accept !!!");
        } else if (jCheckBoxMale.isSelected() == false && jCheckBoxFemale.isSelected() == false) {
            JOptionPane.showMessageDialog(this, "(Gender) Null value not accept !!!");
        } else if ("<Select>".equals(jComboBoxReligion.getSelectedItem().toString())) {
            JOptionPane.showMessageDialog(this, "(Religion) Null value not accept !!!");
        } else if ("".equals(jTextFieldNationality.getText())) {
            JOptionPane.showMessageDialog(this, "(Nationality) Null value not accept !!!");
        } else if ("".equals(jTextFieldNationalID.getText())) {
            JOptionPane.showMessageDialog(this, "(National ID) Null value not accept !!!");
        } else {
            String SQLStr = "DELETE FROM teacher_info WHERE TeacherID=?";
            PreparedStatement PreSta = DBCon.prepareStatement(SQLStr);
            PreSta.setString(1, jTextFieldSearch.getText());

            int i = PreSta.executeUpdate();
            if (i != 0) {
                JOptionPane.showMessageDialog(this, "Delete Complete.");
            } else {
                JOptionPane.showMessageDialog(this, "Problem in deleting.");
            }
        }
    }

    public void RefreshMethod() {
        jTextFieldTeaID.setText(null);
        jTextFieldTeaName.setText("");
        jRadioButtonFaculty.setSelected(false);
        jRadioButtonAdjoin.setSelected(false);
        jRadioButtonHead.setSelected(false);
        jComboBoxDepartment.setSelectedItem("<Select>");
        jTextAreaDegree.setText(null);
        jTextAreaParmAddr.setText(null);
        jTextAreaTempAddr.setText(null);
        jCheckBoxMale.setSelected(false);
        jCheckBoxFemale.setSelected(false);
        jComboBoxReligion.setSelectedItem("<Select>");
        jTextFieldNationality.setText(null);
        jTextFieldNationalID.setText(null);
        jTextFieldSearch.setText(null);
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
        jPanel3 = new javax.swing.JPanel();
        jComboBoxDepartment = new javax.swing.JComboBox<>();
        jCheckBoxFemale = new javax.swing.JCheckBox();
        jLabelParmAddr = new javax.swing.JLabel();
        jLabelReligion = new javax.swing.JLabel();
        jComboBoxReligion = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaParmAddr = new javax.swing.JTextArea();
        jLabelTempAddr = new javax.swing.JLabel();
        jTextFieldNationalID = new javax.swing.JTextField();
        jLabelGender = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaTempAddr = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jTextFieldTeaID = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextAreaDegree = new javax.swing.JTextArea();
        jLabelDegree = new javax.swing.JLabel();
        jLabelNationality = new javax.swing.JLabel();
        jRadioButtonAdjoin = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldTeaName = new javax.swing.JTextField();
        jTextFieldNationality = new javax.swing.JTextField();
        jCheckBoxMale = new javax.swing.JCheckBox();
        jLabelNationalID = new javax.swing.JLabel();
        jRadioButtonFaculty = new javax.swing.JRadioButton();
        jRadioButtonHead = new javax.swing.JRadioButton();
        jPanel4 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jTextFieldSearch = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder()));

        jLabel1.setFont(new java.awt.Font("Lucida Calligraphy", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(192, 0, 0));
        jLabel1.setText("Teacher Details");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "INFO", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Console", 0, 12))); // NOI18N
        jPanel3.setToolTipText("Information");

        jComboBoxDepartment.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jComboBoxDepartment.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "<Select>", "Science", "Commerce", "Arts" }));

        jCheckBoxFemale.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jCheckBoxFemale.setText("Female");
        jCheckBoxFemale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxFemaleActionPerformed(evt);
            }
        });

        jLabelParmAddr.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jLabelParmAddr.setText("Parmanent Address");

        jLabelReligion.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jLabelReligion.setText("Religion");

        jComboBoxReligion.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jComboBoxReligion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "<Select>", "Muslim", "Hindu", "Buddhist", "Christen" }));

        jTextAreaParmAddr.setColumns(20);
        jTextAreaParmAddr.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jTextAreaParmAddr.setRows(5);
        jScrollPane1.setViewportView(jTextAreaParmAddr);

        jLabelTempAddr.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jLabelTempAddr.setText("Temporary Address");

        jTextFieldNationalID.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N

        jLabelGender.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jLabelGender.setText("Gender");

        jTextAreaTempAddr.setColumns(20);
        jTextAreaTempAddr.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jTextAreaTempAddr.setRows(5);
        jScrollPane2.setViewportView(jTextAreaTempAddr);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jLabel2.setText("Teacher ID");

        jLabel13.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jLabel13.setText("Department");

        jTextFieldTeaID.setEditable(false);
        jTextFieldTeaID.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jLabel10.setText("Member");

        jTextAreaDegree.setColumns(20);
        jTextAreaDegree.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jTextAreaDegree.setRows(5);
        jScrollPane3.setViewportView(jTextAreaDegree);

        jLabelDegree.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jLabelDegree.setText("Degree");

        jLabelNationality.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jLabelNationality.setText("Nationality");

        jRadioButtonAdjoin.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jRadioButtonAdjoin.setText("Adjoin");
        jRadioButtonAdjoin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonAdjoinActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jLabel4.setText("Teacher Name");

        jTextFieldTeaName.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N

        jTextFieldNationality.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N

        jCheckBoxMale.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jCheckBoxMale.setText("Male");
        jCheckBoxMale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMaleActionPerformed(evt);
            }
        });

        jLabelNationalID.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jLabelNationalID.setText("National ID");

        jRadioButtonFaculty.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jRadioButtonFaculty.setText("Faculty");
        jRadioButtonFaculty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonFacultyActionPerformed(evt);
            }
        });

        jRadioButtonHead.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jRadioButtonHead.setText("Head");
        jRadioButtonHead.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonHeadActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelNationalID)
                    .addComponent(jLabelNationality)
                    .addComponent(jLabelReligion)
                    .addComponent(jLabelGender)
                    .addComponent(jLabelTempAddr)
                    .addComponent(jLabelParmAddr)
                    .addComponent(jLabelDegree)
                    .addComponent(jLabel13)
                    .addComponent(jLabel10)
                    .addComponent(jLabel4)
                    .addComponent(jLabel2))
                .addGap(52, 52, 52)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jCheckBoxMale)
                            .addGap(18, 18, 18)
                            .addComponent(jCheckBoxFemale))
                        .addComponent(jComboBoxReligion, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextFieldNationality)
                        .addComponent(jTextFieldNationalID, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jRadioButtonFaculty)
                            .addGap(18, 18, 18)
                            .addComponent(jRadioButtonAdjoin)
                            .addGap(18, 18, 18)
                            .addComponent(jRadioButtonHead))
                        .addComponent(jTextFieldTeaName)
                        .addComponent(jTextFieldTeaID)
                        .addComponent(jScrollPane3)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jComboBoxDepartment, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldTeaID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldTeaName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel10)
                    .addComponent(jRadioButtonFaculty)
                    .addComponent(jRadioButtonAdjoin)
                    .addComponent(jRadioButtonHead))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jComboBoxDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabelDegree)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabelParmAddr)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabelTempAddr)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jCheckBoxMale)
                    .addComponent(jCheckBoxFemale)
                    .addComponent(jLabelGender))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabelReligion)
                    .addComponent(jComboBoxReligion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabelNationality)
                    .addComponent(jTextFieldNationality, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabelNationalID)
                    .addComponent(jTextFieldNationalID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Control", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Console", 0, 12))); // NOI18N
        jPanel4.setToolTipText("Control Panel");

        jButton1.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jButton1.setText("Save");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jButton3.setText("Delete");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jButton4.setText("Refresh");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jButton5.setText("Close");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jButton2.setText("Update");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(53, 53, 53)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton5)
                .addContainerGap())
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Search", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Console", 0, 12))); // NOI18N
        jPanel5.setToolTipText("Search Box");

        jTextFieldSearch.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jTextFieldSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldSearchKeyReleased(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jLabel14.setText("Teacher ID");

        jButton6.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jButton6.setText("Search");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jTextFieldSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel14)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton6)
                .addGap(66, 66, 66))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextFieldSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton6)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(253, 253, 253)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // Open this form in Center
        this.setLocationRelativeTo(null);
    }//GEN-LAST:event_formWindowOpened

    private void jRadioButtonFacultyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonFacultyActionPerformed
        if (jRadioButtonFaculty.isSelected()) {
            jRadioButtonAdjoin.setSelected(false);
            jRadioButtonHead.setSelected(false);
        }
    }//GEN-LAST:event_jRadioButtonFacultyActionPerformed

    private void jRadioButtonAdjoinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonAdjoinActionPerformed
        if (jRadioButtonAdjoin.isSelected()) {
            jRadioButtonFaculty.setSelected(false);
            jRadioButtonHead.setSelected(false);
        }
    }//GEN-LAST:event_jRadioButtonAdjoinActionPerformed

    private void jRadioButtonHeadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonHeadActionPerformed
        if (jRadioButtonHead.isSelected()) {
            jRadioButtonFaculty.setSelected(false);
            jRadioButtonAdjoin.setSelected(false);
        }
    }//GEN-LAST:event_jRadioButtonHeadActionPerformed

    private void jCheckBoxMaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMaleActionPerformed
        if (jCheckBoxMale.isSelected()) {
            jCheckBoxFemale.setSelected(false);
        }
    }//GEN-LAST:event_jCheckBoxMaleActionPerformed

    private void jCheckBoxFemaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxFemaleActionPerformed
        if (jCheckBoxFemale.isSelected()) {
            jCheckBoxMale.setSelected(false);
        }
    }//GEN-LAST:event_jCheckBoxFemaleActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            SaveMethod();
        } catch (SQLException ex) {
            Logger.getLogger(TeacherDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        try {
            SearchMethod();
        } catch (SQLException ex) {
            Logger.getLogger(TeacherDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            UpdateMethod();
        } catch (SQLException ex) {
            Logger.getLogger(TeacherDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            DeleteMethod();
        } catch (SQLException ex) {
            Logger.getLogger(TeacherDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        RefreshMethod();
        autoTeaID();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTextFieldSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldSearchKeyReleased
        try {
            SearchMethod();  //Call method
        } catch (SQLException ex) {
            Logger.getLogger(TeacherDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jTextFieldSearchKeyReleased

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
            java.util.logging.Logger.getLogger(TeacherDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TeacherDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TeacherDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TeacherDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new TeacherDetails().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(TeacherDetails.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JCheckBox jCheckBoxFemale;
    private javax.swing.JCheckBox jCheckBoxMale;
    private javax.swing.JComboBox<String> jComboBoxDepartment;
    private javax.swing.JComboBox<String> jComboBoxReligion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabelDegree;
    private javax.swing.JLabel jLabelGender;
    private javax.swing.JLabel jLabelNationalID;
    private javax.swing.JLabel jLabelNationality;
    private javax.swing.JLabel jLabelParmAddr;
    private javax.swing.JLabel jLabelReligion;
    private javax.swing.JLabel jLabelTempAddr;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JRadioButton jRadioButtonAdjoin;
    private javax.swing.JRadioButton jRadioButtonFaculty;
    private javax.swing.JRadioButton jRadioButtonHead;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextAreaDegree;
    private javax.swing.JTextArea jTextAreaParmAddr;
    private javax.swing.JTextArea jTextAreaTempAddr;
    private javax.swing.JTextField jTextFieldNationalID;
    private javax.swing.JTextField jTextFieldNationality;
    private javax.swing.JTextField jTextFieldSearch;
    private javax.swing.JTextField jTextFieldTeaID;
    private javax.swing.JTextField jTextFieldTeaName;
    // End of variables declaration//GEN-END:variables
}
