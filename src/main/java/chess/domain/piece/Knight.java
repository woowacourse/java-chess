package chess.domain.piece;

import chess.domain.board.ChessBoard;
import chess.domain.board.Position;
import chess.domain.state.TeamColor;
import java.util.HashSet;
import java.util.Set;

public class Knight extends PieceOnBoard {

    public Knight(TeamColor teamColor) {
        super(teamColor, PieceInformation.KNIGHT);
    }

    public Knight(TeamColor teamColor, Position position) {
        super(teamColor, PieceInformation.KNIGHT, position);
    }

    @Override
    public boolean isMovable(Position source, Position target, ChessBoard chessBoard) {

        Set<Position> candidates = new HashSet<>();

        candidates.addAll(knightMoveUp(source, target, chessBoard));
        candidates.addAll(knightMoveDown(source, target, chessBoard));
        candidates.addAll(knightMoveLeft(source, target, chessBoard));
        candidates.addAll(knightMoveRight(source, target, chessBoard));
        return candidates.contains(target);

    }

    private Set<Position> knightMoveUp(Position source, Position target, ChessBoard chessBoard) {
        Set<Position> candidates = new HashSet<>();

        Position position = source.moveUp().moveLeftUp();
        if (movable(position, target, chessBoard)) {
            candidates.add(position);
        }
        position = source.moveUp().moveRightUp();
        if (movable(position, target, chessBoard)) {
            candidates.add(position);
        }
        return candidates;
    }

    private Set<Position> knightMoveRight(Position source, Position target, ChessBoard chessBoard) {
        Set<Position> candidates = new HashSet<>();

        Position position = source.moveRight().moveRightUp();
        if (movable(position, target, chessBoard)) {
            candidates.add(position);
        }
        position = source.moveRight().moveRightDown();
        if (movable(position, target, chessBoard)) {
            candidates.add(position);
        }
        return candidates;
    }

    private Set<Position> knightMoveDown(Position source, Position target, ChessBoard chessBoard) {
        Set<Position> candidates = new HashSet<>();

        Position position = source.moveDown().moveLeftDown();
        if (movable(position, target, chessBoard)) {
            candidates.add(position);
        }
        position = source.moveDown().moveRightDown();
        if (movable(position, target, chessBoard)) {
            candidates.add(position);
        }
        return candidates;
    }

    private Set<Position> knightMoveLeft(Position source, Position target, ChessBoard chessBoard) {
        Set<Position> candidates = new HashSet<>();

        Position position = source.moveLeft().moveLeftUp();
        if (movable(position, target, chessBoard)) {
            candidates.add(position);
        }
        position = source.moveLeft().moveLeftDown();
        if (movable(position, target, chessBoard)) {
            candidates.add(position);
        }
        return candidates;
    }

}
