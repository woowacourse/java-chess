package chess.domain.piece;

import chess.domain.attribute.Color;
import chess.domain.attribute.File;
import chess.domain.attribute.Position;
import chess.domain.attribute.Rank;

public class Queen extends SlidingPiece {
    public Queen(final Color color) {
        super(color, Position.of(File.D, Rank.startRankOf(color)));
    }
}
