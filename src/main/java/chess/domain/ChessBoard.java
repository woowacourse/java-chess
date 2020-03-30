package chess.domain;

import chess.domain.piece.*;
import chess.domain.square.Square;

import java.util.*;
import java.util.stream.Collectors;

public class ChessBoard {

    private Map<Square, Piece> chessBoard = new HashMap<>();

    public ChessBoard() {
        for (char file = 'a'; file <= 'h'; file++) {
            chessBoard.put(Square.of(file + "2"), Pawn.of(Color.WHITE));
            chessBoard.put(Square.of(file + "7"), Pawn.of(Color.BLACK));
        }

        chessBoard.put(Square.of("a1"), Rook.of(Color.WHITE));
        chessBoard.put(Square.of("b1"), Knight.of(Color.WHITE));
        chessBoard.put(Square.of("c1"), Bishop.of(Color.WHITE));
        chessBoard.put(Square.of("d1"), Queen.of(Color.WHITE));
        chessBoard.put(Square.of("e1"), King.of(Color.WHITE));
        chessBoard.put(Square.of("f1"), Bishop.of(Color.WHITE));
        chessBoard.put(Square.of("g1"), Knight.of(Color.WHITE));
        chessBoard.put(Square.of("h1"), Rook.of(Color.WHITE));

        chessBoard.put(Square.of("a8"), Rook.of(Color.BLACK));
        chessBoard.put(Square.of("b8"), Knight.of(Color.BLACK));
        chessBoard.put(Square.of("c8"), Bishop.of(Color.BLACK));
        chessBoard.put(Square.of("d8"), Queen.of(Color.BLACK));
        chessBoard.put(Square.of("e8"), King.of(Color.BLACK));
        chessBoard.put(Square.of("f8"), Bishop.of(Color.BLACK));
        chessBoard.put(Square.of("g8"), Knight.of(Color.BLACK));
        chessBoard.put(Square.of("h8"), Rook.of(Color.BLACK));
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
}