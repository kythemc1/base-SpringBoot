package co.siten.osp.birt.service;


import co.siten.osp.birt.bean.Report;

public class BIRTReport extends Report {

	public BIRTReport(String name, String reportParameters, ReportRunner reportRunner) {
		super(name, reportParameters, reportRunner);
	}

	@Override
	public Report runReport() {
		this.reportContent = reportRunner.runReport(this);
		return this;
	}
}