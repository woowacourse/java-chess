package chess.domain.piece;

import chess.domain.pieceinformations.PieceInformation;
import chess.domain.pieceinformations.State;
import chess.domain.pieceinformations.TeamColor;
import chess.domain.position.Position;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Pawn extends PieceOnBoard {
    public Pawn(TeamColor teamColor) {
        super(teamColor, PieceInformation.PAWN);
    }

    public Pawn(TeamColor teamColor, State state) {
        super(teamColor, PieceInformation.PAWN, state);
    }

    @Override
    public boolean isPawn() {
        return true;
    }

    @Override
    public boolean isMovable(Position source, Position target, Map<Position, Piece> chessBoard) {
        final Set<Position> candidates = new HashSet<>();
        candidates.addAll(checkFront(source, chessBoard));
        candidates.addAll(checkFrontDiagonal(source, target, chessBoard));
        return candidates.contains(target);
    }

}
