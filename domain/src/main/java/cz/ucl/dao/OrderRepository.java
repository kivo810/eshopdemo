package cz.ucl.dao;

import cz.ucl.model.shopOrder.ShopOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;

public interface OrderRepository extends JpaRepository <ShopOrder, Integer> {
    List<ShopOrder> findAllByCompletedAtOrderByCompletedAtAsc();
}
