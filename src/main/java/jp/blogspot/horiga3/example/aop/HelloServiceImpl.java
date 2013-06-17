package jp.blogspot.horiga3.example.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl implements HelloService {
	
	private static Logger logger = LoggerFactory.getLogger(HelloServiceImpl.class);  
	
	/* (non-Javadoc)
	 * @see jp.blogspot.horiga3.example.aop.HelloService#runOnSuccess()
	 */
	@Override
	public Hello runOnSuccess() {
		logger.info("success"); // success
		return new Hello("hello");
	}
	
	/* (non-Javadoc)
	 * @see jp.blogspot.horiga3.example.aop.HelloService#runOnException()
	 */
	@Override
	public Hello runOnException() throws Exception {
		logger.info("error"); // user error.
		throw new ProcedureException(50001, "runOnException");
	}
	
	/* (non-Javadoc)
	 * @see jp.blogspot.horiga3.example.aop.HelloService#runOnFailure()
	 */
	@Override
	public Hello runOnFailure() {
		logger.info("failure"); // user error.
		throw new IllegalArgumentException("Invalid parameters"); // occur runtime failed.
	}
}
