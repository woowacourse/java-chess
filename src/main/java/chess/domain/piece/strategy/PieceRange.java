package chess.domain.piece.strategy;

import chess.domain.board.Path;
import chess.domain.board.Paths;
import chess.domain.position.Notation;
import chess.domain.position.Notations;
import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class PieceRange {

    List<Notations> allDirectionRange;

    public PieceRange(List<Notations> allDirectionRange) {
        this.allDirectionRange = allDirectionRange;
    }

    public Paths calculatePaths(final Map<Notation, Position> boardPositions) {
        List<Path> paths = new ArrayList<>();
        // todo
        return new Paths(paths);
    }
}
