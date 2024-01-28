package com.azhar.spks.model;

import java.io.Serializable;

/**
 * Created by Azhar Rivaldi on 29-07-2022
 * Youtube Channel : https://bit.ly/2PJMowZ
 * Github : https://github.com/AzharRivaldi
 * Twitter : https://twitter.com/azharrvldi_
 * Instagram : https://www.instagram.com/azhardvls_
 * Linkedin : https://www.linkedin.com/in/azhar-rivaldi
 */

public class ModelKonsultasi implements Serializable {

    String strGejala = null;
    String kodeGejala = null;
    boolean selected = false;

    public String getKodeGejala() {
        return kodeGejala;
    }

    public void setKodeGejala(String kodeGejala) {
        this.kodeGejala = kodeGejala;
    }
    public String getStrGejala() {
        return strGejala;
    }

    public void setStrGejala(String strGejala) {
        this.strGejala = strGejala;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public void setEnabled(boolean b) {
    }

    public boolean isEnabled() {
        return true;
    }
}
