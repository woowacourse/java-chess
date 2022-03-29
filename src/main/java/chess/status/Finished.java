package chess.status;

import chess.game.Board;
import chess.game.MoveCommand;
import chess.piece.Color;
import chess.view.Command;
import java.util.Map;

public final class Finished implements State {

    Finished() {
    }

    @Override
    public State turn(final Command command) {
        throw new IllegalStateException("게임이 이미 종료되었습니다.");
    }

    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public void move(final MoveCommand moveCommand) {
        throw new IllegalStateException("게임이 종료되어 말을 움직일 수 없습니다.");
    }

    @Override
    public boolean canMove() {
        return false;
    }

    @Override
    public Board getBoard() {
        throw new IllegalStateException("게임이 종료되어 체스판을 불러올 수 없습니다.");
    }

    @Override
    public boolean isGameEnd() {
        return true;
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
