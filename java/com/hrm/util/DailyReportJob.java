package com.hrm.util;

import java.io.ByteArrayOutputStream;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.hrm.Models.UserSessionInfo;
import com.hrm.controller.PdfGeneratorController;

public class DailyReportJob implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		try {
			// Fetch all user session info from the database
			PdfGeneratorController controller = new PdfGeneratorController();
			List<UserSessionInfo> sessionInfoList = controller.getAllSessionInfo();

			if (!sessionInfoList.isEmpty()) {
				// Generate a single PDF containing all user reports
				ByteArrayOutputStream baos = controller.generateCombinedPdf(sessionInfoList);

				// Send the PDF as an email attachment
				controller.sendEmailWithAttachment("daily_combined_report", baos.toByteArray());
			}
		} catch (SQLException | ClassNotFoundException | ServletException e) {
			throw new JobExecutionException("Failed to generate and send the daily report", e);
		}
	}
}
