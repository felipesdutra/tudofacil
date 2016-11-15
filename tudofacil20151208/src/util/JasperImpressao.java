package util;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.util.Locale;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

public class JasperImpressao {
	
	public void imprimirRelatorioPdf(Map<String, Object> param, String report){
		
		
		try{
			FacesContext ctx = FacesContext.getCurrentInstance();
			
			HttpServletResponse response = (HttpServletResponse) ctx.getExternalContext().getResponse();
			ServletOutputStream servletOutputStream = response.getOutputStream();
			ctx.responseComplete();
			response.setContentType("application/pdf");
			
	        InputStream reportStream = this.getClass().getResourceAsStream(report);
	        
	        URL caminho = this.getClass().getResource("");
	        
	        param.put("caminho", caminho);
	        param.put("REPORT_LOCALE", new Locale("pt", "BR"));

			Connection con = Conexao.getConnection();
	        JasperRunManager.runReportToPdfStream(reportStream, servletOutputStream, param, con);
			con.close();
			
			
			
		}catch (Exception e) {
	        	System.out.println("Erro no imprimir relatório Jasper");
	        	System.out.println(e.getMessage());
	        	e.printStackTrace();
	        	
	    }

		
	}
	
	public void imprimirXLSX(Map<String, Object> param, String report){
		try{

			FacesContext ctx = FacesContext.getCurrentInstance();
			HttpServletResponse response = (HttpServletResponse) ctx.getExternalContext().getResponse();
			ServletOutputStream servletOutputStream = response.getOutputStream();
			ctx.responseComplete();
			
			ByteArrayOutputStream xlsReport = new ByteArrayOutputStream();   
			SimpleOutputStreamExporterOutput teste = new SimpleOutputStreamExporterOutput(xlsReport);
			
	        URL caminho = this.getClass().getResource("");
	        param.put("caminho", caminho);

			Connection con = Conexao.getConnection();
			InputStream reportStream = this.getClass().getResourceAsStream(report);
			JasperPrint jasperPrint = JasperFillManager.fillReport(reportStream, param,con);
			con.close();
			
			JRXlsxExporter exporter = new JRXlsxExporter();
			exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			exporter.setExporterOutput(teste);
			SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
			configuration.setOnePagePerSheet(false);
			configuration.setDetectCellType(true);
			configuration.setCollapseRowSpan(true);
			exporter.setConfiguration(configuration);
			exporter.exportReport();

			byte[] bytes = xlsReport.toByteArray();  
			response.setContentType("application/vnd.ms-excel");
           response.setHeader("Content-Disposition","inline; filename=\"pagtoAnual.xlsx\"");            

			response.setContentLength(bytes.length);   
			xlsReport.close();   
			servletOutputStream.write(bytes, 0, bytes.length);   
			servletOutputStream.flush();   
			servletOutputStream.close();  
			
			}catch (Exception e) {
	        	System.out.println("Erro no imprimir");
	        	System.out.println(e.getMessage());
	        	e.printStackTrace();
	        	
			}
			
	}
	
}
