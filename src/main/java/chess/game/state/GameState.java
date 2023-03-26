package chess.game.state;

import chess.domain.Team;
import chess.dto.SquareResponse;
import chess.game.state.running.RunningState;
import java.util.List;
import java.util.function.DoubleSupplier;
import java.util.function.Supplier;

public interface GameState {
    String INVALID_TEAM_EXCEPTION_MESSAGE = "[ERROR] 해당 팀에 대한 조건이 없습니다.";

    void startGame(Runnable runnable);

    boolean isEnd();

    void movePiece(Runnable runnable);

    List<SquareResponse> getBoard(Supplier<List<SquareResponse>> supplier);

    double getTeamScore(DoubleSupplier doubleSupplier);

    Team getTurn();

    RunningState changeTurn();

    Team getWinner();

    boolean isChecked();

    void saveGame(Runnable runnable);

    void loadGame(Runnable runnable);

    void leaveGame(Runnable runnable);
}
