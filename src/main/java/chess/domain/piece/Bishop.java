package chess.domain.piece;

import chess.domain.pieceinformations.PieceInformation;
import chess.domain.pieceinformations.State;
import chess.domain.pieceinformations.TeamColor;
import chess.domain.position.Position;

import java.util.Map;
import java.util.Set;

public class Bishop extends PieceOnBoard {
    private static final PieceInformation BISHOP = PieceInformation.BISHOP;

    public Bishop(TeamColor teamColor) {
        super(teamColor, BISHOP);
    }

    public Bishop(TeamColor teamColor, State state) {
        super(teamColor, BISHOP, state);
    }

    @Override
    public boolean isMovable(Position source, Position target, Map<Position, Piece> chessBoard) {
        final Set<Position> candidates = moveDiagonalAsPossible(source, target, chessBoard);
        return candidates.contains(target);
    }

}
