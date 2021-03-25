package chess.domain.piece;

import chess.domain.board.ChessBoard;
import chess.domain.board.Position;
import chess.domain.state.TeamColor;
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
    public boolean isMovable(Position source, Position target, ChessBoard chessBoard) {
        Set<Position> candidates = new HashSet<>();

        candidates.addAll(moveCross(source, target, chessBoard));
        candidates.addAll(moveDiagonal(source, target, chessBoard));

        return candidates.contains(target);
    }
}
