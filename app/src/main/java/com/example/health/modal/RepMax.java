package com.example.health.modal;

public class RepMax {
    String valueKG;
    String indexRM;

    public String getIndexRM() {
        return indexRM;
    }

    public void setIndexRM(String indexRM) {
        this.indexRM = indexRM;
    }

    public RepMax(String valueKG, String indexRM) {
        this.valueKG = valueKG;
        this.indexRM = indexRM;
    }

    public RepMax(String valueKG) {
        this.valueKG = valueKG;
    }

    public String getValueKG() {
        return valueKG;
    }

    public void setValueKG(String valueKG) {
        this.valueKG = valueKG;
    }
}
