package chess.domain.player;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import chess.domain.Color;
import chess.domain.Position;
import chess.domain.piece.Piece;

public class Player {

    private final Color color;
    private final Map<Position, Piece> pieces;

    public Player(final Color color, final Map<Position, Piece> pieces) {
        this.color = color;
        this.pieces = pieces;
    }

    public List<Position> calculateRouteToMove(final Position source, final Position target) {
        final Piece piece = findPieceByPosition(source);
        return piece.calculateRouteToMove(source, target);
    }

    public List<Position> calculateRouteToAttack(final Position source, final Position target) {
        final Piece piece = findPieceByPosition(source);
        return piece.calculateRouteToAttack(source, target);
    }

    private Piece findPieceByPosition(final Position position) {
        validatePieceExist(position);
        return pieces.get(position);
    }

    private void validatePieceExist(final Position position) {
        if (!contains(position)) {
            throw new NoSuchElementException("위치에 해당하는 기물을 찾을 수 없습니다.");
        }
    }

    private boolean contains(final Position position) {
        return pieces.containsKey(position);
    }
}
