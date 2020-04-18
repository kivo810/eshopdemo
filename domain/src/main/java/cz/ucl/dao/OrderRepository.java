package cz.ucl.dao;

import cz.ucl.model.shopOrder.ShopOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository <ShopOrder, Integer> {
}
