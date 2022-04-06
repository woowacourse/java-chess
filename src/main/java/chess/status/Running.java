package chess.status;

import static chess.piece.detail.Color.BLACK;
import static chess.piece.detail.Color.WHITE;

import chess.game.Board;
import chess.game.MoveCommand;
import chess.piece.Piece;
import chess.piece.detail.Color;
import chess.position.Position;
import chess.view.Command;
import java.util.Map;

public final class Running implements State {

    private static final Color FIRST_TURN_COLOR = WHITE;

    private Board board;
    private Color turn = FIRST_TURN_COLOR;

    Running() {
        this.board = Board.create();
    }

    public Running(final Map<Position, Piece> value, final Color turn) {
        this.board = Board.of(value);
        this.turn = turn;
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
        board.move(moveCommand, turn);
        reverseColor(this.turn);
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
    public Color getTurn() {
        return turn;
    }

    private void reverseColor(final Color color) {
        if (WHITE == color) {
            this.turn = BLACK;
        }
        if (BLACK == color) {
            this.turn = WHITE;
        }
    }

    @Override
    public Map<Color, Double> getStatus() {
        return board.createBoardScore();
    }


    @Override
    public void load(final Map<Position, Piece> value) {
        board = Board.of(value);
    }
}
