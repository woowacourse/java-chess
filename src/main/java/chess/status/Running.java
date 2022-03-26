package chess.status;

import chess.Board;
import chess.MoveCommand;
import chess.view.Command;

public class Running implements State {

    private final Board board;

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
        board.move(moveCommand);
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
