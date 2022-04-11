package chess.domain.board;

import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.HashMap;
import java.util.Map;

public class BasicChessBoardGenerator {

    public static Board generator() {
        Map<Position, Piece> board = new HashMap<>();

        board.put(Position.valueOf("a8"), new Rook(Color.BLACK));
        board.put(Position.valueOf("b8"), new Knight(Color.BLACK));
        board.put(Position.valueOf("c8"), new Bishop(Color.BLACK));
        board.put(Position.valueOf("d8"), new Queen(Color.BLACK));
        board.put(Position.valueOf("e8"), new King(Color.BLACK));
        board.put(Position.valueOf("f8"), new Bishop(Color.BLACK));
        board.put(Position.valueOf("g8"), new Knight(Color.BLACK));
        board.put(Position.valueOf("h8"), new Rook(Color.BLACK));

        board.put(Position.valueOf("a7"), Pawn.of(Color.BLACK));
        board.put(Position.valueOf("b7"), Pawn.of(Color.BLACK));
        board.put(Position.valueOf("c7"), Pawn.of(Color.BLACK));
        board.put(Position.valueOf("d7"), Pawn.of(Color.BLACK));
        board.put(Position.valueOf("e7"), Pawn.of(Color.BLACK));
        board.put(Position.valueOf("f7"), Pawn.of(Color.BLACK));
        board.put(Position.valueOf("g7"), Pawn.of(Color.BLACK));
        board.put(Position.valueOf("h7"), Pawn.of(Color.BLACK));

        board.put(Position.valueOf("a1"), new Rook(Color.WHITE));
        board.put(Position.valueOf("b1"), new Knight(Color.WHITE));
        board.put(Position.valueOf("c1"), new Bishop(Color.WHITE));
        board.put(Position.valueOf("d1"), new Queen(Color.WHITE));
        board.put(Position.valueOf("e1"), new King(Color.WHITE));
        board.put(Position.valueOf("f1"), new Bishop(Color.WHITE));
        board.put(Position.valueOf("g1"), new Knight(Color.WHITE));
        board.put(Position.valueOf("h1"), new Rook(Color.WHITE));

        board.put(Position.valueOf("a2"), Pawn.of(Color.WHITE));
        board.put(Position.valueOf("b2"), Pawn.of(Color.WHITE));
        board.put(Position.valueOf("c2"), Pawn.of(Color.WHITE));
        board.put(Position.valueOf("d2"), Pawn.of(Color.WHITE));
        board.put(Position.valueOf("e2"), Pawn.of(Color.WHITE));
        board.put(Position.valueOf("f2"), Pawn.of(Color.WHITE));
        board.put(Position.valueOf("g2"), Pawn.of(Color.WHITE));
        board.put(Position.valueOf("h2"), Pawn.of(Color.WHITE));

        return new Board(board);
    }
}
