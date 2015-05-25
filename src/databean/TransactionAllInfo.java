package databean;


public class TransactionAllInfo {

	private int customerId;
	private int fundId;
	private String symbol;
	private String fundname;
	private String executeDate;
	private String numberOfShares;
	private String transactionType;
	private String amount;
	private String status;
	private String buyingPrice;
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public int getFundId() {
		return fundId;
	}
	public void setFundId(int fundId) {
		this.fundId = fundId;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getFundname() {
		return fundname;
	}
	public void setFundname(String fundname) {
		this.fundname = fundname;
	}
	public String getExecuteDate() {
		return executeDate;
	}
	public void setExecuteDate(String executeDate) {
		this.executeDate = executeDate;
	}
	public String getNumberOfShares() {
		return numberOfShares;
	}
	public void setNumberOfShares(String numberOfShares) {
		this.numberOfShares = numberOfShares;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getBuyingPrice() {
		return buyingPrice;
	}
	public void setBuyingPrice(String buyingPrice) {
		this.buyingPrice = buyingPrice;
	}
}
