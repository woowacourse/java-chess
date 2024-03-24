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
        final Movement movement = new Movement(current, destination);

        final Piece currentPiece = findPieceBy(current);

        if (canPawnCatch(currentPiece, movement) || isPieceExist(destination)) {
            validateOpponent(currentPiece, movement);
        }

        validateRoute(currentPiece, movement);

        movePiece(currentPiece, movement);
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
        if (!currentPiece.isOpponent(targetPiece)) {
            throw new IllegalArgumentException("[ERROR] 잡을 수 없는 기물입니다.");
        }
    }

    public Map<Position, Piece> getPieces() {
        return pieces;
    }
}
