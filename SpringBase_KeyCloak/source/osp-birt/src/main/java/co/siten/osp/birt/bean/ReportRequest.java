package co.siten.osp.birt.bean;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ReportRequest {
    private String reportName;
    private String reportParameters;
    public ReportRequest(@JsonProperty("reportName") String reportName,
                         @JsonProperty("reportParameters") String reportParameters) {
        this.reportName = reportName;
        this.reportParameters = reportParameters;
    }

    public String getReportName() {
        return reportName;
    }

    public String getReportParameters() {
        return reportParameters;
    }

    public void setReportParameters(String reportParameters) {
        this.reportParameters = reportParameters;
    }


}