package com.nearshoretechnology.focalpoint.api.admin.config;

import java.time.LocalTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class FPInterceptor implements AsyncHandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView model)
			throws Exception {
//		System.out.println("Called after handler method request completion," + " before rendering the view");
		if (model != null) {
			LocalTime time = LocalTime.now();
			int hrs = time.getHour();
			if (hrs >= 0 && hrs <= 12) {
				model.addObject("greeting", "Good morning!");
			} else if (hrs > 12 && hrs <= 17) {
				model.addObject("greeting", "Good afternoon!");
			} else {
				model.addObject("greeting", "Good evening!");
			}
		}

	}

	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
//		System.out.println("Called after handler method request completion," + " before rendering the view");

		// TODO Auto-generated method stub
		
	}

}
