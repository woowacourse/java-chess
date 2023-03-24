package chess.domain.board;

import chess.domain.Position;
import chess.domain.math.Direction;
import chess.domain.math.UnitVector;
import chess.domain.pieces.EmptyPiece;
import chess.domain.pieces.Piece;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Board {

    static final String INVALID_TARGET_POSITION = "위치가 중복되었습니다.";

    private final Map<Position, Piece> board;

    public Board(final BoardMaker boardMaker) {
        this.board = boardMaker.createBoard();
    }

    public void movePiece(final Position currentPosition, final Position targetPosition) {
        validateDuplicatePositions(currentPosition, targetPosition);

        Piece currentPositionPiece = findPieceAt(currentPosition);
        Direction correctDirection = Direction.computeDirection(currentPosition, targetPosition);

        UnitVector unitVector = UnitVector.compute(currentPosition, targetPosition);
        List<Piece> onRoutePieces = getOnRoutePieces(currentPosition, targetPosition, unitVector);

        currentPositionPiece.validateMove(correctDirection, onRoutePieces);

        move(currentPosition, targetPosition);
    }

    private void validateDuplicatePositions(final Position currentPosition, final Position targetPosition) {
        if (currentPosition.equals(targetPosition)) {
            throw new IllegalArgumentException(INVALID_TARGET_POSITION);
        }
    }

    private Piece findPieceAt(final Position position) {
        return board.get(position);
    }

    private List<Piece> getOnRoutePieces(final Position currentPosition, final Position targetPosition, final UnitVector unitVector) {
        List<Piece> foundPieces = new ArrayList<>();

        Position pieceFinder = new Position(currentPosition).move(unitVector);
        while (!pieceFinder.equals(targetPosition)) {
            foundPieces.add(findPieceAt(pieceFinder));
            pieceFinder = pieceFinder.move(unitVector);
        }
        foundPieces.add(findPieceAt(targetPosition));

        return foundPieces;
    }

    private void move(final Position currentPosition, final Position targetPosition) {
        Piece currentPositionPiece = findPieceAt(currentPosition);
        board.replace(targetPosition, currentPositionPiece);
        board.replace(currentPosition, new EmptyPiece());
    }

    public Map<Position, Piece> getBoard() {
        return Map.copyOf(board);
    }
}
