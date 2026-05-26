package itch.tsp.util;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;

public class LiberacionAsesorInternoPdf {

	public static byte[] generarPdf(
			String nombreProyecto,
			String nombreResidente,
			String numeroControl,
			String carrera,
			String nombreAsesorInterno,
			String nombreJefeDepartamento,
			String nombreDepartamento,
			String periodoTexto) throws Exception {

		Document document = new Document(new Rectangle(595, 842), 60, 60, 50, 50);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		PdfWriter.getInstance(document, out);
		document.open();

		Font normal = new Font(Font.FontFamily.TIMES_ROMAN, 12);
		Font bold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
		Font small = new Font(Font.FontFamily.TIMES_ROMAN, 11);

		SimpleDateFormat sdf = new SimpleDateFormat("d 'de' MMMM 'de' yyyy", new Locale("es", "MX"));
		String fechaActual = sdf.format(new Date());

		Paragraph fecha = new Paragraph("Chilpancingo, Gro., " + fechaActual, normal);
		fecha.setAlignment(Element.ALIGN_RIGHT);
		document.add(fecha);
		document.add(new Paragraph(" "));

		Paragraph destinatario = new Paragraph();
		destinatario.add(new Paragraph(nombreJefeDepartamento.toUpperCase(), bold));
		destinatario.add(new Paragraph(nombreDepartamento.toUpperCase(), bold));
		destinatario.add(new Paragraph("INSTITUTO TECNOLÓGICO DE CHILPANCINGO", bold));
		destinatario.add(new Paragraph("P R E S E N T E", bold));
		document.add(destinatario);
		document.add(new Paragraph(" "));

		String texto = "Por este medio comunico a usted, que el Proyecto de Residencia Profesional denominado "
				+ nombreProyecto.toUpperCase()
				+ ", realizado por el(la) estudiante "
				+ nombreResidente
				+ " con no. de control "
				+ numeroControl
				+ ", del Programa Educativo de "
				+ carrera
				+ ", en el que fungí como asesor(a) interno(a); fue desarrollado en tiempo y forma de acuerdo con su programa de actividades en el periodo del "
				+ periodoTexto
				+ ".";
		Paragraph cuerpo1 = new Paragraph(texto, normal);
		cuerpo1.setAlignment(Element.ALIGN_JUSTIFIED);
		document.add(cuerpo1);
		document.add(new Paragraph(" "));

		Paragraph cuerpo2 = new Paragraph(
				"Por lo anterior, una vez que ha sido revisado y avalado el Reporte de Residencia Profesional mencionado, se da por concluido el proyecto, quedando liberado(a) el(la) estudiante que en él intervino.",
				normal);
		cuerpo2.setAlignment(Element.ALIGN_JUSTIFIED);
		document.add(cuerpo2);
		document.add(new Paragraph(" "));
		document.add(new Paragraph("Sin otro particular por el momento, reciba un cordial saludo.", normal));
		document.add(new Paragraph(" "));
		document.add(new Paragraph(" "));
		document.add(new Paragraph("A t e n t a m e n t e", bold));
		document.add(new Paragraph(" "));
		document.add(new Paragraph(" "));
		document.add(new Paragraph("________________________________", normal));
		document.add(new Paragraph(nombreAsesorInterno.toUpperCase(), bold));
		document.add(new Paragraph("Asesor(a) Interno(a)", normal));
		document.add(new Paragraph(" "));
		document.add(new Paragraph("C.c.p. Archivo.", small));

		document.close();
		return out.toByteArray();
	}
}