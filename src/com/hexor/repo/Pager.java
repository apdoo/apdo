package com.hexor.repo;

public class Pager {
   private int count;//��������
   private int index=0;//��ʼ���������mysql��˵���ݿ�������ʼ����Ϊ0
   private int data=10;//������������mysql��limit��˵ end�����ѯ����ʼ������ʼ�Ķ�����
   //��limit 2,1 ���ʾ��ѯ���ݿ��������ʼ���ܹ�һ������
   private int pageCount;//��ʾ����ҳ��
   private int currentPage=1;//��ǰҳ
   	
    
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
		return "��ҳ��Ϣ:"+count+"��ʼ"+index+"������"+data;
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
