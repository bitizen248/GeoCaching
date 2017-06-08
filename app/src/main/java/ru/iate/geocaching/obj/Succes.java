
package ru.iate.geocaching.obj;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Succes {

    @SerializedName("succes")
    @Expose
    private Boolean succes;

    public Boolean getSucces() {
        return succes;
    }

    public void setSucces(Boolean succes) {
        this.succes = succes;
    }

}
