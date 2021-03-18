package chess.domain.board;

import chess.domain.piece.*;
import chess.domain.board.position.Position;
import chess.domain.board.position.Xpoint;
import chess.domain.board.position.Ypoint;

import java.util.*;

public class InitBoardGenerator {

    private InitBoardGenerator(){}

    public static List<Map<Position, Piece>> initLines(){
        List<Map<Position, Piece>> lines = new ArrayList<>();
        for (Ypoint ypoint : Ypoint.values()) {
            lines.add(initLine(ypoint));
        }
        return lines;
    }

    private static Map<Position, Piece> initLine(final Ypoint ypoint) {
        Map<Position, Piece> line = new LinkedHashMap<>();
        for (Xpoint xpoint : Xpoint.values()) {
            line.put(Position.valueOf(xpoint, ypoint), InitPieces.findPiece(xpoint, ypoint));
        }
        return line;
    }
}
