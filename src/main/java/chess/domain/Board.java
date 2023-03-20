package chess.domain;

import static chess.domain.piece.PieceType.INITIAL_PAWN;
import static chess.domain.piece.PieceType.KNIGHT;
import static java.lang.Math.*;

import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.square.File;
import chess.domain.square.Rank;
import chess.domain.square.Square;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Board {
    private final Map<Square, Piece> board;

    public Board() {
        this.board = Pieces.init();
    }

    public boolean isSameColor(final Square source, final Team team) {
        Piece piece = findPieceBy(source);
        return piece.getTeam() == team;
    }

    public void move(final Square source, final Square target) {
        validateTarget(source, target);
        updateSquare(source, target);
    }

    private void validateTarget(final Square source, final Square target) {
        if (!canMove(source, target)) {
            throw new IllegalArgumentException("이동할 수 없는 칸입니다.");
        }
    }

    private void updateSquare(final Square source, final Square target) {
        Piece piece = findPieceBy(source);
        if (piece.isSameType(INITIAL_PAWN)) {
            piece = new Pawn(piece.getTeam());
        }
        board.put(target, piece);
        board.remove(source);
    }

    private boolean canMove(final Square source, final Square target) {
        final Piece piece = findPieceBy(source);
        final int fileInterval = File.calculate(source.getFile(), target.getFile());
        final int rankInterval = Rank.calculate(source.getRank(), target.getRank());
        piece.canMove(fileInterval, rankInterval, isEnemy(target, piece));
        if (piece.isSameType(KNIGHT)) {
            return !board.containsKey(target);
        }
        return canMoveNextSquare(source, fileInterval, rankInterval);
    }

    private boolean isEnemy(final Square target, final Piece piece) {
        if (board.containsKey(target)) {
            return !isSameTeam(piece, board.get(target));
        }
        return false;
    }

    private Piece findPieceBy(final Square square) {
        if (!board.containsKey(square)) {
            throw new IllegalArgumentException("말이 있는 위치를 입력해주세요.");
        }
        return board.get(square);
    }

    private boolean canMoveNextSquare(final Square source, final int fileInterval, final int rankInterval) {
        int fileMoveDirection = getMoveDirection(fileInterval);
        int rankMoveDirection = getMoveDirection(rankInterval);
        int interval = getMoveInterval(fileInterval, rankInterval);

        return checkNextSquares(source, List.of(fileMoveDirection, rankMoveDirection), interval);
    }

    private boolean checkNextSquares(final Square source, final List<Integer> direction, int interval) {
        Square current = source;
        do {
            current = current.next(direction.get(0), direction.get(1));
            interval--;
        } while (interval > 0 && canMoveToNext(source, current));
        return canMoveToNext(source, current);
    }

    private boolean canMoveToNext(final Square source, final Square nextSquare) {
        Piece piece = board.get(source);
        return !(board.containsKey(nextSquare) && isSameTeam(piece, board.get(nextSquare)));
    }

    private boolean isSameTeam(final Piece piece, final Piece nextPiece) {
        return nextPiece.getTeam() == piece.getTeam();
    }

    private int getMoveDirection(final int interval) {
        return Integer.compare(0, interval);
    }

    private int getMoveInterval(final int fileInterval, final int rankInterval) {
        return max(abs(fileInterval), abs(rankInterval));
    }

    public Map<Square, Piece> getBoard() {
        return Collections.unmodifiableMap(board);
    }
}
