package chess.domain.board;

import chess.domain.piece.Blank;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class Board {

    private final Map<Position, Piece> board;

    public Board(final Map<Position, Piece> board) {
        this.board = new TreeMap<>(board);
    }

    public Piece movePiece(final Position source, final Position target) {
        Piece targetPiece = board.replace(target, getPiece(source));
        board.replace(source, new Blank());
        return targetPiece;
    }

    public Color getPieceColorByPosition(final Position position) {
        return getPiece(position).getColor();
    }

    public Piece getPiece(final Position position) {
        return board.get(position);
    }

    public Map<Position, Piece> getBoard() {
        return Collections.unmodifiableMap(board);
    }
}
