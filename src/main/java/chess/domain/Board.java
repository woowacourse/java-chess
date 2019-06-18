package chess.domain;

import chess.domain.Piece;
import chess.domain.Position;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {
    private static Map<Position, Piece> pieces = new HashMap<>();

    static {
        pieces.put(Position.valueOf("a1"),new Rook(Aliance.WHITE));
        pieces.put(Position.valueOf("b1"),new Knight(Aliance.WHITE));
        pieces.put(Position.valueOf("c1"),new Bishop(Aliance.WHITE));
        pieces.put(Position.valueOf("d1"),new Queen(Aliance.WHITE));
        pieces.put(Position.valueOf("e1"),new King(Aliance.WHITE));
        pieces.put(Position.valueOf("f1"),new Bishop(Aliance.WHITE));
        pieces.put(Position.valueOf("g1"),new Knight(Aliance.WHITE));
        pieces.put(Position.valueOf("h1"),new Rook(Aliance.WHITE));

        pieces.put(Position.valueOf("a8"),new Rook(Aliance.BLACK));
        pieces.put(Position.valueOf("b8"),new Knight(Aliance.BLACK));
        pieces.put(Position.valueOf("c8"),new Bishop(Aliance.BLACK));
        pieces.put(Position.valueOf("d8"),new Queen(Aliance.BLACK));
        pieces.put(Position.valueOf("e8"),new King(Aliance.BLACK));
        pieces.put(Position.valueOf("f8"),new Bishop(Aliance.BLACK));
        pieces.put(Position.valueOf("g8"),new Knight(Aliance.BLACK));
        pieces.put(Position.valueOf("h8"),new Rook(Aliance.BLACK));

        List<Position> whitePawnPositions = Position.getRow("2");
        List<Position> blackPawnPositions = Position.getRow("7");

        for(Position whitePawnPosition : whitePawnPositions){
            pieces.put(whitePawnPosition,new Pawn(Aliance.WHITE));
        }

        for(Position blackPawnPosition : blackPawnPositions){
            pieces.put(blackPawnPosition,new Pawn(Aliance.BLACK));
        }

    }

    public static Piece pieceValueOf(String position) {
        return pieces.get(Position.valueOf(position));
    }
}
