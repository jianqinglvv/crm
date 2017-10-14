package com.atguigu.crm.orm;

import java.util.List;

public class Page<T> {

	private int pageNo=1;
	private int pageSize=5;
	
	private int totalElements;
	
	private List<T> content;

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		if(pageNo<1){
			this.pageNo=1;
		}
		else{
			this.pageNo = pageNo;
		}
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(int totalElements) {
		this.totalElements = totalElements;
		if(this.pageNo>this.getPages()){
			this.pageNo=this.getPages();
		}
	}

	public List<T> getContent() {
		return content;
	}

	public void setContent(List<T> content) {
		this.content = content;
	}
	
	//获取总页数
	public int getPages(){
		int pages=totalElements/pageSize;
		return (totalElements%pageSize==0?pages:pages+1);
	}
	//有上一页？
	public boolean isHasPrev(){
		if(pageNo>1){
			return true;
		}
		return false;
	}
	//有下一页？	
	public boolean isHasNext(){
		if(pageNo<this.getPages()){
			return true;
		}
		return false;
	}
	
	public int getPrev(){
		return pageNo-1;
	}
	
	public int getNext(){
		return pageNo+1;
	}
	
}
