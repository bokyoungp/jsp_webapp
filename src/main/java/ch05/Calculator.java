package ch05;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Calculator {
  private int n1;
  private int n2;
  private String op;

  public int calc() {
    int result = 0;
    switch (op) {
      case "+" : result = n1 + n2; break;
      case "-" : result = n1 - n2; break;
      case "*" : result = n1 * n2; break;
      case "/" : {
        if (n2 != 0) result = n1 / n2;
      }
    }
    return result;
  }
}
