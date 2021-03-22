package chess.domain.piece;

import chess.domain.ChessBoard;
import chess.domain.pieceinformations.PieceInformation;
import chess.domain.position.Moves;
import chess.domain.position.Position;
import chess.domain.pieceinformations.TeamColor;
import java.util.HashSet;
import java.util.Set;

public class King extends PieceOnBoard {

    public King(TeamColor teamColor) {
        super(teamColor, PieceInformation.KING);
    }

    public King(TeamColor teamColor, Position position) {
        super(teamColor, PieceInformation.KING, position);
    }

    @Override
    public boolean isMoveAble(Position target, ChessBoard chessBoard) {
        Set<Position> candidates = new HashSet<>();
        candidates.add(moveOnce(Moves.UP, target, chessBoard));
        candidates.add(moveOnce(Moves.DOWN, target, chessBoard));
        candidates.add(moveOnce(Moves.LEFT, target, chessBoard));
        candidates.add(moveOnce(Moves.RIGHT, target, chessBoard));
        candidates.add(moveOnce(Moves.LEFT_UP, target, chessBoard));
        candidates.add(moveOnce(Moves.LEFT_DOWN, target, chessBoard));
        candidates.add(moveOnce(Moves.RIGHT_UP, target, chessBoard));
        candidates.add(moveOnce(Moves.RIGHT_DOWN, target, chessBoard));
        return candidates.contains(target);
    }
}
