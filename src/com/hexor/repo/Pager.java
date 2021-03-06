package com.hexor.repo;

public class Pager {
   private int count;//数据总数
   private int index=0;//起始条数，针对mysql来说数据库数据起始条数为0
   private int data=10;//查多少条，针对mysql的limit来说 end代表查询从起始条数开始的多少条
   //若limit 2,1 则表示查询数据库第三条开始的总共一条数据
   private int pageCount;//显示的总页数
   private int currentPage=1;//当前页
   	
    
    public int getCurrentPage() {
	return currentPage;
}
public void setCurrentPage(int currentPage) {
	this.currentPage = currentPage;
}
	public int getPageCount() {
	return pageCount;
}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

   
   
	public int getCount() {
		return count;
	}
	@Override
	public String toString() {
		return "分页信息:"+count+"起始"+index+"总条数"+data;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public int getData() {
		return data;
	}
	public void setData(int data) {
		this.data = data;
	}
	
}
