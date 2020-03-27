package chess.domain.board;

import chess.domain.piece.Color;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TeamScore {

    private final Map<Color, Double> teamScore;

    public TeamScore(Map<BoardSquare, Piece> chessBoard) {
        this.teamScore = Collections.unmodifiableMap(getTeamScore(chessBoard));
    }

    private Map<Color, Double> getTeamScore(Map<BoardSquare, Piece> chessBoard) {
        Map<Color, Double> teamScore = new HashMap<>();
        double blackScore = 0;
        double whiteScore = 0;
        for (Piece piece : chessBoard.values()) {
            if (piece.isBlack()) {
                blackScore += piece.getScore();
                continue;
            }
            whiteScore += piece.getScore();
        }
        blackScore -= calculatePawnScore(chessBoard, Color.BLACK);
        whiteScore -= calculatePawnScore(chessBoard, Color.WHITE);
        teamScore.put(Color.BLACK, blackScore);
        teamScore.put(Color.WHITE, whiteScore);
        return teamScore;
    }

    private double calculatePawnScore(Map<BoardSquare, Piece> chessBoard, Color color) {
        int count;
        List<BoardSquare> boardSquares = chessBoard.keySet().stream()
            .filter(square -> chessBoard.get(square) == Pawn.getPieceInstance(color))
            .collect(Collectors.toList());
        count = 0;
        for (BoardSquare boardSquare : boardSquares) {
            for (BoardSquare boardSquareCompared : boardSquares) {
                if (boardSquare.isSameFile(boardSquareCompared)
                    && boardSquare != boardSquareCompared) {
                    count++;
                }
            }
        }
        return count * 0.5;
    }

    public List<Color> getWinners() {
        Map<Color, Double> teamScore = getTeamScore();
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
