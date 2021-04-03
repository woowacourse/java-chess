package chess.domain.piece;

import chess.domain.pieceinformations.PieceInformation;
import chess.domain.pieceinformations.State;
import chess.domain.pieceinformations.TeamColor;
import chess.domain.position.Position;

import java.util.Map;
import java.util.Set;

public class Rook extends PieceOnBoard {

    public Rook(TeamColor teamColor) {
        super(teamColor, PieceInformation.ROOK);
    }

    public Rook(TeamColor teamColor, Position position) {
        super(teamColor, PieceInformation.ROOK, position);
    }

    public Rook(TeamColor teamColor, Position position, State state) {
        super(teamColor, PieceInformation.BISHOP, position, state);
    }

    @Override
    public boolean isMovable(Position target, Map<Position, Piece> chessBoard) {
        Set<Position> candidates = moveCrossAsPossible(target, chessBoard);
        return candidates.contains(target);
    }


}
