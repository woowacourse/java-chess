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
        initBlank();
    }

    public void initBoard() {
        initBlack();
        initWhite();
    }

    private void initBlank() {
        for (int i = 0; i < 8; i++) {
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

    private void initBlack() {
        getSquare(Position.of("a8")).addPiece(new Rook(Color.BLACK));
        getSquare(Position.of("b8")).addPiece(new Knight(Color.BLACK));
        getSquare(Position.of("c8")).addPiece(new Bishop(Color.BLACK));
        getSquare(Position.of("d8")).addPiece(new Queen(Color.BLACK));
        getSquare(Position.of("e8")).addPiece(new King(Color.BLACK));
        getSquare(Position.of("f8")).addPiece(new Bishop(Color.BLACK));
        getSquare(Position.of("g8")).addPiece(new Knight(Color.BLACK));
        getSquare(Position.of("h8")).addPiece(new Rook(Color.BLACK));

        getSquare(Position.of("a7")).addPiece(new Pawn(Color.BLACK));
        getSquare(Position.of("b7")).addPiece(new Pawn(Color.BLACK));
        getSquare(Position.of("c7")).addPiece(new Pawn(Color.BLACK));
        getSquare(Position.of("d7")).addPiece(new Pawn(Color.BLACK));
        getSquare(Position.of("e7")).addPiece(new Pawn(Color.BLACK));
        getSquare(Position.of("f7")).addPiece(new Pawn(Color.BLACK));
        getSquare(Position.of("g7")).addPiece(new Pawn(Color.BLACK));
        getSquare(Position.of("h7")).addPiece(new Pawn(Color.BLACK));
    }

    private void initWhite() {
        getSquare(Position.of("a1")).addPiece(new Rook(Color.WHITE));
        getSquare(Position.of("b1")).addPiece(new Knight(Color.WHITE));
        getSquare(Position.of("c1")).addPiece(new Bishop(Color.WHITE));
        getSquare(Position.of("d1")).addPiece(new Queen(Color.WHITE));
        getSquare(Position.of("e1")).addPiece(new King(Color.WHITE));
        getSquare(Position.of("f1")).addPiece(new Bishop(Color.WHITE));
        getSquare(Position.of("g1")).addPiece(new Knight(Color.WHITE));
        getSquare(Position.of("h1")).addPiece(new Rook(Color.WHITE));

        getSquare(Position.of("a2")).addPiece(new Pawn(Color.WHITE));
        getSquare(Position.of("b2")).addPiece(new Pawn(Color.WHITE));
        getSquare(Position.of("c2")).addPiece(new Pawn(Color.WHITE));
        getSquare(Position.of("d2")).addPiece(new Pawn(Color.WHITE));
        getSquare(Position.of("e2")).addPiece(new Pawn(Color.WHITE));
        getSquare(Position.of("f2")).addPiece(new Pawn(Color.WHITE));
        getSquare(Position.of("g2")).addPiece(new Pawn(Color.WHITE));
        getSquare(Position.of("h2")).addPiece(new Pawn(Color.WHITE));
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
