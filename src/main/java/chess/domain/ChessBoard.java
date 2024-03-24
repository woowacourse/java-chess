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
        final Piece currentPiece = findPieceBy(current);

        final Movement movement = new Movement(current, target);

        validateJumpOver(currentPiece, movement);

        if (isPieceExist(target) || canPawnCatch(currentPiece, movement)) {
            validateNotMySide(currentPiece, movement);
            movePiece(currentPiece, movement);
            return;
        }

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
        pieces.put(movement.getTarget(), currentPiece);
    }

    private boolean isPieceExist(final Position position) {
        return pieces.containsKey(position);
    }

    private boolean canPawnCatch(final Piece currentPiece, final Movement movement) {
        return currentPiece instanceof Pawn
                && isPieceExist(movement.getTarget())
                && (((Pawn) currentPiece).canCatch(movement));
    }

    private void validateJumpOver(final Piece currentPiece, final Movement movement) {
        if (existPieceInWay(currentPiece, movement)) {
            throw new IllegalArgumentException("[ERROR] 경로상 기물이 존재합니다.");
        }
    }

    private boolean existPieceInWay(final Piece currentPiece, final Movement movement) {
        return pieces.keySet().stream().anyMatch(currentPiece.getRoute(movement)::contains);
    }

    private void validateNotMySide(final Piece currentPiece, final Movement movement) {
        final Piece targetPiece = findPieceBy(movement.getTarget());
        if (currentPiece.isMySide(targetPiece)) {
            throw new IllegalArgumentException("[ERROR] 잡을 수 없는 기물입니다.");
        }
    }

    public Map<Position, Piece> getPieces() {
        return pieces;
    }
}
