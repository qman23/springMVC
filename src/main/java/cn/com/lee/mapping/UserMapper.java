package cn.com.lee.mapping;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import cn.com.lee.bean.User;

public interface UserMapper {
		@Insert("insert into test.user (id,name,password) values (#{id},#{name},#{password})")
		public void insertUser(User user);
		
		@Select("select * from test.user")
		public User getUser(@Param("id") String id);
		
		
}
