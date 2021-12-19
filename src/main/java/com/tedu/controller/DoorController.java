package com.tedu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tedu.dao.DoorMapper;
import com.tedu.pojo.Door;

/*
 * @Controller: 标识当前类属于controller层
 * 	标识当前类的实例的创建由spring容器负责
 */
@Controller
public class DoorController {
	
	/* @Autowired: 自动装配, 根据当前变量的类型(DoorMapper) 到spring容器中, 
	 * 		获取该接口对应的子类实例, 将接口的子类实例赋值给doorMapper */
	@Autowired
	private DoorMapper doorMapper;
	
	
	/** 1、查询所有的门店信息 */
	@RequestMapping("/doorList")
	public String doorList( Model model ) {
		//调用DoorMapper的findAll方法, 查询所有的门店信息
		List<Door> list = doorMapper.findAll();
		//将所有门店信息存入Model中, 带到JSP显示
		model.addAttribute( "list", list ); 
		//跳转到门店列表页面
		return "door_list";
	}
	
	/** 2、根据id删除门店信息 */
	@RequestMapping("/doorDelete")
	public String doorDelete( Integer id ) {
		//调用DoorMapper中的根据id删除门店信息的方法
		doorMapper.deleteById( id );
		
		//更新门店信息后, 跳转到查询所有门店的方法, 显示最新门店信息
		return "forward:/doorList";
	}
	
	/** 3、新增门店信息 */
	@RequestMapping("/doorAdd")
	public String doorAdd( Door door ) {
		//调用DoorMapper的新增门店的方法, 新增一条门店信息
		doorMapper.add( door );
		
		//更新门店信息后, 跳转到查询所有门店的方法, 显示最新门店信息
		return "forward:/doorList";
	}
	
	/** 4、根据id查询门店信息 */
	@RequestMapping("/doorInfo")
	public String doorInfo( Integer id, Model model ) {
		//根据id查询门店信息, 将门店信息存入Model中
		Door door = doorMapper.findById( id );
		model.addAttribute( "door",  door );
		//跳转到门店修改页面, 回显原有的门店信息
		return "door_update";
	}
	
	/** 5、根据id修改门店信息  */
	@RequestMapping("/doorUpdate")
	public String doorUpdate( Door door ) {
		//调用DoorMapper的修改门店的方法, 根据id修改门店信息
		doorMapper.updateById( door );
		
		//更新门店信息后, 跳转到查询所有门店的方法, 显示最新门店信息
		return "forward:/doorList";
	}

	
	
	
	
	
	/**
	 * 通用的页面跳转方法
	 * 例如: 访问路径为: "/index", {}括号中的jspName的值就是 "index", 在将这个jspName的值
	 * 		传递给方法上的形参--jspName, 方法上的形参再作为方法的返回值返回, 因此最后return的
	 * 		就是 "index"
	 * 通过jsp的名字作为访问路径, 就可以跳转到 /WEB-INF/pages/下的指定名称的jsp页面!!
	 */
	@RequestMapping("/{jspName}")
	public String toJsp(@PathVariable String jspName ) {
		return jspName;
	}

	
	
	//======================================
	/* 测试springmvc框架的开发环境
	 */
	@RequestMapping("/testmvc")
	public String testmvc() {
		System.out.println( "springmvc的环境已OK~~~" );
		return "test";
	}
	
	/* 测试ssm的开发环境
	 */
	@RequestMapping("/testssm")
	public String testssm() {
		System.out.println("DoorController.testssm()...");
		List<Door> list  = doorMapper.findAll();
		for (Door door : list) {
			System.out.println( door );
		}
		return "test";
	}
}






