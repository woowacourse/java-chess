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
        List<Piece> pieces = logicExistPiece(currentPosition, targetPosition);
        Direction movableDirection = Direction.findDirection(currentPosition, targetPosition);

        currentPiece.checkDirection(movableDirection);
        currentPiece.checkEachPiece(currentPosition, movableDirection, pieces);
        currentPiece.checkCanMove(board.get(currentPosition), pieces);
        move(currentPosition, targetPosition);
    }

    private List<Piece> logicExistPiece(final Position current, final Position target) {
        List<Piece> pieces = new ArrayList<>();
        Position nextPosition = current;

        do {
            nextPosition = moveNextPosition(nextPosition, target);
            pieces.add(board.get(nextPosition));
        } while (!nextPosition.equals(target));
        return List.copyOf(pieces);
    }

    private void move(final Position currentPosition, final Position targetPosition) {
        Piece currentPointPiece = board.get(currentPosition);
        board.put(currentPosition, new EmptyPiece(NEUTRALITY));
        board.put(targetPosition, currentPointPiece);
    }

    private Position moveNextPosition(final Position currentPosition, final Position targetPosition) {
        int rankGap = targetPosition.getRank() - currentPosition.getRank();
        int fileGap = targetPosition.getFile() - currentPosition.getFile();

        if (rankGap != 0) {
            rankGap = rankGap / Math.abs(targetPosition.getRank() - currentPosition.getRank());
        }
        if (fileGap != 0) {
            fileGap = fileGap / Math.abs(targetPosition.getFile() - currentPosition.getFile());
        }
        return currentPosition.nextPosition(rankGap, fileGap);
    }

    public Map<Position, Piece> getBoard() {
        return this.board;
    }
}
