package chess.domain;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import java.util.Map;

public class Board {
    private final Map<Square, Piece> pieces;

    public Board() {
        this.pieces = Pieces.init();
    }

    public boolean isSameColor(Square src, Color color) {
        Piece piece = findPieceBy(src);
        return piece.getColor() == color;
    }

    public void move(Square src, Square dst) {
        if (canMove(src, dst)) {
            pieces.put(dst, findPieceBy(src));
            pieces.remove(src);
        }
    }

    private boolean canMove(Square src, Square dst) {
        Piece piece = findPieceBy(src);

        // piece가 폰인데, 화이트일떄는 2이고 블랙일때는 7이다 -> 초기위치다
        // 초기위치일 때는 2칸 쌉가능

        int fileInterval = File.calculate(src.getFile(), dst.getFile());
        int rankInterval = Rank.calculate(src.getRank(), dst.getRank());
        boolean result = piece.canMove(fileInterval, rankInterval);

        return result && canMoveNextSquare(src, fileInterval, rankInterval);
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
            notContainPiece = !pieces.containsKey(nextSquare);
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

    public Map<Square, Piece> getPieces() {
        return pieces;
    }
}
