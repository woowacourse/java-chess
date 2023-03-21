package chess.game.state;

import chess.dto.SquareResponse;
import java.util.List;
import java.util.function.Supplier;

public interface GameState {
    void startGame(Runnable runnable);

    boolean isEnd();

    void movePiece(Runnable runnable);

    List<SquareResponse> getBoard(Supplier<List<SquareResponse>> supplier);
}
