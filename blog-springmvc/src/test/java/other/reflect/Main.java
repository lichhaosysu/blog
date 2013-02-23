package other.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {
	public static void main(String[] args) throws SecurityException,
			NoSuchMethodException, IllegalArgumentException,
			IllegalAccessException, InvocationTargetException, NoSuchFieldException {
		Method m = A.class.getDeclaredMethod("getName", new Class[] {});
		Field f = A.class.getDeclaredField("name");
		System.out.println("公共方法的Accessible："+m.isAccessible());
		System.out.println("私有属性的Accessible："+f.isAccessible());		
		// getName是public的,猜猜输出是true还是false

		A a = new A();
		a.setName("Mr Lee");
		System.out.println("设置前的Name属性："+a.getName());
		
		f.setAccessible(true);
		f.set(a, "Mr Chen");
		f.setAccessible(false);
		System.out.println("设置后的Name属性："+a.getName());
		
		long start = System.currentTimeMillis();
		for (int i = 0; i < 10000000; i++) {
			m.invoke(a, new Object[] {});
		}
		System.out.println("Simple              :"
				+ (System.currentTimeMillis() - start));

		m.setAccessible(true);// 注意此处不同
		long start1 = System.currentTimeMillis();
		for (int i = 0; i < 10000000; i++) {
			m.invoke(a, new Object[] {});
		}
		System.out.println("setAccessible(true) :"
				+ (System.currentTimeMillis() - start1));
	}
}

class A {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}