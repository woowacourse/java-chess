package chess.domain;

import chess.domain.piece.Empty;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.player.Player;
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
        validatePawn(departure, destination, piece);
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

    // TODO 리팩터링
    private void validatePawn(Point departure, Point destination, Piece piece) {
        if (piece instanceof Pawn) {
            if (departure.isDiagonalWithSlopeOfOne(destination)) {
                if (board.get(destination).equals(new Empty(Team.EMPTY))) {
                    throw new IllegalArgumentException("폰은 상대방의 기물이 대각선에 위치한 경우만 이동할 수 있습니다.");
                }
            }
            if (departure.isStraight(destination)) {
                if (!board.get(destination).equals(new Empty(Team.EMPTY))) {
                    throw new IllegalArgumentException("폰의 이동 경로에 기물이 존재하여 이동할 수 없습니다.");
                }
            }
        }
    }

    public Map<Point, Piece> getBoard() {
        return board;
    }
}
