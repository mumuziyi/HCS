package org.example;

public class Keys {
    private String key;
    private double row;
    private double column;

    public Keys(String key, double row, double column){
        this.key = key;
        this.row = row;
        this.column = column;
    }

    public double getRow() {
        return row;
    }



    public void setRow(double row) {
        this.row = row;
    }

    public double getColumn() {
        return column;
    }

    public void setColumn(double column) {
        this.column = column;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "Keys{" +
                "key='" + key + '\'' +
                ", row=" + row +
                ", column=" + column +
                '}';
    }
}
