package chess.game.state;

import chess.domain.Board;
import chess.dto.SquareResponse;
import java.util.List;

public interface GameState {
    void startGame(Runnable runnable);

    boolean isEnd();

    void movePiece(Runnable runnable);

    List<SquareResponse> getBoard(Board board);
}
