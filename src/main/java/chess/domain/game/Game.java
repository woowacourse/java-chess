package chess.domain.game;

import chess.dto.GameResultDto;
import chess.dto.MoveCommandDto;

public interface Game {

    Game init();

    Game moveChessmen(MoveCommandDto dto);

    boolean isEnd();

    GameResultDto getGameResult();

    ActivePieces getChessmen();
}
