package chess.domain.board;

import chess.domain.Position;
import chess.domain.math.Direction;
import chess.domain.math.UnitVector;
import chess.domain.pieces.EmptyPiece;
import chess.domain.pieces.Piece;
import java.util.ArrayList;
import java.util.List;

public final class Board {

    private final List<Rank> board;

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

    private void validateDuplicatePositions(final Position current, final Position target) {
        if (current.equals(target)) {
            throw new IllegalArgumentException("위치가 중복되었습니다.");
        }
    }

    private Piece findPieceAt(final Position position) {
        Rank rank = this.board.get(position.getRow());
        Square square = rank.findSquareAt(position.getColumn());

        return square.getPiece();
    }

    private List<Piece> getOnRoutePieces(final Position current, final Position target, final UnitVector unitVector) {
        List<Piece> foundPieces = new ArrayList<>();

        Position pieceFinder = new Position(current).move(unitVector);
        while (!pieceFinder.equals(target)) {
            foundPieces.add(findPieceAt(pieceFinder));
            pieceFinder = pieceFinder.move(unitVector);
        }
        foundPieces.add(findPieceAt(target));

        return foundPieces;
    }

    private void move(final Position current, final Position target) {
        Rank currentRank = board.get(current.getRow());
        Piece currentPositionPiece = currentRank.findPieceAt(current.getColumn());
        currentRank.replacePiece(current.getColumn(), new EmptyPiece());

        Rank targetRank = board.get(target.getRow());
        targetRank.replacePiece(target.getColumn(), currentPositionPiece);
    }

    public List<Rank> getBoard() {
        return List.copyOf(board);
    }
}
