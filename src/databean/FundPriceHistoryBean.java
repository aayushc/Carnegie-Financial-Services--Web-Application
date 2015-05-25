package databean;

import org.genericdao.PrimaryKey;
import java.util.Date;

@PrimaryKey("fundPriceHistoryId")
public class FundPriceHistoryBean {
	
	private int fundPriceHistoryId;
	private int fundId;
	private Date priceDate;
	private long price;
	
	public int getFundPriceHistoryId() {
		return fundPriceHistoryId;
	}
	public void setFundPriceHistoryId(int fundPriceHistoryId) {
		this.fundPriceHistoryId = fundPriceHistoryId;
	}
	public int getFundId()   		{ return fundId;   	   }
	public Date getPriceDate()   	{ return priceDate;    }
	public long getPrice()   		{ return price;   	   }
	
	public void setFundId(int f)      	  { fundId = f;        }
	public void setPriceDate(Date d)      { priceDate = d;     }
	public void setPrice(long p)      	  { price = p;         }

}
