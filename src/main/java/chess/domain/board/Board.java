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

public class Board {

    private final List<Rank> board;

    private Board(List<Rank> ranks) {
        this.board = ranks;
    }

    public static Board create() {
        return new Board(BoardMaker.create());
    }

    public void movePiece(Position current, Position target) {
        Piece currentPointPiece = findPiece(current);
        Direction moveableDirection = Direction.findDirection(current, target);

        if (currentPointPiece.hasDirection(moveableDirection)) {
            runLogic(current, target, moveableDirection);
            return;
        }
        throw new IllegalArgumentException("패턴에서 걸러진 예외");
    }

    private void runLogic(final Position current, final Position target, final Direction moveableDirection) {
        Piece currentPointPiece = findPiece(current);

        if (currentPointPiece instanceof Knight) {
            validateSameTeam(current, target);
        }

        if (currentPointPiece instanceof King) {
            if (calculateStep(current, target) != 1) {
                throw new IllegalArgumentException("킹은 1만");
            }
            validateSameTeam(current, target);
        }

        if (currentPointPiece instanceof Pawn) {
            isValidDirection(currentPointPiece, moveableDirection); // 팀에 따라 유효한 방향인지 확인

            Pawn pawn = (Pawn) currentPointPiece;
            int targetStep = calculateStep(current, target);

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
            validateSameTeam(current, target);
        }

        move(current, target);
    }

    private void validateSameTeam(final Position current, final Position target) {
        if (findPiece(current).getTeam() == findPiece(target).getTeam()) {
            throw new IllegalArgumentException("같은 팀이 존재하므로 이동할 수 없습니다.");
        }
    }

    private void isValidDirection(final Piece piece, final Direction moveableDirection) {
        if (piece.getTeam() == Team.BLACK && moveableDirection == Direction.DOWN) {
            return;
        }
        if (piece.getTeam() == Team.WHITE && moveableDirection == Direction.UP) {
            return;
        }

        throw new IllegalArgumentException("폰은 앞으로만 이동할 수 있습니다.");
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
        Position iterator = new Position(current.getRow(), current.getCol()).move(unitVector);

        while (!iterator.equals(target)) {
            Rank iteratorRank = board.get(iterator.getRow());

            if (!iteratorRank.isEmptyPiece(iterator.getCol())) {
                throw new IllegalArgumentException("가는 경로 도중에 다른 기물 존재");
            }
            iterator = iterator.move(unitVector);
        }
    }

    private Piece findPiece(final Position position) {
        Rank rank = board.get(position.getRow());
        Square square = rank.findSquare(position.getCol());

        return square.getPiece();
    }

    private void move(final Position current, final Position target) {
        Piece currentPointPiece = board.get(current.getRow()).findPiece(current.getCol());
        Rank targetRank = board.get(target.getRow());

        Square replacedSquare = targetRank.replacePiece(target.getCol(), currentPointPiece);
        targetRank.replaceSquare(target.getCol(), replacedSquare);

        targetRank.replaceSquare(target.getCol(), replacedSquare);

        Rank currentRank = board.get(current.getRow());
        Square newCurrentSquare = currentRank.replacePiece(current.getCol(), new EmptyPiece(NEUTRALITY));
        currentRank.replaceSquare(current.getCol(), newCurrentSquare);
    }

    private int calculateStep(final Position current, final Position target) {
        int rowDifferent = Math.abs(current.getRow() - target.getRow());
        int colDifferent = Math.abs(current.getCol() - target.getCol());

        return Math.max(rowDifferent, colDifferent);
    }

    public List<Rank> getBoard() {
        return List.copyOf(board);
    }
}
