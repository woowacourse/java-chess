package chess.domain.piece.piecefigure;

import chess.domain.board.Position;
import chess.domain.board.PositionChecker;
import chess.domain.piece.pieceinfo.PieceType;
import chess.domain.piece.pieceinfo.TeamType;

import java.util.HashSet;
import java.util.Set;

public class Blank extends Piece {
    private static final Piece BLANK = new Blank();

    private Blank() {
        super(TeamType.NEUTRAL, PieceType.BLANK);
    }

    public static Piece of() {
        return BLANK;
    }

    @Override
    public Set<Position> makePossiblePositions(Position source, PositionChecker positionChecker) {
        return new HashSet<>();
    }
}
