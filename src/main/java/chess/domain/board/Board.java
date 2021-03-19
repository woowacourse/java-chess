package chess.domain.board;

import chess.domain.Team;
import chess.domain.pieces.Piece;
import chess.domain.position.Position;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {
    private final Map<Team, List<Piece>> board;

    public Board(final Map<Team, List<Piece>> board) {
        this.board = new HashMap<>(board);
    }

    public Map<Team, List<Piece>> toMap() {
        return new HashMap<>(board);
    }

    public void move(final Position startPoint, final Position endPoint, final Team team) {
        List<Piece> pieces = board.get(team);
        Piece startPointPiece = pieces.stream()
                .filter(piece -> piece.samePosition(startPoint))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("올바르지 않은 말을 움직이려고 하고 있습니다."));
        startPointPiece.move(board, endPoint);
    }
}
