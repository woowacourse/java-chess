package chess.domain.board;

import chess.domain.board.position.Position;
import chess.domain.board.position.Xpoint;
import chess.domain.board.position.Ypoint;
import chess.domain.piece.Empty;
import chess.domain.piece.Piece;

import java.util.LinkedHashMap;
import java.util.Map;

public class BoardGenerator {
    private final Map<Position, Piece> squares;

    public BoardGenerator() {
        squares = initLines();
    }

    public void put(Position position, Piece piece) {
        squares.replace(position, piece);
    }

    public static Map<Position, Piece> initLines() {
        Map<Position, Piece> lines = new LinkedHashMap<>();
        for (Ypoint ypoint : Ypoint.values()) {
            lines.putAll(initLine(ypoint));
        }
        return lines;
    }

    private static Map<Position, Piece> initLine(final Ypoint ypoint) {
        Map<Position, Piece> line = new LinkedHashMap<>();
        for (Xpoint xpoint : Xpoint.values()) {
            line.put(Position.of(xpoint, ypoint), Empty.create());
        }
        return line;
    }

    public Map<Position, Piece> create() {
        return squares;
    }

    public void print() {
        for (Ypoint ypoint : Ypoint.values()) {
            for (Xpoint xpoint : Xpoint.values()) {
                System.out.print(squares.get(Position.of(xpoint, ypoint)).getSymbol());
            }
            System.out.println();
        }
    }
}
