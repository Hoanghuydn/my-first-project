package QLSV;

import java.io.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class DTAction {

    private File File;
    private ArrayList<SV> list = new ArrayList<>();

    public File getFile() {
        return File;
    }

    public void setFile(File File) {
        this.File = File;
    }

    public void setList(ArrayList<SV> list) {
        this.list = list;
    }

    public ArrayList<SV> getList() {
        return list;
    }

    public DTAction() {
        File = new File("sv.bin");
        if (!File.exists()) {
            try {
                File.createNewFile();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "File error", "Title", JOptionPane.ERROR_MESSAGE);
            }
            getAllSV();
        }
    }

    private void getAllSV() {
        try {
            FileInputStream fis = new FileInputStream(File);
            ObjectInputStream ois = new ObjectInputStream(fis);
            list = (ArrayList<SV>) ois.readObject();
            ois.close();
            fis.close();
        } catch (IOException | ClassNotFoundException el) {
            JOptionPane.showMessageDialog(null, "Read file error | Empty file", "Title", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void deleteSV(int i) {
        list.remove(i);
        save();
    }

    public void addSV(String maSV, String tenSV, String diachiSV) {
        SV sv = new SV(maSV, tenSV, diachiSV);
        list.add(sv);
        save();
    }

    private void save() {
        try {
            FileOutputStream fos = new FileOutputStream(File, false);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(list); //Lenh chinh
            oos.close();
            fos.close();
        } catch (IOException el) {
            JOptionPane.showMessageDialog(null, "Write file error", "Title", JOptionPane.ERROR_MESSAGE);
        }
    }

    void editSV(String s1, String s2, String s3, int row) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
