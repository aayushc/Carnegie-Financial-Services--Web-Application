package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;

import databean.*;
import model.*;

public class ResearchFundAction extends Action {
	
	private FundDAO fundDAO;
	private FundPriceHistoryDAO fundPriceHistoryDAO;
	private TransactionDAO transactionDAO;
	
	public ResearchFundAction(Model model) {
		fundDAO = model.getFundDAO();
		fundPriceHistoryDAO = model.getFundPriceHistoryDAO();
		transactionDAO = model.getTransactionDAO();
	}
	
	public String getName() {
		return "researchFund.do";
	}
	
	public String perform(HttpServletRequest req) {
		// TODO Auto-generated method stub
		List<String> errors= new ArrayList<String>();
		req.setAttribute("errors", errors);
		req.setAttribute("searchId",req.getParameter("searchId"));
		
		try {			
			//check if user is logged in
			if(req.getSession().getAttribute("customer")==null) {
				errors.add("Please log-in to access this page.");
				return "login.jsp";
			}
			
			// below code to show existing funds of the users
			FundBean[] allFunds= fundDAO.read();
			
			if(allFunds.length==0) {
				errors.add("No funds exist");
				//change here to home page
				return "Cus_ViewAccount.do";
			}
			
			FundBean[] fundDailyList = null;
			
			fundDailyList = allFunds;
			
			int searchFundId = allFunds[0].getFundId();
			
			if (req.getParameter("searchId") != null){	        	
	        	if (req.getParameter("searchId").isEmpty()==false){
	        		try {
	        				searchFundId = Integer.parseInt(req.getParameter("searchId"));
	        				if (searchFundId < 1) {
	        					errors.add("We are sorry, it seems we couldn't find this fund in our record, please try again.");
	        					searchFundId = allFunds[0].getFundId();
	        					fundDailyList = allFunds;
	        				} else {
	        					fundDailyList= fundDAO.matchLastName(searchFundId);
								if (fundDailyList.length == 0) {
									errors.add("We are sorry, it seems we couldn't find this fund in our record, please try again.");
									searchFundId = allFunds[0].getFundId();
									fundDailyList = allFunds;
								}
	        				}
						} catch (NumberFormatException n) {
							errors.add("We are sorry, it seems we couldn't find this fund in our record, please try again.");
							searchFundId = allFunds[0].getFundId();
							fundDailyList = allFunds;
						} catch (RollbackException e) {
							errors.add(e.getMessage());
							searchFundId = allFunds[0].getFundId();
							fundDailyList = allFunds;
						} catch (ArrayIndexOutOfBoundsException a) {
							errors.add("We are sorry, it seems we couldn't find this fund in our record, please try again.");
							searchFundId = allFunds[0].getFundId();
							fundDailyList = allFunds;
						} catch(NullPointerException n) {
							errors.add("We are sorry, it seems we couldn't find this fund in our record, please try again.");
							searchFundId = allFunds[0].getFundId();
							fundDailyList = allFunds;
						}
	        	}
			}
						
			List<ResearchFundAllInfo> researchFunds= new ArrayList<ResearchFundAllInfo>();			
					
				for(int i=0; i<fundDailyList.length;i++) {
					ResearchFundAllInfo each= new ResearchFundAllInfo();
					each.setFundId(fundDailyList[i].getFundId());
					each.setName(fundDailyList[i].getName());
					each.setSymbol(fundDailyList[i].getSymbol());
					each.setPrice(fundPriceHistoryDAO.getFundPrice(fundDailyList[i].getFundId()));
					Double priceDiff = fundPriceHistoryDAO.getFundPrice(fundDailyList[i].getFundId()) - fundPriceHistoryDAO.getFundPrice2(fundDailyList[i].getFundId());
					each.setPriceDifference(priceDiff);
					Double priceGrowth;
					if (fundPriceHistoryDAO.getFundPrice2(fundDailyList[i].getFundId())==0) {
						priceGrowth=0.0;
					} else {
						priceGrowth = (fundPriceHistoryDAO.getFundPrice(fundDailyList[i].getFundId()) 
								 		- fundPriceHistoryDAO.getFundPrice2(fundDailyList[i].getFundId()))
								 		/ fundPriceHistoryDAO.getFundPrice2(fundDailyList[i].getFundId());
					}
					
					each.setPriceGrowth(priceGrowth);
					researchFunds.add(each);
				}
			req.setAttribute("researchFunds", researchFunds);
			
			List<FundDailyPrice> fundDailyFirst= new ArrayList<FundDailyPrice>();
			
			FundPriceHistoryBean[] fundDailyChartFirst= fundPriceHistoryDAO.matchFundId(searchFundId);
			
			for(int i=0; i<fundDailyChartFirst.length;i++) {
				FundDailyPrice each= new FundDailyPrice();
				each.setFundId(searchFundId);
				each.setName(fundDAO.getFundName(searchFundId));
				each.setSymbol(fundDAO.getTicker(searchFundId));
				each.setPrice((fundDailyChartFirst[i].getPrice()));
				each.setPriceDate(fundDailyChartFirst[i].getPriceDate());
				long volume=0;
				System.out.println("fund ID "+each.getFundId());
				System.out.println("fund date "+each.getPriceDate());
				try {
					TransactionBean[] fundDailyVolumeFirst= transactionDAO.getDailyTransactions(each.getFundId(), each.getPriceDate());
					for(int j=0; j<fundDailyVolumeFirst.length;j++) {
						volume = volume + fundDailyVolumeFirst[j].getNumberOfShares();
						/*System.out.println("fund ID "+each.getFundId());
						System.out.println("fund date "+each.getPriceDate());
						System.out.println("fund transaction "+fundDailyVolumeFirst[j].getNumberOfShares());
						System.out.println("volume: "+volume);*/
					}
				} catch(NullPointerException n) {
					/*System.out.println("id and date is not exists");*/
					volume=0;
				} 
				each.setNumberOfShares(volume);
				fundDailyFirst.add(each);
			}
			
			req.setAttribute("fundDaily1", fundDailyFirst);
			
			req.setAttribute("fundChartName1", fundDAO.getFundName(searchFundId));
			
			int limit;
			
			if (allFunds.length>5){
				limit = 5;
			} else {
				limit = allFunds.length;
			}
			
			/*System.out.println("limit loop: "+limit);*/
			
			int count=2;
			
			for (int i=0; i<limit;i++) {
				
				List<FundDailyPrice> fundDailyNext= new ArrayList<FundDailyPrice>();
				
				/*System.out.println("start loop: "+i);*/				
				
				if (allFunds[i].getFundId()!=searchFundId) {
					FundPriceHistoryBean[] fundDailyChartNext= fundPriceHistoryDAO.matchFundId(allFunds[i].getFundId());
					
					for(int j=0; j<fundDailyChartNext.length;j++) {
						FundDailyPrice each= new FundDailyPrice();
						each.setFundId(allFunds[i].getFundId());
						each.setName(fundDAO.getFundName(allFunds[i].getFundId()));
						each.setSymbol(fundDAO.getTicker(allFunds[i].getFundId()));
						each.setPrice(fundDailyChartNext[j].getPrice());
						each.setPriceDate(fundDailyChartNext[j].getPriceDate());
						long volume=0;
						try {
							TransactionBean[] fundDailyVolumeNext= transactionDAO.getDailyTransactions(each.getFundId(), each.getPriceDate());
							for(int k=0; k<fundDailyVolumeNext.length;k++) {
								volume = volume + fundDailyVolumeNext[k].getNumberOfShares();
							}
						} catch(NullPointerException n) {
							volume=0;
						}
						each.setNumberOfShares(volume);
						fundDailyNext.add(each);
					}
					
					req.setAttribute("fundDaily"+count, fundDailyNext);
					
					req.setAttribute("fundChartName"+count, fundDAO.getFundName(allFunds[i].getFundId()));
							
					/*System.out.println("match loop: "+i);
					System.out.println("fund id: "+searchFundId);
					System.out.println("parameter: "+count);*/
					
					count = count + 1;
				}
				
				/*System.out.println("end loop: "+i);*/
			}
			
			req.setAttribute("fundLimit", count-2);
			
			return "researchFund.jsp";
		} catch (ArrayIndexOutOfBoundsException a) {
			errors.add("We are sorry, we are unable to show this page.");
			return "researchFund.do";
		} catch(NullPointerException n) {
			errors.add("We are sorry, we are unable to show this page.");
			return "researchFund.do";
		} catch(RollbackException e) {
			errors.add("We are sorry, we are unable to show this page.");
        	return "researchFund.do";
		}
	}
}
