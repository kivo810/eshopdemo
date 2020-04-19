package cz.ucl.model.shopOrder;

import cz.ucl.model.product.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class ShopOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Timestamp completedAt;
    private Double finalPrice;
    private int customerId;

    //CUSTOMER
    private String name;
    private String email;
    private String address;
    private String cardNumber;

    @OneToMany
    private List<Product> productList;
}
