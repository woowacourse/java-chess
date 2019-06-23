package chess.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class TableCreator {
	private static final Logger LOGGER = LoggerFactory.getLogger(TableCreator.class);
	private static final String SEMICOLON_DELIMITER = ";";

	private static boolean flag = false;
	private DbConnector dbConnector;

	public TableCreator(final DbConnector dbConnector) {
		this.dbConnector = dbConnector;
	}

	public void create() throws Exception {
		if (flag) {
			return;
		}
		flag = true;

		File file = new File("src/main/resources/schema.sql");
		FileInputStream fis = new FileInputStream(file);

		String[] querys = getFileContent(fis).split(SEMICOLON_DELIMITER);
		for (final String query : querys) {
			Connection connection = dbConnector.getConnection();
			PreparedStatement ps = connection.prepareStatement(query);
			ps.executeUpdate();
			ps.close();
			LOGGER.info(query);
		}
	}

	public String getFileContent(FileInputStream fis) throws IOException {
		StringBuilder sb = new StringBuilder();
		Reader r = new InputStreamReader(fis, StandardCharsets.UTF_8);
		char[] buf = new char[1024];
		int amt = r.read(buf);
		while (amt > 0) {
			sb.append(buf, 0, amt);
			amt = r.read(buf);
		}
		return sb.toString();
	}
}
