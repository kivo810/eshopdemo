package cz.ucl.model.shopOrder;

import cz.ucl.model.product.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
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
    @NotEmpty(message = "Name cannot be empty")
    private String name;
    @Email
    private String email;
    private String address;
    @CreditCardNumber
    private String cardNumber;
    @Min(value = 18, message = "Age should not be less than 18")
    @Max(value = 150, message = "Age should not be greater than 150")
    private int age;

    @OneToMany
    private List<Product> productList;
}
