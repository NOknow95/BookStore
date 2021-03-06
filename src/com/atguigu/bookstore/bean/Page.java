package com.atguigu.bookstore.bean;

import java.util.List;

/**
 * 用来封装分页信息
 * 
 * @author lilichao
 * 
 * @param <T>
 */
public class Page<T> {
	private String path;
	/**
	 * 当前页码 ， 由用户传过来的
	 */
	private int pageNumber;

	/**
	 * 每页显示几条数据 ， 在Servlet中指定
	 */
	private int pageSize;

	/**
	 * 总页数 ， 通过计算获得
	 */
	// private int totalPage;

	/**
	 * 总记录数 ， 通过查询数据库
	 */
	private int totalRecord;

	/**
	 * 索引位置 ， 通过计算获得
	 */
	// private int index;

	/**
	 * 分页的数据， 通过查询数据库
	 */
	private List<T> data;

	public int getPageNumber() {

		// 如果pageNumber<1,则返回1
		if (pageNumber < 1) {
			return 1;
		}

		// 如果当前页大于总页数，则返回总页数
		if (pageNumber > getTotalPage()) {
			return getTotalPage();
		}

		return pageNumber;
	}

	@Override
	public String toString() {
		return "Page [pageNumber=" + pageNumber + ", pageSize=" + pageSize
				+ ", totalRecord=" + totalRecord + ", data=" + data + "]";
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPage() {

		/**
		 * 在这来计算总页数
		 * 
		 * 总记录数 每页的条数 总页数 10 2 5 9 2 5 8 2 4 7 2 4
		 * 
		 * 当可以整除时： totalRecord/pageSize
		 * 
		 * 当有余数时： totalRecord/pageSize+1
		 */

		// if(getTotalRecord()%getPageSize()==0){
		// return getTotalRecord()/getPageSize();
		// }else{
		// return getTotalRecord()/getPageSize()+1;
		// }
		return (getTotalRecord() - 1) / getPageSize() + 1;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	public int getIndex() {

		/**
		 * 在getIndex方法中来计算index值
		 * 
		 * index pageSize pageNumber 0 3 1 3 3 2 6 3 3 index =
		 * (pageNumber-1)*pagesize
		 */

		return (getPageNumber() - 1) * getPageSize();
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
