package chess.domain.board;

import chess.domain.board.position.Position;
import chess.domain.board.position.Xpoint;
import chess.domain.board.position.Ypoint;
import chess.domain.piece.Empty;
import chess.domain.piece.Piece;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BoardGenerator {
    private final List<Map<Position, Piece>> squares;

    public BoardGenerator() {
        squares = initLines();
    }

    public void put(Position position, Piece piece) {
        squares.get(8 - position.yValue()).replace(position, piece);
    }

    public static List<Map<Position, Piece>> initLines() {
        List<Map<Position, Piece>> lines = new ArrayList<>();
        for (Ypoint ypoint : Ypoint.values()) {
            lines.add(initLine(ypoint));
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

    public List<Map<Position, Piece>> create() {
        return squares;
    }

    public void print() {
        for (Map<Position, Piece> square : squares) {
            for (Piece value : square.values()) {
                System.out.print(value.getSymbol());
            }
            System.out.println();
        }
    }
}
