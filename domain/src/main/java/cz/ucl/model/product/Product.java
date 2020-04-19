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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private Double price;
    private int available;

    @ManyToOne
    private ShopOrder shopOrder;

}
