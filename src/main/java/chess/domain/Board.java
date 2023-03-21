package chess.domain;

import static chess.domain.piece.PieceType.INITIAL_PAWN;
import static chess.domain.piece.PieceType.KNIGHT;

import chess.domain.piece.pawn.Pawn;
import chess.domain.piece.Piece;
import chess.domain.square.File;
import chess.domain.square.Rank;
import chess.domain.square.Square;

import java.util.Map;

public class Board {
    private final Map<Square, Piece> value;

    public Board() {
        this.value = Pieces.init();
    }

    public boolean isSameTeam(final Square src, final Team team) {
        Piece piece = findPieceBy(src);
        return piece.getTeam() == team;
    }

    public void move(final Square src, final Square dst) {
        if (!canMove(src, dst)) {
            throw new IllegalArgumentException("이동할 수 없는 칸입니다.");
        }

        Piece piece = findPieceBy(src);
        if (piece.getPieceType() == INITIAL_PAWN) {
            piece = new Pawn(piece.getTeam());
        }
        value.put(dst, piece);
        value.remove(src);
    }

    private boolean canMove(final Square src, final Square dst) {
        Piece piece = findPieceBy(src);
        if (value.containsKey(dst)) {
            validateTeam(piece, dst);
        }

        int fileInterval = File.calculate(src.getFile(), dst.getFile());
        int rankInterval = Rank.calculate(src.getRank(), dst.getRank());
        piece.validateMovement(fileInterval, rankInterval);

        if (piece.getPieceType() == KNIGHT) {
            return true;
        }
        return canMoveNextSquare(src, fileInterval, rankInterval);
    }

    private Piece findPieceBy(final Square square) {
        if (!value.containsKey(square)) {
            throw new IllegalArgumentException("말이 있는 위치를 입력해주세요.");
        }
        return value.get(square);
    }

    private void validateTeam(final Piece piece, final Square dst) {
        Piece target = findPieceBy(dst);
        if (piece.isSameTeam(target)) {
            throw new IllegalArgumentException("같은 팀 말을 공격할 수 없습니다.");
        }
    }

    private boolean canMoveNextSquare(final Square src, final int fileInterval, final int rankInterval) {
        Square square = src;
        int fileMoveDirection = getMoveDirection(fileInterval);
        int rankMoveDirection = getMoveDirection(rankInterval);
        int interval = getMoveInterval(fileInterval, rankInterval);

        boolean notContainPiece = true;
        while (interval > 1 && notContainPiece) {
            square = square.next(fileMoveDirection, rankMoveDirection);
            notContainPiece = !value.containsKey(square);
            interval--;
        }
        return notContainPiece;
    }

    private int getMoveDirection(final int interval) {
        return Integer.compare(0, interval);
    }

    private int getMoveInterval(final int fileInterval, final int rankInterval) {
        return Math.max(Math.abs(fileInterval), Math.abs(rankInterval));
    }

    public Map<Square, Piece> getValue() {
        return value;
    }
}
