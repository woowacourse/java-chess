package chess.domain;

import chess.domain.piece.*;
import chess.domain.square.Square;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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

    public void updateChessBoard(Map<Square, Piece> updatedChessBoard) {
        chessBoard = updatedChessBoard;
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
        return chessBoard.get(before).calculateMovableSquares(before, chessBoard).contains(after);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChessBoard that = (ChessBoard) o;
        return Objects.equals(chessBoard, that.chessBoard);
    }

    @Override
    public int hashCode() {
        return Objects.hash(chessBoard);
    }
}