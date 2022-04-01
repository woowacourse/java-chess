package chess.status;

import chess.game.Board;
import chess.game.Command;
import chess.game.MoveCommand;
import chess.game.Score;
import chess.piece.Color;

public final class Finished implements State {

    private static final String FINISHED_GAME = "게임이 종료되었습니다.";

    Finished() {
    }

    @Override
    public void move(final MoveCommand moveCommand) {
        throw new IllegalStateException(FINISHED_GAME);
    }

    @Override
    public State turn(final Command command) {
        throw new IllegalStateException(FINISHED_GAME);
    }

    @Override
    public Score score() {
        throw new IllegalStateException(FINISHED_GAME);
    }

    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public boolean canMove() {
        return false;
    }

    @Override
    public boolean isGameEnd() {
        return true;
    }

    @Override
    public Board getBoard() {
        throw new IllegalStateException(FINISHED_GAME);
    }

    @Override
    public Color getColor() {
        throw new IllegalStateException(FINISHED_GAME);
    }
}
