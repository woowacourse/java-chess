package chess.domain.board;

import java.sql.SQLException;
import java.util.Map;
import java.util.stream.Collectors;

import chess.domain.dao.BoardDao;
import chess.domain.dao.SQLConnector;
import chess.domain.dto.PieceDto;
import chess.domain.dto.PieceEditDto;
import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

public class Board {
	private final BoardDao boardDao;

	public Board(Map<Position, Piece> pieces) {
		boardDao = new BoardDao(new SQLConnector().getConnection());
		pieces.entrySet()
			.stream()
			.map(x -> PieceDto.of(x.getKey(), x.getValue()))
			.forEach(x -> {
				try {
					boardDao.addPiece(x);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			});
	}

	public boolean isNotEmptyPosition(Position position) throws SQLException {
		return boardDao.findPiece(position) != null;
	}

	public Piece findPieceBy(Position position) throws SQLException {
		PieceDto pieceDto = boardDao.findPiece(position);
		return pieceDto.getPiece();
	}

	public void movePiece(Position from, Position to) throws SQLException {
		PieceDto pieceDto = boardDao.findPiece(from);
		PieceEditDto pieceEditDto = new PieceEditDto(to, pieceDto.getPiece());
		boardDao.editPieceByPosition(pieceEditDto);
		boardDao.deletePieceByPosition(from);
	}

	public boolean isKingAliveOf(Color color) throws SQLException {
		return boardDao.findAllPieces()
			.stream()
			.map(PieceDto::getPiece)
			.anyMatch(piece -> isKingOf(color, piece));
	}

	private boolean isKingOf(Color color, Piece piece) {
		return piece.isSameColor(color) && piece instanceof King;
	}

	public Map<Position, Piece> getPieces() throws SQLException {
		return boardDao.findAllPieces()
			.stream()
			.collect(Collectors.toMap(PieceDto::getPosition, PieceDto::getPiece));
	}

	public void deleteAll() throws SQLException {
		boardDao.deleteAll();
	}
}
