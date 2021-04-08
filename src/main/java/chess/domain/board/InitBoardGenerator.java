package chess.domain.board;

import chess.domain.board.position.Position;
import chess.domain.board.position.Xpoint;
import chess.domain.board.position.Ypoint;
import chess.domain.piece.Piece;

import java.util.LinkedHashMap;
import java.util.Map;

public class InitBoardGenerator {
    private InitBoardGenerator() {
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
            line.put(Position.of(xpoint, ypoint), InitPieces.findPiece(xpoint, ypoint));
        }
        return line;
    }
}
