package chess.domain.board;

import chess.domain.Position;
import chess.domain.math.Direction;
import chess.domain.pieces.*;

import java.util.Map;

import static chess.domain.Team.NEUTRALITY;

public class Board {

    private final Map<Position, Piece> board;

    private Board(Map<Position, Piece> board) {
        this.board = board;
    }

    public static Board create() {
        return new Board(BoardFactory.create());
    }

    public void movePiece(Position current, Position target) {
        Piece currentPointPiece = findPiece(current);
        Direction currentToTargetDirection = Direction.findDirection(current, target);

        if (currentPointPiece.hasDirection(currentToTargetDirection)) {
            runLogic(current, target, currentToTargetDirection);
            return;
        }
        throw new RuntimeException("[ERROR] 패턴에서 걸러진 예외");
    }

    private void runLogic(final Position current, final Position target, final Direction movableDirection) {
        Piece currentPointPiece = findPiece(current);

        if (currentPointPiece instanceof Knight) {
            validateTargetPieceSameTeam(current, target);
        }

        if (currentPointPiece instanceof King) {
            checkKingLogic(currentPointPiece, current, target);
        }

        if (currentPointPiece instanceof Pawn) {
            checkPawnLogic(currentPointPiece, current, target, movableDirection);
        }

        if (currentPointPiece instanceof Rook || currentPointPiece instanceof Bishop || currentPointPiece instanceof Queen) {
            checkExistPiece(current, target);
            validateTargetPieceSameTeam(current, target);
        }
        move(current, target);
    }

    private void checkKingLogic(Piece currentPointPiece, Position current, Position target) {
        if (!((King) currentPointPiece).canMoveStep(current, target)) {
            throw new RuntimeException("킹은 한칸만 움직일 수 있습니다.");
        }
        validateTargetPieceSameTeam(current, target);
    }

    private void checkPawnLogic(Piece currentPointPiece, Position current, Position target, Direction movableDirection) {
        if (currentPointPiece instanceof Pawn) {
            if (!((Pawn) currentPointPiece).canMoveStep(current, target)) {
                throw new RuntimeException("폰이 갈 수 없는 거리입니다.");
            }
            if (((Pawn) currentPointPiece).isUpOrDown(movableDirection)) {
                checkExistPiece(current, target);
                validateTargetPieceEmpty(target);
            }
            if (!((Pawn) currentPointPiece).isUpOrDown(movableDirection)) {
                validateTargetPieceSameTeam(current, target);
            }
        }
    }


    private void validateTargetPieceSameTeam(final Position current, final Position target) {
        if (findPiece(current).getTeam() == findPiece(target).getTeam()) {
            throw new RuntimeException("[ERROR] 같은 팀이 존재하므로 이동할 수 없습니다.");
        }
    }

    private void validateTargetPieceEmpty(final Position target) {
        if (findPiece(target).getTeam() != NEUTRALITY) {
            throw new RuntimeException("[ERROR] 빈 자리가 아니므로 이동할 수 없습니다.");
        }
    }

    private void checkExistPiece(final Position current, final Position target) {
        Position nextPosition = moveNextPosition(current, target);

        while (!nextPosition.equals(target)) {
            if (!findPiece(nextPosition).getName().getName().equals(".")) {
                throw new RuntimeException("[ERROR] 가는 경로 도중에 다른 기물 존재");
            }
            nextPosition = moveNextPosition(current, target);
        }
    }

    private void move(final Position current, final Position target) {
        Piece currentPointPiece = findPiece(current);
        board.put(current, new EmptyPiece(NEUTRALITY));
        board.put(target, currentPointPiece);
    }

    public Position moveNextPosition(final Position current, final Position target) {
        int rankGap = Math.abs(target.getRank() - current.getRank());
        int fileGap = Math.abs(target.getFile() - current.getFile());
        return current.nextPosition(rankGap, fileGap);
    }

    private Piece findPiece(final Position position) {
        return board.get(position);
    }

    public Map<Position, Piece> getBoard() {
        return this.board;
    }
}
