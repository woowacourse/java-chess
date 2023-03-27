package chess.dao;

import java.util.List;

import chess.dto.MoveDto;

public interface ChessGameDao {

	boolean isLastGameExists();
	void saveMove(MoveDto moveDto);
	List<MoveDto> loadMoves();
	void deleteMoves();
}
