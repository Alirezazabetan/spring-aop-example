package jp.blogspot.horiga3.example.aop;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
	
	private static Logger logger = LoggerFactory.getLogger(HelloController.class);
	
	@Autowired
	private HelloService service;
	
	@Procedure
	@RequestMapping(value="/hello/{type}", method={RequestMethod.GET})
	public @ResponseBody
	Output<?> hello(
			@PathVariable("type") String type) throws Exception {
		logger.info("- start hello");
		try {
		if ("exception".equalsIgnoreCase(type))
			return new Output<Hello>(UUID.randomUUID().toString(), service.runOnException());
		else if ("failure".equalsIgnoreCase(type)) 
			return new Output<Hello>(UUID.randomUUID().toString(), service.runOnFailure());
		
		return new Output<Hello>(UUID.randomUUID().toString(), service.runOnSuccess());
		} finally {
			logger.info("- end hello");
		}
	}
}
