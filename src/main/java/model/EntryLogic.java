package model;

import java.io.UnsupportedEncodingException;

import jakarta.servlet.http.HttpServletRequest;

public class EntryLogic {
	public boolean execute( HttpServletRequest request ) {
		try {
			request.setCharacterEncoding( "UTF-8" );
		} catch (UnsupportedEncodingException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		//
		String userId = request.getParameter( "userId" );
		if( userId == null || userId.length() <= 0 ) {
			return false;
		}
		String pass = request.getParameter( "pass" );
		if( pass == null || pass.length() <= 0 ) {
			return false;
		}
		String mail = request.getParameter( "mail" );
		if( mail == null || mail.length() <= 0 ) {
			return false;
		}

		return true;
	}
}
