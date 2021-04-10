package chess.domain.piece;

import chess.domain.pieceinformations.PieceInformation;
import chess.domain.pieceinformations.State;
import chess.domain.pieceinformations.TeamColor;
import chess.domain.position.Moves;
import chess.domain.position.Position;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class King extends PieceOnBoard {
    private static final PieceInformation KING = PieceInformation.KING;

    public King(TeamColor teamColor) {
        super(teamColor, KING);
    }

    public King(TeamColor teamColor, State state) {
        super(teamColor, KING, state);
    }

    @Override
    public boolean isKing() {
        return true;
    }

    @Override
    public boolean isMovable(Position source, Position target, Map<Position, Piece> chessBoard) {
        final Set<Position> candidates = new HashSet<>();
        candidates.add(moveOnce(source, Moves.UP, target, chessBoard));
        candidates.add(moveOnce(source, Moves.DOWN, target, chessBoard));
        candidates.add(moveOnce(source, Moves.LEFT, target, chessBoard));
        candidates.add(moveOnce(source, Moves.RIGHT, target, chessBoard));
        candidates.add(moveOnce(source, Moves.LEFT_UP, target, chessBoard));
        candidates.add(moveOnce(source, Moves.LEFT_DOWN, target, chessBoard));
        candidates.add(moveOnce(source, Moves.RIGHT_UP, target, chessBoard));
        candidates.add(moveOnce(source, Moves.RIGHT_DOWN, target, chessBoard));
        return candidates.contains(target);
    }

}