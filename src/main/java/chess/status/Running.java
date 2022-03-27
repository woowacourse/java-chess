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
    public Map<Color, Double> score() {
        return board.getBoardScore();
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
