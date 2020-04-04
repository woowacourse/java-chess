package chess.domain;

import chess.domain.piece.Color;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.square.Square;

import java.util.*;
import java.util.stream.Collectors;

public class TeamScore {
    public static final double PAWN_SCORE_MULTIPLIER = 0.5;

    private Map<Color, Double> teamScore;

    public TeamScore() {
        teamScore = new HashMap<>();
        teamScore.put(Color.WHITE, 0.0);
        teamScore.put(Color.BLACK, 0.0);
    }

    public TeamScore updateTeamScore(ChessBoard board) {
        Map<Square, Piece> chessBoard = board.getChessBoard();
        double blackScore = 0;
        double whiteScore = 0;
        for (Piece piece : chessBoard.values()) {
            if (piece.isBlack()) {
                blackScore += piece.getScore();
                continue;
            }
            whiteScore += piece.getScore();
        }
        blackScore -= calculatePawnScore(Color.BLACK, board);
        whiteScore -= calculatePawnScore(Color.WHITE, board);
        teamScore.put(Color.BLACK, blackScore);
        teamScore.put(Color.WHITE, whiteScore);
        return this;
    }

    private double calculatePawnScore(Color color, ChessBoard board) {
        int count = 0;
        Map<Square, Piece> chessBoard = board.getChessBoard();
        List<Square> squares = chessBoard.keySet().stream()
                .filter(square -> chessBoard.get(square) == Pawn.of(color))
                .collect(Collectors.toList());
        for (Square square : squares) {
            for (Square squareCompared : squares) {
                if (square.isJustSameFile(squareCompared)) {
                    count++;
                }
            }
        }
        return count * PAWN_SCORE_MULTIPLIER;
    }

    public List<Color> getWinners() {
        if (teamScore.get(Color.BLACK) > teamScore.get(Color.WHITE)) {
            return Collections.singletonList(Color.BLACK);
        }
        if (teamScore.get(Color.BLACK) < teamScore.get(Color.WHITE)) {
            return Collections.singletonList(Color.WHITE);
        }
        return Arrays.asList(Color.WHITE, Color.BLACK);
    }

    public Map<Color, Double> getTeamScore() {
        return teamScore;
    }
}
