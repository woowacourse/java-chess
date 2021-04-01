package chess.domain.piece;

import chess.domain.board.ChessBoard;
import chess.domain.board.Position;
import chess.domain.state.TeamColor;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Rook extends PieceOnBoard implements Serializable {

    public Rook(TeamColor teamColor) {
        super(teamColor, PieceInformation.ROOK);
    }

    public Rook(TeamColor teamColor, Position position) {
        super(teamColor, PieceInformation.ROOK, position);
    }

    @Override
    public boolean isMovable(Position source, Position target, ChessBoard chessBoard) {
        Set<Position> candidates = new HashSet<>();

        candidates.addAll(moveCross(source, target, chessBoard));
        return candidates.contains(target);
    }
}
