package chess.dao;

import chess.domain.ChessBoard;
import chess.domain.piece.Piece;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;
import chess.dto.PieceNameConverter;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChessBoardDao extends DaoTemplate {
	public void addPieces(ChessBoard chessBoard, int gameId) {
		String query = "INSERT INTO piece VALUES (?, ?, ?, ?)";
		try (PreparedStatement pstmt = getConnection().prepareStatement(query)) {
			setBatch(gameId, pstmt, chessBoard.getPieces());
			pstmt.executeBatch();
		} catch (SQLException e) {
			throw new DataAccessException("DB 데이터 삽입 중 오류가 발생했습니다.", e);
		}
	}

	private void setBatch(int gameId, PreparedStatement pstmt, List<Piece> pieces) throws SQLException {
		for (Piece piece : pieces) {
			Position position = piece.getPosition();
			Column column = position.getCol();
			Row row = position.getRow();
			pstmt.setString(1, PieceNameConverter.toName(piece));
			pstmt.setInt(2, column.getValue());
			pstmt.setInt(3, row.getSymbol());
			pstmt.setInt(4, gameId);
			pstmt.addBatch();
		}
	}

	public void deleteByGameId(int gameId) {
		String query = "DELETE FROM piece WHERE id = (?)";
		try (PreparedStatement pstmt = getConnection().prepareStatement(query)) {
			pstmt.setInt(1, gameId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException("DB 데이터 제거 중 오류가 발생했습니다.", e);
		}
	}

	public ChessBoard findByGameId(int gameId) {
		List<Piece> pieces = new ArrayList<>();
		String query = "SELECT * FROM piece WHERE id = (?)";
		try (PreparedStatement pstmt = getConnection().prepareStatement(query)) {
			pstmt.setInt(1, gameId);
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					pieces.add(PieceNameConverter.toPiece(rs.getString("name"), rs.getInt("col"), rs.getInt("row")));
				}
				if (pieces.isEmpty()) {
					throw new DataAccessException("id에 해당하는 정보가 없습니다.");
				}
				return new ChessBoard(pieces);
			}
		} catch (SQLException e) {
			throw new DataAccessException("DB 검색 중 오류가 발생했습니다.", e);
		}
	}
}
