package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.piece.Position;
import chess.domain.piece.type.Pawn;
import java.util.Map;

public class ChessBoard {

    private final Map<Position, Piece> pieces;

    public ChessBoard(final Map<Position, Piece> pieces) {
        this.pieces = pieces;
    }

    public void move(final Position current, final Position target) {
        final Movement movement = new Movement(current, target);

        final Piece currentPiece = findPieceBy(current);
//        if (currentPiece instanceof Pawn && canPawnCatch(currentPiece, movement)) {
//            catchPiece(currentPiece, target);
//            return;
//        }

        validateJumpOver(currentPiece, movement);

        if (isPieceExist(target)) {
            validateNotMySide(currentPiece, target);
            catchPiece(currentPiece, target);
        }

        pieces.put(target, currentPiece);
    }

    public Piece findPieceBy(final Position current) {
        if (!pieces.containsKey(current)) {
            throw new IllegalArgumentException("[ERROR] 해당 위치에 기물이 존재하지 않습니다.");
        }

        return pieces.get(current);
    }

//    private boolean canPawnCatch(final Piece currentPiece, final Movement movement) {
//        if (!isPieceExist(movement.getTarget())) {
//            return false;
//        }
//
//        return (movement.isDiagonal() && movement.getRankDistance() == Pawn.DEFAULT_STEP)
//                && currentPiece.isMySide(findPieceBy(movement.getTarget()));
//    }

    private boolean isPieceExist(Position target) {
        return pieces.containsKey(target);
    }

    private void catchPiece(final Piece currentPiece, final Position target) {
        pieces.remove(target);
        pieces.put(target, currentPiece);
    }

    private void validateJumpOver(final Piece currentPiece, final Movement movement) {
        if (existPieceInWay(currentPiece, movement)) {
            throw new IllegalArgumentException("[ERROR] 경로상 기물이 존재합니다.");
        }
    }

    private boolean existPieceInWay(final Piece currentPiece, final Movement movement) {
        return pieces.keySet().stream()
                .anyMatch(currentPiece.getRoute(movement)::contains);
    }

    private void validateNotMySide(final Piece currentPiece, final Position targetPosition) {
        final Piece targetPiece = findPieceBy(targetPosition);
        if (currentPiece.isMySide(targetPiece)) {
            throw new IllegalArgumentException("[ERROR] 잡을 수 없는 기물입니다.");
        }
    }

    public Map<Position, Piece> getPieces() {
        return pieces;
    }
}
