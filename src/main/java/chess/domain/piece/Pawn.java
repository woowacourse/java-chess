package chess.domain.piece;

import chess.domain.board.Move;
import chess.domain.board.Square;
import java.util.List;

public class Pawn extends Piece {
    private final List<Move> possibleMoves;
    private final boolean isMoved;

    public Pawn(final Camp camp, final Square position) {
        super(camp, position);
        this.isMoved = false;
        this.possibleMoves = makePossibleMoves();
    }

    public Pawn(final Camp camp, final Square position, final boolean isMoved) {
        super(camp, position);
        this.isMoved = isMoved;
        this.possibleMoves = makePossibleMoves();
    }

    private List<Move> makePossibleMoves() {
        if (camp().equals(Camp.WHITE)) {
            return generateWhitePossibleMoves();
        }

        return generateBlackPossibleMoves();
    }

    private List<Move> generateWhitePossibleMoves() {
        if (isMoved) {
            return List.of(
                    Move.UP
            );
        }
        return List.of(
                Move.UP,
                Move.UP_UP
        );
    }

    private List<Move> generateBlackPossibleMoves() {
        if (isMoved) {
            return List.of(
                    Move.DOWN
            );
        }
        return List.of(
                Move.DOWN,
                Move.DOWN_DOWN
        );
    }

    private List<Move> possibleMovesIfTargetExist() {
        if (camp().equals(Camp.WHITE)) {
            return whitePossibleMovesIfTargetExist();
        }

        return blackPossibleMovesIfTargetExist();
    }

    private List<Move> whitePossibleMovesIfTargetExist() {
        return List.of(
                Move.UP_RIGHT,
                Move.UP_LEFT
        );
    }

    private List<Move> blackPossibleMovesIfTargetExist() {
        return List.of(
                Move.DOWN_RIGHT,
                Move.DOWN_LEFT
        );
    }

    @Override
    public boolean isMovable(final Piece targetPiece,
                             final boolean isPathBlocked) {
        final Move move = calculateMove(targetPiece);
        if (targetPiece.isSameType(PieceType.EMPTY)) {
            //    적이 없을 경우
            return possibleMoves.contains(move) && isNotSlidingMove(targetPiece.position(), move) && !isPathBlocked;
        } else {
            return possibleMovesIfTargetExist().contains(move) && isNotSlidingMove(targetPiece.position(), move);
        }
    }

    private boolean isNotSlidingMove(final Square targetSquare, final Move move) {
        return (targetSquare.getFile() == position().getFile() + move.getFile())
                && (targetSquare.getRank() == position().getRank() + move.getRank());
    }

    @Override
    public Piece move(final Square target) {
        return new Pawn(camp(), target, true);
    }

    @Override
    public PieceType pieceType() {
        return PieceType.PAWN;
    }

    @Override
    public boolean isSameType(final PieceType pieceType) {
        return PieceType.PAWN.equals(pieceType);
    }
}
