package chess.domain.piece;

import chess.domain.pieceinformations.PieceInformation;
import chess.domain.pieceinformations.State;
import chess.domain.pieceinformations.TeamColor;
import chess.domain.position.Position;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Queen extends PieceOnBoard {
    private static final PieceInformation QUEEN = PieceInformation.QUEEN;

    public Queen(TeamColor teamColor) {
        super(teamColor, QUEEN);
    }

    public Queen(TeamColor teamColor, State state) {
        super(teamColor, QUEEN, state);
    }


    @Override
    public boolean isMovable(Position source, Position target, Map<Position, Piece> chessBoard) {
        final Set<Position> candidates = new HashSet<>();
        candidates.addAll(moveDiagonalAsPossible(source, target, chessBoard));
        candidates.addAll(moveCrossAsPossible(source, target, chessBoard));
        return candidates.contains(target);
    }

}
