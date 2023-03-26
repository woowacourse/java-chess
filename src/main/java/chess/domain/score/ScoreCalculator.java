package chess.domain.score;

import chess.domain.Column;
import chess.domain.Position;
import chess.domain.game.ChessBoard;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static chess.domain.Column.A;
import static chess.domain.Column.H;

public class ScoreCalculator {

    public double getBlackScore(ChessBoard chessBoard) {
        double blackScore = 0.0;
        List<Piece> pieces = chessBoard.findPiecesByColor(Color.BLACK);
        for (Piece piece : pieces) {
            blackScore += piece.getScore();
        }

        return calculatePawnScore(chessBoard, blackScore, Color.BLACK);
    }

    public double getWhiteScore(ChessBoard chessBoard) {
        double whiteScore = 0.0;
        List<Piece> pieces = chessBoard.findPiecesByColor(Color.WHITE);
        for (Piece piece : pieces) {
            whiteScore += piece.getScore();
        }

        return calculatePawnScore(chessBoard, whiteScore, Color.WHITE);
    }

    public double calculatePawnScore(ChessBoard chessBoard, double score, Color color) {
        Map<Position, Piece> board = chessBoard.getChessBoard();

        for (int index = A.getIndex(); index < H.getIndex(); index++) {
            List<Piece> sameColumnPieces = getSameColumnPieces(board, index);
            int pawnCount = getPawnCount(sameColumnPieces, color);
            score = getScore(score, pawnCount);
        }
        return score;
    }

    private List<Piece> getSameColumnPieces(Map<Position, Piece> board, int index) {
        List<Piece> sameColumnPieces = new ArrayList<>();

        for (Position position : board.keySet()) {
            if (position.getColumnIndex() == index) {
                sameColumnPieces.add(board.get(position));
            }
        }
        return sameColumnPieces;
    }

    private double getScore(double score, int pawnCount) {
        if (pawnCount >= 2) {
            score -= Score.PAWN_SPECIAL_SCORE.getScore() * pawnCount;
        }
        return score;
    }

    private int getPawnCount(List<Piece> sameColumnsPieces, Color color) {
        int pawnCount = 0;
        for (Piece piece : sameColumnsPieces) {
            pawnCount = piece.calculatePawn(pawnCount, color);
        }
        return pawnCount;
    }
}
