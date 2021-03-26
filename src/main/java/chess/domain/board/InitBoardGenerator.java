package chess.domain.board;

import chess.domain.board.position.Position;
import chess.domain.board.position.Xpoint;
import chess.domain.board.position.Ypoint;
import chess.domain.piece.Piece;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class InitBoardGenerator {
    private InitBoardGenerator() {
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
            line.put(Position.of(xpoint, ypoint), InitPieces.findPiece(xpoint, ypoint));
        }
        return line;
    }
}
