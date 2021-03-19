package chess.domain.board;

import chess.domain.Team;
import chess.domain.pieces.Piece;
import chess.domain.pieces.Pieces;
import chess.domain.position.Position;

import java.util.HashMap;
import java.util.Map;

public class Board {
    private final Map<Team, Pieces> board;

    public Board(final Map<Team, Pieces> board) {
        this.board = new HashMap<>(board);
    }

    public Map<Team, Pieces> toMap() {
        return new HashMap<>(board);
    }

    public void move(final Position startPoint, final Position endPoint, final Team team) {

        Pieces pieces = board.get(team);
        Piece startPointPiece = pieces.getPieceByPosition(startPoint);

        startPointPiece.move(board, endPoint);
    }
}
