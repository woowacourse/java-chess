package chess.domain.piece;

import chess.domain.pieceinformations.PieceInformation;
import chess.domain.pieceinformations.TeamColor;
import chess.domain.position.Position;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Queen extends PieceOnBoard {

    public Queen(TeamColor teamColor) {
        super(teamColor, PieceInformation.QUEEN);
    }


    public Queen(TeamColor teamColor, Position position) {
        super(teamColor, PieceInformation.QUEEN, position);
    }

    @Override
    public boolean isMoveAble(Position target, Map<Position, Piece> chessBoard) {
        final Set<Position> candidates = new HashSet<>();
        candidates.addAll(moveDiagonalAsPossible(target, chessBoard));
        candidates.addAll(moveCrossAsPossible(target, chessBoard));
        return candidates.contains(target);
    }

}
