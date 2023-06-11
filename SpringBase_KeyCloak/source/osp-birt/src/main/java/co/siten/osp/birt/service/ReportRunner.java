package co.siten.osp.birt.service;
import co.siten.osp.birt.bean.Report;

import java.io.ByteArrayOutputStream;


public interface ReportRunner {

    ByteArrayOutputStream runReport(Report report);
}