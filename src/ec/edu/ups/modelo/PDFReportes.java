package ec.edu.ups.modelo;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author paul idrovo
 */
public class PDFReportes {

    // Fonts definitions (Definición de fuentes).
    private static Font tituloFont;
    private static Font textoFont;
    private static Font textoFontPar;
    private static Font textoFontBold;

    private int saltoInicial;
    private int saltoFinal;

    private Chapter chapter = new Chapter(new Paragraph("", textoFont), 0);

    private static String iTextExampleImage = "";

    /**
     * We create a PDF document with iText using different elements to learn to
     * use this library.Creamos un documento PDF con iText usando diferentes
     * elementos para aprender a usar esta librería.
     *
     * @param pdfNewFile  <code>String</code> pdf File we are going to write.
     * Fichero pdf en el que vamos a escribir.
     * @param fechaInicial
     * @param fechaFinal
     *
     */
    public void createPDF(File pdfNewFile, String fechaInicial, String fechaFinal) {
        // We create the document and set the file name.        
        // Creamos el documento e indicamos el nombre del fichero.
        saltoInicial = 10;
        saltoFinal = 30;
        int letraPDF = 20;
        int letraPDF1 = letraPDF + 4;
        int letraPDF2 = letraPDF + 2;
        tituloFont = FontFactory.getFont(FontFactory.HELVETICA, letraPDF1, Font.BOLD);
        textoFont = FontFactory.getFont(FontFactory.HELVETICA, letraPDF, Font.NORMAL);
        textoFontPar = FontFactory.getFont(FontFactory.HELVETICA, letraPDF, Font.BOLD);
        textoFontBold = FontFactory.getFont(FontFactory.HELVETICA, letraPDF2, Font.BOLD);
        try {
            Document document = new Document();
            try {

                PdfWriter.getInstance(document, new FileOutputStream(pdfNewFile));
                iTextExampleImage = "C:\\Programas Java\\UPS\\3ROCICLO\\Parqueadero\\Reportes\\fondo.jpg";
            } catch (FileNotFoundException fileNotFoundException) {
                System.out.println("No such file was found to generate the PDF "
                        + "(No se encontró el fichero para generar el pdf)" + fileNotFoundException);
            }

            document.setPageSize(new Rectangle(1594, 2256));
            document.open();
            // We add metadata to PDF
            // Añadimos los metadatos del PDF

            document.addTitle("REPORTE CUENTAS");

            boolean one = true;
            int i = 1;
            double total = 0;
            String parametro = "";
            String valorReferencial = "";
            String categoria = "";
            PdfPCell cell;
            PdfPTable tabla = new PdfPTable(8);     //Numero de columnas 
            tabla.setWidthPercentage(90); // Porcentaje de la pagina que ocupa
            tabla.setHorizontalAlignment(Element.ALIGN_CENTER);//Alineacion horizontal
            tabla.setWidths(new float[]{40, 40, 40, 40, 40, 40, 40, 40});
            Fondo_Encabezado(fechaInicial, fechaFinal);
            boolean primero = false;
            Ticket tk = new Ticket();
            for (Ticket ticket : tk.getTicketFecha(fechaInicial, fechaFinal)) {

                cell = new PdfPCell(new Paragraph(ticket.getId() + "", textoFontBold));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.disableBorderSide(Rectangle.TOP | Rectangle.RIGHT | Rectangle.LEFT | Rectangle.BOTTOM);
                tabla.addCell(cell);

                cell = new PdfPCell(new Paragraph(ticket.getTipo(), textoFontBold));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.disableBorderSide(Rectangle.TOP | Rectangle.RIGHT | Rectangle.LEFT | Rectangle.BOTTOM);
                tabla.addCell(cell);

                cell = new PdfPCell(new Paragraph(ticket.getFechaEntrada(), textoFontBold));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.disableBorderSide(Rectangle.TOP | Rectangle.RIGHT | Rectangle.LEFT | Rectangle.BOTTOM);
                tabla.addCell(cell);

                cell = new PdfPCell(new Paragraph(ticket.getFechaSalida(), textoFontBold));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.disableBorderSide(Rectangle.TOP | Rectangle.RIGHT | Rectangle.LEFT | Rectangle.BOTTOM);
                tabla.addCell(cell);
                total = total + ticket.getValor();
                cell = new PdfPCell(new Paragraph(ticket.getValor() + "", textoFontBold));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.disableBorderSide(Rectangle.TOP | Rectangle.RIGHT | Rectangle.LEFT | Rectangle.BOTTOM);
                tabla.addCell(cell);

                cell = new PdfPCell(new Paragraph(ticket.getDireccion(), textoFontBold));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.disableBorderSide(Rectangle.TOP | Rectangle.RIGHT | Rectangle.LEFT | Rectangle.BOTTOM);
                tabla.addCell(cell);

                cell = new PdfPCell(new Paragraph(ticket.getCedula(), textoFontBold));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.disableBorderSide(Rectangle.TOP | Rectangle.RIGHT | Rectangle.LEFT | Rectangle.BOTTOM);
                tabla.addCell(cell);

                cell = new PdfPCell(new Paragraph(ticket.getCorreo(), textoFontBold));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.disableBorderSide(Rectangle.TOP | Rectangle.RIGHT | Rectangle.LEFT | Rectangle.BOTTOM);
                tabla.addCell(cell);

                cell = new PdfPCell(new Paragraph(ticket.getNombre(), textoFontBold));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.disableBorderSide(Rectangle.TOP | Rectangle.RIGHT | Rectangle.LEFT | Rectangle.BOTTOM);
                tabla.addCell(cell);

                if (i > saltoFinal) {
                    i = 0;
                    if (primero) {
                        chapter.add(tabla);
                        document.add(chapter);
                        one = false;
                    }
                    chapter.clear();
                    Fondo_Encabezado(fechaInicial, fechaFinal);
                }
                i++;
            }
            if (one) {
                chapter.add(tabla);
            }
            chapter.add(new Paragraph("GANACIAS TOTALES: $ " + total, FontFactory.getFont(FontFactory.HELVETICA, 30, Font.BOLD, BaseColor.BLACK)));
            document.add(chapter);
            
            document.close();
        } catch (DocumentException documentException) {
            System.out.println("The file not exists (Se ha producido un error al generar un documento): " + documentException);
        }
    }

    private void Fondo_Encabezado(String fechaInicio, String fechaFinal) {
        try {
            chapter.clear();
            //DATOS DEL PACIENTE
            chapter.setNumberDepth(0);
            for (int i = 0; i < saltoInicial; i++) {
                chapter.add(new Paragraph(" "));
            }
            chapter.add(new Paragraph("                                REPORTE DE TICKEST", FontFactory.getFont(FontFactory.HELVETICA, 30, Font.BOLD, BaseColor.WHITE)));
            chapter.add(new Paragraph(""));
            chapter.add(new Paragraph("                                FECHA INICIAL: " + fechaInicio, FontFactory.getFont(FontFactory.HELVETICA, 30, Font.BOLD, BaseColor.WHITE)));
            chapter.add(new Paragraph("                                FECHA FINAL: " + fechaFinal, FontFactory.getFont(FontFactory.HELVETICA, 30, Font.BOLD, BaseColor.WHITE)));

            for (int i = 0; i < 16; i++) {
                chapter.add(new Paragraph(" "));
            }

            Image image;
            try {
                image = Image.getInstance(iTextExampleImage);
                image.setAbsolutePosition(0, 2256 - image.getHeight());
                chapter.add(image);
            } catch (BadElementException ex) {
                System.out.println("Image BadElementException" + ex);
            } catch (IOException ex) {
                System.out.println("Image IOException " + ex);
            }

            chapter.add(new Paragraph(" "));
            PdfPCell cell;
            PdfPTable tabla = new PdfPTable(8);     //Numero de columnas
            tabla.setWidthPercentage(90); // Porcentaje de la pagina que ocupa
            tabla.setHorizontalAlignment(Element.ALIGN_CENTER);//Alineacion horizontal
            tabla.setWidths(new float[]{40, 40, 40, 40, 40, 40, 40, 40});

            cell = new PdfPCell(new Paragraph("NUMERO", textoFontBold));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.disableBorderSide(Rectangle.TOP | Rectangle.RIGHT | Rectangle.LEFT);
            tabla.addCell(cell);

            cell = new PdfPCell(new Paragraph("TIPO", textoFontBold));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.disableBorderSide(Rectangle.TOP | Rectangle.RIGHT | Rectangle.LEFT);
            tabla.addCell(cell);

            cell = new PdfPCell(new Paragraph("F.INGRESO", textoFontBold));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.disableBorderSide(Rectangle.TOP | Rectangle.RIGHT | Rectangle.LEFT);
            tabla.addCell(cell);

            cell = new PdfPCell(new Paragraph("F. SALIDA", textoFontBold));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.disableBorderSide(Rectangle.TOP | Rectangle.RIGHT | Rectangle.LEFT);
            tabla.addCell(cell);
            cell = new PdfPCell(new Paragraph("VALOR", textoFontBold));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.disableBorderSide(Rectangle.TOP | Rectangle.RIGHT | Rectangle.LEFT);
            tabla.addCell(cell);
            cell = new PdfPCell(new Paragraph("PLACA", textoFontBold));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.disableBorderSide(Rectangle.TOP | Rectangle.RIGHT | Rectangle.LEFT);
            tabla.addCell(cell);

            cell = new PdfPCell(new Paragraph("CEDULA", textoFontBold));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.disableBorderSide(Rectangle.TOP | Rectangle.RIGHT | Rectangle.LEFT);
            tabla.addCell(cell);

            cell = new PdfPCell(new Paragraph("U. COBRO", textoFontBold));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.disableBorderSide(Rectangle.TOP | Rectangle.RIGHT | Rectangle.LEFT);
            tabla.addCell(cell);
            chapter.add(tabla);
        } catch (DocumentException ex) {
            Logger.getLogger(PDFReportes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
