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

    public void move(final Position source, final Position target) {
        validatePositionEmpty(target);
        Piece piece = findPieceByPosition(source).move();
        pieces.remove(source);
        pieces.put(target, piece);
    }

    public void attack(final Position source, final Position target, final Player enemyPlayer) {
        validatePositionEmpty(target);
        enemyPlayer.removePiece(target);
        move(source, target);
    }

    private void removePiece(final Position target) {
        validatePieceExist(target);
        pieces.remove(target);
    }

    private void validatePositionEmpty(final Position position) {
        if (contains(position)) {
            throw new IllegalStateException("해당 위치에 이미 기물이 존재합니다.");
        }
    }

    public boolean contains(final Position position) {
        return pieces.containsKey(position);
    }

    public boolean isPromotablePawnExist() {
        return pieces.keySet()
                .stream()
                .anyMatch(this::isPromotablePawnPlacedAt);
    }

    private boolean isPromotablePawnPlacedAt(final Position position) {
        final Piece piece = findPieceByPosition(position);
        return piece.isPawn() && position.isEndOfRowRange();
    }

    public void promotePawn(final Piece piece) {
        final Position position = findPositionOfPromotablePawn();
        pieces.put(position, piece);
    }

    private Position findPositionOfPromotablePawn() {
        return pieces.keySet()
                .stream()
                .filter(this::isPromotablePawnPlacedAt)
                .findAny()
                .orElseThrow(() -> new IllegalStateException("프로모션 가능한 폰이 존재하지 않습니다."));
    }

    public boolean isKingAlive() {
        return pieces.values()
                .stream()
                .anyMatch(Piece::isKing);
    }

    public boolean isColorSame(final Color color) {
        return color.equals(this.color);
    }

    public Map<Position, Piece> getPieces() {
        return Map.copyOf(pieces);
    }
}
