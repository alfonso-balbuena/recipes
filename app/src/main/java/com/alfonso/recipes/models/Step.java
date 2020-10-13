package com.alfonso.recipes.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "steps",foreignKeys = @ForeignKey(entity = Recipe.class,
                                            parentColumns = "id",
                                            childColumns = "recipeId",
                                            onDelete = CASCADE),
        indices = @Index(value = "recipeId"))
public class Step implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int idStep;
    @SerializedName("id")
    @ColumnInfo
    private int numberStep;
    @ColumnInfo
    private String shortDescription;
    @ColumnInfo
    private String description;
    @ColumnInfo
    private String videoURL;
    @ColumnInfo
    private String thumbnailURL;
    @ColumnInfo
    private int recipeId;

    public Step() {

    }

    @Ignore
    public Step(int numberStep,String shortDescription,String description,String videoURL, String thumbnailURL,int recipeId) {
        this.setNumberStep(numberStep);
        this.shortDescription = shortDescription;
        this.description = description;
        this.videoURL = videoURL;
        this.thumbnailURL = thumbnailURL;
        this.recipeId = recipeId;
    }

    @Ignore
    protected Step(Parcel in) {
        idStep = in.readInt();
        numberStep = in.readInt();
        shortDescription = in.readString();
        description = in.readString();
        videoURL = in.readString();
        thumbnailURL = in.readString();
        recipeId = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(idStep);
        dest.writeInt(numberStep);
        dest.writeString(shortDescription);
        dest.writeString(description);
        dest.writeString(videoURL);
        dest.writeString(thumbnailURL);
        dest.writeInt(recipeId);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Step> CREATOR = new Creator<Step>() {
        @Override
        public Step createFromParcel(Parcel in) {
            return new Step(in);
        }

        @Override
        public Step[] newArray(int size) {
            return new Step[size];
        }
    };

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public void setThumbnailURL(String thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public int getNumberStep() {
        return numberStep;
    }

    public void setNumberStep(int numberStep) {
        this.numberStep = numberStep;
    }

    public int getIdStep() {
        return idStep;
    }

    public void setIdStep(int idStep) {
        this.idStep = idStep;
    }

    public String getVideoURLToExoPlayer() {
        return videoURL.isEmpty() ? thumbnailURL : videoURL;
    }
}
