package databean;

public class ResearchFundAllInfo {
	
	private int fundId;
	private String name;
	private String symbol;
	private Double price;
	private String priceDate;
	private Double priceDifference;
	private Double priceGrowth;
	
	public int    getFundId()      { return fundId;    }
	public String getName()        { return name;      }
	public String getSymbol()      { return symbol;    }
	public Double getPrice()       { return price;     }
	public String getPriceDate()   { return priceDate; }
	public Double getPriceDifference() { return priceDifference;     }
	public Double getpriceGrowth() { return priceGrowth;     };
	
	public void setFundId(int fundId)        { this.fundId = fundId;       }
	public void setName(String name)         { this.name = name;           }
	public void setSymbol(String symbol)     { this.symbol = symbol;       }
	public void setPrice(Double price)       { this.price = price;         }
	public void setPriceDate(String priceDate) { this.priceDate = priceDate; }
	public void setPriceDifference(Double priceDifference) { this.priceDifference = priceDifference; }
	public void setPriceGrowth(Double priceGrowth) { this.priceGrowth = priceGrowth; }

}
