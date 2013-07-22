package ch.zhaw.lab4;

public class ApiException extends RuntimeException {
		/**
	 * 
	 */
	private static final long serialVersionUID = -3101101411881625355L;

		public ApiException(String msg) {
			super(msg);
		}
		
		public ApiException(String msg, Throwable thr) {
			super(msg, thr);
		}
}
