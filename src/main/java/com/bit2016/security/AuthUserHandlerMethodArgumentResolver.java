package com.bit2016.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.bit2016.mysite.vo.UserVo;

public class AuthUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mav, NativeWebRequest webRequest,
			WebDataBinderFactory binderfactory) throws Exception {

		if (supportsParameter(parameter) == false) {
			return WebArgumentResolver.UNRESOLVED;
		}
		// @AuthUser 붙어있고
		// 파라미터 타입이 UserVo
		HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);

		HttpSession session = request.getSession();
		if (session == null) {
			return WebArgumentResolver.UNRESOLVED;
		}

		return session.getAttribute("authUser");
	}

	@Override
	public boolean supportsParameter(MethodParameter parameter) {

		AuthUser authUser = parameter.getParameterAnnotation(AuthUser.class);
		if (authUser == null) {
			// @AuthUser 이 안붙어있음

			return false;
		}
		// 파라미터 타입이 UserVo가 아님
		if (parameter.getParameterType().equals(UserVo.class) == true) {
			return false;
		}

		return true;
	}

}
