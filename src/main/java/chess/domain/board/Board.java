package chess.domain.board;

import chess.domain.piece.Camp;
import chess.domain.piece.Empty;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
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

        updateIfPawn(source, target);
        board.put(target, board.get(source));
        board.put(source, Empty.of());
    }

    private boolean isMovable(final Square source, final Square target) {
        final Piece sourcePiece = board.get(source);
        final Move move = Move.calculateMove(source, target);
        final boolean isPathBlocked = isPathBlocked(source, target, move);

        if (isSourceAndTargetSameCamp(source, target)) {
            return false;
        }
        return sourcePiece.isMovable(target, move, isPathBlocked);
    }

    private boolean isSourceAndTargetSameCamp(final Square source, final Square target) {
        final Piece sourcePiece = board.get(source);
        final Camp targetCamp = board.get(target).camp();

        return sourcePiece.isSameCamp(targetCamp);
    }

    private void updateIfPawn(final Square source, final Square target) {
        if (board.get(source).getClass() == Pawn.class) {
            board.put(source, new Pawn(board.get(source).camp(), target, MOVED));
        }
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

    private boolean isEmptyPiece(final Square source) {
        return board.get(source).equals(Empty.of());
    }

    public boolean isSameCamp(final Square square, final Camp camp) {
        return board.get(square).isSameCamp(camp);
    }

    public List<Piece> getPieces() {
        return new ArrayList<>(board.values());
    }
}
