package chess.domain.piece;

import chess.domain.ChessBoard;
import chess.domain.pieceinformations.TeamColor;
import chess.domain.pieceinformations.PieceInformation;
import chess.domain.position.Moves;
import chess.domain.position.Position;
import java.util.HashSet;
import java.util.Set;

public class Queen extends PieceOnBoard {

    public Queen(TeamColor teamColor) {
        super(teamColor, PieceInformation.QUEEN);
    }


    public Queen(TeamColor teamColor, Position position) {
        super(teamColor, PieceInformation.QUEEN, position);
    }

    @Override
    public boolean isMoveAble(Position target, ChessBoard chessBoard) {
        Set<Position> candidates = new HashSet<>();

        candidates.addAll(moveAsPossible(Moves.LEFT_DOWN,target, chessBoard));
        candidates.addAll(moveAsPossible(Moves.LEFT_UP,target, chessBoard));
        candidates.addAll(moveAsPossible(Moves.RIGHT_DOWN,target, chessBoard));
        candidates.addAll(moveAsPossible(Moves.RIGHT_UP,target, chessBoard));
        candidates.addAll(moveAsPossible(Moves.UP,target, chessBoard));
        candidates.addAll(moveAsPossible(Moves.DOWN, target, chessBoard));
        candidates.addAll(moveAsPossible(Moves.RIGHT,target, chessBoard));
        candidates.addAll(moveAsPossible(Moves.LEFT, target, chessBoard));

        return candidates.contains(target);
    }

}
