package chess.domain.board;

import chess.domain.piece.Blank;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class Board {

    private final Map<Position, Piece> board;

    public Board(final Map<Position, Piece> board) {
        this.board = new TreeMap<>(board);
    }

    public void movePiece(final Position source, final Position target) {
        board.put(target, getPiece(source));
        board.put(source, new Blank());
    }

    public Team getTeamOfPiece(final Position position) {
        return board.get(position).getTeam();
    }

    public Piece getPiece(final Position position) {
        return board.get(position);
    }

    public Map<Position, Piece> getBoard() {
        return Collections.unmodifiableMap(board);
    }
}
