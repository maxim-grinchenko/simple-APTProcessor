import com.privatefields.processor.PrivateFieldsOnlyProcessor;

public class Dede {
	
	
	public static void main(String[] args) {
		
		Class<?> dClass = PrivateFieldsOnlyProcessor.class;
		
		System.out.println(dClass.getName());
	}

}
