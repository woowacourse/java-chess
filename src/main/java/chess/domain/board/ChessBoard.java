package chess.domain.board;

import chess.domain.piece.Bishop;
import chess.domain.piece.Blank;
import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ChessBoard {

    private final List<List<Square>> chessBoard = new ArrayList<>(8);

    public ChessBoard() {
    }

    public void initBoard() {
        initBlack();
        initBlank();
        initWhite();
    }

    private void initBlack() {
        chessBoard.add(
            new ArrayList<>(Arrays.asList(
                new Square(Position.of("a8"), new Rook(Color.BLACK)),
                new Square(Position.of("b8"), new Knight(Color.BLACK)),
                new Square(Position.of("c8"), new Bishop(Color.BLACK)),
                new Square(Position.of("d8"), new Queen(Color.BLACK)),
                new Square(Position.of("e8"), new King(Color.BLACK)),
                new Square(Position.of("f8"), new Bishop(Color.BLACK)),
                new Square(Position.of("g8"), new Knight(Color.BLACK)),
                new Square(Position.of("h8"), new Rook(Color.BLACK))
            ))
        );
        chessBoard.add(
            new ArrayList<>(Arrays.asList(
                new Square(Position.of("a7"), new Pawn(Color.BLACK)),
                new Square(Position.of("b7"), new Pawn(Color.BLACK)),
                new Square(Position.of("c7"), new Pawn(Color.BLACK)),
                new Square(Position.of("d7"), new Pawn(Color.BLACK)),
                new Square(Position.of("e7"), new Pawn(Color.BLACK)),
                new Square(Position.of("f7"), new Pawn(Color.BLACK)),
                new Square(Position.of("g7"), new Pawn(Color.BLACK)),
                new Square(Position.of("h7"), new Pawn(Color.BLACK))
            ))
        );
    }

    private void initWhite() {
        chessBoard.add(
            new ArrayList<>(Arrays.asList(
                new Square(Position.of("a2"), new Pawn(Color.WHITE)),
                new Square(Position.of("b2"), new Pawn(Color.WHITE)),
                new Square(Position.of("c2"), new Pawn(Color.WHITE)),
                new Square(Position.of("d2"), new Pawn(Color.WHITE)),
                new Square(Position.of("e2"), new Pawn(Color.WHITE)),
                new Square(Position.of("f2"), new Pawn(Color.WHITE)),
                new Square(Position.of("g2"), new Pawn(Color.WHITE)),
                new Square(Position.of("h2"), new Pawn(Color.WHITE))
            ))
        );
        chessBoard.add(
            new ArrayList<>(Arrays.asList(
                new Square(Position.of("a1"), new Rook(Color.WHITE)),
                new Square(Position.of("b1"), new Knight(Color.WHITE)),
                new Square(Position.of("c1"), new Bishop(Color.WHITE)),
                new Square(Position.of("d1"), new Queen(Color.WHITE)),
                new Square(Position.of("e1"), new King(Color.WHITE)),
                new Square(Position.of("f1"), new Bishop(Color.WHITE)),
                new Square(Position.of("g1"), new Knight(Color.WHITE)),
                new Square(Position.of("h1"), new Rook(Color.WHITE))
            ))
        );

    }

    private void initBlank() {
        for (int i = 2; i < 6; i++) {
            chessBoard.add(
                new ArrayList<>(Arrays.asList(
                    new Square(Position.of(i, 0), new Blank(Color.NO_COLOR)),
                    new Square(Position.of(i, 1), new Blank(Color.NO_COLOR)),
                    new Square(Position.of(i, 2), new Blank(Color.NO_COLOR)),
                    new Square(Position.of(i, 3), new Blank(Color.NO_COLOR)),
                    new Square(Position.of(i, 4), new Blank(Color.NO_COLOR)),
                    new Square(Position.of(i, 5), new Blank(Color.NO_COLOR)),
                    new Square(Position.of(i, 6), new Blank(Color.NO_COLOR)),
                    new Square(Position.of(i, 7), new Blank(Color.NO_COLOR))
                ))
            );
        }
    }

    public List<List<Square>> getChessBoard() {
        return chessBoard;
    }

    public void move(String source, String target) {
        Square sourceSquare = getSquare(Position.of(source));
        Square targetSquare = getSquare(Position.of(target));
        sourceSquare.move(this, targetSquare);
    }

    public Square getSquare(Position position) {
        return chessBoard.stream()
            .flatMap(Collection::stream)
            .filter(square -> square.hasSamePosition(position))
            .findFirst()
            .orElseThrow(IllegalArgumentException::new);
    }

    public boolean isOver() {
        long kingCount = chessBoard.stream()
            .flatMap(Collection::stream)
            .filter(Square::hasKing)
            .count();
        return kingCount < 2;
    }

    public double getScore(Color color) {
        double score = calculateScore(color);
        Map<Column, Long> pawnCount = calculatePawnCount(color);
        for (long count : pawnCount.values()) {
            if (count >= 2) {
                score -= (double) count / 2;
            }
        }
        return score;
    }

    private double calculateScore(Color color) {
        return chessBoard.stream()
            .flatMap(Collection::stream)
            .filter(square -> square.hasSameColor(color))
            .mapToDouble(Square::score)
            .sum();
    }

    private Map<Column, Long> calculatePawnCount(Color color) {
        return chessBoard.stream()
            .flatMap(Collection::stream)
            .filter(Square::hasPawn)
            .filter(square -> square.hasSameColor(color))
            .collect(Collectors.groupingBy(Square::getColumn, Collectors.counting()));
    }
}
