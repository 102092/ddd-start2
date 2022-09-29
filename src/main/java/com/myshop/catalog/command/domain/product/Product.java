package com.myshop.catalog.command.domain.product;

import com.myshop.catalog.command.domain.category.CategoryId;
import com.myshop.common.jpa.MoneyConverter;
import com.myshop.common.model.Money;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "product")
public class Product {
    @EmbeddedId
    private ProductId id;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "product_category",
            joinColumns = @JoinColumn(name = "product_id"))
    private Set<CategoryId> categoryIds;

    private String name;

    @Convert(converter = MoneyConverter.class)
    private Money price;

    private String detail;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    @OrderColumn(name = "list_idx")
    private List<Image> images = new ArrayList<>();

    protected Product() {
    }

    public Product(ProductId id, String name, Money price, String detail, List<Image> images) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.detail = detail;
        this.images.addAll(images);
    }

    public ProductId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Money getPrice() {
        return price;
    }

    public String getDetail() {
        return detail;
    }

    public List<Image> getImages() {
        return Collections.unmodifiableList(images);
    }

    public void changeImages(List<Image> newImages) {
        // clear() 하는 순간 select * from image where product_id = ? 쿼리로 매칭된 이미지를 다 가져오고.
        // delete from where image_id 로 각각 O(N) 만큼 실행됨.
        // 만약 이미지 개수가 많다면, 효율적이지 않은 방법이 됨.
        // 그래서 만약 @Embeddable로 이 부분을 구현한다면 위에서 야기되는 문제를 해결할 수 있음. (select 문 없이 한번의 delete로 삭제 쿼리 실행됨)
        images.clear();
        images.addAll(newImages);
    }

    public String getFirstIamgeThumbnailPath() {
        if (images == null || images.isEmpty()) return null;
        return images.get(0).getThumbnailUrl();
    }
}
