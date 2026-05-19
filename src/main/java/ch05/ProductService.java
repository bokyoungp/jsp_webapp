package ch05;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductService {
  Map<String, Product> products = new HashMap<>();

  public ProductService(){
    Product p1 = new Product("1", "갤럭시", "samsung", 250, "2026-01-01");
    products.put("1", p1);
    Product p2 = new Product("2", "i-phone", "apple", 200, "2026-03-01");
    products.put("2", p2);
  }

  // crud
  public void insert(Product product) {
    products.put(product.getId(), product);
  }
  public Product findById(String id) {
    Product product = products.get(id);
    return product;
  }
  public List<Product> findAll() {
    return new ArrayList<>(products.values());
  }
}
