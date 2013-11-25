package ee.ut.math.tvt.salessystem.domain.controller;

import java.util.List;

import ee.ut.math.tvt.salessystem.domain.data.Client;
import ee.ut.math.tvt.salessystem.domain.data.Sale;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.domain.exception.VerificationFailedException;
import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;

/**
 * Sales domain controller is responsible for the domain specific business
 * processes.
 */
public interface SalesDomainController {

    /**
     * Load the current state of the warehouse.
     *
     * @return List of ${link
     *         ee.ut.math.tvt.salessystem.domain.data.StockItem}s.
     */
    public List<StockItem> getAllStockItems();


    public List<Client> getAllClients();

    public List<Sale> getAllSales();

    public Client getClient(long id);

    public void createStockItem(StockItem stockItem);

    /**
     * Commit business transaction - purchase of goods.
     *
     * @param goods
     *            Goods that the buyer has chosen to buy.
     * @throws VerificationFailedException
     */
    /* dzh 2013-11-25 from condition: 
     * Replace the method submitCurrentPurchase(List<SoldItem> goods, Client currentClient) with the new method: 
     * salesDomainController.registerSale(sale);
     * 
    public void submitCurrentPurchase(List<SoldItem> goods, Client client)
            throws VerificationFailedException;
	*/      
        
	public void registerSale(Sale sale) 
			throws VerificationFailedException;

    public void setModel(SalesSystemModel model);

    /**
     * Close all resources
     */
    public void endSession();
}
