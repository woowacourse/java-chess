package chess.status;

import static chess.piece.Color.BLACK;
import static chess.piece.Color.WHITE;

import chess.game.Board;
import chess.game.MoveCommand;
import chess.piece.Color;
import chess.game.Command;
import java.util.Map;

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
        reverseColor(this.color);
    }

    private void reverseColor(final Color color) {
        if (WHITE == color) {
            this.color = BLACK;
        }
        if (BLACK == color) {
            this.color = WHITE;
        }
    }

    @Override
    public boolean canMove() {
        return true;
    }

    @Override
    public Board getBoard() {
        return board;
    }

    @Override
    public boolean isGameEnd() {
        return board.isKingDead();
    }

    @Override
    public Color getColor() {
        reverseColor(color);
        return color;
    }

    @Override
    public Map<Color, Double> getStatus() {
        return board.getBoardScore();
    }
}
