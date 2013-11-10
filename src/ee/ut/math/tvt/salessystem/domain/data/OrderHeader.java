package ee.ut.math.tvt.salessystem.domain.data;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ORDER_HEADER")
public class OrderHeader implements Cloneable, DisplayableItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Column(name = "DATE")
    private String date;

	@Column(name = "TIME")
    private String time;    
    
	@Column(name = "SUMMA")
    private double sum;
    
    private List <SoldItem> orderDetail;

    public OrderHeader(Long id, String date, String time, double sum, List <SoldItem> orderDetail) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.sum = sum;
        this.orderDetail = orderDetail;
    }
    
    /**
     * Constructs new  <code>OrderHeader</code>.
     */
    public OrderHeader() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }    
    
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public List <SoldItem> getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(List <SoldItem> orderDetail) {
        this.orderDetail = orderDetail;
    }    
    
    public String toString() {
        return id + " " + date + " " + time + " " + sum;
    }

    /**
     * Method for querying the value of a certain column when HeaderOrder are shown
     * as table rows in the SalesSystemTableModel. The order of the columns is:
     * id, date, time, sum.
     */
    public Object getColumn(int columnIndex) {
        switch(columnIndex) {
            case 0: return id;
            case 1: return date;
            case 2: return time;
            case 3: return new Double(sum);
            default: throw new RuntimeException("invalid column!");
        }
    }    
    
    public Object clone() {
    	OrderHeader item =
            new OrderHeader(getId(), getDate(), getTime(), getSum(), getOrderDetail());
        return item;
    }
		
}
