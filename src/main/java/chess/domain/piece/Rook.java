package chess.domain.piece;

import chess.domain.ChessBoard;
import chess.domain.pieceinformations.PieceInformation;
import chess.domain.pieceinformations.TeamColor;
import chess.domain.position.Moves;
import chess.domain.position.Position;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Rook extends PieceOnBoard {

    public Rook(TeamColor teamColor) {
        super(teamColor, PieceInformation.ROOK);
    }

    public Rook(TeamColor teamColor, Position position) {
        super(teamColor, PieceInformation.ROOK, position);
    }

    @Override
    public boolean isMoveAble(Position target, Map<Position, Piece> chessBoard) {
        Set<Position> candidates = new HashSet<>();
        candidates.addAll(moveAsPossible(Moves.UP, target, chessBoard));
        candidates.addAll(moveAsPossible(Moves.DOWN, target, chessBoard));
        candidates.addAll(moveAsPossible(Moves.RIGHT, target, chessBoard));
        candidates.addAll(moveAsPossible(Moves.LEFT, target, chessBoard));
        return candidates.contains(target);
    }

    @Override
    public boolean isMoveAble(Position target, ChessBoard chessBoard) {
        Set<Position> candidates = new HashSet<>();
        candidates.addAll(moveAsPossible(Moves.UP, target, chessBoard));
        candidates.addAll(moveAsPossible(Moves.DOWN, target, chessBoard));
        candidates.addAll(moveAsPossible(Moves.RIGHT, target, chessBoard));
        candidates.addAll(moveAsPossible(Moves.LEFT, target, chessBoard));
        return candidates.contains(target);
    }


}
