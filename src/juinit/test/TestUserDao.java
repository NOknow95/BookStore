package juinit.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.atguigu.bookstore.bean.User;
import com.atguigu.bookstore.dao.UserDao;
import com.atguigu.bookstore.dao.impl.UserDaoImpl;

public class TestUserDao {

	UserDao userDao = new UserDaoImpl();

	@Test
	public void testGetUserByUsernameAndPassword() {
		User user = new User(null,"1", "2",  null);
		User user2 = userDao.getUserByUsernameAndPassword(user);
		System.out.println(user2);
	}

	@Test
	public void testSaveUser() {
		User user = new User(null, "1", "2", "ad@3162.com");
		int count = userDao.saveUser(user);
		System.out.println(count);
	}
}
