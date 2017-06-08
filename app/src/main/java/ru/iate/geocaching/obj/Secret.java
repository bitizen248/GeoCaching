
package ru.iate.geocaching.obj;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Secret implements Parcelable {

    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("geo")
    @Expose
    private Geo geo;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("passed")
    @Expose
    private Boolean passed;
    @SerializedName("question")
    @Expose
    private String question;
    @SerializedName("region")
    @Expose
    private String region;
    @SerializedName("timestamp")
    @Expose
    private Long timestamp;

    protected Secret(Parcel in) {
        description = in.readString();
        geo = in.readParcelable(Geo.class.getClassLoader());
        image = in.readString();
        name = in.readString();
        passed = in.readInt() == 1;
        question = in.readString();
        region = in.readString();
        timestamp = in.readLong();
    }

    public static final Creator<Secret> CREATOR = new Creator<Secret>() {
        @Override
        public Secret createFromParcel(Parcel in) {
            return new Secret(in);
        }

        @Override
        public Secret[] newArray(int size) {
            return new Secret[size];
        }
    };

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Geo getGeo() {
        return geo;
    }

    public void setGeo(Geo geo) {
        this.geo = geo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getPassed() {
        return passed;
    }

    public void setPassed(Boolean passed) {
        this.passed = passed;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(description);
        parcel.writeParcelable(geo, i);
        parcel.writeString(image);
        parcel.writeString(name);
        parcel.writeInt(passed ? 1 : 0);
        parcel.writeString(question);
        parcel.writeString(region);
        parcel.writeLong(timestamp);
    }
}
