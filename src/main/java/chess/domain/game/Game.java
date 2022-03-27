package chess.domain.game;

import chess.domain.piece.Piece;
import chess.dto.GameResultDto;
import chess.dto.MoveCommandDto;
import java.util.List;

public interface Game {

    Game moveChessmen(MoveCommandDto dto);

    boolean isEnd();

    GameResultDto getGameResult();

    List<Piece> getChessmen();
}
