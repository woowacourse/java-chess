package chess.domain.piece;

import static chess.domain.attribute.File.E;

import chess.domain.attribute.Color;
import chess.domain.attribute.File;
import chess.domain.attribute.Position;
import chess.domain.attribute.Rank;

public class King extends UnslidingPiece {

    public King(final Color color, final PieceType pieceType) {
        super(color, pieceType);
    }
}
