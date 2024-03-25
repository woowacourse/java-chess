package chess.domain;

import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.player.Player;

import java.util.Collections;
import java.util.Map;

public class Board {
    private final Map<Point, Piece> board;

    public Board(Map<Point, Piece> board) {
        this.board = board;
    }

    public void move(Player player, Point departure, Point destination) {
        Piece piece = board.get(departure);

        validateMove(player, departure, destination, piece);

        movePiece(departure, destination, piece);
    }

    private void movePiece(Point departure, Point destination, Piece piece) {
        board.put(departure, new Empty(Team.EMPTY));
        board.put(destination, piece);
    }

    private void validateMove(Player player, Point departure, Point destination, Piece piece) {
        validateMyPiece(player, piece);
        validateMovePoint(departure, destination, piece);
    }

    private void validateMyPiece(Player player, Piece piece) {
        if (!player.isMyPiece(piece)) {
            throw new IllegalArgumentException("상대방의 기물을 움직일 수 없습니다.");
        }
    }

    private void validateMovePoint(Point departure, Point destination, Piece piece) {
        if (!piece.isMovable(departure, destination, board)) {
            throw new IllegalArgumentException("해당 기물이 이동할 수 있는 위치가 아닙니다.");
        }
    }

    public Map<Point, Piece> getBoard() {
        return Collections.unmodifiableMap(board);
    }
}
