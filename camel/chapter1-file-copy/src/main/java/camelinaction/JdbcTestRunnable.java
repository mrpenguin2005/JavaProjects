package camelinaction;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class JdbcTestRunnable implements Runnable {
	private String jdbcUrl;
	private String jdbcUser;
	private String jdbcPassowrd;
	private Integer codProcessoControle;

	public JdbcTestRunnable(String jdbcUrl, String jdbcUser, String jdbcPassowrd, Integer codProcessoControle) {
		this.jdbcUrl = jdbcUrl;
		this.jdbcUser = jdbcUser;
		this.jdbcPassowrd = jdbcPassowrd;
		this.codProcessoControle = codProcessoControle;
	}	

	public void run() {
		System.err.println("Starting USP_RESOLVE_PENDENCIA");
		
		try {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch(ClassNotFoundException e) {
			System.err.println("Unable to load JDBC Driver : "+ e.getMessage());
		}

		PreparedStatement st = null;
		CallableStatement stmt = null;
		try {
			Connection connection = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassowrd);
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			connection.setAutoCommit(false);
			
			st = connection.prepareStatement("update tb_processo_controle set DT_INICIO_PROCESSO = ? where ID_PROCESSO_CONTROLE = ?");
			st.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
			st.setInt(2, codProcessoControle);
			st.execute();
			connection.commit();
			
			stmt = connection.prepareCall("{call USP_RESOLVE_PENDENCIA(?)}");
			stmt.setInt(1, 1936);
			stmt.execute();
			
			st = connection.prepareStatement("update tb_processo_controle set tp_status = ? , DT_TERMINO_PROCESSO = ? where ID_PROCESSO_CONTROLE = ?");
			st.setString(1, "F");
			st.setTimestamp(2,new Timestamp(System.currentTimeMillis()));
			st.setInt(3, codProcessoControle);
			st.execute();
			connection.commit();
			
			stmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.err.println("End of execution: USP_RESOLVE_PENDENCIA");
	}

}
