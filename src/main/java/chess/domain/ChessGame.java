package chess.domain;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.board.Squares;
import chess.domain.piece.Color;

import java.util.List;

public final class ChessGame {

    private Color turn;
    private final Board board;

    public ChessGame() {
        this.board = new Board();
        this.turn = Color.WHITE;
    }

    public void playTurn(Position source, Position target) {
        board.play(source, target, turn);
        this.turn = changeTurn();
    }

    public boolean isKingDead() {
        return board.isKingDead(turn);
    }

    private Color changeTurn() {
        if (turn.isBlack()) {
            return Color.WHITE;
        }
        return Color.BLACK;
    }

    public Color getTurn() {
        return turn;
    }

    public List<Squares> getBoard() {
        return board.getSquares();
    }
}
