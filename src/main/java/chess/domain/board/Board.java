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

    public void movePiece(final Position current, final Position target) {
        validatePositions(current, target);

        Piece currentPositionPiece = findPiece(current);
        Direction legalDirection = Direction.findDirection(current, target);
        UnitVector unitVector = UnitVector.of(current, target);
        List<Piece> onRoutePieces = onRoutePieces(current, target, unitVector);

        currentPositionPiece.validateMove(legalDirection, onRoutePieces);

        move(current, target);
    }

    private void validatePositions(final Position current, final Position target) {
        if (current.equals(target)) {
            throw new IllegalArgumentException("위치가 중복되었습니다.");
        }
    }

    private Piece findPiece(final Position position) {
        Rank rank = this.board.get(position.getRow());
        Square square = rank.findSquare(position.getColumn());

        return square.getPiece();
    }

    private List<Piece> onRoutePieces(final Position current, final Position target, final UnitVector unitVector) {
        List<Piece> foundPieces = new ArrayList<>();

        Position pieceFinder = new Position(current).move(unitVector);
        while (!pieceFinder.equals(target)) {
            foundPieces.add(findPiece(pieceFinder));
            pieceFinder = pieceFinder.move(unitVector);
        }
        foundPieces.add(findPiece(target));

        return foundPieces;
    }

    private void move(final Position current, final Position target) {
        Rank currentRank = board.get(current.getRow());
        Piece currentPositionPiece = currentRank.findPiece(current.getColumn());
        currentRank.replacePiece(current.getColumn(), new EmptyPiece());

        Rank targetRank = board.get(target.getRow());
        targetRank.replacePiece(target.getColumn(), currentPositionPiece);
    }

    public List<Rank> getBoard() {
        return List.copyOf(board);
    }
}
