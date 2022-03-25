package chess;

import static chess.position.File.*;
import static chess.position.Rank.*;

import chess.piece.*;
import chess.position.Position;
import java.util.*;

public class ChessGame {

    private ChessBoard chessBoard;
    private Color currentColor;

    public ChessGame() {
        chessBoard = new ChessBoard(List.of(
            new Rook(Color.BLACK, new Position(A, EIGHT)),
            new Knight(Color.BLACK, new Position(B, EIGHT)),
            new Bishop(Color.BLACK, new Position(C, EIGHT)),
            new Queen(Color.BLACK, new Position(D, EIGHT)),
            new King(Color.BLACK, new Position(E, EIGHT)),
            new Bishop(Color.BLACK, new Position(F, EIGHT)),
            new Knight(Color.BLACK, new Position(G, EIGHT)),
            new Rook(Color.BLACK, new Position(H, EIGHT)),
            new Pawn(Color.BLACK, new Position(A, SEVEN)),
            new Pawn(Color.BLACK, new Position(B, SEVEN)),
            new Pawn(Color.BLACK, new Position(C, SEVEN)),
            new Pawn(Color.BLACK, new Position(D, SEVEN)),
            new Pawn(Color.BLACK, new Position(E, SEVEN)),
            new Pawn(Color.BLACK, new Position(F, SEVEN)),
            new Pawn(Color.BLACK, new Position(G, SEVEN)),
            new Pawn(Color.BLACK, new Position(H, SEVEN)),
            new Rook(Color.WHITE, new Position(A, ONE)),
            new Knight(Color.WHITE, new Position(B, ONE)),
            new Bishop(Color.WHITE, new Position(C, ONE)),
            new Queen(Color.WHITE, new Position(D, ONE)),
            new King(Color.WHITE, new Position(E, ONE)),
            new Bishop(Color.WHITE, new Position(F, ONE)),
            new Knight(Color.WHITE, new Position(G, ONE)),
            new Rook(Color.WHITE, new Position(H, ONE)),
            new Pawn(Color.WHITE, new Position(A, TWO)),
            new Pawn(Color.WHITE, new Position(B, TWO)),
            new Pawn(Color.WHITE, new Position(C, TWO)),
            new Pawn(Color.WHITE, new Position(D, TWO)),
            new Pawn(Color.WHITE, new Position(E, TWO)),
            new Pawn(Color.WHITE, new Position(F, TWO)),
            new Pawn(Color.WHITE, new Position(G, TWO)),
            new Pawn(Color.WHITE, new Position(H, TWO))));
        this.currentColor = Color.WHITE;
    }

    public ChessGame(List<Piece> pieces, Color color) {
        this.chessBoard = new ChessBoard(pieces);
        this.currentColor = color;
    }

    public void move(Position from, Position to) {
        if (isUnmovablePieceColor(from) || isSamePosition(from, to)) {
            throw new IllegalArgumentException();
        }

        chessBoard = chessBoard.transfer(from, to);
        currentColor = currentColor.reverse();
    }

    private boolean isUnmovablePieceColor(Position from) {
        return !chessBoard.isSameColorByPosition(from, currentColor);
    }

    private boolean isSamePosition(Position from, Position to) {
        return from.equals(to);
    }

    public List<Piece> getPieces() {
        return new ArrayList<>(chessBoard.getPieces());
    }
}
