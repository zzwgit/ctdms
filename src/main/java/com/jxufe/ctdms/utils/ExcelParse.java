package com.jxufe.ctdms.utils;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.jxufe.ctdms.bean.CourseTime;
 

public class ExcelParse {
	public static void main(String[] args) {
		System.out.println(parse("E:\\QQ\\QQmessage\\1059654342\\FileRecv\\软通学院本科162学期课表_撤班后.xls"));
	}
	
	public static List<ExcelBean> parse(String ExcelPath){
		InputStream stream;
		try {
			stream = new FileInputStream(ExcelPath);
		} catch (FileNotFoundException e) { 
			return Collections.emptyList();
		}
		return parseByPath(stream,ExcelPath);
	} 
	public static List<ExcelBean> parse(byte[] bytes,String name){ 
		InputStream stream = new ByteArrayInputStream(bytes); 
		return parseByPath(stream,name);
	}
	private static List<ExcelBean> parseByPath(InputStream stream,String ExcelPath) { 
		List<ExcelBean> list = null; // 作为course 表的信息插入
		Workbook workbook = null; 
		try {  
			if (ExcelPath.endsWith("xls")) {
				workbook = new HSSFWorkbook(stream);
			} else if (ExcelPath.endsWith("xlsx")) {
				workbook = new XSSFWorkbook(stream);
			} else {
				stream.close();
				return Collections.emptyList();
			}
			list = new ArrayList<>();
			Sheet sheet = (Sheet) workbook.getSheetAt(1);
			if (!excelCheck(sheet)){ 
				System.err.println("不规范的教学计划");
				return Collections.emptyList();
			}
			int rows = sheet.getPhysicalNumberOfRows();
			for (int r = 1; r < rows; r++) {
				Row row = sheet.getRow(r);
				if (row != null) {
					int cells = row.getPhysicalNumberOfCells();
					ExcelBean eb = new ExcelBean();
					eb.setCourseTimes(new ArrayList<CourseTime>());
					for (short i = 0; i < cells; i++) {
						Cell cell = row.getCell((short) i);
						if (cell != null) {
							switch (i) {
							case 0:
								eb.setCourseCode(cellValueToString(cell));
								break;
							case 1:
								eb.setShift(cellValueToString(cell));
								break;
							case 3:
								eb.setCourseName(cellValueToString(cell));
								break;
							case 4:
								eb.setTeacherName(cellValueToString(cell));
								break;
							case 5: 
							case 6: 
							case 7:
								setCourseTime(eb.getCourseTimes(),cellValueToString(cell)); 
								break;
							case 8: 
							case 9: 
							case 10:
								setClassRoom(eb.getCourseTimes(),cellValueToString(cell), i - 8); 
								break;
							case 11:
								eb.setWeekly(cellValueToString(cell));
								break;
							case 14:
								eb.setDistrict(cellValueToString(cell));
								break;
							default:
								break;
							}
						}
					}  
					list.add(eb);
				}
			}
		} catch (Exception e) {  
			e.printStackTrace();
			return Collections.emptyList();
		}
		return list;
	}
	private static void setCourseTime(List<CourseTime> cts ,String str){
		if("".equals(str))
			return;
		CourseTime ct = new CourseTime(str); 
		cts.add(ct);
	}
	private static void setClassRoom(List<CourseTime> cts ,String str,int index){
		if("".equals(str))
			return;
		if(cts.size() -1 < index )
			return;
		cts.get(index).setClassRoom(str);
	}
	/**
	 * 检查是否为标准的excel
	 * @param sheet
	 * @return
	 */
	private static boolean excelCheck(Sheet sheet) {
		Row row = sheet.getRow(0);
		for (int i = 0; i < 15; i++)
			switch (i) {
			case 0:
				if (!"课程号".equals(row.getCell(i).getStringCellValue()))
					return false;
				break;
			case 1:
				if (!"班次".equals(row.getCell(i).getStringCellValue()))
					return false;
				break;
			case 2:
				if (!"学分".equals(row.getCell(i).getStringCellValue()))
					return false;
				break;
			case 3:
				if (!"课程名称".equals(row.getCell(i).getStringCellValue()))
					return false;
				break;
			default:
				break;
			}
		return true;
	}
	
	private static String cellValueToString(Cell cell) {
		if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
			return String.valueOf(cell.getNumericCellValue());
		} else if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {
			return "";
		}
		String value = cell.getStringCellValue(); 
		return "0000".equals(value)?"":value; 
	}
	 
}
