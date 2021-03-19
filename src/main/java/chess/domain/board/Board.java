package chess.domain.board;

import chess.controller.direction.Direction;
import chess.domain.board.position.Horizontal;
import chess.domain.board.position.Position;
import chess.domain.board.position.Vertical;
import chess.domain.piece.Empty;
import chess.domain.piece.Piece;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Board {
    private final Map<Position, Piece> board;

    public Board(Map<Position, Piece> board) {
        this.board = board;
    }

    public Piece of(Position position) {
        return board.get(position);
    }

    public Piece of(Vertical vertical, Horizontal horizontal) {
        return of(new Position(vertical, horizontal));
    }

    public Map<Position, Piece> getBoard() {
        return board;
    }

    public void move(Position source, Position target) {
        List<Position> ablePositions = ableToMove(source, target);
        if (ablePositions.contains(target)) {
            movePiece(source, target);
        }
    }

    private void movePiece(Position source, Position target) {
        putPiece(source, target);
        putEmpty(source);
    }

    private void putPiece(Position source, Position target) {
        board.put(target, board.get(source));
    }

    private void putEmpty(Position position) {
        board.put(position, Empty.getInstance());
    }

    private List<Position> ableToMove(Position source, Position target) {
        List<Position> ableToMove = new ArrayList<>();
        Piece sourcePiece = of(source);


        for (Direction direction : sourcePiece.getDirections()) {
            for (int i = 0; i < sourcePiece.getMaxDistance(); i++) {
                Position nextPosition;
                try {
                    nextPosition = source.next(direction, i);
                } catch (IllegalArgumentException e) {
                    break;
                }
                Piece nextPiece = of(nextPosition);

                if (sourcePiece.isSameTeam(nextPiece)) {
                    break;
                }

                if (sourcePiece.validateMove(source, nextPosition, nextPiece)) {
                    ableToMove.add(nextPosition);
                }

                //

                if (sourcePiece.isEnemy(nextPiece)) {
                    break;
                }
            }
        }

        return ableToMove;
    }
}
