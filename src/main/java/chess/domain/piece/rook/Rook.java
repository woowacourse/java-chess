package chess.domain.piece.rook;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.piece.move.CanNotMoveStrategy;
import chess.domain.piece.state.Initialized;
import chess.domain.piece.team.Team;
import chess.domain.position.Position;

import java.util.List;

public class Rook extends Initialized {
    private static final int LINE_START_INDEX = 1;
    private static final int LINE_END_INDEX = 8;

    public Rook(String name, Position position, Team team, List<CanNotMoveStrategy> canNotMoveStrategies) {
        super(name, position, team, canNotMoveStrategies);
    }

    @Override
    public Piece move(Position to, Board board) {
        return new Rook(name, to, team, canNotMoveStrategies);
    }

    @Override
    public boolean hasHindrance(Position to, Board board) {
        if (hasHindranceVertically(getStart(position.getY(), to.getY()), getEnd(position.getY(), to.getY()), position, board)) {
            return true;
        }

        return hasHindranceHorizontally(getStart(position.getX(), to.getX()), getEnd(position.getX(), to.getX()), position, board);


    }

    private boolean hasHindranceVertically(int start, int end, Position from, Board board) {
        for (int i = start; i <= end; i++) {
            if (hasHindranceVertically(i, from, board)) {
                return true;
            }
        }

        return false;
    }

    private boolean hasHindranceHorizontally(int start, int end, Position from, Board board) {
        for (int i = start; i <= end; i++) {
            if (hasHindranceHorizontally(i, from, board)) {
                return true;
            }
        }

        return false;
    }

    private int getEnd(int from, int to) {
        return Math.max(from, to) - 1;
    }

    private int getStart(int from, int to) {
        return Math.min(from, to) + 1;
    }

    private boolean hasHindranceVertically(int y, Position from, Board board) {
        Position position = Position.of(from.getX(), y);
        Piece piece = board.getPiece(position);
        return piece.isNotBlank();
    }

    private boolean hasHindranceHorizontally(int x, Position from, Board board) {
        Position position = Position.of(x, from.getY());
        Piece piece = board.getPiece(position);
        return piece.isNotBlank();
    }
}
