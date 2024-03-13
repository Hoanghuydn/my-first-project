package QLSV;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class CSDL {

    DefaultTableModel model;
    JTextField maSV, tenSV, diachiSV;
    int row = -1;
    JTable table;
    DTAction dtAction;
    SV sv;
    JFrame fr;

    public CSDL() {
        fr = new JFrame("StuManager");
        fr.setSize(550, 260);//Thiet lap kích thước
        fr.setResizable(false);
        fr.setLocationRelativeTo(null);//Thiet lap vị trí
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Chỉ dùng cho frame chính
        fr.setLayout(new BorderLayout());
        JLabel label = new JLabel("STUDENT MANAGEMENT");
        label.setFont(new Font("Courier New", Font.BOLD, 28));//Thiet lap font
        label.setForeground(Color.BLUE);//Thiet lap mau
        label.setHorizontalAlignment(0);//Canh giua theo chieu ngang
        fr.add(label, BorderLayout.NORTH);//Dat vo phia bac
        String[] col = {"StuID", "Name", "Address"};
        model = new DefaultTableModel(col, 0);
        table = new JTable(model);
        table.setAutoCreateRowSorter(true);//Mới thêm
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getColumnModel().getColumn(0).setPreferredWidth(50);
        table.getColumnModel().getColumn(1).setPreferredWidth(125);
        table.getColumnModel().getColumn(2).setMinWidth(130);
        table.addMouseListener(new MyMouseLis());
        table.addKeyListener(new MyKeyLis());
        fr.add(new JScrollPane(table), BorderLayout.CENTER);
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(6, 2));
        fr.add(p, BorderLayout.WEST);
        p.add(new JLabel("StuID:"));
        maSV = new JTextField(10);
        p.add(maSV);
        p.add(new JLabel("Name:"));
        tenSV = new JTextField(10);
        p.add(tenSV);
        p.add(new JLabel("Address:"));
        diachiSV = new JTextField(10);
        p.add(diachiSV);
        JButton b1 = new JButton("Add");
        b1.addActionListener(new MyListener());
        p.add(b1);
        JButton b2 = new JButton("Delete");
        b2.addActionListener(new MyListener());
        p.add(b2);
        JButton b3 = new JButton("Edit");
        b3.addActionListener(new MyListener());
        p.add(b3);
        JButton b4 = new JButton("Search");
        b4.addActionListener(new MyListener());
        p.add(b4);
        JButton b5 = new JButton("Clear");
        b5.addActionListener(new MyListener());
        p.add(b5);
        JButton b6 = new JButton("Cancel");
        b6.addActionListener(new MyListener());
        p.add(b6);
        fr.setVisible(true);
        dtAction = new DTAction();
        for (SV sv : dtAction.getList()) {
            Object[] obj = {sv.getMaSV(), sv.getTenSV(), sv.getDiachiSV()};
            model.addRow(obj);
        }
        //sv = new SV();
    }

    class MyListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String s = e.getActionCommand();
            switch (s) {
                case "Add" -> {
                    String s1 = maSV.getText();
                    String s2 = tenSV.getText();
                    String s3 = diachiSV.getText();
                    Object[] obj = {s1, s2, s3};
                    model.addRow(obj);
                    dtAction.addSV(s3, s3, s3);
                    row = model.getColumnCount() - 1;
                }
                case "Clear" -> {
                    maSV.setText("");
                    tenSV.setText("");
                    diachiSV.setText("");
                    row = -1;
                }
                case "Cancel" -> {
                    //System.exit(0);
                    fr.dispose();
                }
                case "Delete" -> {
                    int n = JOptionPane.showConfirmDialog(null,
                            model.getValueAt(row, 0).toString() + " will be deleted.\nAre you sure?",
                            "Title", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (n == 0) {
                        model.removeRow(row);
                        dtAction.deleteSV(row);
                        maSV.setText("");
                        tenSV.setText("");
                        diachiSV.setText("");
                    }

                }
                case "Search" -> {
                    int n = model.getColumnCount(), i;
                    for (i = 0; i < n; i++) {
                        if (model.getValueAt(i, 0).equals(maSV.getText())) {
                            tenSV.setText(model.getValueAt(i, 1).toString());
                            diachiSV.setText(model.getValueAt(i, 2).toString());
                            table.setRowSelectionInterval(i, 1);
                            row = i;
                            break;
                        }
                        if (i == n) {
                            JOptionPane.showMessageDialog(null, "Not found", "Title", JOptionPane.ERROR_MESSAGE);
                            row = -1;
                            tenSV.setText("");
                            diachiSV.setText("");
                        }
                    }

                }
                case "Edit" -> {
                    if (row > -1) {
                        String s1 = maSV.getText();
                        String s2 = tenSV.getText();
                        String s3 = diachiSV.getText();
                        model.setValueAt(s1, row, 0);
                        model.setValueAt(s2, row, 1);
                        model.setValueAt(s3, row, 3);
                        dtAction.editSV(s1, s2, s3, row);
                    }
                }
            }
        }
    }

    class MyMouseLis extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
        }
    }

    class MyKeyLis extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }

    public static void main(String[] args) {
        CSDL csdl = new CSDL();
    }
}
