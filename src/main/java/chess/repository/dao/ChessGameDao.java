package chess.repository.dao;

import java.util.List;

import chess.repository.entity.MoveDto;

public interface ChessGameDao {

	boolean isLastGameExists();
	void saveMove(MoveDto moveDto);
	List<MoveDto> findMoves();
	void deleteAllMoves();
}
