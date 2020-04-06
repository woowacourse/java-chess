package chess.dao;

import chess.domain.dto.PieceDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChessBoardDao extends DaoTemplate {
	public void add(PieceDto pieceDto, int gameId) throws SQLException {
		String query = "INSERT INTO piece VALUES (?, ?, ?, ?)";
		try (PreparedStatement pstmt = getConnection().prepareStatement(query)) {
			pstmt.setString(1, pieceDto.getName());
			pstmt.setInt(2, pieceDto.getCol());
			pstmt.setInt(3, pieceDto.getRow());
			pstmt.setInt(4, gameId);
			pstmt.executeUpdate();
		}
	}

	public void addPieces(List<PieceDto> pieceDtos, int gameId) throws SQLException {
		for (PieceDto pieceDto : pieceDtos) {
			add(pieceDto, gameId);
		}
	}

	public void deleteByGameId(int gameId) throws SQLException {
		String query = "DELETE FROM piece WHERE id = (?)";
		try (PreparedStatement pstmt = getConnection().prepareStatement(query)) {
			pstmt.setInt(1, gameId);
			pstmt.executeUpdate();
		}
	}

	public List<PieceDto> findByGameId(int gameId) throws SQLException {
		List<PieceDto> piecesDtos = new ArrayList<>();
		String query = "SELECT * FROM piece WHERE id = (?)";
		try (PreparedStatement pstmt = getConnection().prepareStatement(query)) {
			pstmt.setInt(1, gameId);
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					piecesDtos.add(new PieceDto(rs.getString("name"), rs.getInt("col"), rs.getInt("row")));
				}
				if (piecesDtos.isEmpty()) {
					throw new IllegalArgumentException("id에 해당하는 정보가 없습니다.");
				}
				return piecesDtos;
			}
		}
	}
}
