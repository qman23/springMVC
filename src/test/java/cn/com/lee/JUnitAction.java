package cn.com.lee;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.BeforeClass;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockServletContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;
import org.springframework.web.servlet.mvc.annotation.DefaultAnnotationHandlerMapping;

public class JUnitAction {
	private static HandlerMapping handlerMapping;
	
	private static HandlerAdapter handeAdapter;
	
	@BeforeClass
	/**
	 * 读取 spring MVC配置，初始化变量
	 */
	public static void setUp(){
		if(handlerMapping==null){
			String[] configs = {"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"}; 
			XmlWebApplicationContext context = new XmlWebApplicationContext();
			context.setConfigLocations(configs);
			MockServletContext msc = new MockServletContext();
			context.setServletContext(msc);
			context.refresh();
			msc.setAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE, context);
			handlerMapping = context.getBean(DefaultAnnotationHandlerMapping.class);
			handeAdapter = (HandlerAdapter)context.getBean(context.getBeanNamesForType(AnnotationMethodHandlerAdapter.class)[0]);
		}
	}
	
	/**
	 * 执行request对象请求的action
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView excuteAction(HttpServletRequest request,HttpServletResponse response) throws Exception{
		HandlerExecutionChain chain = handlerMapping.getHandler(request);
		final ModelAndView model = handeAdapter.handle(request, response, chain.getHandler());
		return model;
	}
}
