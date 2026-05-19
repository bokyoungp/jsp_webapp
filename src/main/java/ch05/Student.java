package ch05;

public class Student {
  private int id;
  private String name;
  private String grade;
  private String phoneNumber;

  public Student(int id, String name, String grade, String phoneNumber) {
    this.id = id;
    this.name = name;
    this.grade = grade;
    this.phoneNumber = phoneNumber;
  }
  public Student(int id, String name, String phoneNumber) {
    this.id = id;
    this.name = name;
    this.grade = "";
    this.phoneNumber = phoneNumber;
  }

  public Student(int id, String name) {
    this.id = id;
    this.name = name;
    this.grade = "";
    this.phoneNumber = "";
  }

}

class StudentBuilder {
  private int id;
  private String name;
  private String grade;
  private String phoneNumber;

  public StudentBuilder id(int id){
    this.id = id;
    return this;
  }

  public StudentBuilder name(String name){
    this.name = name;
    return this;
  }

  public StudentBuilder grade(String grade){
    this.grade = grade;
    return this;
  }

  public StudentBuilder phoneNumber(String phoneNumber){
    this.phoneNumber = phoneNumber;
    return this;
  }

  public Student build(){
    return new Student(id, name, grade, phoneNumber);
  }

}
