package com.lichhao.blog.action;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

public class BaseAction extends ActionSupport implements ServletRequestAware,
		ServletResponseAware, SessionAware, ServletContextAware, Preparable {

	protected HttpServletRequest request;

	protected HttpServletResponse response;

	protected Map<String, Object> session;

	protected ServletContext servletContext;

	protected ActionContext actionContext;

	public BaseAction() {
		this.actionContext = ActionContext.getContext();
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		String contextPath = request.getContextPath();
		request.setAttribute("contextPath", contextPath);
		this.request = request;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	@Override
	public void setServletContext(ServletContext context) {
		this.servletContext = context;
	}

	@Override
	public void prepare() throws Exception {
	}
}
