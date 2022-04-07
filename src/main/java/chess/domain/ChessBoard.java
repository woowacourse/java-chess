package chess.domain;

import chess.domain.piece.Piece;
import java.util.AbstractMap.SimpleEntry;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ChessBoard {

    private static final int FINISHED_GAME_KING_COUNT = 1;
    private static final double PAWN_PENALTY_SCORE = 0.5;

    private final Map<Position, Piece> pieces;

    public ChessBoard(Map<Position, Piece> pieces) {
        Objects.requireNonNull(pieces, "pieces는 null이 들어올 수 없습니다.");
        this.pieces = new HashMap<>(pieces);
    }

    public void movePiece(Position source, Position target, Color color) {
        validateFinishedGame();
        validateMovableColor(source, color);
        validateSameColor(source, target);

        Piece movedPiece = pieceByPosition(source).move(source, target, this);
        pieces.remove(source);
        pieces.put(target, movedPiece);
    }

    private void validateFinishedGame() {
        if (isFinished()) {
            throw new IllegalStateException("게임이 종료되어 기물을 움직일 수 없습니다.");
        }
    }

    private void validateMovableColor(Position source, Color color) {
        if (!pieceByPosition(source).isSameColor(color)) {
            throw new IllegalStateException("상대 진영의 기물을 움직일 수 없습니다.");
        }
    }

    private void validateSameColor(Position source, Position target) {
        if (pieces.containsKey(target) && pieceByPosition(source).isSameTeamPiece(pieceByPosition(target))) {
            throw new IllegalStateException("같은 색의 기물 위치로는 이동할 수 없습니다.");
        }
    }

    public Entry<Position, Piece> promotion(PromotionPiece promotionPiece, Color color) {
        Position position = pieces.entrySet()
                .stream()
                .filter(entry -> isPromotionPositionPawn(entry.getKey(), entry.getValue(), color))
                .map(Entry::getKey)
                .findAny()
                .orElseThrow(() -> new IllegalStateException("프로모션 프로모션 가능한 기물이 없습니다."));
        Piece piece = promotionPiece.createPiece(color);
        pieces.put(position, piece);
        return new SimpleEntry<>(position, piece);
    }

    public boolean isPositionEmpty(Position position) {
        return !pieces.containsKey(position);
    }

    public Piece pieceByPosition(Position position) {
        if (!pieces.containsKey(position)) {
            throw new NoSuchElementException("해당 위치에 존재하는 기물이 없습니다.");
        }
        return pieces.get(position);
    }

    public Map<Color, Double> calcualteScoreStatus() {
        Map<Color, Double> result = new EnumMap<>(Color.class);

        for (Color color : Color.values()) {
            result.put(color, calculateColorScore(color));
        }
        return result;
    }

    private double calculateColorScore(Color color) {
        return calculateColorDefaultScore(color) - PAWN_PENALTY_SCORE * countDuplicatedColumnPawn(color);
    }

    private double calculateColorDefaultScore(Color color) {
        return pieces.values()
                .stream()
                .filter(piece -> piece.isSameColor(color))
                .mapToDouble(Piece::score)
                .sum();
    }

    private long countDuplicatedColumnPawn(Color color) {
        return pieces.entrySet()
                .stream()
                .filter(entry -> isSameColorPawn(color, entry.getValue()))
                .filter(entry -> existSameColorPawnInColumn(entry.getKey(), color))
                .count();
    }

    private boolean isSameColorPawn(Color color, Piece piece) {
        return piece.isSameColor(color) && piece.isPawn();
    }

    private boolean existSameColorPawnInColumn(Position position, Color color) {
        return pieces.entrySet()
                .stream()
                .filter(entry -> isSameColorPawn(color, entry.getValue()))
                .anyMatch(entry -> existOtherPawnInColumn(position, entry.getKey()));
    }

    private boolean existOtherPawnInColumn(Position position, Position comparePosition) {
        return !position.equals(comparePosition) && position.equalsColumn(comparePosition);
    }

    public boolean isFinished() {
        return countKing() == FINISHED_GAME_KING_COUNT;
    }

    private long countKing() {
        return pieces.values()
                .stream()
                .filter(Piece::isKing)
                .count();
    }

    public Color winner() {
        if (!isFinished()) {
            throw new IllegalStateException("경기가 종료되지 않아 우승자를 계산할 수 없습니다.");
        }
        return pieces.values()
                .stream()
                .filter(Piece::isKing)
                .map(Piece::color)
                .findAny()
                .orElseThrow(() -> new IllegalStateException("킹이 존재하지 않아 우승자를 계산할 수 없습니다."));
    }

    public boolean isPromotionStatus(Color color) {
        return pieces.entrySet()
                .stream()
                .anyMatch(entry -> isPromotionPositionPawn(entry.getKey(), entry.getValue(), color));
    }

    private boolean isPromotionPositionPawn(Position position, Piece piece, Color color) {
        return position.isPromotionPosition(color) && piece.isPawn() && piece.isSameColor(color);
    }

    public Map<Position, Piece> getPieces() {
        return Map.copyOf(pieces);
    }
}
