package hchen365_CSCI201_Assignment4;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.net.HttpURLConnection;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.google.gson.Gson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@WebServlet("/html/stockInfo")
public class TradeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        PrintWriter out = response.getWriter();
	        String stockTicker = request.getParameter("stockTicker");
	        StockPriceInfo stockPriceInfo = getStockPriceJson(stockTicker);
	        StockInfo stockInfo = getStockInfoJson(stockTicker);
	        
	        String htmlResponse = "<p><b>" + stockInfo.getTicker() + "</b></p>"
	        					+ "<p>" + stockInfo.getName() + "</p>"
	        					+ "<p>" + stockInfo.getExchange() + "</p>"
	        					+ "<p>Summary</p>"
	        					+ "<hr>"
	        					+ "<table id=\"stock_price_table\">"
	        					+ "<tr><td>High Price:</td><td>" + stockPriceInfo.getH() + "</td></tr>"
	        				    + "<tr><td>Low Price:</td><td>" + stockPriceInfo.getL() + "</td></tr>"
	        				    + "<tr><td>Open Price:</td><td>" + stockPriceInfo.getO() + "</td></tr>"
	        				    + "<tr><td>Close Price:</td><td>" + stockPriceInfo.getPc() + "</td></tr>"
	        					+ "</table>"
	        				    + "<hr>"
	        					+ "<p><b>Company Information</b></p>"
	        				    + "<div id=\"company_info_div\">"
	        					+ "<p><b>IPO Date: </b>" + stockInfo.getIpo() + "</p>"
	        					+ "<p><b>Market Cap ($M): </b>" + stockInfo.getMarketCapitalization() + "</p>"
	        					+ "<p><b>Share Outstanding: </b>" + stockInfo.getShareOutstanding() + "</p>"
	        					+ "<p><b>Website: </b>" + stockInfo.getWeburl() + "</p>"
	        					+ "<p><b>Phone: </b>" + stockInfo.getPhone() + "</p>"
	        					+ "</div>";
	        
	        response.setContentType("text/html");
	        out.println(htmlResponse);
	    }
	
	private static StockPriceInfo getStockPriceJson(String ticker) throws IOException{
		URL url = new URL(String.format("https://finnhub.io/api/v1/quote?symbol=%s&token=%s", ticker, "cnuuc69r01qub9j02m6gcnuuc69r01qub9j02m70"));
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		Gson gson = new Gson();
		return gson.fromJson(in.readLine(), StockPriceInfo.class);
	}
	
	private static StockInfo getStockInfoJson(String ticker) throws IOException {
		URL url = new URL(String.format("https://finnhub.io/api/v1/stock/profile2?symbol=%s&token=%s", ticker, "cnuuc69r01qub9j02m6gcnuuc69r01qub9j02m70"));
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		Gson gson = new Gson();
		return gson.fromJson(in.readLine(), StockInfo.class);
	}
}

class StockPriceInfo {
	@SerializedName("c")
	@Expose
	private Double c;
	@SerializedName("d")
	@Expose
	private Double d;
	@SerializedName("dp")
	@Expose
	private Double dp;
	@SerializedName("h")
	@Expose
	private Double h;
	@SerializedName("l")
	@Expose
	private Double l;
	@SerializedName("o")
	@Expose
	private Double o;
	@SerializedName("pc")
	@Expose
	private Double pc;
	@SerializedName("t")
	@Expose
	private Integer t;
	
	public Double getC() {
		return c;
	}
	
	public void setC(Double c) {
		this.c = c;
	}
	
	public Double getD() {
		return d;
	}
	
	public void setD(Double d) {
		this.d = d;
	}
	
	public Double getDp() {
		return dp;
	}
	
	public void setDp(Double dp) {
		this.dp = dp;
	}
	
	public Double getH() {
		return h;
	}
	
	public void setH(Double h) {
		this.h = h;
	}
	
	public Double getL() {
		return l;
	}
	
	public void setL(Double l) {
		this.l = l;
	}
	
	public Double getO() {
		return o;
	}
	
	public void setO(Double o) {
		this.o = o;
	}
	
	public Double getPc() {
		return pc;
	}
	
	public void setPc(Double pc) {
		this.pc = pc;
	}
	
	public Integer getT() {
		return t;
	}
	
	public void setT(Integer t) {
		this.t = t;
	}
}

class StockInfo {
	@SerializedName("country")
	@Expose
	private String country;
	@SerializedName("currency")
	@Expose
	private String currency;
	@SerializedName("estimateCurrency")
	@Expose
	private String estimateCurrency;
	@SerializedName("exchange")
	@Expose
	private String exchange;
	@SerializedName("finnhubIndustry")
	@Expose
	private String finnhubIndustry;
	@SerializedName("ipo")
	@Expose
	private String ipo;
	@SerializedName("logo")
	@Expose
	private String logo;
	@SerializedName("marketCapitalization")
	@Expose
	private Double marketCapitalization;
	@SerializedName("name")
	@Expose
	private String name;
	@SerializedName("phone")
	@Expose
	private String phone;
	@SerializedName("shareOutstanding")
	@Expose
	private Double shareOutstanding;
	@SerializedName("ticker")
	@Expose
	private String ticker;
	@SerializedName("weburl")
	@Expose
	private String weburl;
	
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	public String getCurrency() {
		return currency;
	}
	
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	public String getEstimateCurrency() {
		return estimateCurrency;
	}
	
	public void setEstimateCurrency(String estimateCurrency) {
		this.estimateCurrency = estimateCurrency;
	}
	
	public String getExchange() {
		return exchange;
	}
	
	public void setExchange(String exchange) {
		this.exchange = exchange;
	}
	
	public String getFinnhubIndustry() {
		return finnhubIndustry;
	}
	
	public void setFinnhubIndustry(String finnhubIndustry) {
		this.finnhubIndustry = finnhubIndustry;
	}
	
	public String getIpo() {
		return ipo;
	}
	
	public void setIpo(String ipo) {
		this.ipo = ipo;
	}
	
	public String getLogo() {
		return logo;
	}
	
	public void setLogo(String logo) {
		this.logo = logo;
	}
	
	public Double getMarketCapitalization() {
		return marketCapitalization;
	}
	
	public void setMarketCapitalization(Double marketCapitalization) {
		this.marketCapitalization = marketCapitalization;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public Double getShareOutstanding() {
		return shareOutstanding;
	}
	
	public void setShareOutstanding(Double shareOutstanding) {
		this.shareOutstanding = shareOutstanding;
	}
	
	public String getTicker() {
		return ticker;
	}
	
	public void setTicker(String ticker) {
		this.ticker = ticker;
	}
	
	public String getWeburl() {
		return weburl;
	}
	
	public void setWeburl(String weburl) {
		this.weburl = weburl;
	}
}
