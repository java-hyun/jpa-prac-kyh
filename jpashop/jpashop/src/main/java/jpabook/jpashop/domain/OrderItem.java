package jpabook.jpashop.domain;
import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;


// OrderItem 엔티티가 다루는 주요 사항
// 주문한 상품의 수량
//주문한 상품의 가격
// 어떤 주문에 대한 것인지
// 어떤 상품을 샀는지

// OrderItem 엔티티의 릴레잇션 관계 요약
// 주문 상품 정보 여러개가 하나의 주문에 대한것일수 있으므로 ManyToOne으로 Order 엔티티를 참조
// 주문 상품 정보 여러개가 하나의 아이템 정보를 참조할 수 있으므로 ManyToOne으로 Item 엔티티를 참조
@Entity
@Getter
@Setter
public class OrderItem {
    // pk 필드 지정
    // 벨류값 자동 생성 설정
    // pk nick order_item_id
    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    private int orderPrice;     // 주문 가격
    private int count;          // 주문 수량

}
