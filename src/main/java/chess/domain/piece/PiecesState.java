package chess.domain.piece;

import chess.domain.piece.position.Direction;
import chess.domain.piece.position.Distance;
import chess.domain.piece.position.Position;

import java.util.Map;
import java.util.Objects;

public class PiecesState {
    private final Map<Position, Piece> pieces;

    public PiecesState(Map<Position, Piece> pieces) {
        this.pieces = pieces;
    }

    Piece getPiece(Position position) {
        return pieces.get(position);
    }

    public boolean isFilled(Position position) {
        Piece piece = getPiece(position);
        return Objects.nonNull(piece);
    }

    public boolean isNotFilled(Position position) {
        Piece piece = getPiece(position);
        return Objects.isNull(piece);
    }

    public boolean hasHindranceInBetween(Distance amount, Direction direction, Position position) {
        for (int i = Position.FORWARD_AMOUNT; i < amount.getValue(); i++) {
            position = position.go(direction);
            if (isFilled(position)) {
                return true;
            }
        }

        return false;
    }

    public boolean isSameTeam(Position from, Position to) {
        Piece fromPiece = pieces.get(from);
        Piece toPiece = pieces.get(to);
        if (Objects.isNull(fromPiece) || Objects.isNull(toPiece)) {
            return false;
        }
        return fromPiece.isSameTeam(toPiece);
    }

    public boolean isOppositeTeam(Position from, Position to) {
        Piece fromPiece = pieces.get(from);
        Piece toPiece = pieces.get(to);
        if (Objects.isNull(fromPiece) || Objects.isNull(toPiece)) {
            return false;
        }
        return fromPiece.isOppositeTeam(toPiece);
    }
}
