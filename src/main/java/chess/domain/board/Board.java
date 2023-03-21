package chess.domain.board;

import static chess.domain.Team.NEUTRALITY;

import chess.domain.Position;
import chess.domain.math.Direction;
import chess.domain.math.UnitVector;
import chess.domain.pieces.Bishop;
import chess.domain.pieces.EmptyPiece;
import chess.domain.pieces.King;
import chess.domain.pieces.Pawn;
import chess.domain.pieces.Piece;
import chess.domain.pieces.Queen;
import chess.domain.pieces.Rook;
import java.util.List;

public final class Board {

    private final List<Rank> board;

    public Board(final BoardMaker boardMaker) {
        this.board = boardMaker.createBoard();
    }

    public void movePiece(final Position current, final Position target) {
        Piece currentPointPiece = findPiece(current);
        Direction legalDirection = Direction.findDirection(current, target);

        currentPointPiece.validateDirection(legalDirection);
        currentPointPiece.validateSameTeam(findPiece(target));

        runLogic(current, target, legalDirection);
    }

    private Piece findPiece(final Position position) {
        Rank rank = this.board.get(position.getRow());
        Square square = rank.findSquare(position.getColumn());

        return square.getPiece();
    }

    private void runLogic(final Position current, final Position target, final Direction legalDirection) {
        Piece currentPointPiece = findPiece(current);

        if (currentPointPiece instanceof King) {
            currentPointPiece.validateDistance(current, target);
        }

        if (currentPointPiece instanceof Pawn) {
            currentPointPiece.validateDirection(legalDirection);

            Pawn pawn = (Pawn) currentPointPiece;

            pawn.validateDistance(current, target);
            pawn.validateExistPiece(findPiece(target));

            pawn.move();
        }

        if (currentPointPiece instanceof Rook || currentPointPiece instanceof Bishop || currentPointPiece instanceof Queen) {
            checkExistPiece(current, target, UnitVector.of(current, target));
        }

        move(current, target);
    }

    private void checkExistPiece(final Position current, final Position target, final UnitVector unitVector) {
        Position pieceFinder = new Position(current).move(unitVector);

        while (!pieceFinder.equals(target)) {
            Rank findedRank = board.get(pieceFinder.getRow());
            findedRank.validatePiece(pieceFinder.getColumn());
            pieceFinder = pieceFinder.move(unitVector);
        }
    }

    private void move(final Position current, final Position target) {
        Piece currentPointPiece = board.get(current.getRow()).findPiece(current.getColumn());
        Rank targetRank = board.get(target.getRow());

        Square replacedSquare = targetRank.replacePiece(target.getColumn(), currentPointPiece);
        targetRank.replaceSquare(target.getColumn(), replacedSquare);

        targetRank.replaceSquare(target.getColumn(), replacedSquare);

        Rank currentRank = board.get(current.getRow());
        Square newCurrentSquare = currentRank.replacePiece(current.getColumn(), new EmptyPiece(NEUTRALITY));
        currentRank.replaceSquare(current.getColumn(), newCurrentSquare);
    }

    public List<Rank> getBoard() {
        return List.copyOf(board);
    }
}
