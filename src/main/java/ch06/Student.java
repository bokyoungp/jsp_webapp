package ch06;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Student {
  private int id;
  private String name;
  private String univ;
  private Date birth;
  private String email;

  public void setUniv(String univ) {
    this.univ = univ;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
