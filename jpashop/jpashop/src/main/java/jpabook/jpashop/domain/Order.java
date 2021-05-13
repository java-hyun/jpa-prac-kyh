package jpabook.jpashop.domain;
import jpabook.jpashop.domain.Delivery;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.OrderItem;
import jpabook.jpashop.domain.OrderStatus;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;



@Entity
@Table(name = "orders")
@Getter @Setter
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    // 릴레이션 관계는 주문 한개에 여러개의 주문 상품 정보가 관련 되므로 OneToMany
    // 데이터 참조를 위한 가상 필드이므로 mappedBy 설정 필요 (실제 디비에 저장 안됨)
    // 주문 상품 객체를 통해 참조하고 여러개일수 있으므로  List<OrderItem> 객체 형식으로 저장
    // cascade = CascadeType.ALL => 주문 정보가 지워지면 주문 상품 정보도 지워라 (리스트 정보를 참조)
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery; //배송정보

    private LocalDateTime orderDate; //주문시간

    @Enumerated(EnumType.STRING)
    private OrderStatus status; //주문상태 [ORDER, CANCEL]


    // 연관 관계 편의 메서드
    // 연관 관계 데이터를 동시에 추가하기 편리하도록 만든 함수
    // 일단 만들고 사용하면서 연관 관계 함수의 편리함을 알수 있을것

    // 주문 할때
    // 해당 주문 정보에 주문한 멤버 정보 설정
    // 주문 멤버의 주문 정보에 해당 주문 정보 추가
    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }

    // 주문 할때
    // 상품 정보 추가할때
    // 주문 상품 정보를 주문 정보에 추가
    // 주문 상품 정보에 주문 정보를 추가 
    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    // 주문 할때
    // 배송 정보를 추가 할때
    // 주문 정보에 배송 정보를 설정
    // 배송 정보가 참조하는 배달 정보를 설정
    public void setDelivery(Delivery delivery){
        this.delivery = delivery;
        delivery.setOrder(this);
    }

}
