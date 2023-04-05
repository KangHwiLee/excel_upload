package model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;

import mybatis.MybatisFactory;

public class SocketDAO {
	private SqlSession mybatis = MybatisFactory.getSqlSession(); 
	
	public String test() {
		return mybatis.selectOne("sqlmap.test.test");
	}
	
	public int create_table(HashMap<String, Object> map) {
		return mybatis.update("sqlmap.test.create_table", map);
	}
	
	public int last_table() {
		return mybatis.selectOne("sqlmap.test.last_table");
	}
	
	public int upload_excel(HashMap<String, Object> map) {
		return mybatis.insert("sqlmap.test.upload_excel", map);
	}
	
}
