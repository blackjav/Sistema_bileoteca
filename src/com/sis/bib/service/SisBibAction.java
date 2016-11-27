package com.sis.bib.service;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.ParameterNameAware;

public class SisBibAction extends ActionSupport implements SessionAware, ParameterNameAware{
    /**
	 * 
	 */
	private static final long serialVersionUID = -2337113804906674248L;

	@Override
    public String execute(){
        return null;
    } 

    protected void sendJSONObjectToResponse(Object objToSend){
        Gson gson = new Gson();
        String jsonResult = gson.toJson(objToSend);	      
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("application/json");
        try {
            response.getWriter().write(jsonResult );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void sendXMLObjectToResponse(Object xmlFile){
        byte[] objXml = (byte[])xmlFile;	      
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("application/xml");
        try {
            response.setContentType("text/xml");
            response.setCharacterEncoding("UTF-8");
            response.setContentLength(objXml.length);
            response.setHeader("Cache-Control", "no-cache");
            response.getOutputStream().write(objXml);
            response.getOutputStream().flush();
            response.getOutputStream().close();
        } catch (IOException e) {
            System.out.println("Imposible generar la respuesta. Error: " + e.getMessage());
            e.printStackTrace();			
        }
    }

    /* (non-Javadoc)
     * Se implementa este método para prevenir que pidan un parámetro de nombre session
     */
    @Override
    public boolean acceptableParameterName(String parameterName) {	     
        boolean allowedParameterName = true ;	     
        if ( parameterName.contains("session")  || parameterName.contains("request") ) {	     
            allowedParameterName = false ;	         
        } 	     
        return allowedParameterName;
    }
    
	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		
	}
}
