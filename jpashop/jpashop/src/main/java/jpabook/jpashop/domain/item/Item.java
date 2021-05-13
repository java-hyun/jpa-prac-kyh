package jpabook.jpashop.domain.item;
import jpabook.jpashop.domain.Category;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


// 엔티티 클래스
// @Inheritance(strategy = InheritanceType.SINGLE_TABLE) => 아이템 테이블을 상속받으면 디비 상에서는 하나의 통합 테이블로 존재
// @DiscriminatorColumn(name = "dtype") => 상속받은 테이블들을 구분하기 위한 필드
// 게터 세터

// 아이템 엔티티 릴레이션 설정 요약
// 카테고리와는 ManyToMany 실제 데이터는 카데고리 엔티티의 item
// ManyToMany는 중간 테이블을 통해 데이터가 관리 된다.
// Item 엔티티의 카테고리 정보는 일종의 가상 정보라고 할 수 있다.
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter
@Setter
public abstract class Item {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;
    private String name;
    private int price;
    private int stockQuantity;


    // 이 상품 정보를 참조하는 주문상품정보들의 리스트들
    // 상품 입장에서 어떤 주문 상품 정보가 상품을 참조하는지 알고 싶다면 다음과 같이 가상 리스트를 설정
    //  @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    //  private List<OrderItem> orderItems = new ArrayList<>();
    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<Category>();

}
