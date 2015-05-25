package databean;

import java.util.Date;

public class FundDailyPrice {
	
	private int fundId;
	private String name;
	private String symbol;
	private long price;
	private long numberOfShares;
	private Date priceDate;
	
	public int    getFundId()         { return fundId;         }
	public String getName()           { return name;           }
	public String getSymbol()         { return symbol;         }
	public long getPrice()          { return price;          }
	public Date getPriceDate()        { return priceDate;      }
	public long getNumberOfShares() { return numberOfShares; }
	
	public void setFundId(int fundId)        { this.fundId = fundId;       }
	public void setName(String name)         { this.name = name;           }
	public void setSymbol(String symbol)     { this.symbol = symbol;       }
	public void setPrice(long price)       { this.price = price;         }
	public void setPriceDate(Date priceDate) { this.priceDate = priceDate; }
	public void setNumberOfShares(long s)  { numberOfShares = s;         }

}
