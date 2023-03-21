package chess.domain.board;

import chess.domain.piece.Camp;
import chess.domain.piece.Empty;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Role;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Board {
    private static final boolean MOVED = true;

    private final Map<Square, Piece> board;

    public Board() {
        this.board = BoardFactory.createBoard();
    }

    public void move(final Square source, final Square target) {
        if (!isMovable(source, target)) {
            throw new IllegalArgumentException("이동할 수 없습니다.");
        }

        updateIfPawn(source);
        board.put(target, board.get(source));
        board.put(source, Empty.of());
    }

    private void updateIfPawn(final Square source) {
        if (board.get(source).isSameRole(Role.PAWN)) {
            board.put(source, new Pawn(board.get(source).getCamp(), MOVED));
        }
    }

    private boolean isMovable(final Square source, final Square target) {
        final Piece sourcePiece = board.get(source);
        final Move move = Move.calculateMove(source, target);
        final boolean isPathBlocked = isPathBlocked(source, target, move);

        if (isTargetSameCamp(source, target)) {
            return false;
        }
        return sourcePiece.isMovable(source, target, move, isPathBlocked);
    }

    private boolean isPathBlocked(final Square source, final Square target, final Move move) {
        final Square nextSquare = source.nextSquare(source, move);

        if (nextSquare.equals(target)) {
            return false;
        }
        if (isEmptyPiece(nextSquare)) {
            return isPathBlocked(nextSquare, target, move);
        }
        return true;
    }

    private boolean isTargetSameCamp(final Square source, final Square target) {
        final Piece sourcePiece = board.get(source);
        final Camp targetCamp = board.get(target).getCamp();

        return sourcePiece.isSameCamp(targetCamp);
    }

    public boolean isTargetSameCamp(final Square square, final Camp camp) {
        return board.get(square).isSameCamp(camp);
    }

    public boolean isEmptyPiece(final Square source) {
        return board.get(source).equals(Empty.of());
    }

    public List<Piece> getPieces() {
        return new ArrayList<>(board.values());
    }
}
