package chess.domain.board;

import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.point.Point;

import java.util.Collections;
import java.util.Map;

public class Board {
    private final Map<Point, Piece> board;

    public Board(final Map<Point, Piece> board) {
        this.board = board;
    }

    public void move(final Team team, final Point departure, final Point destination) {
        final Piece piece = board.get(departure);

        validateMove(team, departure, destination, piece);

        movePiece(departure, destination, piece);
    }

    private void movePiece(final Point departure, final Point destination, final Piece piece) {
        board.put(departure, Empty.INSTANCE);
        board.put(destination, piece);
    }

    private void validateMove(final Team team, final Point departure, final Point destination, final Piece piece) {
        validateMyPiece(team, piece);
        validateMovePoint(departure, destination, piece);
    }

    private void validateMyPiece(final Team team, final Piece piece) {
        if (!piece.isSameTeam(team)) {
            throw new IllegalArgumentException("상대방의 기물을 움직일 수 없습니다.");
        }
    }

    private void validateMovePoint(final Point departure, final Point destination, final Piece piece) {
        if (!piece.isMovable(departure, destination, board)) {
            throw new IllegalArgumentException("해당 기물이 이동할 수 있는 위치가 아닙니다.");
        }
    }

    public Map<Point, Piece> getBoard() {
        return Collections.unmodifiableMap(board);
    }
}
