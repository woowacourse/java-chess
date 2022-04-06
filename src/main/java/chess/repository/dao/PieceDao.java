package chess.repository.dao;

import static java.util.stream.Collectors.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import chess.converter.StringToPieceConverter;
import chess.converter.StringToPositionConverter;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.repository.PieceDto;

public class PieceDao {

	private static final String PIECE_NAME = "name";
	private static final String POSITION = "position";

	public void insertAll(List<PieceDto> pieceDtos, int foreignKey) {
		for (PieceDto dto : pieceDtos) {
			insert(dto.getPiece().toString(), dto.getPosition().toString(), foreignKey);
		}
	}

	private void insert(String name, String position, int foreignKey) {
		ConnectionManager.createQuery("insert into piece (name, position, game_id) values (? ,?, ?)")
			.setParameter(1, name)
			.setParameter(2, position)
			.setParameter(3, foreignKey)
			.executeUpdate();
	}

	public List<PieceDto> selectByGameId(int foreignKey) {
		Map<String, String> result = ConnectionManager
			.createQuery("select position, name from piece where game_id = ?")
			.setParameter(1, foreignKey)
			.executeQuery()
			.getResultMap(POSITION, PIECE_NAME);

		return collectToDto(result);
	}

	public List<PieceDto> selectByGameName(String gameName) {
		Map<String, String> result = ConnectionManager.createQuery(
				"select p.position, p.name from piece p "
					+ "join chessGame g on p.game_id = g.game_id where g.name = ?")
			.setParameter(1, gameName)
			.executeQuery()
			.getResultMap(POSITION, PIECE_NAME);

		return collectToDto(result);
	}

	private List<PieceDto> collectToDto(Map<String, String> result) {
		return result.entrySet().stream()
			.map(entry -> new PieceDto(
				StringToPieceConverter.from(entry.getValue()),
				StringToPositionConverter.from(entry.getKey())))
			.collect(toList());
	}

	public void deleteByPosition(Position position, String gameName) {
		ConnectionManager.createQuery(
				"delete p from piece p "
					+ "join chessGame g on p.game_id = g.game_id "
					+ "where p.position = ? and g.name = ?")
			.setParameter(1, position.toString())
			.setParameter(2, gameName)
			.executeUpdate();
	}

	public void updatePositionOfPiece(Piece piece, Position from, Position to, String gameName) {
		ConnectionManager.createQuery(
				"update piece p join chessGame g on p.game_id = g.game_id "
					+ "set p.position = ? "
					+ "where p.name = ? and p.position = ? and g.name = ?")
			.setParameter(1, to.toString())
			.setParameter(2, piece.toString())
			.setParameter(3, from.toString())
			.setParameter(4, gameName)
			.executeUpdate();
	}
}

