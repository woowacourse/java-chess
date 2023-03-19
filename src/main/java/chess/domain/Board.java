package chess.domain;

import static chess.domain.piece.PieceType.INITIAL_PAWN;
import static chess.domain.piece.PieceType.KNIGHT;
import static java.lang.Math.*;

import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.square.File;
import chess.domain.square.Rank;
import chess.domain.square.Square;
import java.util.Map;

public class Board {
    private final Map<Square, Piece> pieces;

    public Board() {
        this.pieces = Pieces.init();
    }

    public boolean isSameColor(Square src, Team team) {
        Piece piece = findPieceBy(src);
        return piece.getColor() == team;
    }

    public void move(Square src, Square dst) {
        if (!canMove(src, dst)) {
            throw new IllegalArgumentException("이동할 수 없는 칸입니다.");
        }

        Piece piece = findPieceBy(src);
        if (piece.getPieceType() == INITIAL_PAWN) {
            piece = new Pawn(piece.getColor());
        }
        pieces.put(dst, piece);
        pieces.remove(src);
    }

    private boolean canMove(final Square src, final Square dst) {
        final Piece piece = findPieceBy(src);
        final int fileInterval = File.calculate(src.getFile(), dst.getFile());
        final int rankInterval = Rank.calculate(src.getRank(), dst.getRank());
        final boolean canAttack = canAttack(dst, piece);

        piece.canMove(fileInterval, rankInterval, canAttack);

        if (piece.getPieceType() == KNIGHT) {
            return !pieces.containsKey(dst);
        }
        return canMoveNextSquare(src, fileInterval, rankInterval);
    }

    private boolean canAttack(final Square dst, final Piece piece) {
        if (pieces.containsKey(dst)) {
            return pieces.get(dst).getColor() != piece.getColor();
        }
        return false;
    }

    private Piece findPieceBy(Square square) {
        if (!pieces.containsKey(square)) {
            throw new IllegalArgumentException("말이 있는 위치를 입력해주세요.");
        }
        return pieces.get(square);
    }

    private boolean canMoveNextSquare(final Square src, final int fileInterval, final int rankInterval) {
        Square nextSquare = src;
        int fileMoveDirection = getMoveDirection(fileInterval);
        int rankMoveDirection = getMoveDirection(rankInterval);

        int interval = getMoveInterval(fileInterval, rankInterval);

        boolean notContainPiece = true;
        while (interval > 0 && notContainPiece) {
            nextSquare = nextSquare.next(fileMoveDirection, rankMoveDirection);
            notContainPiece = isNotContainPiece(src, nextSquare);
            interval--;
        }
        return notContainPiece;
    }

    private boolean isNotContainPiece(Square src, Square nextSquare) {
        Piece piece = pieces.get(src);
        if (pieces.containsKey(nextSquare)) {
            return pieces.get(nextSquare).getColor() != piece.getColor();
        }
        return true;
    }

    private int getMoveDirection(final int interval) {
        return Integer.compare(0, interval);
    }

    private int getMoveInterval(final int fileInterval, final int rankInterval) {
        return max(abs(fileInterval), abs(rankInterval));
    }

    public Map<Square, Piece> getPieces() {
        return pieces;
    }
}
