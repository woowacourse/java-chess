package chess.domain.piece;

import static chess.domain.attribute.File.E;

import chess.domain.attribute.Color;
import chess.domain.attribute.File;
import chess.domain.attribute.Position;
import chess.domain.attribute.Rank;

public class King extends UnslidingPiece {

    private static final File START_FILE = E;

    public King(final Color color) {
        super(color, Position.of(START_FILE, Rank.startRankOf(color)));
    }
}
