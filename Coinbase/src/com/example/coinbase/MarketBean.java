package com.example.coinbase;
/**
 * 
 date:交易时间
 date_ms:交易时间(ms)
 price: 交易价格
 amount: 交易数量
 tid: 交易生成ID
 type: buy/sell
 *
 */
public class MarketBean {

	public String  price;

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
	
}
