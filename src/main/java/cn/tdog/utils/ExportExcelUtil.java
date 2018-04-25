package cn.tdog.utils;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;

@SuppressWarnings("all")
public class ExportExcelUtil {
	private static SimpleDateFormat SDF = null;

	@SuppressWarnings("deprecation")
	public static HSSFWorkbook generateExcel(
									Collection<?> collection, 
									List<ExportExcelParam> listExportExcelParam,
									String strTitleName,
									String strSheetName
									) throws IllegalAccessException,
																				InvocationTargetException, NoSuchMethodException {

		HSSFWorkbook wb        = new HSSFWorkbook();
		int          icellsize = 0;
		

		// 设置单元格样式

		HSSFCellStyle style = wb.createCellStyle();
		style.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
		// style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND); //设置单元格背景颜色
		// style.setBorderBottom(HSSFCellStyle.BORDER_THIN); //设置单元格下部线加粗
		// style.setBorderLeft(HSSFCellStyle.BORDER_THIN); //设置单元格左部线加粗
		// style.setBorderRight(HSSFCellStyle.BORDER_THIN); //设置单元格右部线加粗
		// style.setBorderTop(HSSFCellStyle.BORDER_THIN); //设置单元格上部线加粗
		// style.setAlignment(HSSFCellStyle.ALIGN_CENTER); //设置单元格字符居中
		style.setWrapText(true);

		// 生成一个字体
		HSSFFont font = wb.createFont();
		// font.setColor(HSSFColor.W HITE.index); //设置字体颜色
		font.setFontHeightInPoints((short) 10);
		// font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); //设置字体加粗
		// 把字体应用到当前的样式
		style.setFont(font);

		if (collection == null || collection.size() < 1)
			return wb; // 无数据侧返回
		if (0 == listExportExcelParam.size()  ) return wb;
		
		icellsize = listExportExcelParam.size();		
		
		HSSFSheet     sheet  = wb.createSheet();
		int           rowNum = 0;		
		HSSFCellStyle s      = wb.createCellStyle();
		
		s.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 设置单元格字符居中
		HSSFRow r = sheet.createRow(rowNum);
		//合并行
		sheet.addMergedRegion ( new CellRangeAddress( 0, 0, 0, icellsize) );
		
		HSSFCell c = r.createCell((short) 0);
		c.setCellStyle(s);
		c.setCellValue(strTitleName);
		rowNum++;
		
		HSSFRow row = null;
		row = sheet.createRow(rowNum);		
		 
		setTitle(row, listExportExcelParam, wb);		
		setColumnWidth(sheet,listExportExcelParam);
		
		int count = 1;
		for (Iterator<?> iter = collection.iterator(); iter.hasNext();) {
			Object exportEle = iter.next();
			// 行对象
			row = sheet.createRow(++rowNum);
			// 设置对应值
			setRow(wb, row, listExportExcelParam, exportEle, count);
			count++;
		}
		wb.setSheetName(0, strSheetName);

		return wb;
	}
	
	

	@SuppressWarnings("deprecation")
	private static void setTitle(HSSFRow row, 
								List<ExportExcelParam> listparam,
								HSSFWorkbook wb ) throws IllegalAccessException,
										InvocationTargetException, 
										NoSuchMethodException {
		
		HSSFCellStyle style = wb.createCellStyle();
		
		style.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND); // 设置单元格背景颜色
		// style.setBorderBottom(HSSFCellStyle.BORDER_THIN); //设置单元格下部线加粗
		// style.setBorderLeft(HSSFCellStyle.BORDER_THIN); //设置单元格左部线加粗
		// style.setBorderRight(HSSFCellStyle.BORDER_THIN); //设置单元格右部线加粗
		// style.setBorderTop(HSSFCellStyle.BORDER_THIN); //设置单元格上部线加粗
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 设置单元格字符居中
		// HSSFFont font = wb.createFont();
		// font.setColor(HSSFColor.WHITE.index); //设置字体颜色
		
		HSSFCell c = row.createCell((short) 0);
		c.setCellStyle(style);
		c.setCellValue("序号");
		
		ExportExcelParam item = null;
		
		for (int k = 0; k < listparam.size(); k++) {
			item = listparam.get(k);
			if ( null == item) break;
			
			HSSFCell cell = row.createCell((short) k + 1);
			cell.setCellStyle(style);
			cell.setCellValue(item.getStrMeaning());
		}
		
	}

	// 设置表格列宽 这里可以根据字符长度调整
	public static void setColumnWidth(HSSFSheet sheet, List<ExportExcelParam> listparam) {
		sheet.setColumnWidth((short) 0, (short) 10 * 256);// 调整第1列宽度
		
		ExportExcelParam item = null;
		
		for (int k = 0; k < listparam.size(); k++) {
			item = listparam.get(k);

			if ( null == item) break;

			sheet.setColumnWidth((short) (k+1), (short) item.getColumnWidth().shortValue());// 调整列宽度
		}
	}

	/**
	 * 
	 * 方法描述: 为Excel页中的每个横行设置值
	 * 
	 * @param row
	 * @param strName
	 * @param exportModel
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 *             void
	 */
	@SuppressWarnings("deprecation")
	private static void setRow(HSSFWorkbook wb, 
								HSSFRow row,
								List<ExportExcelParam> listparam, 
								Object exportModel, 
								int count)
			throws IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		Object temp = null;
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 设置单元格字符居中
		HSSFCell c = row.createCell((short) 0);
		c.setCellValue(count);
		ExportExcelParam item = null;
		
		for (int k = 0; k < listparam.size(); k++) {
			item = listparam.get(k);

			if ( null == item) break;
			// Cell对象
			HSSFCell cell = row.createCell((short) k + 1);
			cell.setCellStyle(style);
			// 设置对应值
			try {
				// 检查该实体是否有这个属性
				temp = PropertyUtils.getProperty(exportModel, item.getStrName());
			} catch (Exception e) {
				e.getStackTrace();
				continue;
			}
			if (temp == null) {
				cell.setCellValue("无");
			} else {
				if (temp instanceof Date) {
					cell.setCellValue(getDateTimeFormat().format(
							Date.class.cast(temp)));
				} else if (NumberUtils.isNumber(temp.toString())) {
					cell.setCellValue(temp.toString());
				} else {
					cell.setCellValue(temp.toString());
				}
			}
		}
	}

	/**
	 * 方法描述: 获取系统时间格式
	 * 
	 * @return SimpleDateFormat
	 */
	public static SimpleDateFormat getDateFormat() {
		if (SDF == null) {
			SDF = new SimpleDateFormat("yyyy-MM-dd");
		}
		return SDF;
	}

	/**
	 * 方法描述: 获取系统精确时间格式
	 * 
	 * @return SimpleDateFormat
	 */
	public static SimpleDateFormat getDateTimeFormat() {
		if (SDF == null) {
			SDF = new SimpleDateFormat("yyyy-MM-dd");
		}
		return SDF;
	}
}
