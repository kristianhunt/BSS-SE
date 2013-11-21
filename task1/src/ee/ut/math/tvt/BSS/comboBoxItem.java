package ee.ut.math.tvt.BSS;

/*
 * Class for ComboBox for items with key -> value 
 * dzh last update 2013-10-18 
 */


public class comboBoxItem {
	private Long id;
	private String description;

	public comboBoxItem(Long id, String description) {
		this.id = id;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public String toString() {
		return description;
	}
} 
