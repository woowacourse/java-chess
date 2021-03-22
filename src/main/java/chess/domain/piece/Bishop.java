package chess.domain.piece;

import chess.domain.ChessBoard;
import chess.domain.position.Moves;
import chess.domain.position.Position;
import chess.domain.TeamColor;
import java.util.HashSet;
import java.util.Set;

public class Bishop extends PieceOnBoard {

    public Bishop(TeamColor teamColor) {
        super(teamColor, PieceInformation.BISHOP);
    }

    public Bishop(TeamColor teamColor, Position position) {
        super(teamColor, PieceInformation.BISHOP, position);
    }

    @Override
    public boolean isMoveAble(Position target, ChessBoard chessBoard) {
        Set<Position> candidates = new HashSet<>();
        candidates.addAll(moveAsPossible(Moves.LEFT_DOWN,target, chessBoard));
        candidates.addAll(moveAsPossible(Moves.LEFT_UP,target, chessBoard));
        candidates.addAll(moveAsPossible(Moves.RIGHT_DOWN,target, chessBoard));
        candidates.addAll(moveAsPossible(Moves.RIGHT_UP,target, chessBoard));
        return candidates.contains(target);
    }
}
