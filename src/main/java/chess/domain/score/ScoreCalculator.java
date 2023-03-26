package chess.domain.score;

import chess.domain.Column;
import chess.domain.Position;
import chess.domain.piece.Color;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ScoreCalculator {

    private double blackScore = 0;
    private double whiteScore = 0;

    public void calculateScores(Map<Position, Piece> chessBoard) {
        for (Piece piece : chessBoard.values()) {
            getBlackScore(piece);
            getWhiteScore(piece);
        }

        blackScore = checkPawnScore(chessBoard, blackScore, Color.BLACK);
        whiteScore = checkPawnScore(chessBoard, whiteScore, Color.WHITE);
    }

    private void getWhiteScore(Piece piece) {

        if (Color.isSameColor(piece.getColor(), Color.WHITE)) {
            whiteScore += piece.getScore();
        }
    }

    private void getBlackScore(Piece piece) {
        if (piece.getColor().equals(Color.BLACK)) {
            blackScore += piece.getScore();
        }
    }

    public double checkPawnScore(Map<Position, Piece> chessBoard, double score, Color color) {
        for (int index = Column.A.getIndex(); index < Column.H.getIndex(); index++) {
            List<Piece> sameColumnPieces = new ArrayList<>();

            for (Position position : chessBoard.keySet()) {
                if (position.getColumnIndex() == index) {
                    sameColumnPieces.add(chessBoard.get(position));
                }
            }

            int pawnCount = getPawnCount(sameColumnPieces, color);
            score = getScore(score, pawnCount);
        }
        return score;
    }

    private double getScore(double score, int pawnCount) {
        if (pawnCount >= 2) {
            score -= Score.PAWN_SPECIAL_SCORE.getScore() * pawnCount;
        }
        return score;
    }

    private int getPawnCount(List<Piece> columns, Color color) {
        int pawnCount = 0;
        for (Piece piece : columns) {
           pawnCount = piece.calculatePawn(pawnCount,color);
        }
        return pawnCount;
    }

    public double getBlackScore() {
        return blackScore;
    }

    public double getWhiteScore() {
        return whiteScore;
    }
}
