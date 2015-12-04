package mainPackage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class XMLEditor {
	public static final String XML_DEC = "<?xml version=\"1.0\" encoding=\"gb2312\"?>";
	public static final String NEW_LINE = "\r\n";
	
	public String XMLName = null;
	public String XMLDetails = null;
	
	File file = null;
	String path = null;
	BufferedWriter bw = null;
	
	public XMLEditor(String XMLName, String XMLDetails) {
		this.XMLName = XMLName;
		this.XMLDetails = XMLDetails;
		init();
	}

	public void init() {
		path = getClass().getResource("/").getFile().toString();
		path = path.substring(0, path.length() - 4);
		path += "XMLs";
		file = new File(path);
		if (!file.exists())
			file.mkdirs();
		path += "/" + XMLName + ".xml";
		file = new File(path);
		if (!file.exists())
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		
			try {
				bw = new BufferedWriter(new FileWriter(file,false));
				bw.write(XML_DEC + NEW_LINE);
			} catch (IOException e) {
				System.out.println("写入失败");
				e.printStackTrace();
			} finally {
				try {
					bw.flush();
					bw.close();
				} catch (IOException e) {
					System.out.println("关闭失败");
					e.printStackTrace();
				}
			}
	}

	/*
	 * 根据String生成xml文件
	 * 单个换行为换行，两个换行为下一句话
	 * “choise:”为选项按钮，多个按钮用“;(英文）”分隔
	 * 每个String生成一个xml。每次choise后更换xml文件
	 */
	public void madeXML() {
		String strs[] = null;
		strs = XMLDetails.split("\n\n");
		try {
			bw = new BufferedWriter(new FileWriter(file, true));
			bw.write("<story>" + NEW_LINE);
			for (int i = 0; i < strs.length; i++) {
				if(strs[i].startsWith("choise:"))
				{
					strs = strs[i].split(";");
					for(int j=0; j<strs.length; j++) {
					bw.write("<choise" + j + ">" + NEW_LINE);
					bw.write("<details>");
					bw.write(strs[j]);
					bw.write("</details>" + NEW_LINE);
					bw.write("</choise" + j + ">" + NEW_LINE);
					}
					break;
				}
				bw.write("<sentence" + i + ">" + NEW_LINE);
				bw.write("<details>");
				bw.write(strs[i]);
				bw.write("</details>" + NEW_LINE);
				bw.write("</sentence" + i + ">" + NEW_LINE);
			}
			bw.write("</story>" + NEW_LINE);
		} catch (IOException e) {
			System.out.println("写入失败");
			e.printStackTrace();
		} finally {
			try {
				bw.flush();
				bw.close();
			} catch (IOException e) {
				System.out.println("关闭失败");
				e.printStackTrace();
			}

		}

	}
}
