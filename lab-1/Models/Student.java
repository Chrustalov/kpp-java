package Models;

public class Student {
  private String firstName;
  private String lastName;
  private String dormitory;
  private int roomNumber;
  private double fee;
  private int age;
  private boolean isPrivileged;

  public Student(String firstName, String lastName, String dormitory, int roomNumber, double fee, int age, boolean isPrivileged) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.dormitory = dormitory;
    this.roomNumber = roomNumber;
    this.fee = fee;
    this.age = age;
    this.isPrivileged = isPrivileged;
  }

  public String getFirstName() {
    return this.firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return this.lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getDormitory() {
    return this.dormitory;
  }

  public void setDormitory(String dormitory) {
    this.dormitory = dormitory;
  }

  public int getRoomNumber() {
    return this.roomNumber;
  }

  public void setRoomNumber(int roomNumber) {
    this.roomNumber = roomNumber;
  }

  public double getFee() {
    return fee;
  }

  public void setFee(double fee) {
    this.fee = fee;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public boolean isPrivileged() {
    return this.isPrivileged;
  }

  public void setPrivileged(boolean isPrivileged) {
    this.isPrivileged = isPrivileged;
  }

  @Override
  public String toString() {
    return firstName + " " + lastName + " (" + dormitory + ", room " + roomNumber + "), fee: " + fee + ", age: " + age + ", privileged: " + isPrivileged;
  }
}
