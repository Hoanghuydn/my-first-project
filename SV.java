package QLSV;

import java.io.Serializable;

public class SV implements Serializable {

    private String maSV;
    private String tenSV;
    private String diachiSV;

    public SV() {
    }

    public SV(String maSV, String tenSV, String diachiSV) {
        this.maSV = maSV;
        this.tenSV = tenSV;
        this.diachiSV = diachiSV;
    }

    public String getMaSV() {
        return maSV;
    }

    public String getTenSV() {
        return tenSV;
    }

    public String getDiachiSV() {
        return diachiSV;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    public void setTenSV(String tenSV) {
        this.tenSV = tenSV;
    }

    public void setDiachiSV(String diachiSV) {
        this.diachiSV = diachiSV;
    }

    @Override
    public String toString() {
        return "SV{" + "maSV=" + maSV + ", tenSV=" + tenSV + ", diachiSV=" + diachiSV + '}';
    }

}
