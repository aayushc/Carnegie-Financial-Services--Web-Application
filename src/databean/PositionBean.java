package databean;

import org.genericdao.PrimaryKey;

@PrimaryKey("positionId")
public class PositionBean {
	
	private int positionId;
	private int customerId;
	private int fundId;
	private long numberOfShares;
	
	public int getPositionId() { return positionId; }	
	public int getCustomerId()   	{ return customerId;   }
	public int getFundId()   		{ return fundId;   	   }
	public long getNumberOfShares()   		{ return numberOfShares;   	   }
	
	public void setPositionId(int positionId) {this.positionId = positionId;}
	public void setCustomerId(int c)      { customerId = c;    }
	public void setFundId(int f)      	  { fundId = f;        }
	public void setNumberOfShares(long s)      	  { numberOfShares = s;        }

}
