package chess;

import static chess.position.File.*;
import static chess.position.Rank.*;

import chess.piece.*;
import chess.position.Position;
import java.util.*;

public class ChessGame {

    private final ChessBoard chessBoard;
    private Color currentColor;

    public ChessGame() {
        chessBoard = new ChessBoard(Set.of(
            new Square(new Rook(Color.BLACK), new Position(A, EIGHT)),
            new Square(new Knight(Color.BLACK), new Position(B, EIGHT)),
            new Square(new Bishop(Color.BLACK), new Position(C, EIGHT)),
            new Square(new Queen(Color.BLACK), new Position(D, EIGHT)),
            new Square(new King(Color.BLACK), new Position(E, EIGHT)),
            new Square(new Bishop(Color.BLACK), new Position(F, EIGHT)),
            new Square(new Knight(Color.BLACK), new Position(G, EIGHT)),
            new Square(new Rook(Color.BLACK), new Position(H, EIGHT)),
            new Square(new Pawn(Color.BLACK), new Position(A, SEVEN)),
            new Square(new Pawn(Color.BLACK), new Position(B, SEVEN)),
            new Square(new Pawn(Color.BLACK), new Position(C, SEVEN)),
            new Square(new Pawn(Color.BLACK), new Position(D, SEVEN)),
            new Square(new Pawn(Color.BLACK), new Position(E, SEVEN)),
            new Square(new Pawn(Color.BLACK), new Position(F, SEVEN)),
            new Square(new Pawn(Color.BLACK), new Position(G, SEVEN)),
            new Square(new Pawn(Color.BLACK), new Position(H, SEVEN)),
            new Square(new Rook(Color.WHITE), new Position(A, ONE)),
            new Square(new Knight(Color.WHITE), new Position(B, ONE)),
            new Square(new Bishop(Color.WHITE), new Position(C, ONE)),
            new Square(new Queen(Color.WHITE), new Position(D, ONE)),
            new Square(new King(Color.WHITE), new Position(E, ONE)),
            new Square(new Bishop(Color.WHITE), new Position(F, ONE)),
            new Square(new Knight(Color.WHITE), new Position(G, ONE)),
            new Square(new Rook(Color.WHITE), new Position(H, ONE)),
            new Square(new Pawn(Color.WHITE), new Position(A, TWO)),
            new Square(new Pawn(Color.WHITE), new Position(B, TWO)),
            new Square(new Pawn(Color.WHITE), new Position(C, TWO)),
            new Square(new Pawn(Color.WHITE), new Position(D, TWO)),
            new Square(new Pawn(Color.WHITE), new Position(E, TWO)),
            new Square(new Pawn(Color.WHITE), new Position(F, TWO)),
            new Square(new Pawn(Color.WHITE), new Position(G, TWO)),
            new Square(new Pawn(Color.WHITE), new Position(H, TWO))));
        this.currentColor = Color.WHITE;
    }

    public ChessGame(Set<Square> squares, Color color) {
        this.chessBoard = new ChessBoard(squares);
        this.currentColor = color;
    }

    public void move(Position from, Position to) {
        if (isSamePosition(from, to)) {
            throw new IllegalArgumentException(String.format(
                "같은 위치(%s)로 기물을 이동할 수 없습니다.", from));
        }

        if (isUnmovablePieceColor(from)) {
            throw new IllegalArgumentException(String.format(
                "%s에 위치한 기물은 %s 색깔이 아닙니다.", from, currentColor));
        }

        chessBoard.move(from, to);
        currentColor = currentColor.reverse();
    }

    private boolean isUnmovablePieceColor(Position from) {
        return !chessBoard.isSameColorByPosition(from, currentColor);
    }


    private boolean isSamePosition(Position from, Position to) {
        return from.equals(to);
    }

    public List<Square> getSquare() {
        return new ArrayList<>(chessBoard.getSquares());
    }
}
