package cz.ucl.model.product;

import cz.ucl.model.shopOrder.ShopOrder;
import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Product {
    @Id
    @NonNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private Double price;
    private int available;

    @ManyToOne(fetch=FetchType.LAZY)
    private ShopOrder shopOrder;

    public Product(String name, String description, Double price, int available) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.available = available;
    }
}
