package chess.domain.piece;

import chess.domain.pieceinformations.PieceInformation;
import chess.domain.pieceinformations.State;
import chess.domain.pieceinformations.TeamColor;
import chess.domain.position.Moves;
import chess.domain.position.Position;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Knight extends PieceOnBoard {
    private static final PieceInformation KNIGHT = PieceInformation.KNIGHT;

    public Knight(TeamColor teamColor) {
        super(teamColor, KNIGHT);
    }

    public Knight(TeamColor teamColor, State state) {
        super(teamColor, KNIGHT, state);
    }

    @Override
    public boolean isMovable(Position source, Position target, Map<Position, Piece> chessBoard) {
        final Set<Position> candidates = new HashSet<>();

        candidates.add(moveOnce(source, Moves.LEFT_DOWN_DOWN, target, chessBoard));
        candidates.add(moveOnce(source, Moves.LEFT_UP_LEFT, target, chessBoard));
        candidates.add(moveOnce(source, Moves.LEFT_DOWN_LEFT, target, chessBoard));
        candidates.add(moveOnce(source, Moves.LEFT_UP_UP, target, chessBoard));
        candidates.add(moveOnce(source, Moves.RIGHT_DOWN_DOWN, target, chessBoard));
        candidates.add(moveOnce(source, Moves.RIGHT_DOWN_RIGHT, target, chessBoard));
        candidates.add(moveOnce(source, Moves.RIGHT_UP_RIGHT, target, chessBoard));
        candidates.add(moveOnce(source, Moves.RIGHT_UP_UP, target, chessBoard));

        return candidates.contains(target);
    }

}
