package chess.game.state;

import chess.domain.Team;
import chess.dto.SquareResponse;
import chess.game.state.running.RunningState;
import java.util.List;
import java.util.function.DoubleSupplier;
import java.util.function.Supplier;

public interface GameState {
    void startGame(Runnable runnable);

    boolean isEnd();

    void movePiece(Runnable runnable);

    List<SquareResponse> getBoard(Supplier<List<SquareResponse>> supplier);

    double getTeamScore(DoubleSupplier doubleSupplier);

    Team getTurn();

    RunningState changeTurn();

    Team getWinner();

    void checkCheckmate(Runnable runnable);

    boolean hasWinner();
}
