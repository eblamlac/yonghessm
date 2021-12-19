package com.tedu.test;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.tedu.dao.DoorMapper;
import com.tedu.pojo.Door;

/** 
 * mybatis的mapper接口开发
 */
public class TestMybatis04 {
	private static SqlSession session = null;
	static {
		//对SqlSession进行初始化
		try {
			//1.读取mybatis的核心配置文件(mybatis-config.xml)
			InputStream in = Resources.getResourceAsStream( "mybatis/mybatis-config2.xml" );
			//2.基于配置信息获取一个SqlSessionFactory工厂对象
			SqlSessionFactory factory = new SqlSessionFactoryBuilder().build( in );
			session = factory.openSession( true ); //true表示自动提交事务
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFindAll() {
		//获取DoorMapper接口的子类实例
		DoorMapper mapper = session.getMapper( DoorMapper.class );
		//查询所有的门店信息
		List<Door> list = mapper.findAll();
		for (Door door : list) {
			System.out.println( door );
		}
	}
	
}














