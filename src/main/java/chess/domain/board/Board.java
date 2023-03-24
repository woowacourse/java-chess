package chess.domain.board;

import chess.domain.Direction;
import chess.domain.pieces.EmptyPiece;
import chess.domain.pieces.Piece;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static chess.domain.pieces.component.Team.NEUTRALITY;

public class Board {

    private final Map<Position, Piece> board;

    private Board(Map<Position, Piece> board) {
        this.board = board;
    }

    public static Board create() {
        return new Board(BoardFactory.create());
    }

    public void movePiece(Position currentPosition, Position targetPosition) {
        Piece currentPiece = board.get(currentPosition);
        Direction movableDirection = Direction.findDirection(currentPosition, targetPosition);
        List<Piece> pieces = getLogicExistPiece(currentPosition, targetPosition, movableDirection);

        currentPiece.checkDirection(movableDirection);
        currentPiece.checkStep(currentPosition, movableDirection, pieces);
        currentPiece.checkExistPiece(pieces);
        currentPiece.checkSameTeam(currentPiece, board.get(targetPosition));
        move(currentPosition, targetPosition);
    }

    private List<Piece> getLogicExistPiece(final Position current, final Position target, final Direction movableDirection) {
        List<Piece> pieces = new ArrayList<>();
        if (movableDirection == Direction.KNIGHT) {
            return pieces;
        }

        Position nextPosition = current;
        do {
            nextPosition = moveNextPosition(nextPosition, movableDirection);
            pieces.add(board.get(nextPosition));
        } while (!nextPosition.equals(target));
        return List.copyOf(pieces);
    }

    private void move(final Position currentPosition, final Position targetPosition) {
        Piece currentPointPiece = board.get(currentPosition);
        board.put(currentPosition, new EmptyPiece(NEUTRALITY));
        board.put(targetPosition, currentPointPiece);
    }

    private Position moveNextPosition(final Position currentPosition, final Direction movableDirection) {
        return currentPosition.nextPosition(movableDirection.getRank(), movableDirection.getFile());
    }

    public Map<Position, Piece> getBoard() {
        return this.board;
    }

}
