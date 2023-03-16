package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.Map;

public class Game {

    private boolean isWhiteTurn;
    private final Board board;

    public Game() {
        this.isWhiteTurn = true;
        this.board = BoardFactory.createBoard();
    }

    public void movePiece(Position source, Position target) {
        board.move(source, target, isWhiteTurn);
        changeTurn();
    }

    private void changeTurn() {
        isWhiteTurn = !isWhiteTurn;
    }

    public Map<Position, Piece> getPieces() {
        return board.getPieces();
    }
}
