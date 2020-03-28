package chess.domain;

import java.util.*;
import java.util.stream.Collectors;

public class ChessBoard {

    private Map<Square, Piece> chessBoard = new HashMap<>();

    public ChessBoard() {
        initPawnLocation();
        for (char file = 'a'; file <= 'h'; file++) {
            initRookLocation(file);
            initKnightLocation(file);
            initBishopLocation(file);
            initQueenAndKingLocation(file);
        }
    }

    private void initQueenAndKingLocation(char file) {
        if (file == 'd') {
            chessBoard.put(Square.of(file + "1"), Queen.of(Color.WHITE));
            chessBoard.put(Square.of(file + "8"), King.of(Color.BLACK));
        }
        if (file == 'e') {
            chessBoard.put(Square.of(file + "1"), King.of(Color.WHITE));
            chessBoard.put(Square.of(file + "8"), Queen.of(Color.BLACK));
        }
    }

    private void initBishopLocation(char file) {
        if (file == 'c' || file == 'f') {
            chessBoard.put(Square.of(file + "1"), Bishop.of(Color.WHITE));
            chessBoard.put(Square.of(file + "8"), Bishop.of(Color.BLACK));
        }
    }

    private void initKnightLocation(char file) {
        if (file == 'b' || file == 'g') {
            chessBoard.put(Square.of(file + "1"), Knight.of(Color.WHITE));
            chessBoard.put(Square.of(file + "8"), Knight.of(Color.BLACK));
        }
    }

    private void initRookLocation(char file) {
        if (file == 'a' || file == 'h') {
            chessBoard.put(Square.of(file + "1"), Rook.of(Color.WHITE));
            chessBoard.put(Square.of(file + "8"), Rook.of(Color.BLACK));
        }
    }

    private void initPawnLocation() {
        for (char file = 'a'; file <= 'h'; file++) {
            chessBoard.put(Square.of(file + "2"), Pawn.of(Color.WHITE));
            chessBoard.put(Square.of(file + "7"), Pawn.of(Color.BLACK));
        }
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
        return !(chessBoard.containsValue(King.of(Color.WHITE))
                && chessBoard.containsValue(King.of(Color.BLACK)));
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
                .filter(square -> chessBoard.get(square) == Pawn.of(color))
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
            return Collections.singletonList(Color.BLACK);
        }
        if (teamScore.get(Color.BLACK) < teamScore.get(Color.WHITE)) {
            return Collections.singletonList(Color.WHITE);
        }
        return Arrays.asList(Color.WHITE, Color.BLACK);
    }

    public Map<Square, Piece> getChessBoard() {
        return chessBoard;
    }

}