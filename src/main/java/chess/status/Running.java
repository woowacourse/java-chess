package chess.status;

import static chess.piece.Color.BLACK;
import static chess.piece.Color.WHITE;

import chess.game.*;
import chess.piece.Color;

public class Running implements State {

    private final Board board;
    private Color color = WHITE;

    Running() {
        this.board = new Board(BoardInitializer.getBoard());
    }

    @Override
    public void move(final MoveCommand moveCommand) {
        board.move(moveCommand, color);
        reverseColor(color);
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
    public Score score() {
        return board.calculateBoardScore();
    }

    @Override
    public boolean isRunning() {
        return true;
    }

    @Override
    public boolean canMove() {
        return true;
    }

    @Override
    public boolean isGameEnd() {
        return board.isKingDead();
    }

    @Override
    public Board getBoard() {
        return board;
    }

    @Override
    public Color getColor() {
        return color;
    }

    private void reverseColor(final Color color) {
        if (WHITE == color) {
            this.color = BLACK;
        }
        if (BLACK == color) {
            this.color = WHITE;
        }
    }
}
