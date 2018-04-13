package cn.tdog.utils;

public class ExportExcelParam {
	
	private String strMeaning;
	private String strName;
	private Integer ColumnWidth;
	
	
	public ExportExcelParam(String strMeaning, String strName,
			Integer columnWidth) {
		super();
		this.strMeaning = strMeaning;
		this.strName = strName;
		ColumnWidth = columnWidth;
	}
	
	public String getStrMeaning() {
		return strMeaning;
	}
	public void setStrMeaning(String strMeaning) {
		this.strMeaning = strMeaning;
	}
	public String getStrName() {
		return strName;
	}
	public void setStrName(String strName) {
		this.strName = strName;
	}
	public Integer getColumnWidth() {
		return ColumnWidth;
	}
	public void setColumnWidth(Integer columnWidth) {
		ColumnWidth = columnWidth;
	}
	
	
	
	
}
