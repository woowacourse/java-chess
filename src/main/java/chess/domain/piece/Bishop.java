package chess.domain.piece;

import chess.domain.ChessBoard;
import chess.domain.Position;
import chess.domain.TeamColor;
import java.util.HashSet;
import java.util.Set;

public class Bishop extends PieceOnBoard {
    private Position position;

    public Bishop(TeamColor teamColor) {
        super(teamColor, PieceInformation.BISHOP);
    }

    public Bishop(TeamColor teamColor, Position position) {
        super(teamColor, PieceInformation.BISHOP);
        this.position = position;
    }

    @Override
    public boolean isMoveAble(Position source, Position target, ChessBoard chessBoard) {
        Set<Position> candidates = new HashSet<>();

        Position position = source.moveLeftDown();
        while (movable(position, target, chessBoard)) {
            candidates.add(position);
            position = position.moveLeftDown();
        }
        position = source.moveLeftUp();
        while (movable(position, target, chessBoard)) {
            candidates.add(position);
            position = position.moveLeftUp();
        }
        position = source.moveRightDown();
        while (movable(position, target, chessBoard)) {
            candidates.add(position);
            position = position.moveRightDown();
        }
        position = source.moveRightUp();
        while (movable(position, target, chessBoard)) {
            candidates.add(position);
            position = position.moveRightUp();
        }

        return candidates.contains(target);
    }
}
