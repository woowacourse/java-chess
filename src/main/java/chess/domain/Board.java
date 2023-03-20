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
    private final Map<Square, Piece> board;

    public Board() {
        this.board = Pieces.init();
    }

    public boolean isSameColor(Square source, Team team) {
        Piece piece = findPieceBy(source);
        return piece.getColor() == team;
    }

    public void move(Square src, Square dst) {
        if (!canMove(src, dst)) {
            throw new IllegalArgumentException("이동할 수 없는 칸입니다.");
        }

        Piece piece = findPieceBy(source);
        if (piece.getPieceType() == INITIAL_PAWN) {
            piece = new Pawn(piece.getColor());
        }
        board.put(target, piece);
        board.remove(source);
    }

    private boolean canMove(final Square source, final Square target) {
        final Piece piece = findPieceBy(source);
        final int fileInterval = File.calculate(source.getFile(), target.getFile());
        final int rankInterval = Rank.calculate(source.getRank(), target.getRank());
        final boolean canAttack = canAttack(target, piece);

        piece.canMove(fileInterval, rankInterval, canAttack);

        if (piece.getPieceType() == KNIGHT) {
            return !board.containsKey(target);
        }
        return canMoveNextSquare(source, fileInterval, rankInterval);
    }

    private boolean canAttack(final Square target, final Piece piece) {
        if (board.containsKey(target)) {
            return board.get(target).getColor() != piece.getColor();
        }
        return false;
    }

    private Piece findPieceBy(Square square) {
        if (!board.containsKey(square)) {
            throw new IllegalArgumentException("말이 있는 위치를 입력해주세요.");
        }
        return board.get(square);
    }

    private boolean canMoveNextSquare(final Square src, final int fileInterval, final int rankInterval) {
        Square nextSquare = src;
        int fileMoveDirection = getMoveDirection(fileInterval);
        int rankMoveDirection = getMoveDirection(rankInterval);

        int interval = getMoveInterval(fileInterval, rankInterval);

        boolean notContainPiece = true;
        while (interval > 0 && notContainPiece) {
            nextSquare = nextSquare.next(fileMoveDirection, rankMoveDirection);
            notContainPiece = isNotContainPiece(source, nextSquare);
            interval--;
        }
        return notContainPiece;
    }

    private boolean isNotContainPiece(Square source, Square nextSquare) {
        Piece piece = board.get(source);
        if (board.containsKey(nextSquare)) {
            return board.get(nextSquare).getColor() != piece.getColor();
        }
        return true;
    }

    private int getMoveDirection(final int interval) {
        return Integer.compare(0, interval);
    }

    private int getMoveInterval(final int fileInterval, final int rankInterval) {
        return max(abs(fileInterval), abs(rankInterval));
    }

    public Map<Square, Piece> getBoard() {
        return board;
    }
}
