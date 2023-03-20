package chess.domain;

import static chess.domain.piece.PieceType.INITIAL_PAWN;
import static chess.domain.piece.PieceType.KNIGHT;

import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.square.File;
import chess.domain.square.Rank;
import chess.domain.square.Square;
import chess.domain.square.Squares;
import java.util.LinkedHashMap;
import java.util.Map;

public class Board {
    private final Map<Square, Piece> pieces;

    public Board() {
        this.pieces = Pieces.init();
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
        pieces.put(dst, piece);
        pieces.remove(src);
    }

    private boolean canMove(final Square src, final Square dst) {
        Piece piece = findPieceBy(src);

        int fileInterval = File.calculate(src.getFile(), dst.getFile());
        int rankInterval = Rank.calculate(src.getRank(), dst.getRank());
        piece.validateMovement(fileInterval, rankInterval);

        if (piece.getPieceType() == KNIGHT) {
            return !pieces.containsKey(dst);
        }
        return canMoveNextSquare(src, fileInterval, rankInterval);
    }

    private Piece findPieceBy(final Square square) {
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

        boolean notContainPiece = true; // TODO: 같은 팀 일때만 확인하기
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

    public Map<Integer, String> getBoardResult() {
        Map<Integer, String> boardResult = new LinkedHashMap<>();
        for (Square key : pieces.keySet()) {
            boardResult.put(Squares.getIndex(key), pieces.get(key).getPieceTypeName());
        }
        return boardResult;
    }

    public Map<Square, Piece> getPieces() {
        return pieces;
    }
}
