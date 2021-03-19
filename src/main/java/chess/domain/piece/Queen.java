package chess.domain.piece;

import chess.domain.ChessBoard;
import chess.domain.Position;
import chess.domain.TeamColor;
import java.util.HashSet;
import java.util.Set;

public class Queen extends PieceOnBoard {

    private static final String NAME = "q";
    private Position position;

    public Queen(TeamColor teamColor) {
        super(teamColor, NAME);
    }


    public Queen(TeamColor teamColor, Position position) {
        super(teamColor, NAME);
        this.position = position;
    }


    @Override
    public boolean isMoveAble(Position source, Position target, ChessBoard chessBoard) {
        Set<Position> candidates = new HashSet<>();

        Position position = source.moveUp();
        while (movable(position, target, chessBoard)) {
            candidates.add(position);
            position = position.moveUp();

        }
        position = source.moveDown();
        while (movable(position, target, chessBoard)) {
            candidates.add(position);
            position = position.moveDown();
        }
        position = source.moveLeft();
        while (movable(position, target, chessBoard)) {
            candidates.add(position);
            position = position.moveLeft();
        }
        position = source.moveRight();
        while (movable(position, target, chessBoard)) {
            candidates.add(position);
            position = position.moveRight();
        }
        position = source.moveLeftDown();
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
