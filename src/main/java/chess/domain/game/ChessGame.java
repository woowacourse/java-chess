package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.piece.Color;
import chess.domain.piece.Position;

import java.util.Optional;

public class ChessGame {

    private final Board board;
    private State state;

    public ChessGame(final Board board) {
        this.board = board;
        this.state = new Ready(this);
    }

    public void changeState(State state) {
        this.state = state;
    }

    public void move(Position source, Position target) {
        state.move(source, target);
    }

    public void end() {
        state.end();
    }

    public void start() {
        state.start();
    }

    public boolean isFinished() {
        return state.isFinished();
    }

    public boolean isKingsExist() {
        return board.isKingsExist();
    }

    public Board getBoard() {
        return board;
    }

    public double getWhiteScore() {
        return board.getWhiteScore();
    }

    public double getBlackScore() {
        return board.getBlackScore();
    }

    public Optional<Color> getWinnerColor() {
        return state.getWinnerColor();
    }

    public void movePiece(Color color, Position source, Position target) {
        board.movePiece(color, source, target);
    }

    public void catchPiece(Color color) {
        board.catchPiece(color);
    }

    public int getBoardRow() {
        return board.getRow();
    }
}
