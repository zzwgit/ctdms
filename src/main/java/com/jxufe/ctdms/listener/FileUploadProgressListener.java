package com.jxufe.ctdms.listener;

import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.ProgressListener;

import com.jxufe.ctdms.utils.Progress;

 
 

public class FileUploadProgressListener implements ProgressListener {
	private HttpSession session;

	public FileUploadProgressListener() {  }  
	
    public FileUploadProgressListener(HttpSession session) {
        this.session=session;  
        Progress status = new Progress();
        session.setAttribute("upload_ps", status);  
    }  
	@Override
	public void update(long pBytesRead, long pContentLength, int pItems) {
		Progress status = (Progress) session.getAttribute("upload_ps");
		status.setBytesRead(pBytesRead);
		status.setContentLength(pContentLength);
		status.setItems(pItems); 
		session.setAttribute("upload_ps", status);
	}
	
	

}
