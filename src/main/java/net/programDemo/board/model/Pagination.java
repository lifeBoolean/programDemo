package net.programDemo.board.model;

public class Pagination {
	
//	총레코드수
	private int totalCount;
//	화면에 보여줄 글 갯수
	private int viewCount;
//	현재페이지
	private int page;
//	총페이지수
	private int totalPage;
//	목록에 보여줄 리스트 수
	private int rangeSize = 10;
//	첫페이지
	private int startNo;
//	마지막페이지
	private int endNo;
//	출력 시작레코드
	private int rowStart;
//	출력 끝레코드
	private int rowEnd;
//	이전목록페이지
	private boolean prev;
//	다음목록페이지
	private boolean next;
//	검색어타입
	private String searchType = "";
//	검색키워드
	private String keyword = "";
	
	public Pagination() {}
		
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		addData();
	}
	public int getViewCount() {
		return viewCount;
	}
	public void setViewCount(int viewCount) {
		if(viewCount <= 0) {
			this.viewCount = 10;
		} else {
			this.viewCount = viewCount;
		}		
	}
	public int getPage() {
		return page;
	}
	
	public void setPage(int page) {			
		if(page <= 0) {
			this.page = 1;
		} else {
			this.page = page;
		}
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getRangeSize() {
		return rangeSize;
	}
	public void setRangeSize(int rangeSize) {
		if(rangeSize <= 0) {
			this.rangeSize = 10;
		} else {
			this.rangeSize = rangeSize;
		}		
	}
	public int getStartNo() {
		return startNo;
	}
	public void setStartNo(int startNo) {
		this.startNo = startNo;
	}
	public int getEndNo() {
		return endNo;
	}
	public void setEndNo(int endNo) {
		this.endNo = endNo;
	}
	public int getRowStart() {
		rowStart = ((page - 1) * viewCount) + 1;
		return rowStart;
	}

	public void setRowStart(int rowStart) {
		this.rowStart = rowStart;
	}

	public int getRowEnd() {
		this.rowEnd = rowStart + viewCount - 1;
		return rowEnd;
	}

	public void setRowEnd(int rowEnd) {
		this.rowEnd = rowEnd;
	}	

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}
	
	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public void addData() {		
		this.totalPage = (int) Math.ceil(totalCount / (double) viewCount);
		
		this.rowStart = ((page - 1) * viewCount) + 1;
		this.rowEnd = rowStart + viewCount - 1;
		
		this.startNo = (int) Math.floor((page-1) / (double) rangeSize) * rangeSize + 1 ;
		int endNo_ = ((startNo + rangeSize) - 1);
		if(endNo_ > totalPage) {
			this.endNo = totalPage;
			System.out.println("크다");
		} else {
			this.endNo = endNo_;
			System.out.println("작다");
		}
		
		this.prev = startNo == 1 ? false : true;
		this.next = (startNo + rangeSize) >= totalPage ? false : true;
		
		
		System.out.println("rowStart: "+rowStart);
		System.out.println("rowEnd: "+rowEnd);		
		System.out.println("prev: "+prev);		
		System.out.println("next: "+next);		
		
	}
	
	public void pageInfo(int page, int viewCount, String searchType, String keyword) {
		this.page = page;
		this.viewCount = viewCount;
		this.searchType = searchType;
		this.keyword = keyword;
	}

	@Override
	public String toString() {
		return "Pagination [totalCount=" + totalCount + ", viewCount=" + viewCount + ", page=" + page + ", totalPage="
				+ totalPage + ", rangeSize=" + rangeSize + ", startNo=" + startNo + ", endNo=" + endNo + ", rowStart="
				+ rowStart + ", rowEnd=" + rowEnd + ", prev=" + prev + ", next=" + next + ", searchType=" + searchType
				+ ", keyword=" + keyword + "]";
	}

}
