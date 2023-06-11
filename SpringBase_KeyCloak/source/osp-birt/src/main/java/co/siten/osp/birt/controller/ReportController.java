package co.siten.osp.birt.controller;

import java.net.URLEncoder;

import co.siten.osp.birt.bean.ReportRequest;
import co.siten.osp.birt.service.BIRTReport;
import co.siten.osp.birt.service.ReportRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("ReportController")
@RequestMapping("/reports")
public class ReportController {

	private Logger logger = LoggerFactory.getLogger(ReportController.class);

	@Autowired
	@Qualifier("birt")
	ReportRunner reportRunner;

	@RequestMapping(value = "/birt", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getBIRTReport(@RequestParam(value = "reportName", required = true) String reportName,
			@RequestParam(value = "url", required = true) String url) {
		byte[] reportBytes;
		ResponseEntity<byte[]> responseEntity;
		ReportRequest reportRequest = new ReportRequest(reportName,
				"?url=" + URLEncoder.encode(url));
		try {
			reportBytes = new BIRTReport(reportRequest.getReportName(), reportRequest.getReportParameters(),
					reportRunner).runReport().getReportContent().toByteArray();

			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(MediaType.parseMediaType("text/html"));
			String fileName = reportRequest.getReportName() + ".html";
			httpHeaders.setContentDispositionFormData(fileName, fileName);
			httpHeaders.setCacheControl("must-revalidate, post-check=0, pre-check=0");
			
			responseEntity = new ResponseEntity<byte[]>(reportBytes, httpHeaders, HttpStatus.OK);
		} catch (Exception e) {
			responseEntity = new ResponseEntity<byte[]>(HttpStatus.NOT_IMPLEMENTED);
			return responseEntity;
		}
		return responseEntity;
	}
	
	
	
	@RequestMapping(value = "/birtPDF", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getBIRTReportPDF(@RequestParam(value = "reportName", required = true) String reportName,
			@RequestParam(value = "url", required = true) String url) {
		byte[] reportBytes;
		ResponseEntity<byte[]> responseEntity;
		ReportRequest reportRequest = new ReportRequest(reportName,
				"?url=" + URLEncoder.encode(url));
		try {
			reportBytes = new BIRTReport(reportRequest.getReportName(), reportRequest.getReportParameters(),
					reportRunner).runReport().getReportContent().toByteArray();			
			 HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.parseMediaType("application/pdf"));
            String fileName = reportRequest.getReportName() + ".pdf";
            httpHeaders.setContentDispositionFormData(fileName, fileName);
            httpHeaders.setCacheControl("must-revalidate, post-check=0, pre-check=0");
            responseEntity = new ResponseEntity<byte[]>(reportBytes, httpHeaders, HttpStatus.OK);

		} catch (Exception e) {
			responseEntity = new ResponseEntity<byte[]>(HttpStatus.NOT_IMPLEMENTED);
			return responseEntity;
		}
		return responseEntity;
	}
}