package chess.domain.game;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.Position;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class GameResult {

    private static final double DUPLICATED_PAWN_DEDUCTION_SCORE = 0.5;

    private static final long INITIAL_KING_COUNT = 2;

    private final Map<Position, Piece> pieces;

    public GameResult(Map<Position, Piece> pieces) {
        this.pieces = pieces;
    }

    public Score getScore(Color color) {
        Map<Position, Piece> colorPieces = filterPiecesByColor(pieces, color);
        double score = calculateScore(colorPieces);
        return Score.valueOf(score);
    }

    private Map<Position, Piece> filterPiecesByColor(Map<Position, Piece> pieces, Color color) {
        return pieces.entrySet().stream()
                .filter(it -> it.getValue().getColor() == color)
                .collect(Collectors.toMap(Entry::getKey, Entry::getValue));
    }

    private double calculateScore(Map<Position, Piece> pieces) {
        double pieceScore = pieces.values().stream()
                .map(piece -> piece.getScore().getValue())
                .mapToDouble(i -> i)
                .sum();
        double deductionScore = getDuplicatedPawnSize(pieces) * DUPLICATED_PAWN_DEDUCTION_SCORE;
        return pieceScore - deductionScore;
    }

    private int getDuplicatedPawnSize(Map<Position, Piece> pieces) {
        Map<Integer, List<Piece>> fileToPawn = pieces.entrySet().stream()
                .filter(positionToPiece -> positionToPiece.getValue().isSameType(PieceType.PAWN))
                .collect(groupingBy(positionPieceEntry -> positionPieceEntry.getKey().getFileIndex(),
                        mapping(Entry::getValue, Collectors.toList())));

        return fileToPawn.values().stream()
                .filter(it -> it.size() > 1)
                .mapToInt(List::size)
                .sum();
    }

    public Color getWinner() {
        if (isGameOver()) {
            return getWinnerOfEnd();
        }
        return getCurrentWinner(getScore(Color.BLACK), getScore(Color.WHITE));
    }

    private boolean isGameOver() {
        long kingCount = pieces.values().stream()
                .filter(piece -> piece.isSameType(PieceType.KING))
                .count();
        return kingCount != INITIAL_KING_COUNT;
    }

    private Color getWinnerOfEnd() {
        Piece king = pieces.values().stream()
                .filter(piece -> piece.isSameType(PieceType.KING))
                .findAny()
                .orElseThrow(() -> new IllegalStateException("살아있는 왕이 없습니다."));
        return king.getColor();
    }

    private Color getCurrentWinner(Score blackScore, Score whiteScore) {
        if (blackScore.getValue() > whiteScore.getValue()) {
            return Color.BLACK;
        }
        if (blackScore.getValue() < whiteScore.getValue()) {
            return Color.WHITE;
        }
        return Color.NONE;
    }
}
