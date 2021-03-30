package com.test.atlanta_sys.RETROFIT.Modal;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "users", indices = @Index(value = {"id"},unique = true))
public class Users {
    @PrimaryKey(autoGenerate = true)
    private int usersId;

    @SerializedName("id")
    @ColumnInfo(name = "id")
    private int id;

    @SerializedName("name")
    @ColumnInfo(name = "name")
    private String name;

    @SerializedName("username")
    @ColumnInfo(name = "username")
    private String username;

    @SerializedName("email")
    @ColumnInfo(name = "email")
    private String email;

    @SerializedName("phone")
    @ColumnInfo(name = "phone")
    private String phone;


    @SerializedName("website")
    @ColumnInfo(name = "website")
    private String website;


    public Users(int usersId, int id, String name, String username, String email, String phone, String website) {
        this.usersId = usersId;
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.website = website;
    }

    public int getUsersId() {
        return usersId;
    }

    public void setUsersId(int usersId) {
        this.usersId = usersId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    @Override
    public String toString() {
        return "Users{" +
                "usersId=" + usersId +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", email=" + email +
                ", phone=" + phone +
                ", website=" + website +
                '}';
    }
}
