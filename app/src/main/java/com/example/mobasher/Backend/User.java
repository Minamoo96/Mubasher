package com.example.mobasher.Backend;

import java.util.HashMap;
import java.util.Map;

public class User {

        private int id;
        private String firstName, lastName, email, phone, password, image;

        public User(int id, String firstName, String lastName, String email, String phone, String password, String Image) {
            this.id = id;
            this.email = email;
            this.phone = phone;
            this.firstName = firstName;
            this.lastName = lastName;
            this.password = password;
            this.image = Image;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String fname) {
            this.firstName = fname;
        }
        public String getLastName() {
        return lastName;
        }

        public void setLastName(String lname) {
        this.lastName = lname;
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

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getImage(){
            return image;
        }

        public void setImage(){

        }
    }

