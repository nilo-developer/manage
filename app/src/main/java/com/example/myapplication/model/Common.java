package com.example.myapplication.model;

public class Common {
    private String name;
    private String last_name;
    private String jensiat;
    private String code_meli;
    private String address;

    public Common() { }

    public Common(String name, String last_name, String jensiat, String code_meli, String address) {
        this.name = name;
        this.last_name = last_name;
        this.jensiat = jensiat;
        this.code_meli = code_meli;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getJensiat() {
        return jensiat;
    }

    public void setJensiat(String jensiat) {
        this.jensiat = jensiat;
    }

    public String getCode_meli() {
        return code_meli;
    }

    public void setCode_meli(String code_meli) {
        this.code_meli = code_meli;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
