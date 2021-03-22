package chess.domain.board;

import chess.domain.Side;
import chess.domain.piece.Blank;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Rook;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;
import java.util.HashMap;
import java.util.Map;

public class BoardTestInitializer {

    public static Map<Position, Piece> init() {
        Map<Position, Piece> result = new HashMap<>();

        for (Row row : Row.values()) {
            setBlank(result, row);
        }

        result.put(Position.from("h8"), new Rook(Side.BLACK));
        result.put(Position.from("h7"), new Pawn(Side.BLACK));
        result.put(Position.from("h7"), new Pawn(Side.BLACK));
        result.put(Position.from("f6"), new King(Side.BLACK));
        result.put(Position.from("d5"), new Knight(Side.WHITE));
        result.put(Position.from("f3"), new King(Side.WHITE));
        result.put(Position.from("h2"), new Pawn(Side.WHITE));

//        .......R  8
//        .......P  7
//        .....K.P  6
//        ...n....  5
//        ........  4
//        .....k..  3
//        .......p  2
//        ........  1
//
//        abcdefgh

        return result;
    }

    private static void setBlank(Map<Position, Piece> result, Row row) {
        for (Column column : Column.values()) {
            result.put(new Position(column, row), Blank.getBlank());
        }
    }
}
