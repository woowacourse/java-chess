package chess.domain.board;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.Type;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ChessBoard {

    private Map<Square, Piece> chessBoard = new HashMap<>();

    public ChessBoard() {
        for (char file = 'a'; file <= 'h'; file++) {
            chessBoard.put(Square.of(file + "2"), Piece.of(Color.WHITE, Type.PAWN));
            chessBoard.put(Square.of(file + "7"), Piece.of(Color.BLACK, Type.PAWN));
        }

        Type[] arr = new Type[]{Type.ROOK, Type.KNIGHT, Type.BISHOP, Type.QUEEN, Type.KING, Type.BISHOP, Type.KNIGHT, Type.ROOK};
        for (int i = 0; i < 8; i++) {
            char file = (char) ('a' + i);
            if (i == 3) {
                chessBoard.put(Square.of(file + "1"), Piece.of(Color.WHITE, arr[i]));
                chessBoard.put(Square.of(file + "8"), Piece.of(Color.BLACK, arr[i + 1]));
                continue;
            }
            if (i == 4) {
                chessBoard.put(Square.of(file + "1"), Piece.of(Color.WHITE, arr[i]));
                chessBoard.put(Square.of(file + "8"), Piece.of(Color.BLACK, arr[i - 1]));
                continue;
            }
            chessBoard.put(Square.of(file + "1"), Piece.of(Color.WHITE, arr[i]));
            chessBoard.put(Square.of(file + "8"), Piece.of(Color.BLACK, arr[i]));
        }
    }

    public Map<Square, Piece> getChessBoard() {
        return chessBoard;
    }

    public boolean canMove(List<Square> squares, boolean blackTurn) {
        Square before = squares.get(0);
        Square after = squares.get(1);
        if (!chessBoard.containsKey(before) || chessBoard.get(before).isBlack() != blackTurn) {
            return false;
        }
        return chessBoard.get(before).calculateMoveBoundary(before, chessBoard).contains(after);
    }

    public void movePiece(List<Square> squares) {
        Square before = squares.get(0);
        Square after = squares.get(1);
        Piece currentPiece = chessBoard.remove(before);
        chessBoard.put(after, currentPiece);
    }

    public boolean isKingCaptured() {
        return !(chessBoard.containsValue(Piece.of(Color.WHITE, Type.KING))
                && chessBoard.containsValue(Piece.of(Color.BLACK, Type.KING)));
    }

    public Map<Color, Double> getTeamScore() {
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
        blackScore -= calculatePawnScore(Color.BLACK);
        whiteScore -= calculatePawnScore(Color.WHITE);
        teamScore.put(Color.BLACK, blackScore);
        teamScore.put(Color.WHITE, whiteScore);
        return teamScore;
    }


    private double calculatePawnScore(Color color) {
        int count;
        List<Square> squares = chessBoard.keySet().stream()
                .filter(square -> chessBoard.get(square) == Piece.of(color, Type.PAWN))
                .collect(Collectors.toList());
        count = 0;
        for (Square square : squares) {
            for (Square squareCompared : squares) {
                if (square.isJustSameFile(squareCompared)) {
                    count++;
                }
            }
        }
        return count * 0.5;
    }

    public List<Color> getWinners() {
        Map<Color, Double> teamScore = getTeamScore();
        if (teamScore.get(Color.BLACK) > teamScore.get(Color.WHITE)) {
            return Arrays.asList(Color.BLACK);
        }
        if (teamScore.get(Color.BLACK) < teamScore.get(Color.WHITE)) {
            return Arrays.asList(Color.WHITE);
        }
        return Arrays.asList(Color.WHITE, Color.BLACK);
    }
}