package kr.co.sist;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import kr.co.sist.interceptor.AdminInterceptor;
import kr.co.sist.interceptor.UserInterceptor;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
	
	@Value("${myapp.user.addPath}")
	List<String> addPathUserList;
	
	@Value("${myapp.user.excludePath}")
	List<String> excludePathUserList;
	

	@Value("${myapp.admin.addPath}")
	List<String> addPathAdminList;
	
	@Value("${myapp.admin.excludePath}")
	List<String> excludePathAdminList;
	
	// 2. 제작한 Interceptor 의존성 주입
	@Autowired
	private UserInterceptor ui;
	
	@Autowired
	private AdminInterceptor ai;

	/**
	 * 3. 제작된 interceptor 가 동작할 URI를 설정하여 등록
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//UserInterceptor 등록.
		registry.addInterceptor(ui).addPathPatterns(addPathUserList)
		.excludePathPatterns(excludePathUserList);
		
		//AdminInterceptor 등록.
		registry.addInterceptor(ai).addPathPatterns(addPathAdminList)
		.excludePathPatterns(excludePathAdminList);
	} //addInterceptors
	
} //class
