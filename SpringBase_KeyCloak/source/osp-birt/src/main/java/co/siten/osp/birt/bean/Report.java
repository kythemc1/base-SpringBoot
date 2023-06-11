package co.siten.osp.birt.bean;
import java.io.ByteArrayOutputStream;

import co.siten.osp.birt.service.ReportRunner;

/**
 * A Report object has a byte representation of the report output that can be
 * used to write to any output stream. This class is designed around the concept
 * of using ByteArrayOutputStreams to write PDFs to an output stream.
 *
 *
 */
public abstract class Report {

    protected String name;
    protected String parameters;
    protected ByteArrayOutputStream reportContent;
    protected ReportRunner reportRunner;
    public Report(String name, String parameters, ReportRunner reportRunner) {
        this.name = name;
        this.parameters = parameters;
        this.reportRunner = reportRunner;
    }

    /**
     * This is the processing method for a Report. Once the report is ran it
     * populates an internal field with a ByteArrayOutputStream of the
     * report content generated during the run process.
     * @return Returns itself with the report content output stream created.
     */
    public abstract Report runReport();

    public ByteArrayOutputStream getReportContent() {
        return this.reportContent;
    }

    public String getName() {
        return name;
    }

    public String getParameters() {
        return parameters;
    }

    
}