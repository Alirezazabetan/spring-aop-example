package jp.blogspot.horiga3.example.aop;

public interface HelloService {
	public abstract Hello runOnSuccess();
	public abstract Hello runOnException() throws Exception;
	public abstract Hello runOnFailure();
}