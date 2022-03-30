package chess.status;

import chess.game.Board;
import chess.game.MoveCommand;
import chess.piece.detail.Color;
import chess.view.Command;
import java.util.Map;

public final class Ready implements State {

    private Ready() {
    }

    public static State start(final Command command) {
        if (command.isStart()) {
            return new Running();
        }

        if (command.isEnd()) {
            return new Finished();
        }

        throw new IllegalStateException("게임이 시작되지 않았습니다.");
    }

    @Override
    public State turn(final Command command) {
        throw new IllegalStateException("게임이 시작되지 않았습니다.");
    }

    @Override
    public boolean isRunning() {
        return true;
    }

    @Override
    public void move(final MoveCommand moveCommand) {
        throw new IllegalStateException("게임이 시작되지 않았습니다.");
    }

    @Override
    public boolean canMove() {
        return false;
    }

    @Override
    public Board getBoard() {
        throw new IllegalStateException("게임이 시작되지 않았습니다.");
    }

    @Override
    public boolean isGameEnd() {
        return false;
    }

    @Override
    public Color getColor() {
        throw new IllegalStateException();
    }

    @Override
    public Map<Color, Double> getStatus() {
        throw new IllegalStateException();
    }
}
