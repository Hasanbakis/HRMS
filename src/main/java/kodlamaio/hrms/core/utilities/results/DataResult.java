package kodlamaio.hrms.core.utilities.results;

public class DataResult<T> extends Result{
	private T data;

	public DataResult(T data,boolean success, String message) {
		super(success, message);
		// super Base sınıfın constructorlarını çalıştırmaya yarıyor
		this.data = data;
		
	}
	
	public DataResult(T data,boolean success) {
		super(success);
		// super Base sınıfın constructorlarını çalıştırmaya yarıyor
		this.data = data;
		
	}
	
	public T getData() {
		return this.data;
	}

}