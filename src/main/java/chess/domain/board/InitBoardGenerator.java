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

    public static List<Rank> initRanks() {
        List<Rank> ranks = new ArrayList<>();
        for (Ypoint ypoint : Ypoint.values()) {
            ranks.add(initRank(ypoint));
        }
        return ranks;
    }

    private static Rank initRank(final Ypoint ypoint) {
        Map<Position, Piece> line = new LinkedHashMap<>();
        for (Xpoint xpoint : Xpoint.values()) {
            line.put(Position.of(xpoint, ypoint), InitPieces.findPiece(xpoint, ypoint));
        }
        return new Rank(line);
    }
}
