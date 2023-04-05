package mybatis;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisFactory {
	private static SqlSessionFactory factory;
	static {
        try {
            Reader reader = Resources.getResourceAsReader("data/socket_config.xml");
            if (factory == null) {
            	factory = new SqlSessionFactoryBuilder().build(reader);
            }
        } catch (Exception e) {
        	System.out.println(e.getMessage());
        }
	}
	public static SqlSession getSqlSession() {
        return factory.openSession();
   	}
}
