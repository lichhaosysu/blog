package mybatis.mapper;

import mybatis.model.TestBean;

public interface TestMapper {
	TestBean selectTest(TestBean bean);
}
