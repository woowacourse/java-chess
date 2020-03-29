package chess.state;

import chess.domain.Board;
import chess.domain.Team;
import chess.domain.piece.*;
import chess.domain.position.Position;

import java.util.HashMap;
import java.util.Map;

public class BoardRepository {
    private static final Map<Position, Piece> initialBoard = new HashMap<>();

    static {
        initialBoard.put(Position.of("a2"), NotMovedPawn.of(Team.WHITE, Position.of("a2")));
        initialBoard.put(Position.of("b2"), NotMovedPawn.of(Team.WHITE, Position.of("b2")));
        initialBoard.put(Position.of("c2"), NotMovedPawn.of(Team.WHITE, Position.of("c2")));
        initialBoard.put(Position.of("d2"), NotMovedPawn.of(Team.WHITE, Position.of("d2")));
        initialBoard.put(Position.of("e2"), NotMovedPawn.of(Team.WHITE, Position.of("e2")));
        initialBoard.put(Position.of("f2"), NotMovedPawn.of(Team.WHITE, Position.of("f2")));
        initialBoard.put(Position.of("g2"), NotMovedPawn.of(Team.WHITE, Position.of("g2")));
        initialBoard.put(Position.of("h2"), NotMovedPawn.of(Team.WHITE, Position.of("h2")));
        initialBoard.put(Position.of("a1"), Rook.of(Team.WHITE, Position.of("a1")));
        initialBoard.put(Position.of("b1"), Knight.of(Team.WHITE, Position.of("b1")));
        initialBoard.put(Position.of("c1"), Bishop.of(Team.WHITE, Position.of("c1")));
        initialBoard.put(Position.of("d1"), Queen.of(Team.WHITE, Position.of("d1")));
        initialBoard.put(Position.of("e1"), King.of(Team.WHITE, Position.of("e1")));
        initialBoard.put(Position.of("f1"), Bishop.of(Team.WHITE, Position.of("f1")));
        initialBoard.put(Position.of("g1"), Knight.of(Team.WHITE, Position.of("g1")));
        initialBoard.put(Position.of("h1"), Rook.of(Team.WHITE, Position.of("h1")));
        initialBoard.put(Position.of("a7"), NotMovedPawn.of(Team.BLACK, Position.of("a7")));
        initialBoard.put(Position.of("b7"), NotMovedPawn.of(Team.BLACK, Position.of("b7")));
        initialBoard.put(Position.of("c7"), NotMovedPawn.of(Team.BLACK, Position.of("c7")));
        initialBoard.put(Position.of("d7"), NotMovedPawn.of(Team.BLACK, Position.of("d7")));
        initialBoard.put(Position.of("e7"), NotMovedPawn.of(Team.BLACK, Position.of("e7")));
        initialBoard.put(Position.of("f7"), NotMovedPawn.of(Team.BLACK, Position.of("f7")));
        initialBoard.put(Position.of("g7"), NotMovedPawn.of(Team.BLACK, Position.of("g7")));
        initialBoard.put(Position.of("h7"), NotMovedPawn.of(Team.BLACK, Position.of("h7")));
        initialBoard.put(Position.of("a8"), Rook.of(Team.BLACK, Position.of("a8")));
        initialBoard.put(Position.of("b8"), Knight.of(Team.BLACK, Position.of("b8")));
        initialBoard.put(Position.of("c8"), Bishop.of(Team.BLACK, Position.of("c8")));
        initialBoard.put(Position.of("d8"), Queen.of(Team.BLACK, Position.of("d8")));
        initialBoard.put(Position.of("e8"), King.of(Team.BLACK, Position.of("e8")));
        initialBoard.put(Position.of("f8"), Bishop.of(Team.BLACK, Position.of("f8")));
        initialBoard.put(Position.of("g8"), Knight.of(Team.BLACK, Position.of("g8")));
        initialBoard.put(Position.of("h8"), Rook.of(Team.BLACK, Position.of("h8")));
    }

    public static Board create() {
        return new Board(initialBoard);
    }
}
