package juinit.test;

import static org.junit.Assert.*;

import java.sql.Connection;

import org.junit.Test;

import com.atguigu.bookstore.utils.JDBCUtils;

public class TestJDBCUtils {

	@Test
	public void testJDBCUtils() {
		Connection conn = JDBCUtils.getConnection();
		System.out.println(conn);
		JDBCUtils.realseConnection();
	}

}
