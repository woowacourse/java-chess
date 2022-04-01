package chess.domain.game;

import chess.domain.board.piece.Color;
import chess.dto.BoardViewDto;
import chess.dto.MoveCommandDto;

public interface Game {

    Game init();

    Color getCurrentTurnColor();

    Game moveChessmen(MoveCommandDto moveCommand);

    boolean isEnd();

    GameResult getResult();

    BoardViewDto boardView();
}
