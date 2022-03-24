package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ChessBoard {

    private static final int FINISHED_GAME_KING_COUNT = 1;

    private final Map<Position, Piece> pieces;

    public ChessBoard(Map<Position, Piece> pieces) {
        Objects.requireNonNull(pieces, "pieces는 null이 들어올 수 없습니다.");
        this.pieces = new HashMap<>(pieces);
    }

    public static ChessBoard createNewChessBoard() {
        return new ChessBoard(PieceFactory.createNewChessBoard());
    }

    public void movePiece(Position start, Position target, Color color) {
        if (isFinished()) {
            throw new IllegalStateException("게임이 종료되어 기물을 움직일 수 없습니다.");
        }
        if (!pieceByPosition(start).isSameColor(color)) {
            throw new IllegalStateException("상대 진영의 기물을 움직일 수 없습니다.");
        }
        Piece movedPiece = pieceByPosition(start).move(start, target, this);
        pieces.remove(start);
        pieces.put(target, movedPiece);
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
        return calculateColorDefaultScore(color) - 0.5 * countDuplicatedColumnPawn(color);
    }

    private double calculateColorDefaultScore(Color color) {
        return pieces.values()
                .stream()
                .filter(piece -> piece.isSameColor(color))
                .mapToDouble(Piece::score)
                .sum();
    }

    private int countDuplicatedColumnPawn(Color color) {
        return (int) pieces.entrySet()
                .stream()
                .filter(entry -> isSameColorPawn(color, entry.getValue()))
                .filter(entry -> existSameColorPawnInColumn(entry.getKey(), color))
                .count();
    }

    private boolean isSameColorPawn(final Color color, Piece piece) {
        return piece.isSameColor(color) && piece.isPawn();
    }

    private boolean existSameColorPawnInColumn(Position position, Color color) {
        return pieces.entrySet()
                .stream()
                .filter(entry -> isSameColorPawn(color, entry.getValue()))
                .anyMatch(entry -> existOtherPawnInColumn(position, entry.getKey()));
    }

    private boolean existOtherPawnInColumn(final Position position, Position comparePosition) {
        return !position.equals(comparePosition) && position.equalsColumn(comparePosition);
    }

    public boolean isFinished() {
        return countKing() == FINISHED_GAME_KING_COUNT;
    }

    private int countKing() {
        return (int) pieces.values()
                .stream()
                .filter(Piece::isKing)
                .count();
    }

    public Map<Position, Piece> getPieces() {
        return Map.copyOf(pieces);
    }
}
