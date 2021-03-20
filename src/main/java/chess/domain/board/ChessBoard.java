package chess.domain.board;

import chess.domain.piece.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ChessBoard {
    public static final int LAST_BOARD_INDEX = 7;
    public static final int BOARD_SIZE = 8;
    private static final double PAWN_SCORE_PUNISHMENT_RATIO = 0.5;
    private static final int NUMBER_OF_KINGS = 2;
    private static final int COLUMN_NEIGHBOR_PAWN = 2;

    private final List<Square> chessBoard = new ArrayList<>();

    public ChessBoard() {
        initBlank();
    }

    public void initBoard() {
        initBlack();
        initWhite();
    }

    private void initBlank() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                chessBoard.add(new Square(Position.of(i, j), new Blank(Color.NO_COLOR)));
            }
        }
    }

    private void initBlack() {
        getSquare(Position.of("a8")).replacePiece(new Rook(Color.BLACK));
        getSquare(Position.of("b8")).replacePiece(new Knight(Color.BLACK));
        getSquare(Position.of("c8")).replacePiece(new Bishop(Color.BLACK));
        getSquare(Position.of("d8")).replacePiece(new Queen(Color.BLACK));
        getSquare(Position.of("e8")).replacePiece(new King(Color.BLACK));
        getSquare(Position.of("f8")).replacePiece(new Bishop(Color.BLACK));
        getSquare(Position.of("g8")).replacePiece(new Knight(Color.BLACK));
        getSquare(Position.of("h8")).replacePiece(new Rook(Color.BLACK));

        getSquare(Position.of("a7")).replacePiece(new Pawn(Color.BLACK));
        getSquare(Position.of("b7")).replacePiece(new Pawn(Color.BLACK));
        getSquare(Position.of("c7")).replacePiece(new Pawn(Color.BLACK));
        getSquare(Position.of("d7")).replacePiece(new Pawn(Color.BLACK));
        getSquare(Position.of("e7")).replacePiece(new Pawn(Color.BLACK));
        getSquare(Position.of("f7")).replacePiece(new Pawn(Color.BLACK));
        getSquare(Position.of("g7")).replacePiece(new Pawn(Color.BLACK));
        getSquare(Position.of("h7")).replacePiece(new Pawn(Color.BLACK));
    }

    private void initWhite() {
        getSquare(Position.of("a1")).replacePiece(new Rook(Color.WHITE));
        getSquare(Position.of("b1")).replacePiece(new Knight(Color.WHITE));
        getSquare(Position.of("c1")).replacePiece(new Bishop(Color.WHITE));
        getSquare(Position.of("d1")).replacePiece(new Queen(Color.WHITE));
        getSquare(Position.of("e1")).replacePiece(new King(Color.WHITE));
        getSquare(Position.of("f1")).replacePiece(new Bishop(Color.WHITE));
        getSquare(Position.of("g1")).replacePiece(new Knight(Color.WHITE));
        getSquare(Position.of("h1")).replacePiece(new Rook(Color.WHITE));

        getSquare(Position.of("a2")).replacePiece(new Pawn(Color.WHITE));
        getSquare(Position.of("b2")).replacePiece(new Pawn(Color.WHITE));
        getSquare(Position.of("c2")).replacePiece(new Pawn(Color.WHITE));
        getSquare(Position.of("d2")).replacePiece(new Pawn(Color.WHITE));
        getSquare(Position.of("e2")).replacePiece(new Pawn(Color.WHITE));
        getSquare(Position.of("f2")).replacePiece(new Pawn(Color.WHITE));
        getSquare(Position.of("g2")).replacePiece(new Pawn(Color.WHITE));
        getSquare(Position.of("h2")).replacePiece(new Pawn(Color.WHITE));
    }

    public List<Square> getChessBoard() {
        return chessBoard;
    }

    public void move(String source, String target) {
        Square sourceSquare = getSquare(Position.of(source));
        Square targetSquare = getSquare(Position.of(target));
        sourceSquare.move(this, targetSquare);
    }

    public Square getSquare(Position position) {
        return chessBoard.stream()
            .filter(square -> square.hasSamePosition(position))
            .findFirst()
            .orElseThrow(IllegalArgumentException::new);
    }

    public boolean isOver() {
        long kingCount = chessBoard.stream()
            .filter(Square::hasKing)
            .count();
        return kingCount < NUMBER_OF_KINGS;
    }

    public double getScore(Color color) {
        double score = calculateScore(color);
        Map<Column, Long> pawnCount = calculatePawnCount(color);
        double punishmentScore = calculatePunishmentScore(pawnCount);
        return score - punishmentScore;
    }

    private double calculateScore(Color color) {
        return chessBoard.stream()
            .filter(square -> square.hasSameColor(color))
            .mapToDouble(Square::score)
            .sum();
    }

    private Map<Column, Long> calculatePawnCount(Color color) {
        return chessBoard.stream()
            .filter(Square::hasPawn)
            .filter(square -> square.hasSameColor(color))
            .collect(Collectors.groupingBy(Square::getColumn, Collectors.counting()));
    }

    private double calculatePunishmentScore(Map<Column, Long> pawnCount) {
        return pawnCount.values().stream()
            .filter(count -> count >= COLUMN_NEIGHBOR_PAWN)
            .mapToDouble(count -> count * PAWN_SCORE_PUNISHMENT_RATIO)
            .sum();
    }
}
