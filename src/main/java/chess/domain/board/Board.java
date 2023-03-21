package chess.domain.board;

import static chess.domain.Team.NEUTRALITY;

import chess.domain.Position;
import chess.domain.Team;
import chess.domain.math.Direction;
import chess.domain.math.UnitVector;
import chess.domain.pieces.Bishop;
import chess.domain.pieces.EmptyPiece;
import chess.domain.pieces.King;
import chess.domain.pieces.Knight;
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
        Direction movableDirection = Direction.findDirection(current, target);

        currentPointPiece.validateDirection(movableDirection);

        runLogic(current, target, movableDirection);
    }

    private Piece findPiece(final Position position) {
        Rank rank = this.board.get(position.getRow());
        Square square = rank.findSquare(position.getColumn());

        return square.getPiece();
    }

    private void runLogic(final Position current, final Position target, final Direction movableDirection) {
        Piece currentPointPiece = findPiece(current);

        if (currentPointPiece instanceof Knight) {
            currentPointPiece.validateSameTeam(findPiece(target));
        }

        if (currentPointPiece instanceof King) {
            currentPointPiece.validateDistance(current, target);
            currentPointPiece.validateSameTeam(findPiece(target));
        }

        if (currentPointPiece instanceof Pawn) {
//            isValidDirection(currentPointPiece, moveableDirection); // 팀에 따라 유효한 방향인지 확인
            currentPointPiece.validateDirection(movableDirection);

            Pawn pawn = (Pawn) currentPointPiece;
            int targetStep = calculateDistance(current, target);

            if (!pawn.isMoved()) { // 처음 움직임
                if ((0 < targetStep && targetStep <= 2) && isEmptyPiece(target)) {
                    checkPieceOfPawn(current, target, currentPointPiece);
                    pawn.move();
                    move(current, target);
                    return;
                }
                throw new IllegalArgumentException("처음인데 2칸 이내 이동 x 또는 해당 위치 기물 존재");
            }

            if (targetStep == 1 && isEmptyPiece(target)) {
                pawn.move();
                move(current, target);
                return;
            }
            throw new IllegalArgumentException("폰은 첫 이동이 아니면 1칸만 가능 또는 해당 위치 기물 존재");
        }

        if (currentPointPiece instanceof Rook || currentPointPiece instanceof Bishop || currentPointPiece instanceof Queen) {
            checkExistPiece(current, target, UnitVector.of(current, target));
            currentPointPiece.validateSameTeam(findPiece(target));
        }

        move(current, target);
    }

    private void checkPieceOfPawn(final Position current, final Position target, final Piece currentPointPiece) {
        if (currentPointPiece.getTeam() == Team.BLACK) {
            checkExistPiece(current, target, UnitVector.DOWN);
            return;
        }
        checkExistPiece(current, target, UnitVector.UP);
    }

    private boolean isEmptyPiece(final Position target) {
        return findPiece(target) instanceof EmptyPiece;
    }
    // current -> target - 1까지 돌면서 기물이 존재하면 예외 터트리는 함수

    private void checkExistPiece(final Position current, final Position target, final UnitVector unitVector) {
        Position iterator = new Position(current.getRow(), current.getColumn()).move(unitVector);

        while (!iterator.equals(target)) {
            Rank iteratorRank = board.get(iterator.getRow());

            if (!iteratorRank.isEmptyPiece(iterator.getColumn())) {
                throw new IllegalArgumentException("가는 경로 도중에 다른 기물 존재");
            }
            iterator = iterator.move(unitVector);
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

    private int calculateDistance(final Position current, final Position target) {
        int rowDifferent = Math.abs(current.getRow() - target.getRow());
        int colDifferent = Math.abs(current.getColumn() - target.getColumn());

        return Math.max(rowDifferent, colDifferent);
    }

    public List<Rank> getBoard() {
        return List.copyOf(board);
    }
}
