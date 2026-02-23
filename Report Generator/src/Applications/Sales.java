/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sales;

import DBConnect.DBConnection;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.ScrollPane;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Vector;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author User
 */
public class Sales extends javax.swing.JFrame {

    int tot = 0;

    Date now = new Date(); //….current date
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
    //SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat sdf1 = new SimpleDateFormat("yy-MM-dd");
    String dateInString = "01-01-1900";
    String dateStringFrom;
    String dateStringTo, supplierid, autoid;
    Date datedeflt;
    // Date datedeflt = sdf.parse(dateInString);
    // String dsa =  sdf.format(datedeflt);
    DecimalFormat df = new DecimalFormat("###.##");

    Connection con = null;
    PreparedStatement pst = null;
    Statement st = null, stDelete = null;
    ResultSet rs = null;

    ScrollPane s;
    int i_no = 0;

    private Vector<String> header1 = null;

    /**
     * Creates new form Purchase
     *
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    public Sales() throws ClassNotFoundException, SQLException {
        initComponents();

        getContentPane().setBackground(Color.WHITE);
        setIconImage(Toolkit.getDefaultToolkit().getImage(("pic//RCI.png")));
        con = DBConnection.DBConnection();

        labelutype.setVisible(false);
        labelUname.setVisible(false);
        
        jLabel4.setVisible(false);
        jDateChooser1.setVisible(false);

        getVat();
        SelectCustomer();
        autoPurID();

        SelectProduct();

        AutoCompleteDecorator.decorate(comboCustomer);
        AutoCompleteDecorator.decorate(jComboBoxSearchItem);

        jTableSales.getColumnModel().getColumn(2).setMaxWidth(0);
        jTableSales.getColumnModel().getColumn(2).setMinWidth(0);
        jTableSales.getColumnModel().getColumn(2).setPreferredWidth(0);
        jLabel19.setVisible(false);
        txtCostPrice.setVisible(false);

        //////////////////////table column size
        jTableSales.getColumnModel().getColumn(0).setPreferredWidth(40);
        jTableSales.getColumnModel().getColumn(1).setPreferredWidth(170);
        jTableSales.getColumnModel().getColumn(2).setPreferredWidth(0);
        jTableSales.getColumnModel().getColumn(3).setPreferredWidth(90);
        jTableSales.getColumnModel().getColumn(4).setPreferredWidth(90);
        jTableSales.getColumnModel().getColumn(5).setPreferredWidth(90);

        jDateChooser1.setDate(now);
        comboCustomer.requestFocusInWindow();
        /////////
        txtProNo.setEditable(false);
        txtProNo.setEditable(false);
        txtSubtotal.setEditable(false);

    }

    ///////////
    public void SelectProduct() throws ClassNotFoundException {
        try {
            Connection con2;
            con2 = DBConnection.DBConnection();
            String str = "SELECT product_title FROM addproduct where product_type ='Supplier' AND statuss=1 order by product_title asc";
            PreparedStatement pstb = con2.prepareStatement(str);

            ResultSet res = pstb.executeQuery();
            while (res.next()) {
                jComboBoxSearchItem.addItem(res.getString("product_title").trim());
            }

        } catch (SQLException e) {
            // JOptionPane.showMessageDialog(null, e.toString());
        }
    }

    /*
    public DefaultComboBoxModel getLista(String Itemdata) throws ClassNotFoundException {

        DefaultComboBoxModel model = new DefaultComboBoxModel();
        try {

            Connection con2;
            con2 = DBConnection.DBConnection();
            String query = "SELECT product_title FROM addproduct WHERE statuss=1 AND product_title LIKE '" + Itemdata + "%';";
            PreparedStatement pstd = null;
            pstd = con2.prepareStatement(query);
            ResultSet res = pstd.executeQuery();

            while (res.next()) {
                model.addElement(res.getString("product_title"));
            }
        } catch (SQLException ex) {
            System.out.println("Product List Error:" + ex);

        }

        return model;

    }
     */
    public String[] ItemNames(String Item_name) throws ClassNotFoundException {

        String[] dats = new String[5];
        try {

            Connection con2;
            con2 = DBConnection.DBConnection();
            String query = "SELECT ID,product_title,buy_price,sell_price,current_stock FROM addproduct WHERE product_type ='Supplier' AND statuss=1 AND product_title = '" + Item_name + "'";
            PreparedStatement pstd = null;
            pstd = con2.prepareStatement(query);
            ResultSet res = pstd.executeQuery();

            while (res.next()) {
                for (int i = 0; i < dats.length; i++) {
                    dats[i] = res.getString(i + 1);
                }
            }
        } catch (SQLException ex) {
            return null;
        }
        return dats;
    }

    ///////////
    public void showitem(String itemnam) throws ClassNotFoundException {
        String dats[] = ItemNames(itemnam);

        if (dats[0] != null) {
            txtProNo.setText(dats[0]);
            txtPname.setText(dats[1]);
            txtCostPrice.setText(dats[2]);
            txtSellPrice.setText(dats[3]);
            txtStockQuantity.setText(dats[4]);

        } else {

            txtProNo.setText("");
            txtPname.setText("");
            txtCostPrice.setText("");
            txtSellPrice.setText("");
            txtStockQuantity.setText("0");
        }
    }

    private boolean comparar(String cadena) {
        Object[] lista = jComboBoxSearchItem.getComponents();
        boolean encontrado = false;
        for (Object object : lista) {
            if (cadena.equals(object)) {
                encontrado = true;
                break;
            }

        }
        return encontrado;
    }

    ///////////
    /*
    public void QtyCount() {

        int f = 0;
        String s = txtQty.getText();
        String s1 = null;
        for (int i = 0; i < s.length(); i++) {
            char a = s.charAt(i);
            Character chr = new Character(a);

            if (chr.isLetter(a)) {
                f = 1;
                s1 = s.substring(0, i);
            }
        }
        if (f == 1) {
            //JOptionPane.showMessageDialog(null, "Character not allowed");
            txtQty.setText(null);
        }

        int subVal = Integer.parseInt(txtQty.getText());
        double t = Double.parseDouble(txtCostPrice.getText());

        double perVal = 0f;
        perVal = subVal * t;
        txtStockQuantity.setText("" + perVal);
    }
     */
    public void jtableCalculate() {
        double totals = 0;
        try {
            for (int i = 0; i < jTableSales.getRowCount(); i++) {
                String qty = (String) jTableSales.getValueAt(i, 4);  //sale quantity
                String rate = (String) jTableSales.getValueAt(i, 2);    //rate(taka)

                double qtyt = Double.parseDouble(qty);  //sale quantity
                double price = Double.parseDouble(rate); //rate(taka)

                double totalamt = (qtyt * price);
                String total = Double.toString(totalamt);
                jTableSales.setValueAt(total, i, 5);  //total

            }
        } catch (NumberFormatException e) {
            System.out.println("calculate1 Error:" + e);

        }

        try {
            for (int i = 0; i < jTableSales.getRowCount(); i++) {
                String ItemTotal = (String) jTableSales.getValueAt(i, 5);  //Total
                double sbt = Double.parseDouble(ItemTotal);
                totals = (totals + sbt);
                txtSubtotal.setText("" + totals);
                double sbtt = Double.parseDouble(txtSubtotal.getText());
                double DisPer = Double.parseDouble(txtDisper.getText());
                double DisVal = 0;
                DisVal = ((sbtt * DisPer) / 100);
                txtDisAmt.setText("" + DisVal);
                double TotalAftDis = (sbtt - DisVal);

                double taxVal = Double.parseDouble(txtVatPer.getText());
                double perVal = 0;
                perVal = ((TotalAftDis * taxVal) / 100);
                txtTax.setText("" + perVal);
                double total3 = (TotalAftDis + perVal);
                txtTotalgrand.setText("" + total3);

            }

        } catch (NumberFormatException e) {
            System.err.println("Calculate Error: " + e);
        }

    }

    public void calculate() {
        //calculate
        double totals = 0;
        // double totalcostamt = Double.parseDouble(txtCostPrice.getText());
        //  int qnty = Integer.parseInt(txtQty.getText());
        // double totalcostamount = (totalcostamt*qnty);

        try {
            for (int i = 0; i < jTableSales.getRowCount(); i++) {
                //   jTablePurchase.setValueAt(totalcostamount, i, 5);  //total
                String ItemTotal = (String) jTableSales.getValueAt(i, 5);  //Total
                double sbt = Double.parseDouble(ItemTotal);
                totals = (totals + sbt);

            }
        } catch (NumberFormatException ex) {
            System.err.println("Calculate Error: " + ex);
        }

        try {
            txtSubtotal.setText("" + totals);

            double sbt = Double.parseDouble(txtSubtotal.getText());
            double DisPer = Double.parseDouble(txtDisper.getText());
            double DisVal = 0;
            DisVal = ((sbt * DisPer) / 100);
            txtDisAmt.setText("" + DisVal);
            double TotalAftDis = (sbt - DisVal);

            double taxVal = Double.parseDouble(txtVatPer.getText());
            double perVal = 0;
            perVal = ((TotalAftDis * taxVal) / 100);
            txtTax.setText("" + perVal);
            double total3 = (TotalAftDis + perVal);
            txtTotalgrand.setText("" + total3);

        } catch (NumberFormatException e) {
            System.err.println("Calculate Error: " + e);
        }
    }

    public void SelectCustomer() throws ClassNotFoundException {
        try {
            Connection con2;
            con2 = DBConnection.DBConnection();
            PreparedStatement pstc = con2.prepareStatement("select distinct(customer_title) AS customer_title from add_customer where statuss = 1 order by customer_title asc");
            ResultSet rsc = pstc.executeQuery();
            while (rsc.next()) {
                comboCustomer.addItem("" + rsc.getString("customer_title"));

            }
        } catch (SQLException ex) {
            // Logger.getLogger(AddItem.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Supplier search Error:" + ex);
        }
    }

    public void SaveInvoice() throws ClassNotFoundException {

        String ID = "";
        try {
            Connection con2;
            con2 = DBConnection.DBConnection();
            String query = "SELECT * FROM sales where inv_no = '" + txtInvoiceNo.getText() + "'";
            PreparedStatement pstc = con2.prepareStatement(query);
            // pstc.setString(1, txtInvoice.getText());
            ResultSet rsc = pstc.executeQuery();

            if (rsc.next()) {

                System.out.println("Already Exist!!!");

            } else {

                try {
                    //txtInvoiceNo.setText(sdf1.format(now)+"-" + ID);
                    PreparedStatement psts = con2.prepareStatement("insert into sales(pur_no,inv_no,usertype,username) values(?,?,?,?)");
                    psts.setString(1, autoid);
                    psts.setString(2, txtInvoiceNo.getText());
                    psts.setString(3, labelutype.getText());
                    psts.setString(4, labelUname.getText());
                    psts.executeUpdate();
                } catch (SQLException ex) {
                    //   Logger.getLogger(TeacherDetails.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("Saved Error:" + ex);
                }

            }
        } catch (SQLException ex1) {
            //    ex1.printStackTrace();
            System.out.println("Duplicate Error:" + ex1);
        }

        //return ID;
    }

    ///auto id generated
    public void autoPurID() throws ClassNotFoundException {
        String ID = "";
        try {
            Connection con2;
            con2 = DBConnection.DBConnection();
            String queryStr = "SELECT COALESCE(MAX(pur_no),0)+1 AS pur_no FROM sales";
            PreparedStatement PS = con2.prepareStatement(queryStr);
            ResultSet resultQuery = PS.executeQuery();
            if (resultQuery.next()) {
                ID = resultQuery.getString("pur_no");
                autoid = ID;
                txtInvoiceNo.setText(ID + "-" + sdf1.format(now));
            }
        } catch (SQLException ex) {
            //   Logger.getLogger(TeacherDetails.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ID Error:" + ex);
        }

        // return ID;
    }

    public void SupplierID() throws ClassNotFoundException {

        try {
            Connection con2;
            con2 = DBConnection.DBConnection();
            String str = "select * from add_customer where customer_title = ?";
            PreparedStatement pstsc = con2.prepareStatement(str);
            pstsc.setString(1, comboCustomer.getSelectedItem().toString());

            ResultSet res = pstsc.executeQuery();
            if (res.next()) {
                // jComboBoxStoreName.addItem(res.getString("scNAME").trim());
                supplierid = res.getString("ID");
                System.out.println("Customer ID:" + supplierid);
            }

        } catch (SQLException e) {
            // JOptionPane.showMessageDialog(null, e.toString());
            System.out.println("Supplier ID:" + e);
        }
    }

    public void AddData() {

        try {

            ////////////////Add to table
            String itemno = txtProNo.getText();
            String proName = txtPname.getText();
//            String brand = txtmake.getText();
            String CostPrice = txtCostPrice.getText();
            String sellprice = txtSellPrice.getText();
            String Qty = txtQty.getText();
            double qnty = Double.parseDouble(Qty);
            double costprce = Double.parseDouble(CostPrice);
            double totalamt = (costprce * qnty);
            // String Unit = txtUnit.getText();
            String Total = txtStockQuantity.getText();
            DefaultTableModel model = (DefaultTableModel) jTableSales.getModel();
            if (qnty < 1) {
                 JOptionPane.showMessageDialog(null, "Invalid Quantity!!!", "Warning", JOptionPane.ERROR_MESSAGE);

            } else {
                model.addRow(new Object[]{itemno, proName, CostPrice, sellprice, Qty, totalamt + ""});

                txtQty.setText("");
                txtCostPrice.setText("");
                txtStockQuantity.setText("");
                txtProNo.setText("");
                txtPname.setText("");
//            txtmake.setText("");
                txtSellPrice.setText("");
                txtStockQuantity.setText("");
                //       txtUnit.setText("");
//            txtAvQty.setText("");
            }

        } catch (NumberFormatException ex) {
            System.out.println("Add Error = " + ex);
        }
    }

    public void SaveData() throws ClassNotFoundException, SQLException {

        java.util.Date d2 = jDateChooser1.getDate();
        java.sql.Date sqldatechoose = new java.sql.Date(d2.getTime());
        String invoice = "";
        String textid = txtInvoiceNo.getText();
        // int IID = Integer.parseInt(textid.substring(2));
        try {
            Connection con2;
            con2 = DBConnection.DBConnection();

            try {

                PreparedStatement psts = con2.prepareStatement("update sales SET Cus_name = ?,Cus_no = ?,Sub_total = ?,DisPer = ?,DisAmount = ?,Tax_per = ?,Tax = ?,Total = ?,Payment = ?,DueAmt = ?,Po_date = CURDATE(),ChooseDate = ? where inv_no = ?");
                psts.setString(1, comboCustomer.getSelectedItem().toString());
                psts.setString(2, supplierid);
                psts.setString(3, txtSubtotal.getText());
                psts.setString(4, txtDisper.getText());
                psts.setString(5, txtDisAmt.getText());
                psts.setString(6, txtVatPer.getText());
                psts.setString(7, txtTax.getText());
                psts.setString(8, txtTotalgrand.getText());
                psts.setString(9, txtPayment.getText());
                psts.setString(10, txtDue.getText());
                psts.setDate(11, sqldatechoose);
                psts.setString(12, textid);
                int i = psts.executeUpdate();
                if (i != 0) {
                    JOptionPane.showMessageDialog(null, "Data Saved Successfully!!", "Message", 1);
                } else {
                    JOptionPane.showMessageDialog(null, "Data Not Saved!!", "Message", 1);
                }
                //  JOptionPane.showMessageDialog(null, "Data Saved Sucessfully....");

            } catch (HeadlessException | SQLException e) {
                System.out.println("Saved Data Update Error:" + e);
            }

            try {

                for (int i = 0; i < jTableSales.getRowCount(); i++) {
                    PreparedStatement pst1;
                    String sqqldum = "insert into sales_details(Invoice_no,product_No,product_title,CostPrice,Sell_Price,Unit_Quantity,Total,AddDate,ChooseDate)"
                            + " values('" + textid + "',"
                            + "'" + jTableSales.getValueAt(i, 0) + "','" + jTableSales.getValueAt(i, 1) + "',"
                            + "'" + jTableSales.getValueAt(i, 2) + "',"
                            + "'" + jTableSales.getValueAt(i, 3) + "','" + jTableSales.getValueAt(i, 4) + "','" + jTableSales.getValueAt(i, 5) + "',CURDATE(),'" + sqldatechoose + "')";
                    pst1 = con2.prepareStatement(sqqldum);
                    pst1.executeUpdate();
                }
                //  JOptionPane.showMessageDialog(null, "Data Saved Successfully", "Message", 1);
                System.out.println("Sales Details Data Saved");
            } catch (SQLException | HeadlessException ex1) {
                System.out.println("Data Saved Sales Details Error:" + ex1);

            }

            //stock
            try {

                for (int i = 0; i < jTableSales.getRowCount(); i++) {
                    PreparedStatement pst1;
                    String sqqldum = "UPDATE addproduct SET current_stock = current_stock - '" + jTableSales.getValueAt(i, 4) + "' where ID = '" + jTableSales.getValueAt(i, 0) + "'";
                    pst1 = con2.prepareStatement(sqqldum);
                    pst1.executeUpdate();
                }
                //  JOptionPane.showMessageDialog(null, "Data Saved Successfully", "Message", 1);
                System.out.println("Stock Updated + ");
            } catch (SQLException | HeadlessException ex1) {
                System.out.println("Stock updated Error:" + ex1);

            }

        } catch (HeadlessException ex) {
            System.out.print("Saved Error" + ex);
        }
        
        
            //////product master table
            try {
                //jTableSales.setValueAt(total, i, 8);
                for (int i = 0; i < jTableSales.getRowCount(); i++) {
                    PreparedStatement pst1p;
                    String sqqldum = "insert into ProductMaster(AddDate,Pid,ProductName,OpeningStock,PurchaseQty,SalesQty,ReturnQty,ClosingStock,TranID,DateChooser,CostPrice,SalesPrice,ProductDateID)"
                            + " values(CURDATE(),'" + jTableSales.getValueAt(i, 0) + "','" + jTableSales.getValueAt(i, 1) + "','" + "0" + "','"+"0"+"','"+jTableSales.getValueAt(i, 4)+"','"+"0"+"','"+"0"+"','" + "S-"+""+txtInvoiceNo.getText() + "','" + sqldatechoose + "','"+jTableSales.getValueAt(i, 2)+"','"+jTableSales.getValueAt(i, 3)+"','" + jTableSales.getValueAt(i, 0)+"("+sqldatechoose+")" + "') ";

                    pst1p = con.prepareStatement(sqqldum);
                    pst1p.executeUpdate();
                }
                //  JOptionPane.showMessageDialog(null, "Data Saved Successfully", "Message", 1);
                System.out.println("Product Master Data Saved");
            } catch (SQLException | HeadlessException ex1) {
                System.err.println("Product Master Error:" + ex1);
            }

    }

    public void getVat() throws ClassNotFoundException {
        //get vat

        try {
            Connection con2;
            con2 = DBConnection.DBConnection();
            String query = "SELECT VatPer FROM addvat ";
            PreparedStatement pstm = con2.prepareStatement(query);
            ResultSet rsms = pstm.executeQuery();
            if (rsms.next()) {
                txtVatPer.setText(rsms.getString("VatPer"));
            }//end if
        } catch (SQLException ex) {
            System.out.println("Vat Error:" + ex);

        }
    }

    public boolean duplication() throws ClassNotFoundException {
        String invoice = "";
        String textid = txtInvoiceNo.getText();
        // int IID = Integer.parseInt(textid.substring(2));
        // String invoice = "";
        int inv = 0;
        try {

            Connection con2;
            con2 = DBConnection.DBConnection();
            String query = "SELECT * FROM sales_details where Invoice_no = '" + textid + "'";
            PreparedStatement pstc = con2.prepareStatement(query);
            // pstc.setString(1, txtInvoice.getText());
            ResultSet rsc = pstc.executeQuery();

            if (rsc.next()) {
                // invoice = rsc.getString("Bill_no");
                //inv = Integer.parseInt(rsc.getString("Bill_no"));
                //   JOptionPane.showMessageDialog(null, "Data already existed", "Duplication Error", JOptionPane.ERROR_MESSAGE);
                return true;
            }
        } catch (SQLException ex1) {
            //    ex1.printStackTrace();
            System.out.println("Duplicate Error:" + ex1);
        }
        return false;
    }//end duplication()

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        comboCustomer = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        txtInvoiceNo = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jComboBoxSearchItem = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        txtProNo = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtPname = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txtCostPrice = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtSellPrice = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtQty = new javax.swing.JTextField();
        txtStockQuantity = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jPanel1 = new javax.swing.JPanel();
        remove = new javax.swing.JButton();
        btnPrint = new javax.swing.JButton();
        Btnnew = new javax.swing.JButton();
        BtnSave = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        txtDisAmt = new javax.swing.JTextField();
        txtSubtotal = new javax.swing.JTextField();
        txtTax = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txtDue = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        txtPayment = new javax.swing.JTextField();
        txtDisper = new javax.swing.JTextField();
        txtTotalgrand = new javax.swing.JTextField();
        txtVatPer = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableSales = new javax.swing.JTable();
        labelutype = new javax.swing.JLabel();
        labelUname = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Sales");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sales", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18))); // NOI18N

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Invoice No");

        comboCustomer.setEditable(true);
        comboCustomer.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        comboCustomer.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " " }));
        comboCustomer.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboCustomerItemStateChanged(evt);
            }
        });
        comboCustomer.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                comboCustomerFocusLost(evt);
            }
        });
        comboCustomer.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                comboCustomerPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        comboCustomer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                comboCustomerMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                comboCustomerMouseReleased(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Customer");

        txtInvoiceNo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtInvoiceNo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Search", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        jComboBoxSearchItem.setEditable(true);
        jComboBoxSearchItem.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jComboBoxSearchItem.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Search Product" }));
        jComboBoxSearchItem.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jComboBoxSearchItem.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                jComboBoxSearchItemPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        jComboBoxSearchItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBoxSearchItemMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBoxSearchItem, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBoxSearchItem, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel10.setText("Product No.");

        txtProNo.setEditable(false);

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel11.setText("Product Title");

        txtPname.setEditable(false);

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel19.setText("Cost Price");

        txtCostPrice.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtCostPriceMouseClicked(evt);
            }
        });
        txtCostPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCostPriceActionPerformed(evt);
            }
        });
        txtCostPrice.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCostPriceKeyTyped(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel16.setText("Sell Price");

        txtSellPrice.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtSellPriceMouseClicked(evt);
            }
        });
        txtSellPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSellPriceActionPerformed(evt);
            }
        });
        txtSellPrice.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSellPriceKeyTyped(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel17.setText("Quantity");

        txtQty.setText("0");
        txtQty.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtQtyMouseClicked(evt);
            }
        });
        txtQty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtQtyActionPerformed(evt);
            }
        });
        txtQty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtQtyKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtQtyKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtQtyKeyTyped(evt);
            }
        });

        txtStockQuantity.setEditable(false);

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel18.setText("Current Stock");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(comboCustomer, 0, 296, Short.MAX_VALUE)
                            .addComponent(txtInvoiceNo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel19)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11))
                                .addGap(63, 63, 63)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtProNo)
                                    .addComponent(txtPname)
                                    .addComponent(txtCostPrice)
                                    .addComponent(txtSellPrice)
                                    .addComponent(txtQty)))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addGap(72, 72, 72)
                                .addComponent(txtStockQuantity)))
                        .addContainerGap())))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtInvoiceNo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(comboCustomer, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE))
                .addGap(23, 23, 23)
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtProNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtPname)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel19))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCostPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txtSellPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txtQty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtStockQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addContainerGap())
        );

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Date");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        remove.setBackground(new java.awt.Color(204, 255, 255));
        remove.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        remove.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_cancel.png"))); // NOI18N
        remove.setText("Remove");
        remove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeActionPerformed(evt);
            }
        });

        btnPrint.setBackground(new java.awt.Color(204, 255, 255));
        btnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_print.png"))); // NOI18N
        btnPrint.setText("Print");
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });

        Btnnew.setBackground(new java.awt.Color(204, 255, 255));
        Btnnew.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Btnnew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_new.png"))); // NOI18N
        Btnnew.setText("Add New");
        Btnnew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnnewActionPerformed(evt);
            }
        });

        BtnSave.setBackground(new java.awt.Color(204, 255, 255));
        BtnSave.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        BtnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_save.png"))); // NOI18N
        BtnSave.setText("Save");
        BtnSave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnSaveMouseClicked(evt);
            }
        });
        BtnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSaveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(remove)
                .addGap(18, 18, 18)
                .addComponent(BtnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Btnnew)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPrint, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnSave)
                    .addComponent(Btnnew)
                    .addComponent(btnPrint)
                    .addComponent(remove))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel20.setText("Sub Total");

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel22.setText("Discount");

        txtDisAmt.setEditable(false);
        txtDisAmt.setText("0");

        txtSubtotal.setEditable(false);
        txtSubtotal.setText("0");

        txtTax.setEditable(false);
        txtTax.setText("0");

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel21.setText("Vat");

        txtDue.setEditable(false);
        txtDue.setText("0");

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel25.setText("Due");

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel23.setText("Grand Total");

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel24.setText("Payment");

        txtPayment.setText("0");
        txtPayment.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtPaymentMouseClicked(evt);
            }
        });
        txtPayment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPaymentActionPerformed(evt);
            }
        });
        txtPayment.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPaymentKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPaymentKeyTyped(evt);
            }
        });

        txtDisper.setText("0");
        txtDisper.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDisperFocusLost(evt);
            }
        });
        txtDisper.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtDisperMouseClicked(evt);
            }
        });
        txtDisper.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDisperActionPerformed(evt);
            }
        });
        txtDisper.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDisperKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDisperKeyTyped(evt);
            }
        });

        txtTotalgrand.setEditable(false);
        txtTotalgrand.setText("0");

        txtVatPer.setText("0");
        txtVatPer.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtVatPerFocusLost(evt);
            }
        });
        txtVatPer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtVatPerMouseClicked(evt);
            }
        });
        txtVatPer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtVatPerActionPerformed(evt);
            }
        });
        txtVatPer.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtVatPerKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtVatPerKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21)
                    .addComponent(jLabel20)
                    .addComponent(jLabel23)
                    .addComponent(jLabel22)
                    .addComponent(jLabel24)
                    .addComponent(jLabel25))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtTotalgrand)
                        .addGroup(jPanel7Layout.createSequentialGroup()
                            .addComponent(txtVatPer, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(txtTax, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(txtSubtotal, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(txtPayment, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(txtDue, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(txtDisper, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtDisAmt, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(txtSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(txtDisper, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDisAmt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtVatPer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21)
                    .addComponent(txtTax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTotalgrand, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPayment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25))
                .addContainerGap())
        );

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jTableSales.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTableSales.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jTableSales.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Product Title", "Cost Price", "Sell Price", "Quantity", "Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableSales.setGridColor(new java.awt.Color(255, 255, 255));
        jTableSales.setRowHeight(22);
        jTableSales.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableSalesMouseClicked(evt);
            }
        });
        jTableSales.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTableSalesKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTableSales);

        labelutype.setText("jLabel1");

        labelUname.setText("jLabel3");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jScrollPane1)
                                .addContainerGap())))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(143, 143, 143)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelUname)
                            .addComponent(labelutype))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelutype)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelUname)
                .addGap(31, 31, 31))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // center
        this.setLocationRelativeTo(null);
    }//GEN-LAST:event_formWindowOpened

    private void txtQtyKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQtyKeyReleased


    }//GEN-LAST:event_txtQtyKeyReleased

    private void removeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeActionPerformed

        DefaultTableModel model = (DefaultTableModel) jTableSales.getModel();
        // get selected row index
        try {
            int SelectedRowIndex = jTableSales.getSelectedRow();
            model.removeRow(SelectedRowIndex);
        } catch (Exception ex) {
            System.err.println("Delete Row Exception: " + ex);
        }
        calculate();
    }//GEN-LAST:event_removeActionPerformed

    private void BtnSaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnSaveMouseClicked
        //save
    }//GEN-LAST:event_BtnSaveMouseClicked

    private void BtnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSaveActionPerformed
        //save

        try {

            //&& comboSalesTo.getSelectedItem().equals("") && txtContact.getText().equals("")
            /*
            if(comboSupplierFrom.getSelectedItem().toString().equalsIgnoreCase("Search Supplier")){
                 JOptionPane.showMessageDialog(null, "Please Select Supplier!!!", "Empty", JOptionPane.ERROR_MESSAGE);
            }
             */
            if (duplication() == false && jTableSales.getRowCount() > 0) {
                SaveInvoice();
                SaveData();

                printSalesInvoice();
                //JOptionPane.showMessageDialog(null, "Field is empty OR Data Existed!!!", "Empty", JOptionPane.ERROR_MESSAGE); 

            } else {
                // SaveData(); 
                JOptionPane.showMessageDialog(null, "Field is empty OR Data Existed!!!", "Empty", JOptionPane.ERROR_MESSAGE);
                // JOptionPane.showMessageDialog(null, "Field is empty", "Empty", JOptionPane.ERROR_MESSAGE); 
            }

        } catch (HeadlessException e) {
            System.out.println("Saved Error:" + e);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Sales.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_BtnSaveActionPerformed

    private void BtnnewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnnewActionPerformed

        try {
            DefaultTableModel model = (DefaultTableModel) jTableSales.getModel();
            model.setRowCount(0);

            autoPurID();
            // SaveInvoice();
            //////////////////
            txtDisper.setText("0");
            txtDisAmt.setText("0");
            txtPayment.setText("0");
            txtDue.setText("0");
            txtVatPer.setText("0");

            comboCustomer.setSelectedItem("");
            jComboBoxSearchItem.setSelectedItem("Search Product");

            txtProNo.setText("");
            txtPname.setText("");
            txtSellPrice.setText("");
            txtQty.setText("");
            txtStockQuantity.setText("");
            txtSubtotal.setText("0");
            txtTax.setText("0");
            txtSubtotal.setText("0");
            txtTotalgrand.setText("0");
            ///////////////////////
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Sales.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_BtnnewActionPerformed

    private void txtPaymentKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPaymentKeyReleased
        // calculate due

    }//GEN-LAST:event_txtPaymentKeyReleased

    private void txtDisperFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDisperFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDisperFocusLost

    private void txtDisperKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDisperKeyReleased
        // calculate
        // calculate();
    }//GEN-LAST:event_txtDisperKeyReleased

    private void txtVatPerFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtVatPerFocusLost

    }//GEN-LAST:event_txtVatPerFocusLost

    private void txtVatPerKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtVatPerKeyReleased

        /*
        double sbt = Double.parseDouble(txtSubtotal.getText());
        double taxVal = Double.parseDouble(txtPer.getText());
        double perVal = 0;
        perVal = ((sbt * taxVal) / 100);
        txtTax.setText("" + perVal);
        double total3 = sbt + perVal;
        txtTotal2.setText("" + total3);
         */

    }//GEN-LAST:event_txtVatPerKeyReleased

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        // print
        printSalesInvoice();

    }//GEN-LAST:event_btnPrintActionPerformed

    private void comboCustomerItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboCustomerItemStateChanged

    }//GEN-LAST:event_comboCustomerItemStateChanged

    private void comboCustomerFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_comboCustomerFocusLost

    }//GEN-LAST:event_comboCustomerFocusLost

    private void comboCustomerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_comboCustomerMouseClicked
        //comboSupplierFrom.select(0, comboSupplierFrom.getSelectedIndex().length());
    }//GEN-LAST:event_comboCustomerMouseClicked

    private void comboCustomerMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_comboCustomerMouseReleased
        //        pono.setFocusable(true);
    }//GEN-LAST:event_comboCustomerMouseReleased

    private void jComboBoxSearchItemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBoxSearchItemMouseClicked
        // click
        /*
        String itemnames = jComboBoxSearchItem.getEditor().getItem().toString();
        showitem(itemnames);
         */

    }//GEN-LAST:event_jComboBoxSearchItemMouseClicked

    private void txtQtyKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQtyKeyPressed
        // calculate
        
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            
            
            
            ///////////////////////////

            try {
                
                Connection con2;
                con2 = DBConnection.DBConnection();
                String str = "SELECT product_title FROM addproduct where product_type ='Supplier' AND statuss=1 AND '" + txtQty.getText() + "' <=current_stock";
                PreparedStatement pstb = con2.prepareStatement(str);

                ResultSet res = pstb.executeQuery();
                if (res.next()) {

                    //if product is available
                    //jComboBoxSearchItem.addItem(res.getString("product_title").trim());
                    ////after check
                    
                    DefaultTableModel model = (DefaultTableModel) jTableSales.getModel();
                    String itemname = jComboBoxSearchItem.getSelectedItem().toString();
                    //Object[] row = {itemcode, data2, data3, data4};
                    
                    String tabledata = "";
                    boolean exists = false;
                    
                    for (int i = 0; i < jTableSales.getRowCount(); i++) {
                        tabledata = jTableSales.getValueAt(i, 1).toString().trim();
                        System.out.println("******Column Value: " + tabledata);
                        if (itemname.equals(tabledata)) {
                            exists = true;
                            break;
                        }
                    }
                    if (!exists) {
                        //QtyCount();
                        AddData();//add to table
                        calculate();//calculation
                        jComboBoxSearchItem.requestFocusInWindow();

                    } else {
                        JOptionPane.showMessageDialog(null, "Data already exist.", "Warning", JOptionPane.PLAIN_MESSAGE);
                    }

                    //////end of check
                } else {

                    ///if product is not available
                    JOptionPane.showMessageDialog(null, "Product Stock is not Available!!!", "Warning", JOptionPane.ERROR_MESSAGE);

                }

            } catch (SQLException e) {
                // JOptionPane.showMessageDialog(null, e.toString());
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Sales.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            /////////////////////////

        }

    }//GEN-LAST:event_txtQtyKeyPressed

    private void txtPaymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPaymentActionPerformed

        txtDue.setText("");
        double totalAmt = Double.parseDouble(txtTotalgrand.getText());
        double payment = Double.parseDouble(txtPayment.getText());
        double dueAmtt = (totalAmt - payment);
        txtDue.setText("" + dueAmtt);

    }//GEN-LAST:event_txtPaymentActionPerformed

    private void txtDisperActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDisperActionPerformed
        txtDisAmt.setText("0");

        calculate();
        txtVatPer.requestFocusInWindow();
        txtVatPer.select(0, txtVatPer.getText().length());

    }//GEN-LAST:event_txtDisperActionPerformed

    private void txtVatPerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtVatPerActionPerformed
        // vat
        txtTax.setText("0");
        calculate();
        txtPayment.requestFocusInWindow();
        txtPayment.select(0, txtPayment.getText().length());
    }//GEN-LAST:event_txtVatPerActionPerformed

    private void jComboBoxSearchItemPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jComboBoxSearchItemPopupMenuWillBecomeInvisible
        try {
            // search
            ItemNames(jComboBoxSearchItem.getSelectedItem().toString());
            showitem(jComboBoxSearchItem.getSelectedItem().toString());
            txtQty.requestFocus();
            txtQty.select(0, txtQty.getText().length());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Sales.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jComboBoxSearchItemPopupMenuWillBecomeInvisible

    private void jTableSalesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableSalesKeyPressed
        // key pressed
        jtableCalculate();
    }//GEN-LAST:event_jTableSalesKeyPressed

    private void jTableSalesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableSalesMouseClicked
        jtableCalculate();
    }//GEN-LAST:event_jTableSalesMouseClicked

    private void txtCostPriceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCostPriceMouseClicked
        // select
        txtCostPrice.select(0, txtCostPrice.getText().length());

    }//GEN-LAST:event_txtCostPriceMouseClicked

    private void txtSellPriceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtSellPriceMouseClicked

        txtSellPrice.select(0, txtSellPrice.getText().length());

    }//GEN-LAST:event_txtSellPriceMouseClicked

    private void txtQtyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtQtyMouseClicked
        txtQty.select(0, txtQty.getText().length());
    }//GEN-LAST:event_txtQtyMouseClicked

    private void txtCostPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCostPriceActionPerformed
        txtSellPrice.requestFocus();
        txtSellPrice.select(0, txtSellPrice.getText().length());

    }//GEN-LAST:event_txtCostPriceActionPerformed

    private void txtSellPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSellPriceActionPerformed
        txtQty.requestFocus();
        txtQty.select(0, txtQty.getText().length());
    }//GEN-LAST:event_txtSellPriceActionPerformed

    private void comboCustomerPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_comboCustomerPopupMenuWillBecomeInvisible
        try {
            SupplierID();
            jComboBoxSearchItem.requestFocus();
            // txtCostPrice.select(0, txtCostPrice.getText().length());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Sales.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_comboCustomerPopupMenuWillBecomeInvisible

    private void txtDisperMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtDisperMouseClicked
        txtDisper.select(0, txtDisper.getText().length());
    }//GEN-LAST:event_txtDisperMouseClicked

    private void txtVatPerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtVatPerMouseClicked
        txtVatPer.select(0, txtVatPer.getText().length());
    }//GEN-LAST:event_txtVatPerMouseClicked

    private void txtPaymentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtPaymentMouseClicked
        txtPayment.select(0, txtPayment.getText().length());
    }//GEN-LAST:event_txtPaymentMouseClicked

    private void txtCostPriceKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCostPriceKeyTyped
        
        char c = evt.getKeyChar();
        if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE) || (c == '.'))) {
            evt.consume();
        }
        
    }//GEN-LAST:event_txtCostPriceKeyTyped

    private void txtSellPriceKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSellPriceKeyTyped
        
        char c = evt.getKeyChar();
        if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE) || (c == '.'))) {
            evt.consume();
        }
        
    }//GEN-LAST:event_txtSellPriceKeyTyped

    private void txtQtyKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQtyKeyTyped
        
        char c = evt.getKeyChar();
        if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE) || (c == '.'))) {
            evt.consume();
        }
        
    }//GEN-LAST:event_txtQtyKeyTyped

    private void txtDisperKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDisperKeyTyped
        
        char c = evt.getKeyChar();
        if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE) || (c == '.'))) {
            evt.consume();
        }
        
    }//GEN-LAST:event_txtDisperKeyTyped

    private void txtVatPerKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtVatPerKeyTyped
        
        char c = evt.getKeyChar();
        if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE) || (c == '.'))) {
            evt.consume();
        }
        
    }//GEN-LAST:event_txtVatPerKeyTyped

    private void txtPaymentKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPaymentKeyTyped
        
        char c = evt.getKeyChar();
        if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE) || (c == '.'))) {
            evt.consume();
        }
        
    }//GEN-LAST:event_txtPaymentKeyTyped

    private void txtQtyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQtyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQtyActionPerformed

    public void printSalesInvoice() {

        try {
            Connection con2;
            con2 = DBConnection.DBConnection();
            //String report = "‪E:\\project\\Result Processing System_project\\24_04_2016_Rps\\CollegeRPS\\Reports\\TabulationSheet.jrxml";
            String report = "‪Reports/SalesInvoice.jrxml";
            // String report = "TabulationSheet.jasper";
            //String report = "Reports/Tabulation.jrxml";
            //String report = "Reports/Invoice.jrxml";
            //Map<String, Object> paramerter = new HashMap<String, Object>();
            // Map<String, Object> paramerter = new HashMap<>();
            //HashMap paramerter = new HashMap();
            Map<String, Object> paramerter = new HashMap<>();
//        paramerter.put("Roll",textFieldRollForSearch.getText());
//        paramerter.put("CurrentYear",yearChooserYearSS.getYear());
//        paramerter.put("GroupName",ComboBoxGroup.getSelectedItem().toString());
//        paramerter.put("SectionName",comboBoxSection.getSelectedItem().toString());
            paramerter.put("InvoiceNo", txtInvoiceNo.getText());

            //
            // Application path
            /*
				//String report = null;
				try {
					//report = new File(".").getCanonicalPath()+ "\\TabulationSheet.jrxml";
                                       
				} catch (IOException e1) {
                                        System.err.println("Jasper File Er: "+e1);
				}
             */
            try {

                // JasperReport report2 = (JasperReport) JRLoader.loadObject(new File("TabulationSheet.jasper"));
                // JasperDesign jd  = JRXmlLoader.load(getClass().getResourceAsStream("/Reports/TabulationSheet.jrxml")); 
                JasperDesign jd = JRXmlLoader.load("Reports/SalesInvoice.jrxml");

                //  JasperDesign jd  = JRXmlLoader.load(new File("").getAbsolutePath()+"//Reports//TabulationSheet.jrxml");
                //JasperReport jp = JasperCompileManager.compileReport(getClass().getResourceAsStream("/TabulationSheet.jrxml"));
                //JasperReport jp = JasperCompileManager.compileReport("TabulationSheet.jrxml");
                //   JasperReport report3 = JasperCompileManager.compileReport(new File("").getAbsolutePath()+"TabulationSheet.jrxml");
                JasperReport jp = JasperCompileManager.compileReport(jd);
                // Report Viewer
                //JasperReport ir = JasperCompileManager.compileReport(report);

                JasperPrint ip = JasperFillManager.fillReport(jp, paramerter, con2);
                //JasperViewer.viewReport(ip);
                JasperViewer aJasperViewer = new JasperViewer(ip, false);
                aJasperViewer.setVisible(true);
                aJasperViewer.setExtendedState(JFrame.MAXIMIZED_BOTH);

            } catch (JRException e) {
                System.err.println("Jasper View Er: " + e);
            }
            //  InputStream file = getClass().getResourceAsStream(report);
            //String path = getServletContext().getRealPath("/jrxml/employeesList.jrxml");
            //  FileInputStream input = new FileInputStream(report);
            // FileInputStream input = new FileInputStream(new File(report));
            //   JasperDesign jasperDesign = JRXmlLoader.load(input);

            // InputStream is=this.getClass().getResourceAsStream("Reports/TabulationSheet.jrxml");
            //  JasperReport jasperReport = JasperCompileManager.compileReport(is);
            // JasperDesign jasperDesign = JRXmlLoader.load(file);
            //paramerter.put("Date",invoiceDateField.getText());			  			
            //JasperReport j4 = JasperCompileManager.compileReport(jasperDesign);
            //JasperReport j4 = JasperCompileManager.compileReport(report);
            //JasperPrint jp = JasperFillManager.fillReport(j4,paramerter,connection);
            // JasperPrint jp = JasperFillManager.fillReport(j4,null,con);
            // JasperViewer.viewReport(jp,false);
            //JasperViewer aJasperViewer = new JasperViewer(jp,false);
            //aJasperViewer.setVisible(true);
            //aJasperViewer.setExtendedState(JFrame.MAXIMIZED_BOTH);		  						  			          
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Jasper Print Er: " + e);
        }

    }

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
            java.util.logging.Logger.getLogger(Sales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Sales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Sales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Sales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new Sales().setVisible(true);
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(Sales.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnSave;
    private javax.swing.JButton Btnnew;
    private javax.swing.JButton btnPrint;
    private javax.swing.JComboBox comboCustomer;
    private javax.swing.JComboBox<String> jComboBoxSearchItem;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableSales;
    public javax.swing.JLabel labelUname;
    public javax.swing.JLabel labelutype;
    private javax.swing.JButton remove;
    private javax.swing.JTextField txtCostPrice;
    private javax.swing.JTextField txtDisAmt;
    private javax.swing.JTextField txtDisper;
    private javax.swing.JTextField txtDue;
    private javax.swing.JLabel txtInvoiceNo;
    private javax.swing.JTextField txtPayment;
    private javax.swing.JTextField txtPname;
    private javax.swing.JTextField txtProNo;
    private javax.swing.JTextField txtQty;
    private javax.swing.JTextField txtSellPrice;
    private javax.swing.JTextField txtStockQuantity;
    private javax.swing.JTextField txtSubtotal;
    private javax.swing.JTextField txtTax;
    private javax.swing.JTextField txtTotalgrand;
    private javax.swing.JTextField txtVatPer;
    // End of variables declaration//GEN-END:variables
//////////////////////

/////////////////////
}
