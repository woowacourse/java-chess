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
                new Square(new Position("a8"), new Rook(Color.BLACK)),
                new Square(new Position("b8"), new Knight(Color.BLACK)),
                new Square(new Position("c8"), new Bishop(Color.BLACK)),
                new Square(new Position("d8"), new Queen(Color.BLACK)),
                new Square(new Position("e8"), new King(Color.BLACK)),
                new Square(new Position("f8"), new Bishop(Color.BLACK)),
                new Square(new Position("g8"), new Knight(Color.BLACK)),
                new Square(new Position("h8"), new Rook(Color.BLACK))
            ))
        );
        chessBoard.add(
            new ArrayList<>(Arrays.asList(
                new Square(new Position("a7"), new Pawn(Color.BLACK)),
                new Square(new Position("b7"), new Pawn(Color.BLACK)),
                new Square(new Position("c7"), new Pawn(Color.BLACK)),
                new Square(new Position("d7"), new Pawn(Color.BLACK)),
                new Square(new Position("e7"), new Pawn(Color.BLACK)),
                new Square(new Position("f7"), new Pawn(Color.BLACK)),
                new Square(new Position("g7"), new Pawn(Color.BLACK)),
                new Square(new Position("h7"), new Pawn(Color.BLACK))
            ))
        );
    }

    private void initWhite() {
        chessBoard.add(
            new ArrayList<>(Arrays.asList(
                new Square(new Position("a2"), new Pawn(Color.WHITE)),
                new Square(new Position("b2"), new Pawn(Color.WHITE)),
                new Square(new Position("c2"), new Pawn(Color.WHITE)),
                new Square(new Position("d2"), new Pawn(Color.WHITE)),
                new Square(new Position("e2"), new Pawn(Color.WHITE)),
                new Square(new Position("f2"), new Pawn(Color.WHITE)),
                new Square(new Position("g2"), new Pawn(Color.WHITE)),
                new Square(new Position("h2"), new Pawn(Color.WHITE))
            ))
        );
        chessBoard.add(
            new ArrayList<>(Arrays.asList(
                new Square(new Position("a1"), new Rook(Color.WHITE)),
                new Square(new Position("b1"), new Knight(Color.WHITE)),
                new Square(new Position("c1"), new Bishop(Color.WHITE)),
                new Square(new Position("d1"), new Queen(Color.WHITE)),
                new Square(new Position("e1"), new King(Color.WHITE)),
                new Square(new Position("f1"), new Bishop(Color.WHITE)),
                new Square(new Position("g1"), new Knight(Color.WHITE)),
                new Square(new Position("h1"), new Rook(Color.WHITE))
            ))
        );

    }

    private void initBlank() {
        for (int i = 2; i < 6; i++) {
            chessBoard.add(
                new ArrayList<>(Arrays.asList(
                    new Square(new Position(i, 0), new Blank(Color.NO_COLOR)),
                    new Square(new Position(i, 1), new Blank(Color.NO_COLOR)),
                    new Square(new Position(i, 2), new Blank(Color.NO_COLOR)),
                    new Square(new Position(i, 3), new Blank(Color.NO_COLOR)),
                    new Square(new Position(i, 4), new Blank(Color.NO_COLOR)),
                    new Square(new Position(i, 5), new Blank(Color.NO_COLOR)),
                    new Square(new Position(i, 6), new Blank(Color.NO_COLOR)),
                    new Square(new Position(i, 7), new Blank(Color.NO_COLOR))
                ))
            );
        }
    }

    public List<List<Square>> getChessBoard() {
        return chessBoard;
    }

    public void move(String source, String target) {
        Square sourceSquare = getSquare(new Position(source));
        Square targetSquare = getSquare(new Position(target));
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
        double score = chessBoard.stream()
            .flatMap(Collection::stream)
            .filter(square -> square.hasSameColor(color))
            .mapToDouble(Square::score)
            .sum();

        Map<Column, Long> pawnCount = chessBoard.stream()
            .flatMap(Collection::stream)
            .filter(Square::hasPawn)
            .filter(square -> square.hasSameColor(color))
            .collect(Collectors.groupingBy(Square::getColumn, Collectors.counting()));

        for (long count : pawnCount.values()) {
            if (count >= 2) {
                score -= (double) count / 2;
            }
        }
        return score;
    }
}
