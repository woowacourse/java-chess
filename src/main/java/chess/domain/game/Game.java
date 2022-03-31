package chess.domain.game;

import chess.dto.MoveCommandDto;
import chess.dto.BoardViewDto;

public interface Game {

    Game init();

    Game moveChessmen(MoveCommandDto moveCommand);

    boolean isEnd();

    GameResult result();

    BoardViewDto boardView();
}
