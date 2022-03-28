package chess2.domain2.game2;

import chess2.dto2.MoveCommandDto;
import chess2.dto2.BoardViewDto;

public interface Game {

    Game init();

    Game moveChessmen(MoveCommandDto moveCommand);

    boolean isEnd();

    GameResult result();

    BoardViewDto boardView();
}
