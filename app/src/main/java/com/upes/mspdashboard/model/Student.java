package com.upes.mspdashboard.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.upes.mspdashboard.util.WebApiConstants;
import com.upes.mspdashboard.util.retrofit.model.UserDetailsResponse;

public class Student extends User implements Parcelable {
    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };
    private String imageUrl;
    private String enrNo;
    private String semeser;
    private String sapId;
    private String program;
    private float cgpa;

    public String getImageUrl() {
        return imageUrl;
    }

    public String getEnrNo() {
        return enrNo;
    }

    public String getSemeser() {
        return semeser;
    }

    public String getSapId() {
        return sapId;
    }

    public String getProgram() {
        return program;
    }

    public float getCgpa() {
        return cgpa;
    }

    private Student(String username, String password, WebApiConstants.UserType type) {
        super(username, password,type);
    }

    protected Student(Parcel in) {
        this(in.readString(),in.readString(), WebApiConstants.UserType.getType(in.readInt()));
        imageUrl = in.readString();
        enrNo = in.readString();
        semeser = in.readString();
        sapId = in.readString();
        program = in.readString();
        cgpa = in.readFloat();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(getUsername());
        parcel.writeString(getPassword());
        parcel.writeInt(getType().getTypeId());
        parcel.writeString(imageUrl);
        parcel.writeString(enrNo);
        parcel.writeString(semeser);
        parcel.writeString(sapId);
        parcel.writeString(program);
        parcel.writeFloat(cgpa);
    }

    public static class Builder {
        private String username;
        private String password;
        private WebApiConstants.UserType type;
        private String imageUrl;
        private String enrNo;
        private String semeser;
        private String sapId;
        private String program;
        private float cgpa;

        public Builder() {

        }

        public Student build() {
            Student student = new Student(username,password,type);
            student.imageUrl = imageUrl;
            student.enrNo = enrNo;
            student.semeser = semeser;
            //TODO: sap id
            student.program = program;
            student.cgpa = cgpa;
            return student;
        }
        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder type(WebApiConstants.UserType type) {
            this.type = type;
            return this;
        }

        public Builder imageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public Builder enrNo(String enrNo) {
            this.enrNo = enrNo;
            return this;
        }

        public Builder semester(String semeser) {
            this.semeser = semeser;
            return this;
        }

        public Builder sapId(String sapId) {
            this.sapId = sapId;
            return this;
        }

        public Builder program(String program) {
            this.program = program;
            return this;
        }

        public Builder cgpa(float cgpa) {
            this.cgpa = cgpa;
            return this;
        }

        public Builder userDetails(UserDetailsResponse userDetailsResponse) {
            imageUrl = userDetailsResponse.getImageUrl();
            enrNo = userDetailsResponse.getEnrNo();
            semeser = userDetailsResponse.getSemester();
            //sapId = userDetailsResponse.getSapId();
            program = userDetailsResponse.getProgram();
            cgpa = userDetailsResponse.getCgpa();
            return this;
        }
    }
}
