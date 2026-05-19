package ch05;

public class Main {
  public static void main(String[] args) {
    Student std1 = new Student(1,"홍길동","F", "010-0000-0000");

    Student std2 = new StudentBuilder()
        .phoneNumber("010-0000-1111").id(2).grade("A").name("김연아")
        .build();

    Student std3 = new StudentBuilder()
        .id(3).grade("A").name("김길동")
        .build();

    Student std4 = new StudentBuilder()
        .id(3).name("김길동")
        .build();

    System.out.println(std4);
  }
}
