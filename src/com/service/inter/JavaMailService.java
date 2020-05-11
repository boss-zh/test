package com.service.inter;

public interface JavaMailService {

	public boolean sendTextMail(String from,String to,String subject,String content);
}
