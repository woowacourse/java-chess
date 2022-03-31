package chess.domain.board;

import chess.domain.piece.Blank;
import chess.domain.piece.Team;
import chess.domain.piece.Piece;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class Board {

    private final Map<Position, Piece> board;

    public Board(final Map<Position, Piece> board) {
        this.board = new TreeMap<>(board);
    }

    public void movePiece(final Position source, final Position target, final CatchPieces catchPieces) {
        Piece targetPiece = board.getOrDefault(target, new Blank());
        board.put(target, getPiece(source));
        board.put(source, new Blank());
        catchPieces.addPiece(targetPiece);
    }

    public Team getColorOfPiece(final Position position) {
        return board.get(position).getColor();
    }

    public Piece getPiece(final Position position) {
        return board.get(position);
    }

    public Map<Position, Piece> getBoard() {
        return Collections.unmodifiableMap(board);
    }
}
