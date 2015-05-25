package databean;

import org.genericdao.PrimaryKey;

import java.util.Date;

@PrimaryKey("transactionId")
public class TransactionBean {
	
	private int transactionId;
	private int customerId;
	private int fundId;
	private String symbol;
	private String fundname;
	private Date executeDate;
	private long numberOfShares;
	private String transactionType;
	private long amount;
	private String status;
	
	public String getSymbol() {	return symbol;	}	
	public String getFundname() {return fundname;}	
	public String getStatus() {	return status;	}	
	public int getTransactionId()   	{ return transactionId;   }
	public int getCustomerId()   		{ return customerId;   	  }
	public int getFundId()   			{ return fundId;   	      }
	public Date getExecuteDate()   		{ return executeDate;     }
	public long getNumberOfShares()   			{ return numberOfShares;   	      }
	public String getTransactionType()  { return transactionType; }
	public long getAmount()   			{ return amount;   	      }
	
	public void setSymbol(String symbol) {	this.symbol = symbol;	}
	public void setFundname(String fundname) {this.fundname = fundname;}
	public void setStatus(String status) {	this.status = status;	}
	public void setTransactionId(int t)       { transactionId = t;    }
	public void setCustomerId(int c)          { customerId = c;       }
	public void setFundId(int f)      	      { fundId = f;           }
	public void setExecuteDate(Date d)        { executeDate = d;      }
	public void setNumberOfShares(long s)      	      { numberOfShares = s;           }
	public void setTransactionType(String y)  { transactionType = y;  }
	public void setAmount(long m)      	      { amount = m;           }
	
}
