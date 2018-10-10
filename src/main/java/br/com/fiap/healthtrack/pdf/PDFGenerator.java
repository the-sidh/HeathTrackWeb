package br.com.fiap.healthtrack.pdf;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * uma ferramenta simples que criei para facilitar exportar o codigo fonte como
 * pdf como foi solicitado para a atividade do capitulo 7 :P
 * 
 * @author Sid
 *
 */
public class PDFGenerator {

	public static void main(String[] args) throws IOException, DocumentException {
		File file = new File("C:\\Users\\itau-consultor09\\eclipse-workspace\\HealthTrack\\src\\main\\java\\br\\com\\fiap\\healthtrack\\medidas");
		StringBuffer wholeProject = new StringBuffer();
		wholeProject.append(getWholeProject(file, wholeProject));
		// System.out.println(wholeProject);
		generatePDF(wholeProject.toString());

	}

	private static void generatePDF(String wholeProject) throws FileNotFoundException, DocumentException {
		Document document = new Document();
		PdfWriter.getInstance(document, new FileOutputStream("C:\\\\Users\\\\itau-consultor09\\\\healthtrack_heranca.pdf"));

		document.open();
		Font font = FontFactory.getFont(FontFactory.COURIER, 8, BaseColor.BLACK);
		for (String line : wholeProject.split("\\n")) {
			document.add(new Paragraph(line.replace("\t", "         "),font));
		}
		document.close();

	}

	private static StringBuffer getWholeProject(File file, StringBuffer wholeProject) throws IOException {
		if (file.isDirectory()) {
			File[] directoryContent = file.listFiles();
			for (File directoryEntry : directoryContent) {
				getWholeProject(directoryEntry, wholeProject);
			}
		} else {
			if (file.getName().endsWith("java")) {
				String content = "                          "+file.getName() + "\n\n\n";
				content = content + new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())));			
				content += "-------------------------------------------EOF----------------------------------------------\n\n\n\n";
				wholeProject.append(content);
			}
		}
		return wholeProject;
	}

}
