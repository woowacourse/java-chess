package chess.domain.score;

import chess.domain.Column;
import chess.domain.Position;
import chess.domain.game.ChessBoard;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ScoreCalculator {

    public double getScoreByColor(ChessBoard chessBoard, Color color) {
        double score = 0.0;
        Map<Position, Piece> colorBoard = chessBoard.findPiecesByColor(color);
        for (Piece piece : colorBoard.values()) {
            score += piece.getScore();
        }

        return calculatePawnScore(score, colorBoard);
    }

    public double calculatePawnScore(double score, Map<Position, Piece> colorBoard) {
        List<Position> pawnsByColor = getPawnsByColor(colorBoard);
        List<Long> countPerColumn = getPerColumn(pawnsByColor);
        score = getScore(score, countPerColumn);

        return score;
    }

    private double getScore(double score, List<Long> countPerColumn) {
        for (Long aLong : countPerColumn) {
            score = getScore(score, aLong.intValue());
        }
        return score;
    }

    private List<Long> getPerColumn(List<Position> pawnsByColor) {
        List<Long> countPerColumn = new ArrayList<>();
        for (Column column : Column.values()) {
            long count = pawnsByColor
                    .stream()
                    .filter(position -> position.getColumn().isSameColumn(column))
                    .count();
            countPerColumn.add(count);
        }
        return countPerColumn;
    }

    private List<Position> getPawnsByColor(Map<Position, Piece> colorBoard) {
        List<Position> pawnsByColor = colorBoard.entrySet()
                .stream()
                .filter(entry -> entry.getValue().isPawn())
                .map(pawn -> pawn.getKey())
                .collect(Collectors.toList());
        return pawnsByColor;
    }

    private double getScore(double score, int pawnCount) {
        if (pawnCount >= 2) {
            score -= Score.PAWN_SPECIAL_SCORE.getScore() * pawnCount;
        }
        return score;
    }
}
