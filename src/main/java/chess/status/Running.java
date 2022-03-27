package chess.status;

import static chess.piece.Color.BLACK;
import static chess.piece.Color.WHITE;

import chess.Board;
import chess.MoveCommand;
import chess.piece.Color;
import chess.view.Command;

public class Running implements State {

    private final Board board;
    private Color color = WHITE;

    Running() {
        this.board = Board.create();
    }

    @Override
    public State turn(final Command command) {
        if (command.isStart()) {
            throw new IllegalStateException("이미 게임이 시작된 상태입니다.");
        }
        if (command.isEnd()) {
            return new Finished();
        }
        return this;
    }

    @Override
    public boolean isRunning() {
        return true;
    }

    @Override
    public void move(final MoveCommand moveCommand) {
        board.move(moveCommand, color);
        reverseColor(color);
    }

    private void reverseColor(final Color color) {
        if (BLACK == color) {
            this.color = WHITE;
        }
        this.color = BLACK;
    }

    @Override
    public boolean canMove() {
        return true;
    }

    @Override
    public Board getBoard() {
        return board;
    }
}
