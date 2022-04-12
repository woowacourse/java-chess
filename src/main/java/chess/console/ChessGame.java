package chess.console;

import chess.domain.Camp;
import chess.domain.GameSwitch;
import chess.domain.StatusScore;
import chess.domain.board.Position;
import chess.domain.board.Positions;
import chess.domain.gamestate.Ready;
import chess.domain.gamestate.State;
import chess.domain.piece.Piece;
import java.util.List;
import java.util.Map;

public class ChessGame {

    private State state;
    public GameSwitch gameSwitch;

    public ChessGame() {
        state = new Ready();
        gameSwitch = GameSwitch.ON;
    }

    public boolean isAnyMovablePositionKingCheckmated(final List<Position> positions) {
        return positions.size() > 0;
    }

    //web용 (boolean -> 로직(gameSwitchOff();) 실행은 호출부로 가서 하도록 빼자.
    public boolean isAllMovablePositionKingCheckMated(final List<Position> positions) {
        return isAllKingCheckmated(positions);
    }

    public boolean isAllKingCheckmated(final List<Position> positions) {
        return state.isAllKingCheckmated(positions);
    }

    public boolean isKingChecked() {
        return state.isKingChecked();
    }

    public void run() {
        state = state.run();
    }

    public void runWithCurrentState() {
        state = state.runWithCurrentState();
    }

    public void move(final Positions movePositions) {
        state = state.move(movePositions);
    }

    public void status() {
        state = state.status();
    }

    public StatusScore calculateStatus() {
        return state.calculateStatus();
    }

    public boolean isRunning() {
        if (gameSwitch.isOff()) {
            return false;
        }
        return state.isRunning();
    }

    public void gameSwitchOff() {
        gameSwitch = gameSwitch.off();
    }

    public void gameSwitchOn() {
        gameSwitch = gameSwitch.on();
    }

    public void end() {
        state = state.end();
    }

    public boolean isStatusInRunning() {
        if (isEndInGameOff()) {
            return false;
        }
        return state.isStatus();
    }

    public boolean isEndInRunning() {
        if (isEndInGameOff()) {
            return false;
        }
        return state.isFinished();
    }

    public boolean isEndInGameOff() {
        return gameSwitch.isOff();
    }

    public void ready() {
        state = state.ready();
    }

    public Map<Position, Piece> getBoard() {
        return state.getBoard();
    }

    public Camp getCamp() {
        return state.getCamp();
    }

    public List<Position> findKingCheckMatedPosition() {
        return state.getKingCheckmatedPositions();
    }

    public void changeBoard(final Map<Position, Piece> board, final String camp) {
        state.changeBoard(board, camp);
    }

    public List<Position> getKingCheckmatedPositions() {
        return state.getKingCheckmatedPositions();
    }
}
