package com.javaex.vo;
public class ProductVo {
    
    private int proNo;
    private String proName;
    private String proCateg;
    private int proStock;
    private int proPrice;
    private String proDesc;
    private int proOnSale;
    private String proFileName;
    private String proDate;
    private int proHit;
    private float proRate;
    private int amount;
    
    public ProductVo() {
    }
    
    public ProductVo(String proName, String proCateg, int proStock, int proPrice, String proDesc, int proOnSale,
            String proFileName, int proHit) {
        this.proName = proName;
        this.proCateg = proCateg;
        this.proStock = proStock;
        this.proPrice = proPrice;
        this.proDesc = proDesc;
        this.proOnSale = proOnSale;
        this.proFileName = proFileName;
        this.proHit = proHit;
    }
    public ProductVo(int proNo, String proName, String proCateg, int proStock, int proPrice, String proDesc,
            int proOnSale, String proFileName, String proDate, int proHit) {
        this.proNo = proNo;
        this.proName = proName;
        this.proCateg = proCateg;
        this.proStock = proStock;
        this.proPrice = proPrice;
        this.proDesc = proDesc;
        this.proOnSale = proOnSale;
        this.proFileName = proFileName;
        this.proDate = proDate;
        this.proHit = proHit;
    }
    
    
	public ProductVo(String proName, String proCateg, int proStock, int proPrice, String proDesc, int proOnSale) {
		this.proName = proName;
		this.proCateg = proCateg;
		this.proStock = proStock;
		this.proPrice = proPrice;
		this.proDesc = proDesc;
		this.proOnSale = proOnSale;
	}


	public ProductVo(String proName, String proCateg, int proStock, int proPrice, String proDesc,
			int proOnSale, String proFileName) {

		this.proName = proName;
		this.proCateg = proCateg;
		this.proStock = proStock;
		this.proPrice = proPrice;
		this.proDesc = proDesc;
		this.proOnSale = proOnSale;
		this.proFileName = proFileName;
	}
	
	public ProductVo(int proNo, String proName, String proCateg, int proStock, int proPrice, String proDesc,
			int proOnSale, String proFileName) {
		this.proNo = proNo;
		this.proName = proName;
		this.proCateg = proCateg;
		this.proStock = proStock;
		this.proPrice = proPrice;
		this.proDesc = proDesc;
		this.proOnSale = proOnSale;
		this.proFileName = proFileName;
	}

	@Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString();
    }
    public String getProName() {
        return proName;
    }
    public void setProName(String proName) {
        this.proName = proName;
    }
    public int getProNo() {
        return proNo;
    }
    public void setProNo(int proNo) {
        this.proNo = proNo;
    }
    public String getProCateg() {
        return proCateg;
    }
    public void setProCateg(String proCateg) {
        this.proCateg = proCateg;
    }
    public int getProStock() {
        return proStock;
    }
    public void setProStock(int proStock) {
        this.proStock = proStock;
    }
    public int getProPrice() {
        return proPrice;
    }
    public void setProPrice(int proPrice) {
        this.proPrice = proPrice;
    }
    public String getProDesc() {
        return proDesc;
    }
    public void setProDesc(String proDesc) {
        this.proDesc = proDesc;
    }
    public String getProFileName() {
        return proFileName;
    }
    public void setProFileName(String proFileName) {
        this.proFileName = proFileName;
    }
    public String getProDate() {
        return proDate;
    }
    public void setProDate(String proDate) {
        this.proDate = proDate;
    }
    public int getProHit() {
        return proHit;
    }
    public void setProHit(int proHit) {
        this.proHit = proHit;
    }
    public int getProOnSale() {
        return proOnSale;
    }
    public void setProOnSale(int proOnSale) {
        this.proOnSale = proOnSale;
    }

	public float getProRate() {
		return proRate;
	}

	public void setProRate(float proRate) {
		this.proRate = proRate;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
    
}
