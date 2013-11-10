package ee.ut.math.tvt.salessystem.domain.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;




/**
 * Already bought StockItem. SoldItem duplicates name and price for preserving history. 
 */

@Entity
@Table(name = "SOLDITEM")
public class SoldItem implements Cloneable, DisplayableItem {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long rec_id;

	@ManyToOne
	@JoinColumn(name = "SALE_ID", nullable = false)
	private OrderHeader orderHeader;

	@Column(name = "STOCKITEM_ID")
    private Long id;

	@Column(name = "NAME")
    private String name;

	@Column(name = "QUANTITY")
    private Integer quantity;

	@Column(name = "ITEMPRICE")
	private double price;

	@ManyToOne
	@JoinColumn(name = "STOCKITEM_ID", nullable = false, updatable = false, insertable = false)
	private StockItem stockItem;

    public SoldItem(StockItem stockItem, int quantity) {
        this.stockItem = stockItem;
        this.id = stockItem.getId();//dzh 2013-10-18 set bar code
        this.name = stockItem.getName();
        this.price = stockItem.getPrice();
		this.quantity = quantity;
		this.orderHeader = null;
	}

	public SoldItem() {

	}
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public double getPrice() {
        return price;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    
    public Integer getQuantity() {
        return quantity;
    }
    
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public double getSum() {
        return price * ((double) quantity);
    }

    public StockItem getStockItem() {
        return stockItem;
    }

    public void setStockItem(StockItem stockItem) {
        this.stockItem = stockItem;
    }

	public OrderHeader getSaleId() {
		return this.orderHeader;
	}

	public void setSaleId(OrderHeader orderHeader) {
		this.orderHeader = orderHeader;
	}
    
}
