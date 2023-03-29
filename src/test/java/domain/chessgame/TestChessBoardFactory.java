package domain.chessgame;

import domain.chessboard.ChessBoard;
import domain.piece.*;
import domain.position.Position;
import domain.position.PositionFactory;
import domain.squarestatus.SquareStatus;

import java.util.HashMap;
import java.util.Map;

public class TestChessBoardFactory {

    private static final Map<Position, SquareStatus> chessBoard;

    static {
        chessBoard = new HashMap<>();

        chessBoard.put(PositionFactory.createPosition("G5"), new King(Color.BLACK));
        chessBoard.put(PositionFactory.createPosition("C8"), new Rook(Color.BLACK));
        chessBoard.put(PositionFactory.createPosition("A7"), new Pawn(Color.BLACK));
        chessBoard.put(PositionFactory.createPosition("C7"), new Pawn(Color.BLACK));
        chessBoard.put(PositionFactory.createPosition("D7"), new Bishop(Color.BLACK));
        chessBoard.put(PositionFactory.createPosition("B6"), new Pawn(Color.BLACK));
        chessBoard.put(PositionFactory.createPosition("E6"), new Queen(Color.BLACK));

        chessBoard.put(PositionFactory.createPosition("F4"), new Knight(Color.WHITE));
        chessBoard.put(PositionFactory.createPosition("G4"), new Queen(Color.WHITE));
        chessBoard.put(PositionFactory.createPosition("F3"), new Pawn(Color.WHITE));
        chessBoard.put(PositionFactory.createPosition("H3"), new Pawn(Color.WHITE));
        chessBoard.put(PositionFactory.createPosition("F2"), new Pawn(Color.WHITE));
        chessBoard.put(PositionFactory.createPosition("G2"), new Pawn(Color.WHITE));
        chessBoard.put(PositionFactory.createPosition("E1"), new Rook(Color.WHITE));
        chessBoard.put(PositionFactory.createPosition("F1"), new King(Color.WHITE));


    }

    public static ChessBoard generate() {
        return new ChessBoard(new HashMap<>(chessBoard));
    }

}
