package chess.board;

import chess.piece.MovedPawn;
import chess.piece.Piece;
import chess.position.Position;
import java.util.Collections;
import java.util.Map;

public class Board {

    private final Map<Position, Piece> pieces;

    Board(Map<Position, Piece> pieces) {
        this.pieces = pieces;
    }

    public void move(Position source, Position destination) {
        validatePosition(source, destination);
        validateNoPiecesBetween(source, destination);

        Piece piece = pieces.get(source);
        validateMovable(source, destination, piece);
        replacePiece(source, destination, piece);
    }

    private void validatePosition(Position source, Position destination) {
        validatePieceExistsAt(source);
        validateAllyPieceNotOnDestination(source, destination);
    }

    private void validatePieceExistsAt(Position source) {
        if (hasNoPieceOn(source)) {
            throw new IllegalArgumentException("출발 칸에 기물이 없습니다.");
        }
    }

    private void validateAllyPieceNotOnDestination(Position source, Position destination) {
        Piece sourcePiece = pieces.get(source);
        if (hasSameColorPieceOn(destination, sourcePiece)) {
            throw new IllegalArgumentException("도착 칸에 자신의 기물이 있습니다.");
        }
    }

    private void validateNoPiecesBetween(Position source, Position destination) {
        Path path = Path.createExcludingBothEnds(source, destination);
        if (path.hasPieceOnRoute(pieces)) {
            throw new IllegalArgumentException("경로에 기물이 있습니다.");
        }
    }

    private void validateMovable(Position source, Position destination, Piece piece) {
        if (hasDifferentColorPieceOn(destination, piece)) {
            validateAttackable(source, destination, piece);
            return;
        }

        if (!piece.isMovable(source, destination)) {
            throw new IllegalArgumentException("이동할 수 없는 경로입니다.");
        }
    }

    private void validateAttackable(Position source, Position destination, Piece piece) {
        if (!piece.isAttackable(source, destination)) {
            throw new IllegalArgumentException("이동할 수 없는 경로입니다.");
        }
    }

    private void replacePiece(Position source, Position destination, Piece piece) {
        pieces.remove(source);
        if (piece.isInitPawn()) {
            pieces.put(destination, new MovedPawn(piece.getColor()));
            return;
        }
        pieces.put(destination, piece);
    }

    private boolean hasNoPieceOn(Position position) {
        return !pieces.containsKey(position);
    }

    private boolean hasSameColorPieceOn(Position position, Piece piece) {
        if (hasNoPieceOn(position)) {
            return false;
        }
        Piece pieceOnPosition = pieces.get(position);
        return pieceOnPosition.hasSameColorWith(piece);
    }

    private boolean hasDifferentColorPieceOn(Position position, Piece piece) {
        if (hasNoPieceOn(position)) {
            return false;
        }
        Piece pieceOnPosition = pieces.get(position);
        return pieceOnPosition.hasDifferentColorWith(piece);
    }

    public Map<Position, Piece> pieces() {
        return Collections.unmodifiableMap(pieces);
    }
}
