package chess.state;

import chess.game.Board;
import chess.game.Command;
import chess.game.MoveCommand;
import chess.game.Score;
import chess.piece.Color;

public abstract class Running implements State {
    private final Board board;
    private Color color;

    Running(final Board board, final Color color) {
        this.board = board;
        this.color = color;
    }

    @Override
    public abstract void move(final MoveCommand moveCommand);

    @Override
    public final State turn(final Command command) {
        if (command.isStart()) {
            throw new IllegalStateException("이미 게임이 시작된 상태입니다.");
        }
        if (command.isEnd()) {
            return new Finished();
        }
        if (command.isStatus()) {
            return new Status(board, color);
        }
        return new Moving(board, color);
    }

    @Override
    public final Score score() {
        return board.calculateBoardScore();
    }

    @Override
    public final boolean isRunning() {
        return true;
    }

    @Override
    public final boolean isGameEnd() {
        return board.isKingDead();
    }

    public final void reversColor() {
        this.color = color.reverse();
    }

    @Override
    public final Board getBoard() {
        return board;
    }

    @Override
    public final Color getColor() {
        return color;
    }

}
