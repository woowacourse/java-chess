package web.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import web.dto.ChessGameDto;

public class BoardDao {
	private static final BoardDao boardDAO = new BoardDao();
	private static final String TABLE_NAME = "board";

	private DBConnector Connector = new DBConnector();

	public static BoardDao getInstance() {
		return boardDAO;
	}

	public void addBoard(ChessGameDto chessGameDto) throws SQLException {
		String query = "INSERT INTO " + TABLE_NAME + " (board,turn,title) VALUES(?,?,?)";
		try (
			PreparedStatement pstmt = Connector.getConnection().prepareStatement(query)
		) {
			pstmt.setString(1, chessGameDto.getSymbol());
			pstmt.setString(2, chessGameDto.getTurn());
			pstmt.setString(3, chessGameDto.getTitle());
			pstmt.executeUpdate();
		}
	}

	public ChessGameDto findByTitle(String title) throws SQLException {
		String query = "SELECT * FROM " + TABLE_NAME + " WHERE title = ?";
		ResultSet rs;
		try (
			PreparedStatement pstmt = Connector.getConnection().prepareStatement(query);
		) {
			pstmt.setString(1, title);
			rs = pstmt.executeQuery();
			if (!rs.next())
				return null;
			return new ChessGameDto(
				rs.getString("title"),
				rs.getString("board"),
				rs.getString("turn"));
		}
	}
}
