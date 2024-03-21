package chess.domain.piece;

import chess.domain.Board;
import chess.domain.PieceColor;
import chess.domain.PieceType;
import chess.domain.Position;

import java.util.Map;

public class Knight extends Piece {

    public Knight(PieceColor color) {
        super(PieceType.KNIGHT, color);
    }

    @Override
    public boolean canMove(Position source, Position target) {
        return getDeltaFile(source.file(), target.file()) == 1 && getDeltaRank(source.rank(), target.rank()) == 2 ||
                getDeltaFile(source.file(), target.file()) == 2 && getDeltaRank(source.rank(), target.rank()) == 1;
    }
}
