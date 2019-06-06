package com.eran.myapp;

public class Ad {
    private String name, image, type,Purpose,age,information,city,race,training, state,gender,date,firstname,lastname,email,phone;

    public Ad(String name, String image, String type,String Purpose,String age,String information,
              String city,String race,String training,String state,String gender,String date,String firstname,String lastname ,String email, String phone)
    {
        this.name = name;
        this.image = image;
        this.type = type;
        this.Purpose = Purpose;
        this.age = age;
        this.information=information;
        this.city=city;
        this.race=race;
        this.training=training;
        this.state=state;
        this.gender=gender;
        this.date=date;
        this.firstname=firstname;
        this.lastname=lastname;
        this.email=email;
        this.phone=phone;


    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPurpose(String Purpose) {
        this.Purpose = Purpose;
    }

    public String getPurpose() {
        return Purpose;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAge() {
        return age;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getInformation() {
        return information;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getRace() {
        return race;
    }

    public void setTraining(String training) {
        this.training = training;
    }

    public String getTraining() { return training; }

    public void setState(String state) {
        this.state = state;
    }

    public String getState() { return state; }


    public void setGender(String gender) { this.gender = gender; }

    public String getGender() { return gender; }


    public void setDate(String date) { this.date = date; }

    public String getDate() { return date; }

    public void setFirstname(String firstname) { this.firstname = firstname; }

    public String getFirstname() { return firstname; }

    public void setLastname(String lastname) { this.lastname = lastname; }

    public String getLastname() { return lastname; }

    public void setEmail(String email) { this.email = email; }

    public String getEmail() { return email; }

    public void setPhone(String phone) { this.phone = phone; }

    public String getPhone() { return phone; }










}
