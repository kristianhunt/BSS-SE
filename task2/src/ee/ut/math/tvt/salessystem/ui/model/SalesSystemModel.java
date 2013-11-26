package ee.ut.math.tvt.salessystem.ui.model;

import java.util.List;

import ee.ut.math.tvt.salessystem.domain.controller.SalesDomainController;
import ee.ut.math.tvt.salessystem.domain.data.Client;
import ee.ut.math.tvt.salessystem.domain.data.Sale;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;

/**
 * Main model. Holds all the other models.
 */
public class SalesSystemModel {

    // Warehouse model
    private StockTableModel warehouseTableModel;

    // Current shopping cart model
    private PurchaseInfoTableModel currentPurchaseTableModel;

    // Puchase history model
    private PurchaseHistoryTableModel purchaseHistoryTableModel;

    private ClientTableModel clientTableModel;

    private Client selectedClient;

    private SalesDomainController domainController; // dzh 2013-11-25 need for refresh
    
    /**
     * Construct application model.
     * @param domainController Sales domain controller.
     */
    public SalesSystemModel(SalesDomainController domainController) {
		this.domainController = domainController; // dzh 2013-11-25
        warehouseTableModel = new StockTableModel();
        currentPurchaseTableModel = new PurchaseInfoTableModel(this);
        purchaseHistoryTableModel = new PurchaseHistoryTableModel();
        clientTableModel = new ClientTableModel();

        // Load data from the database

		refreshStock(); // dzh 2013-11-25 code to function

		refreshClients(); // dzh 2013-11-25 code to function

		refreshHistory(); // dzh 2013-11-25 code to function
    }

	public void refreshStock() {
		List<StockItem> stockItems = domainController.getAllStockItems();
		warehouseTableModel.populateWithData(stockItems);
		warehouseTableModel.fireTableDataChanged(); // dzh 2013-11-26 need for update JTabel by reference
	}

	public void refreshClients() {
		List<Client> clients = domainController.getAllClients();
		clientTableModel.populateWithData(clients);
		clientTableModel.fireTableDataChanged(); // dzh 2013-11-26 need for update JTabel by reference
	}

	public void refreshHistory() {
		List<Sale> sales = domainController.getAllSales();
		purchaseHistoryTableModel.populateWithData(sales);
		purchaseHistoryTableModel.fireTableDataChanged(); // dzh 2013-11-26 need for update JTabel by reference
	}

    public StockTableModel getWarehouseTableModel() {
        return warehouseTableModel;
    }

    public PurchaseInfoTableModel getCurrentPurchaseTableModel() {
        return currentPurchaseTableModel;
    }

    public PurchaseHistoryTableModel getPurchaseHistoryTableModel() {
        return purchaseHistoryTableModel;
    }

    public ClientTableModel getClientTableModel() {
        return clientTableModel;
    }

    public void setPurchaseHistoryTableModel(
            PurchaseHistoryTableModel purchaseHistoryTableModel) {
        this.purchaseHistoryTableModel = purchaseHistoryTableModel;
    }

    public Client getSelectedClient() {
        return selectedClient;
    }

    public void setSelectedClient(Client client) {
        this.selectedClient = client;
    }

}
