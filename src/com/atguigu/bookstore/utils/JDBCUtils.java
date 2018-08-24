package com.atguigu.bookstore.utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JDBCUtils {

	private static DataSource dataSource = new ComboPooledDataSource(
			"webDataSource");

	// private static Map<Thread, Connection> map = new
	// ConcurrentHashMap<Thread, Connection>();

	// ThreadLocal对象在内部维护着一个map，这个map中的key就是当前线程，而值我们可以指定为一个任意类型。
	// ThreadLocal中用来在当前线程中共享对象
	// 一个ThreadLocal在当前线程中只能存一个对象。

	private static ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();

	/**
	 * 
	 * @return
	 */
	public static Connection getConnection() {
		Connection conn = threadLocal.get();
		if (conn == null) {
			try {
				conn = dataSource.getConnection();
				threadLocal.set(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return conn;
	}

	/**
	 * 
	 * @param conn
	 */
	/*
	 * public static void realseConnection(Connection conn) { if (conn != null)
	 * { try { conn.close(); } catch (SQLException e) { e.printStackTrace(); } }
	 * }
	 */

	public static void realseConnection() {
		Connection conn = threadLocal.get();
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		threadLocal.remove();
		conn = null;
	}
}
