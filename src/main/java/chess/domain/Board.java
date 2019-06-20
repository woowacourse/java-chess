package chess.domain;

import chess.domain.piece.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {
    private Map<Position, Piece> pieces = new HashMap<>();
    private Aliance thisTurn = Aliance.WHITE;

    public Board() {
        initBoard();
    }

    public void initBoard() {
        pieces.put(Position.valueOf("a1"), new Rook(Aliance.WHITE));
        pieces.put(Position.valueOf("b1"), new Knight(Aliance.WHITE));
        pieces.put(Position.valueOf("c1"), new Bishop(Aliance.WHITE));
        pieces.put(Position.valueOf("d1"), new Queen(Aliance.WHITE));
        pieces.put(Position.valueOf("e1"), new King(Aliance.WHITE));
        pieces.put(Position.valueOf("f1"), new Bishop(Aliance.WHITE));
        pieces.put(Position.valueOf("g1"), new Knight(Aliance.WHITE));
        pieces.put(Position.valueOf("h1"), new Rook(Aliance.WHITE));

        pieces.put(Position.valueOf("a8"), new Rook(Aliance.BLACK));
        pieces.put(Position.valueOf("b8"), new Knight(Aliance.BLACK));
        pieces.put(Position.valueOf("c8"), new Bishop(Aliance.BLACK));
        pieces.put(Position.valueOf("d8"), new Queen(Aliance.BLACK));
        pieces.put(Position.valueOf("e8"), new King(Aliance.BLACK));
        pieces.put(Position.valueOf("f8"), new Bishop(Aliance.BLACK));
        pieces.put(Position.valueOf("g8"), new Knight(Aliance.BLACK));
        pieces.put(Position.valueOf("h8"), new Rook(Aliance.BLACK));

        List<Position> whitePawnPositions = Position.getRowPositions("2");
        List<Position> blackPawnPositions = Position.getRowPositions("7");

        for (Position whitePawnPosition : whitePawnPositions) {
            pieces.put(whitePawnPosition, new Pawn(Aliance.WHITE));
        }

        for (Position blackPawnPosition : blackPawnPositions) {
            pieces.put(blackPawnPosition, new Pawn(Aliance.BLACK));
        }

    }

    public Piece pieceValueOf(String position) {
        return pieces.get(Position.valueOf(position));
    }

    public void checkValidPosition(String position) {
        if (Position.valueOf(position) == null) {
            throw new IllegalArgumentException("유효하지 않은 위치입니다.");
        }
    }

    public Aliance switchTurn() {
        if (thisTurn == Aliance.WHITE) {
            thisTurn = Aliance.BLACK;
            return thisTurn;
        }
        thisTurn = Aliance.WHITE;
        return thisTurn;
    }

    public void checkOccupiedPosition(String position) {
        if (pieceValueOf(position) == null) {
            throw new IllegalArgumentException("해당 위치에 말이 없습니다.");
        }

    }

    public void checkProperTeam(Piece piece) {
        if (piece.isDifferentTeam(thisTurn)) {
            throw new IllegalArgumentException("상대팀 말은 움직일 수 없습니다.");
        }
    }
}
