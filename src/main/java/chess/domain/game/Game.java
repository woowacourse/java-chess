package chess.domain.game;

import chess.domain.piece.Piece;
import chess.dto.GameResultDto;
import chess.dto.MovePositionCommandDto;
import java.util.List;

public interface Game {

    Game moveChessmen(MovePositionCommandDto dto);

    boolean isEnd();

    GameResultDto getGameResult();

    List<Piece> getChessmen();
}
