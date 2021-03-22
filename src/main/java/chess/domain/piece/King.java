package chess.domain.piece;

import chess.domain.board.ChessBoard;
import chess.domain.board.Position;
import chess.domain.state.TeamColor;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class King extends PieceOnBoard {

    public King(TeamColor teamColor) {
        super(teamColor, PieceInformation.KING);
    }

    public King(TeamColor teamColor, Position position) {
        super(teamColor, PieceInformation.KING, position);
    }

    @Override
    public boolean isMoveAble(Position source, Position target, ChessBoard chessBoard) {
        Set<Position> candidates = new HashSet<>();
        candidates.addAll(moveDiagonal(source, target, chessBoard));
        candidates.addAll(moveCross(source, target, chessBoard));

        Set<Position> distanceOneCandidates = candidates.stream()
            .filter(position -> position.isDistanceOne(source))
            .collect(Collectors.toSet());

        return distanceOneCandidates.contains(target);
    }
}
