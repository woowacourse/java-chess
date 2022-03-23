package chess.domain;

import chess.domain.piece.File;
import chess.domain.piece.Rank;

public class Position {
    private final Rank rank;
    private final File file;

    public Position(Rank rank, File file) {
        this.rank = rank;
        this.file = file;
    }
}
