package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.position.Movement;
import chess.domain.position.Position;
import chess.domain.piece.type.Pawn;
import java.util.Map;

public class ChessBoard {

    private final Map<Position, Piece> pieces;

    public ChessBoard(final Map<Position, Piece> pieces) {
        this.pieces = pieces;
    }

    public void move(final Position current, final Position destination) {
        final Piece currentPiece = findPieceBy(current);
        final Movement movement = new Movement(current, destination);

        validate(currentPiece, movement);

        movePiece(currentPiece, movement);
    }

    private void validate(final Piece currentPiece, final Movement movement) {
        validateStrategy(currentPiece, movement);
        validateRoute(currentPiece, movement);

        if (canPawnCatch(currentPiece, movement) || isPieceExist(movement.getDestination())) {
            validateOpponent(currentPiece, movement);
        }
    }

    private void validateStrategy(final Piece currentPiece, final Movement movement) {
        if (currentPiece.canMove(movement)) {
            return;
        }

        throw new IllegalArgumentException("[ERROR] 전략상 이동할 수 없는 위치입니다.");
    }

    public Piece findPieceBy(final Position position) {
        if (!isPieceExist(position)) {
            throw new IllegalArgumentException("[ERROR] 해당 위치에 기물이 존재하지 않습니다.");
        }

        return pieces.get(position);
    }

    private void movePiece(final Piece currentPiece, final Movement movement) {
        pieces.remove(movement.getCurrent());
        pieces.put(movement.getDestination(), currentPiece);
    }

    private boolean isPieceExist(final Position position) {
        return pieces.containsKey(position);
    }

    private boolean canPawnCatch(final Piece currentPiece, final Movement movement) {
        return currentPiece instanceof Pawn
                && isPieceExist(movement.getDestination())
                && (((Pawn) currentPiece).canCatch(movement));
    }

    private void validateRoute(final Piece currentPiece, final Movement movement) {
        if (existPieceInWay(currentPiece, movement)) {
            throw new IllegalArgumentException("[ERROR] 경로상 기물이 존재합니다.");
        }
    }

    private boolean existPieceInWay(final Piece currentPiece, final Movement movement) {
        return pieces.keySet().stream().anyMatch(currentPiece.getRoute(movement)::contains);
    }

    private void validateOpponent(final Piece currentPiece, final Movement movement) {
        final Piece targetPiece = findPieceBy(movement.getDestination());
        if (currentPiece.isOpponent(targetPiece)) {
            return;
        }

        throw new IllegalArgumentException("[ERROR] 잡을 수 없는 기물입니다.");
    }

    public Map<Position, Piece> getPieces() {
        return pieces;
    }
}
