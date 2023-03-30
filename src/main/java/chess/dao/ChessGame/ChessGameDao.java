package chess.dao.ChessGame;

import chess.dao.ChessGame.dto.FindResponseDto;
import chess.dao.ChessGame.dto.SaveRequestDto;
import chess.domain.game.Turn;

public interface ChessGameDao {

    Long save(final SaveRequestDto saveRequestDto);
    FindResponseDto findById(final long id);
    void updateTurn(final long id, final Turn turn);
    void delete();
}
