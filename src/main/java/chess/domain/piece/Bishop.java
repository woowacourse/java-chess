package chess.domain.piece;

import chess.domain.pieceinformations.PieceInformation;
import chess.domain.pieceinformations.State;
import chess.domain.pieceinformations.TeamColor;
import chess.domain.position.Position;

import java.util.Map;
import java.util.Set;

public class Bishop extends PieceOnBoard {
    public Bishop(TeamColor teamColor) {
        super(teamColor, PieceInformation.BISHOP);
    }

    public Bishop(TeamColor teamColor, Position position) {
        super(teamColor, PieceInformation.BISHOP, position);
    }

    public Bishop(TeamColor teamColor, Position position, State state) {
        super(teamColor, PieceInformation.BISHOP, position, state);
    }

    @Override
    public boolean isMovable(Position target, Map<Position, Piece> chessBoard) {
        final Set<Position> candidates = moveDiagonalAsPossible(target, chessBoard);
        return candidates.contains(target);
    }

}
