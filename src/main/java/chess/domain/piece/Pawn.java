package chess.domain.piece;

import chess.domain.pieceinformations.PieceInformation;
import chess.domain.pieceinformations.TeamColor;
import chess.domain.position.Position;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Pawn extends PieceOnBoard {

    public Pawn(TeamColor teamColor) {
        super(teamColor, PieceInformation.PAWN);
    }

    public Pawn(TeamColor teamColor, Position position) {
        super(teamColor, PieceInformation.PAWN, position);
    }

    @Override
    public boolean isMoveAble(Position target, Map<Position, Piece> chessBoard) {
        final Set<Position> candidates = new HashSet<>();
        candidates.addAll(checkFront(chessBoard));
        candidates.addAll(checkFrontDiagonal(target, chessBoard));
        return candidates.contains(target);
    }

}
