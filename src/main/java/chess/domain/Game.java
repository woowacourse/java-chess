package chess.domain;

import chess.domain.board.Board;
import chess.domain.board.BoardCreator;
import chess.domain.piece.Side;
import chess.domain.position.Position;
import chess.domain.piece.Piece;
import java.util.List;

public class Game {

    private final Board board;
    private Side currentSide = Side.WHITE;

    public Game() {
        this.board = new Board(new BoardCreator());
    }

    public void proceedTurn(Position source, Position target) {
        checkTurn(source);
        board.move(source, target);
        switchTurn();
    }

    private void checkTurn(Position source) {
        Piece piece = board.findPiece(source);
        if (piece.isOpponentSide(currentSide)) {
            throw new IllegalArgumentException("해당 진영의 차례가 아닙니다.");
        }
    }

    private void switchTurn() {
        currentSide = currentSide.opponent();
    }

    public List<Piece> board() {
        return board.pieces();
    }
}
