package chess.domain.board;

import chess.domain.game.Camp;
import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Board {
    private final Map<Square, Piece> board;

    public Board(final Map<Square, Piece> board) {
        this.board = board;
    }

    public Board() {
        this(BoardFactory.createBoard());
    }

    public void move(final Square source, final Square target) {
        final Piece sourcePiece = board.get(source);

        board.put(target, sourcePiece.move(target));
        board.put(source, new Empty(source));
    }

    public boolean isMovable(final Square source, final Square target) {
        final Piece sourcePiece = board.get(source);
        final Piece targetPiece = board.get(target);

        if (sourcePiece.camp() == targetPiece.camp()) {
            return false;
        }

        final Move move = Move.calculateMove(source, target);
        final boolean isPathBlocked = isPathBlocked(source, target, move);
        return sourcePiece.isMovable(targetPiece, isPathBlocked);
    }

    private boolean isPathBlocked(final Square source, final Square target, final Move move) {
        final Square nextSquare = source.nextSquare(move);

        if (nextSquare.equals(target)) {
            return false;
        }
        if (board.get(nextSquare).isSameType(PieceType.EMPTY)) {
            return isPathBlocked(nextSquare, target, move);
        }
        return true;
    }

    public boolean isSameCamp(final Square square, final Camp camp) {
        return board.get(square).isSameCamp(camp);
    }

    public int countVerticalPawn(final Camp camp) {
        return (int) board.values()
                .stream()
                .filter(piece -> isSameCampVerticalPawn(piece, camp))
                .count();
    }

    private boolean isSameCampVerticalPawn(final Piece piece, final Camp camp) {
        if (!piece.isSameCamp(camp) || !piece.isSameType(PieceType.PAWN)) {
            return false;
        }
        return isSameCampPawnOnMove(piece, Move.UP) || isSameCampPawnOnMove(piece, Move.DOWN);
    }

    private boolean isSameCampPawnOnMove(final Piece piece, final Move move) {
        final Square position = piece.position();
        if (!position.rank().canMove(move)) {
            return false;
        }

        final Square targetSquare = new Square(position.file(), position.rank().moveRank(move));
        final Piece targetPiece = board.get(targetSquare);
        return targetPiece.isSameCamp(piece.camp()) && targetPiece.isSameType(PieceType.PAWN);
    }

    public boolean isKingExist(final Camp camp) {
        return board.values()
                .stream()
                .anyMatch(piece -> piece.isSameCamp(camp) && piece.isSameType(PieceType.KING));
    }

    public List<Piece> getPieces() {
        return new ArrayList<>(board.values());
    }
}
