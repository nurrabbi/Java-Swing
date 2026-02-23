package Applications;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import net.coobird.thumbnailator.Thumbnails;

public class StudentInformation extends javax.swing.JFrame {

    Connection DBCon;

    /* Creates new form Student_Information */
    public StudentInformation() throws SQLException {
        initComponents();

        //Sets whether this frame is resizable by the user.
        this.setResizable(false);

        // Invoke driver from "DBConnect()" method, which is describe in
        // "ClsDatabaseConnection" class of "DatabaseConnection" package.
        DBCon = DatabaseConnection.ClsDatabaseConnection.methodDatabaseConnection();

        autoStuID();  //Call "autoID()" Method
    }

    public void autoStuID() {
        try {
            PreparedStatement PreSta = DBCon.prepareStatement("SELECT MAX(StuID) AS StuID FROM student_info");
            ResultSet resultQuery = PreSta.executeQuery();
            if (resultQuery.next()) {
                String id = resultQuery.getString("StuID");
                int ID = Integer.parseInt(id.substring(2, 7));
                ID = ID + 1;
                String i = String.valueOf(ID);
                if (i.length() == 1) {
                    jTextFieldStuID.setText("S-0000" + i);
                } else if (i.length() == 2) {
                    jTextFieldStuID.setText("S-000" + i);
                } else if (i.length() == 3) {
                    jTextFieldStuID.setText("S-00" + i);
                } else if (i.length() == 4) {
                    jTextFieldStuID.setText("S-0" + i);
                } else if (i.length() == 5) {
                    jTextFieldStuID.setText("S-" + i);
                }
            }
        } catch (SQLException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "No Data Found in Database Generating New Employee ID. Press Ok.\n                                 For First Time.", "Data Retrieve Error", 1);
            jTextFieldStuID.setText("S-00001");
        }
    }

    public void SaveMethod() throws SQLException {
        if ("".equals(jTextFieldStuName.getText())) {
            JOptionPane.showMessageDialog(this, "Missing Student Name");
        } else if ("".equals(jLabelStuPhoto.getIcon())) {
            JOptionPane.showMessageDialog(this, "Missing Student Photo");
        } else if ("<Select>".equals(jComboBoxClass.getSelectedItem().toString())) {
            JOptionPane.showMessageDialog(this, "Missing Class");
        } else if ("<Select>".equals(jComboBoxSec.getSelectedItem().toString())) {
            JOptionPane.showMessageDialog(this, "Missing Section");
        } else if (jComboBoxMajor.isEnabled() == true && ("<Select>".equals(jComboBoxMajor.getSelectedItem().toString()))) {
            JOptionPane.showMessageDialog(this, "Missing Major");
        } else if ("".equals(jTextAreaParmAddress.getText())) {
            JOptionPane.showMessageDialog(this, "Missing Parmanent Address");
        } else if ("".equals(jTextAreaTempAddress.getText())) {
            JOptionPane.showMessageDialog(this, "Missing Temporary Address");
        } else if (jCheckBoxMale.isSelected() == false && jCheckBoxFemale.isSelected() == false) {
            JOptionPane.showMessageDialog(this, "Missing Gender");
        } else if ("<Select>".equals(jComboBoxRel.getSelectedItem().toString())) {
            JOptionPane.showMessageDialog(this, "Missing Religion");
        } else if ("".equals(jTextFieldNationality.getText())) {
            JOptionPane.showMessageDialog(this, "Missing Nationality");
        } else if ("".equals(jTextFieldFatherName.getText())) {
            JOptionPane.showMessageDialog(this, "Missing Father Name");
        } else if ("".equals(jTextFieldMotherName.getText())) {
            JOptionPane.showMessageDialog(this, "Missing Mother Name");
        } else if (jRadioButtonFather.isSelected() == false && jRadioButtonMother.isSelected() == false && jRadioButtonOther.isSelected() == false) {
            JOptionPane.showMessageDialog(this, "Missing Guardian");
        } else if ("".equals(jTextFieldContactNo.getText())) {
            JOptionPane.showMessageDialog(this, "Missing Guardian Contact Number");
        } else {
            String SQLStr = "INSERT INTO student_info(StuID,StuName,Stu_Photo,Class,Section,Major,ParmAddr,TempAddr,Gender,Religion,Nationality,FatherName,MotherName,Guardian,GuardianContNo) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement PreSta = DBCon.prepareStatement(SQLStr);
            PreSta.setString(1, jTextFieldStuID.getText());
            PreSta.setString(2, jTextFieldStuName.getText());
            PreSta.setBytes(3, targetImage);
            PreSta.setString(4, (String) jComboBoxClass.getSelectedItem());
            PreSta.setString(5, (String) jComboBoxSec.getSelectedItem());
            if (classAttr == 0) {
                if (jComboBoxMajor.selectWithKeyChar('S')) {
                    PreSta.setString(6, "Science");
                } else if (jComboBoxMajor.selectWithKeyChar('C')) {
                    PreSta.setString(6, "Commerce");
                } else {
                    PreSta.setString(6, "Arts");
                }
            } else {
                PreSta.setString(6, "");
            }
            PreSta.setString(7, jTextAreaParmAddress.getText());
            PreSta.setString(8, jTextAreaTempAddress.getText());
            if (jCheckBoxMale.isSelected()) {
                PreSta.setString(9, "Male");
            } else {
                PreSta.setString(9, "Female");
            }
            PreSta.setString(10, (String) jComboBoxRel.getSelectedItem());
            PreSta.setString(11, jTextFieldNationality.getText());
            PreSta.setString(12, jTextFieldFatherName.getText());
            PreSta.setString(13, jTextFieldMotherName.getText());
            if (jRadioButtonFather.isSelected()) {
                PreSta.setString(14, "Father");
            } else if (jRadioButtonFather.isSelected()) {
                PreSta.setString(14, "Mother");
            } else {
                PreSta.setString(14, "Other");
            }
            PreSta.setString(15, jTextFieldContactNo.getText());

            int i = PreSta.executeUpdate();
            if (i != 0) {
                JOptionPane.showMessageDialog(this, "Saved :)");
            } else {
                JOptionPane.showMessageDialog(this, "Not Saved :(");
            }
        }
    }

    public void SearchMethod() throws SQLException {
        String SQLStr = "SELECT * FROM student_info WHERE StuID = ?";
        PreparedStatement PreSta = DBCon.prepareStatement(SQLStr);
        PreSta.setString(1, jTextFieldSearch.getText());

        ResultSet resultQuery = PreSta.executeQuery();
        while (resultQuery.next()) {
            jTextFieldStuID.setText(resultQuery.getString("StuID"));
            jTextFieldStuName.setText(resultQuery.getString("StuName"));

            byte[] imaged = resultQuery.getBytes("Stu_Photo");
            imagedata = new ImageIcon(imaged);
            jLabelStuPhoto.setIcon(imagedata);

            jComboBoxClass.setSelectedItem(resultQuery.getString("Class"));
            jComboBoxSec.setSelectedItem(resultQuery.getString("Section"));
            jComboBoxMajor.setSelectedItem(resultQuery.getString("Major"));
            jTextAreaParmAddress.setText(resultQuery.getString("ParmAddr"));
            jTextAreaTempAddress.setText(resultQuery.getString("TempAddr"));
            if ("Male".equals(resultQuery.getString("Gender"))) {
                jCheckBoxMale.setSelected(true);
            } else {
                jCheckBoxFemale.setSelected(true);
            }
            jComboBoxRel.setSelectedItem(resultQuery.getString("Religion"));
            jTextFieldNationality.setText(resultQuery.getString("Nationality"));
            jTextFieldFatherName.setText(resultQuery.getString("FatherName"));
            jTextFieldMotherName.setText(resultQuery.getString("MotherName"));
            if ("Father".equals(resultQuery.getString("Guardian"))) {
                jRadioButtonFather.setSelected(true);
            } else if ("Mother".equals(resultQuery.getString("Guardian"))) {
                jRadioButtonMother.setSelected(true);
            } else {
                jRadioButtonOther.setSelected(true);
            }
            jTextFieldContactNo.setText(resultQuery.getString("GuardianContNo"));
        }
    }

    public void UpdateMethod() {
        if ("".equals(jTextFieldStuName.getText())) {
            JOptionPane.showMessageDialog(this, "(Student Name) Null value not accept !!!");
        } else if ("".equals(jLabelLOC.getText())) {
            JOptionPane.showMessageDialog(this, "Missing Student Photo");
        } else if ("<Select>".equals(jComboBoxClass.getSelectedItem().toString())) {
            JOptionPane.showMessageDialog(this, "(Class) Null value not accept !!!");
        } else if ("<Select>".equals(jComboBoxSec.getSelectedItem().toString())) {
            JOptionPane.showMessageDialog(this, "(Section) Null value not accept !!!");
        } else if (jComboBoxMajor.isEnabled() == true && "<Select>".equals(jComboBoxMajor.getSelectedItem().toString())) {
            JOptionPane.showMessageDialog(this, "(Major) Null value not accept !!!");
        } else if ("".equals(jTextAreaParmAddress.getText())) {
            JOptionPane.showMessageDialog(this, "(Parmanent Address) Null value not accept !!!");
        } else if ("".equals(jTextAreaTempAddress.getText())) {
            JOptionPane.showMessageDialog(this, "(Temporary Address) Null value not accept !!!");
        } else if (jCheckBoxMale.isSelected() == false && jCheckBoxFemale.isSelected() == false) {
            JOptionPane.showMessageDialog(this, "(Gender) Null value not accept !!!");
        } else if ("<Select>".equals(jComboBoxRel.getSelectedItem().toString())) {
            JOptionPane.showMessageDialog(this, "(Religion) Null value not accept !!!");
        } else if ("".equals(jTextFieldNationality.getText())) {
            JOptionPane.showMessageDialog(this, "(Nationality) Null value not accept !!!");
        } else if ("".equals(jTextFieldFatherName.getText())) {
            JOptionPane.showMessageDialog(this, "(Father Name) Null value not accept !!!");
        } else if ("".equals(jTextFieldMotherName.getText())) {
            JOptionPane.showMessageDialog(this, "(Mother Name) Null value not accept !!!");
        } else if (jRadioButtonFather.isSelected() == false && jRadioButtonMother.isSelected() == false && jRadioButtonOther.isSelected() == false) {
            JOptionPane.showMessageDialog(this, "(Guardian) Null value not accept !!!");
        } else if ("".equals(jTextFieldContactNo.getText())) {
            JOptionPane.showMessageDialog(this, "(Guardian) Null value not accept !!!");
        } else {
            try {
                String SQLStr = "UPDATE student_info SET StuID=?, StuName=?, Stu_Photo=? Class=?, Section=?, Major=?, ParmAddr=?, TempAddr=?, Gender=?, Religion=?, Nationality=?, FatherName=?, MotherName=?, Guardian=?, GuardianContNo=? WHERE StuID=?";
                PreparedStatement PreSta = DBCon.prepareStatement(SQLStr);
                PreSta.setString(1, jTextFieldStuID.getText());
                PreSta.setString(2, jTextFieldStuName.getText());
                PreSta.setBytes(3, targetImage);
                PreSta.setString(4, (String) jComboBoxClass.getSelectedItem());
                PreSta.setString(5, (String) jComboBoxSec.getSelectedItem());
                PreSta.setString(6, (String) jComboBoxMajor.getSelectedItem());
                PreSta.setString(7, jTextAreaParmAddress.getText());
                PreSta.setString(8, jTextAreaTempAddress.getText());
                if (jCheckBoxMale.isSelected()) {
                    PreSta.setString(9, "Male");
                } else {
                    PreSta.setString(9, "Female");
                }
                PreSta.setString(10, (String) jComboBoxRel.getSelectedItem());
                PreSta.setString(11, jTextFieldNationality.getText());
                PreSta.setString(12, jTextFieldFatherName.getText());
                PreSta.setString(13, jTextFieldMotherName.getText());
                if (jRadioButtonFather.isSelected()) {
                    PreSta.setString(14, "Father");
                } else if (jRadioButtonMother.isSelected()) {
                    PreSta.setString(14, "Mother");
                } else {
                    PreSta.setString(14, "Other");
                }
                PreSta.setString(15, jTextFieldContactNo.getText());
                PreSta.setString(16, jTextFieldSearch.getText());

                int i = PreSta.executeUpdate();
                if (i != 0) {
                    JOptionPane.showMessageDialog(this, "Update Complete.");
                } else {
                    JOptionPane.showMessageDialog(this, "Problem in updating.");
                }
            } catch (SQLException ex) {
                Logger.getLogger(StudentInformation.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void DeleteMethod() throws SQLException {
        if ("".equals(jTextFieldStuName.getText())) {
            JOptionPane.showMessageDialog(this, "(Student Name) Null value not accept !!!");
        } else if ("".equals(jLabelStuPhoto.getText())) {
            JOptionPane.showMessageDialog(this, "(Student Photo) Null value not accept !!!");
        } else if ("<Select>".equals(jComboBoxClass.getSelectedItem().toString())) {
            JOptionPane.showMessageDialog(this, "(Class) Non select item not accept !!!");
        } else if ("<Select>".equals(jComboBoxSec.getSelectedItem().toString())) {
            JOptionPane.showMessageDialog(this, "(Section) Null value not accept !!!");
        } else if (jComboBoxMajor.isEditable() == true && "<Select>".equals(jComboBoxMajor.getSelectedItem().toString())) {
            JOptionPane.showMessageDialog(this, "(Major) Non select item not accept !!!");
        } else if ("".equals(jTextAreaParmAddress.getText())) {
            JOptionPane.showMessageDialog(this, "(Parmanent Address) Null value not accept !!!");
        } else if ("".equals(jTextAreaTempAddress.getText())) {
            JOptionPane.showMessageDialog(this, "(Temporary Address) Null value not accept !!!");
        } else if ((false == jCheckBoxMale.isSelected()) && (false == jCheckBoxFemale.isSelected())) {
            JOptionPane.showMessageDialog(this, "(Gender) Non select item not accept !!!");
        } else if ("<Select>".equals(jComboBoxRel.getSelectedItem().toString())) {
            JOptionPane.showMessageDialog(this, "(Religion) Null value not accept !!!");
        } else if ("".equals(jTextFieldNationality.getText())) {
            JOptionPane.showMessageDialog(this, "(Nationality) Null value not accept !!!");
        } else if ("".equals(jTextFieldFatherName.getText())) {
            JOptionPane.showMessageDialog(this, "(Father Name) Null value not accept !!!");
        } else if ("".equals(jTextFieldMotherName.getText())) {
            JOptionPane.showMessageDialog(this, "(Mother Name) Null value not accept !!!");
        } else if (jRadioButtonFather.isSelected() == false && jRadioButtonMother.isSelected() == false && jRadioButtonOther.isSelected() == false) {
            JOptionPane.showMessageDialog(this, "(Guardian) Null value not accept !!!");
        } else if ("".equals(jTextFieldContactNo.getText())) {
            JOptionPane.showMessageDialog(this, "(Guardian) Null value not accept !!!");
        } else {
            String SQLStr = "DELETE FROM student_info WHERE StuID = ?";
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
        jTextFieldStuID.setText(null);
        jTextFieldStuName.setText("");
        jLabelStuPhoto.setIcon(null);
        jLabelLOC.setText("");
        jComboBoxClass.setSelectedItem("<Select>");
        jComboBoxSec.setSelectedItem("<Select>");
        jComboBoxMajor.setSelectedItem("<Select>");
        jComboBoxMajor.setEnabled(true);
        jLabelMajor.setEnabled(true);
        jTextAreaParmAddress.setText(null);
        jTextAreaTempAddress.setText(null);
        jCheckBoxMale.setSelected(false);
        jCheckBoxFemale.setSelected(false);
        jComboBoxRel.setSelectedItem("<Select>");
        jTextFieldNationality.setText(null);
        jTextFieldFatherName.setText(null);
        jTextFieldMotherName.setText(null);
        jRadioButtonFather.setSelected(false);
        jRadioButtonMother.setSelected(false);
        jRadioButtonOther.setSelected(false);
        jTextFieldContactNo.setText(null);
        jTextFieldSearch.setText(null);
    }

    public void ReportStudentInfo() {
        try {
            Document ReportDoc = new Document(PageSize.A4);

            //for columns
            Font font1 = new Font(Font.BOLD, 15);
            font1.setColor(Color.BLACK);
            font1.setStyle(Font.BOLD);

            Paragraph para3 = new Paragraph("\n");
            para3.setAlignment(Element.ALIGN_CENTER);

            String StdName = "";
            PdfWriter writer = PdfWriter.getInstance(ReportDoc, new FileOutputStream("StudentInfoReport.pdf"));

            ReportDoc.open();

            //Header
            PdfPCell cell1 = new PdfPCell();
            PdfPCell cell2 = new PdfPCell();
            PdfPCell cell = new PdfPCell();

            PdfPTable my_report_table2 = new PdfPTable(1);
            my_report_table2.setWidthPercentage(105f);

            //Title Result
            cell2.setPhrase(new Phrase("Student Info List ", new Font(Font.BOLD, 18, Font.BOLD, Color.RED)));
            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            // cell2.setGrayFill(0.9f);
            cell2.setBorder(Rectangle.NO_BORDER);
            my_report_table2.addCell(cell2);

            /////////////////////
            PdfPTable my_report_tabledata = new PdfPTable(6);
            my_report_tabledata.setWidthPercentage(105f);

            String[] headers = new String[]{
                "SL", "Student Name", "ID", "Contact No.", "Address", "Class"
            };

            PdfPTable table = new PdfPTable(headers.length);
            float[] columnWidths = {0.4f, 2f, 0.7f, 0.7f, 1.5f, 2f};
            table.setWidths(columnWidths);
            table.setWidthPercentage(105f);
            cell.setBorderColor(Color.GRAY);
            cell1.setBorderColor(Color.GRAY);
            my_report_tabledata.setWidths(columnWidths);
            // my_report_table.setWidthPercentage(105f);

            for (String header : headers) {
                // PdfPCell cell1 = new PdfPCell();

                cell1.setPhrase(new Phrase(header, new Font(Font.NORMAL, 11, Font.BOLD)));
                cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell1.setGrayFill(0.9f);

                table.addCell(cell1);
            }
            table.completeRow();

            /////////////////////////////////
            int slNo = 0;
            String query = "SELECT * FROM student_info ";

            PreparedStatement pst1 = DBCon.prepareStatement(query);

            ResultSet rs1 = pst1.executeQuery();

            while (rs1.next()) {
                slNo++;
                cell = new PdfPCell(new Phrase(slNo + "", new Font(Font.NORMAL, 11, Font.BOLD)));
                cell.setBorderColor(Color.GRAY);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                my_report_tabledata.addCell(cell);

                System.out.println(slNo);

                String student_name = rs1.getString("StuName");
                cell = new PdfPCell(new Phrase(student_name + "", new Font(Font.NORMAL, 11, Font.BOLD)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorderColor(Color.GRAY);
                my_report_tabledata.addCell(cell);

                System.out.println(student_name);

                String student_roll = rs1.getString("StuID");
                cell.setPhrase(new Phrase(student_roll + "", new Font(Font.NORMAL, 11, Font.BOLD)));
                cell.setBorderColor(Color.GRAY);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                my_report_tabledata.addCell(cell);

                System.out.println(student_roll);

                String student_id = rs1.getString("GuardianContNo");
                cell.setPhrase(new Phrase(student_id + "", new Font(Font.NORMAL, 11, Font.BOLD)));
                cell.setBorderColor(Color.GRAY);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                my_report_tabledata.addCell(cell);
                System.out.println(student_id);

                // contact
                String contact = rs1.getString("ParmAddr");

                cell.setPhrase(new Phrase(contact + "", new Font(Font.NORMAL, 11, Font.BOLD)));
                cell.setBorderColor(Color.GRAY);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                my_report_tabledata.addCell(cell);
                System.out.println(contact);

                // contact
                String address = rs1.getString("Class");

                cell.setPhrase(new Phrase(address + "", new Font(Font.NORMAL, 11, Font.BOLD)));
                cell.setBorderColor(Color.GRAY);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                my_report_tabledata.addCell(cell);
                System.out.println(address);
            }

            ////////////////////////////////////////////////////generate grade
            ReportDoc.add(my_report_table2);//  student list
            ReportDoc.add(para3);//new line
            ReportDoc.add(table); //header
            ReportDoc.add(my_report_tabledata);//table data
            ReportDoc.close();
        } catch (DocumentException | FileNotFoundException | SQLException ex) {
            Logger.getLogger(StudentInformation.class.getName()).log(Level.SEVERE, null, ex);
        }

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
        jLabelStuInfo = new javax.swing.JLabel();
        jPanelInfoColect = new javax.swing.JPanel();
        jLabelContactNo = new javax.swing.JLabel();
        jLabelClass = new javax.swing.JLabel();
        jLabelFatherName = new javax.swing.JLabel();
        jLabelStuName = new javax.swing.JLabel();
        jLabelStuID = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaParmAddress = new javax.swing.JTextArea();
        jLabelReligion = new javax.swing.JLabel();
        jComboBoxRel = new javax.swing.JComboBox<>();
        jTextFieldMotherName = new javax.swing.JTextField();
        jLabelMajor = new javax.swing.JLabel();
        jLabelGuardian = new javax.swing.JLabel();
        jTextFieldStuID = new javax.swing.JTextField();
        jCheckBoxMale = new javax.swing.JCheckBox();
        jTextFieldFatherName = new javax.swing.JTextField();
        jLabelGender = new javax.swing.JLabel();
        jCheckBoxFemale = new javax.swing.JCheckBox();
        jLabelMotherName = new javax.swing.JLabel();
        jComboBoxSec = new javax.swing.JComboBox<>();
        jComboBoxClass = new javax.swing.JComboBox<>();
        jTextFieldStuName = new javax.swing.JTextField();
        jLabelParmAddress = new javax.swing.JLabel();
        jLabelSection = new javax.swing.JLabel();
        jLabel1TempAddress = new javax.swing.JLabel();
        jLabelNationality = new javax.swing.JLabel();
        jTextFieldNationality = new javax.swing.JTextField();
        jRadioButtonFather = new javax.swing.JRadioButton();
        jRadioButtonMother = new javax.swing.JRadioButton();
        jRadioButtonOther = new javax.swing.JRadioButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaTempAddress = new javax.swing.JTextArea();
        jTextFieldContactNo = new javax.swing.JTextField();
        jComboBoxMajor = new javax.swing.JComboBox<>();
        jPanelControlBox = new javax.swing.JPanel();
        jButtonDelete = new javax.swing.JButton();
        jButtonUpdate = new javax.swing.JButton();
        jButtonSave = new javax.swing.JButton();
        jButtonRefresh = new javax.swing.JButton();
        jButtonClose = new javax.swing.JButton();
        jPanelSearchBox = new javax.swing.JPanel();
        jTextFieldSearch = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jButtonSearch = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabelStuPhoto = new javax.swing.JLabel();
        jButtonAttach = new javax.swing.JButton();
        jLabelLOC = new javax.swing.JLabel();
        jButtonReport = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(null, new java.awt.Color(0, 0, 0))));

        jLabelStuInfo.setFont(new java.awt.Font("Lucida Calligraphy", 1, 24)); // NOI18N
        jLabelStuInfo.setForeground(new java.awt.Color(192, 0, 0));
        jLabelStuInfo.setText("Student Information");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelStuInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelStuInfo)
                .addContainerGap())
        );

        jPanelInfoColect.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Info Chart", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Console", 0, 12))); // NOI18N
        jPanelInfoColect.setToolTipText("Information");

        jLabelContactNo.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jLabelContactNo.setText("Guardian Contact No.");

        jLabelClass.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jLabelClass.setText("Class");

        jLabelFatherName.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jLabelFatherName.setText("Father Name");

        jLabelStuName.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jLabelStuName.setText("Student Name");

        jLabelStuID.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jLabelStuID.setText("Student ID/Roll");

        jTextAreaParmAddress.setColumns(20);
        jTextAreaParmAddress.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jTextAreaParmAddress.setRows(5);
        jScrollPane1.setViewportView(jTextAreaParmAddress);

        jLabelReligion.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jLabelReligion.setText("Religion");

        jComboBoxRel.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jComboBoxRel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "<Select>", "Muslim", "Hindu", "Buddhist", "Christen" }));

        jTextFieldMotherName.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N

        jLabelMajor.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jLabelMajor.setText("Major");

        jLabelGuardian.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jLabelGuardian.setText("Guardian");

        jTextFieldStuID.setEditable(false);
        jTextFieldStuID.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N

        jCheckBoxMale.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jCheckBoxMale.setText("Male");
        jCheckBoxMale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMaleActionPerformed(evt);
            }
        });

        jTextFieldFatherName.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N

        jLabelGender.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jLabelGender.setText("Gender");

        jCheckBoxFemale.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jCheckBoxFemale.setText("Female");
        jCheckBoxFemale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxFemaleActionPerformed(evt);
            }
        });

        jLabelMotherName.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jLabelMotherName.setText("Mother Name");

        jComboBoxSec.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jComboBoxSec.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "<Select>", "A", "B", "C", "D" }));

        jComboBoxClass.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jComboBoxClass.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "<Select>", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X" }));
        jComboBoxClass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxClassActionPerformed(evt);
            }
        });

        jTextFieldStuName.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N

        jLabelParmAddress.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jLabelParmAddress.setText("Parmanent Address");

        jLabelSection.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jLabelSection.setText("Section");

        jLabel1TempAddress.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jLabel1TempAddress.setText("Temporary Address");

        jLabelNationality.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jLabelNationality.setText("Nationality");

        jTextFieldNationality.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N

        jRadioButtonFather.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jRadioButtonFather.setText("Father");
        jRadioButtonFather.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonFatherActionPerformed(evt);
            }
        });

        jRadioButtonMother.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jRadioButtonMother.setText("Mother");
        jRadioButtonMother.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonMotherActionPerformed(evt);
            }
        });

        jRadioButtonOther.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jRadioButtonOther.setText("Other");
        jRadioButtonOther.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonOtherActionPerformed(evt);
            }
        });

        jTextAreaTempAddress.setColumns(20);
        jTextAreaTempAddress.setRows(5);
        jScrollPane2.setViewportView(jTextAreaTempAddress);

        jTextFieldContactNo.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N

        jComboBoxMajor.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jComboBoxMajor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "<Select>", "Science", "Commerce", "Arts" }));

        javax.swing.GroupLayout jPanelInfoColectLayout = new javax.swing.GroupLayout(jPanelInfoColect);
        jPanelInfoColect.setLayout(jPanelInfoColectLayout);
        jPanelInfoColectLayout.setHorizontalGroup(
            jPanelInfoColectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelInfoColectLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelInfoColectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelGender)
                    .addGroup(jPanelInfoColectLayout.createSequentialGroup()
                        .addGroup(jPanelInfoColectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1TempAddress)
                            .addComponent(jLabelSection)
                            .addComponent(jLabelClass)
                            .addComponent(jLabelStuName)
                            .addComponent(jLabelStuID))
                        .addGap(83, 83, 83)
                        .addGroup(jPanelInfoColectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldStuName, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldStuID, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxClass, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxSec, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxMajor, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabelMajor)
                    .addComponent(jLabelParmAddress)
                    .addComponent(jLabelNationality)
                    .addGroup(jPanelInfoColectLayout.createSequentialGroup()
                        .addGroup(jPanelInfoColectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelReligion)
                            .addComponent(jLabelFatherName)
                            .addComponent(jLabelMotherName)
                            .addComponent(jLabelGuardian)
                            .addComponent(jLabelContactNo))
                        .addGap(66, 66, 66)
                        .addGroup(jPanelInfoColectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldContactNo, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanelInfoColectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanelInfoColectLayout.createSequentialGroup()
                                    .addComponent(jRadioButtonFather)
                                    .addGap(18, 18, 18)
                                    .addComponent(jRadioButtonMother)
                                    .addGap(18, 18, 18)
                                    .addComponent(jRadioButtonOther))
                                .addGroup(jPanelInfoColectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldMotherName)
                                    .addComponent(jTextFieldFatherName)
                                    .addGroup(jPanelInfoColectLayout.createSequentialGroup()
                                        .addComponent(jCheckBoxMale)
                                        .addGap(18, 18, 18)
                                        .addComponent(jCheckBoxFemale)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(jComboBoxRel, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTextFieldNationality))))))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanelInfoColectLayout.setVerticalGroup(
            jPanelInfoColectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelInfoColectLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelInfoColectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelStuID)
                    .addComponent(jTextFieldStuID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelInfoColectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldStuName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelStuName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelInfoColectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxClass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelClass))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelInfoColectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxSec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelSection))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelInfoColectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelMajor)
                    .addComponent(jComboBoxMajor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelInfoColectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabelParmAddress)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelInfoColectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel1TempAddress)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelInfoColectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jCheckBoxMale)
                    .addComponent(jCheckBoxFemale)
                    .addComponent(jLabelGender))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelInfoColectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelReligion)
                    .addComponent(jComboBoxRel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelInfoColectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabelNationality)
                    .addComponent(jTextFieldNationality, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelInfoColectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jTextFieldFatherName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelFatherName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelInfoColectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jTextFieldMotherName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelMotherName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelInfoColectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabelGuardian)
                    .addComponent(jRadioButtonFather)
                    .addComponent(jRadioButtonMother)
                    .addComponent(jRadioButtonOther))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelInfoColectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabelContactNo)
                    .addComponent(jTextFieldContactNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelControlBox.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Control", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Console", 0, 12))); // NOI18N
        jPanelControlBox.setToolTipText("Control Panel");

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

        jButtonClose.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jButtonClose.setText("Close");
        jButtonClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCloseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelControlBoxLayout = new javax.swing.GroupLayout(jPanelControlBox);
        jPanelControlBox.setLayout(jPanelControlBoxLayout);
        jPanelControlBoxLayout.setHorizontalGroup(
            jPanelControlBoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelControlBoxLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelControlBoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelControlBoxLayout.createSequentialGroup()
                        .addComponent(jButtonDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelControlBoxLayout.createSequentialGroup()
                        .addComponent(jButtonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelControlBoxLayout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(jButtonClose, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelControlBoxLayout.setVerticalGroup(
            jPanelControlBoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelControlBoxLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelControlBoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonSave)
                    .addComponent(jButtonUpdate))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelControlBoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonDelete)
                    .addComponent(jButtonRefresh))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonClose)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelSearchBox.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Search", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Console", 0, 12))); // NOI18N
        jPanelSearchBox.setToolTipText("Search Box");

        jTextFieldSearch.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jTextFieldSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldSearchKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldSearchKeyTyped(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jLabel14.setText("Student ID/Roll");

        jButtonSearch.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jButtonSearch.setText("Search");
        jButtonSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelSearchBoxLayout = new javax.swing.GroupLayout(jPanelSearchBox);
        jPanelSearchBox.setLayout(jPanelSearchBoxLayout);
        jPanelSearchBoxLayout.setHorizontalGroup(
            jPanelSearchBoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSearchBoxLayout.createSequentialGroup()
                .addGroup(jPanelSearchBoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelSearchBoxLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanelSearchBoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jButtonSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanelSearchBoxLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel14)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelSearchBoxLayout.setVerticalGroup(
            jPanelSearchBoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSearchBoxLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextFieldSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonSearch)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Student Photo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 2, 14))); // NOI18N

        jLabelStuPhoto.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jLabelStuPhoto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelStuPhoto.setText("Student Photo");
        jLabelStuPhoto.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder()));

        jButtonAttach.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jButtonAttach.setText("Attach");
        jButtonAttach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAttachActionPerformed(evt);
            }
        });

        jLabelLOC.setFont(new java.awt.Font("Times New Roman", 0, 8)); // NOI18N
        jLabelLOC.setText("location");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jButtonAttach)
                    .addComponent(jLabelLOC)
                    .addComponent(jLabelStuPhoto, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelStuPhoto, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelLOC)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonAttach)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButtonReport.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jButtonReport.setText("Generate Report");
        jButtonReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonReportActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanelInfoColect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanelSearchBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanelControlBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(73, 73, 73)
                                .addComponent(jButtonReport))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(223, 223, 223)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanelSearchBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanelControlBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonReport))
                    .addComponent(jPanelInfoColect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // Open this form in Center
        this.setLocationRelativeTo(null);
    }//GEN-LAST:event_formWindowOpened

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

    private void jRadioButtonFatherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonFatherActionPerformed
        if (jRadioButtonFather.isSelected()) {
            jRadioButtonMother.setSelected(false);
            jRadioButtonOther.setSelected(false);
        }
    }//GEN-LAST:event_jRadioButtonFatherActionPerformed

    private void jRadioButtonMotherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonMotherActionPerformed
        if (jRadioButtonMother.isSelected()) {
            jRadioButtonFather.setSelected(false);
            jRadioButtonOther.setSelected(false);
        }
    }//GEN-LAST:event_jRadioButtonMotherActionPerformed

    private void jRadioButtonOtherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonOtherActionPerformed
        if (jRadioButtonOther.isSelected()) {
            jRadioButtonFather.setSelected(false);
            jRadioButtonMother.setSelected(false);
        }
    }//GEN-LAST:event_jRadioButtonOtherActionPerformed

    private void jButtonCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCloseActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButtonCloseActionPerformed

    private void jButtonRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRefreshActionPerformed
        RefreshMethod();
        autoStuID();
    }//GEN-LAST:event_jButtonRefreshActionPerformed

    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed
        try {
            SaveMethod();   //Call "SaveMethod()" Method
        } catch (SQLException ex) {
            Logger.getLogger(StudentInformation.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonSaveActionPerformed

    private void jButtonSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSearchActionPerformed
        try {
            SearchMethod();  //Call "SearchMethod()" Method
        } catch (SQLException ex) {
            Logger.getLogger(StudentInformation.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonSearchActionPerformed

    private void jButtonUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUpdateActionPerformed
        UpdateMethod();  //Call "UpdateMethod()" Method
    }//GEN-LAST:event_jButtonUpdateActionPerformed

    private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteActionPerformed
        try {
            DeleteMethod();  //Call "DeleteMethod()" Method
        } catch (SQLException ex) {
            Logger.getLogger(StudentInformation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonDeleteActionPerformed

    private void jTextFieldSearchKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldSearchKeyTyped
        //RefreshMethod();
    }//GEN-LAST:event_jTextFieldSearchKeyTyped

    private void jTextFieldSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldSearchKeyReleased
        try {
            SearchMethod();
        } catch (SQLException ex) {
            Logger.getLogger(StudentInformation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jTextFieldSearchKeyReleased

    int classAttr;   //Global variable
    private void jComboBoxClassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxClassActionPerformed
        if (("IX".equals(jComboBoxClass.getSelectedItem()) || ("X".equals(jComboBoxClass.getSelectedItem())))) {
            jLabelMajor.setEnabled(true);
            jComboBoxMajor.setEnabled(true);
            classAttr = 0;   //Enable
        } else {
            jLabelMajor.setEnabled(false);
            jComboBoxMajor.setEnabled(false);
            classAttr = 1;   //Disable
        }
    }//GEN-LAST:event_jComboBoxClassActionPerformed

    String path;
    byte[] targetImage;
    private ImageIcon imagedata;

    private void jButtonAttachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAttachActionPerformed
        try {
            JFileChooser chooser = new JFileChooser();
            chooser.showOpenDialog(this);
            File f = chooser.getSelectedFile();
            path = f.getAbsolutePath();
            jLabelLOC.setText(path);
            
            File imgFile = new File(path);
            BufferedImage bufferedImage = ImageIO.read(imgFile);
            BufferedImage thumbnail = Thumbnails.of(bufferedImage).size(139, 138).asBufferedImage();

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(thumbnail, "png", bos);
            ByteArrayInputStream is = new ByteArrayInputStream(bos.toByteArray());
            byte[] buf = new byte[1024];
            for (int readNum; (readNum = is.read(buf)) != -1;) {
                bos.write(buf, 0, readNum);
                System.out.println("Read " + readNum + " bytes");
            }
            targetImage = bos.toByteArray();

            ImageIcon icon = new ImageIcon(thumbnail);
            jLabelStuPhoto.setIcon(icon);
        } catch (IOException ex) {
            Logger.getLogger(StudentInformation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonAttachActionPerformed

    private void jButtonReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonReportActionPerformed
        try {
            ReportStudentInfo();
            if (Desktop.isDesktopSupported()) {
                File generateFile = new File("StudentInfoReport.pdf");
                Desktop.getDesktop().open(generateFile);
            }
        } catch (IOException ex) {
            Logger.getLogger(StudentInformation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonReportActionPerformed

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StudentInformation.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        }
        //</editor-fold>
        //</editor-fold>

        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            try {
                new StudentInformation().setVisible(true);

            } catch (SQLException ex) {
                Logger.getLogger(StudentInformation.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAttach;
    private javax.swing.JButton jButtonClose;
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JButton jButtonRefresh;
    private javax.swing.JButton jButtonReport;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JButton jButtonSearch;
    private javax.swing.JButton jButtonUpdate;
    private javax.swing.JCheckBox jCheckBoxFemale;
    private javax.swing.JCheckBox jCheckBoxMale;
    private javax.swing.JComboBox<String> jComboBoxClass;
    private javax.swing.JComboBox<String> jComboBoxMajor;
    private javax.swing.JComboBox<String> jComboBoxRel;
    private javax.swing.JComboBox<String> jComboBoxSec;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel1TempAddress;
    private javax.swing.JLabel jLabelClass;
    private javax.swing.JLabel jLabelContactNo;
    private javax.swing.JLabel jLabelFatherName;
    private javax.swing.JLabel jLabelGender;
    private javax.swing.JLabel jLabelGuardian;
    private javax.swing.JLabel jLabelLOC;
    private javax.swing.JLabel jLabelMajor;
    private javax.swing.JLabel jLabelMotherName;
    private javax.swing.JLabel jLabelNationality;
    private javax.swing.JLabel jLabelParmAddress;
    private javax.swing.JLabel jLabelReligion;
    private javax.swing.JLabel jLabelSection;
    private javax.swing.JLabel jLabelStuID;
    private javax.swing.JLabel jLabelStuInfo;
    private javax.swing.JLabel jLabelStuName;
    private javax.swing.JLabel jLabelStuPhoto;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanelControlBox;
    private javax.swing.JPanel jPanelInfoColect;
    private javax.swing.JPanel jPanelSearchBox;
    private javax.swing.JRadioButton jRadioButtonFather;
    private javax.swing.JRadioButton jRadioButtonMother;
    private javax.swing.JRadioButton jRadioButtonOther;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextAreaParmAddress;
    private javax.swing.JTextArea jTextAreaTempAddress;
    private javax.swing.JTextField jTextFieldContactNo;
    private javax.swing.JTextField jTextFieldFatherName;
    private javax.swing.JTextField jTextFieldMotherName;
    private javax.swing.JTextField jTextFieldNationality;
    private javax.swing.JTextField jTextFieldSearch;
    private javax.swing.JTextField jTextFieldStuID;
    private javax.swing.JTextField jTextFieldStuName;
    // End of variables declaration//GEN-END:variables
}
