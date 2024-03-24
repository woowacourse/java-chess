package chess.domain.piece;

import chess.domain.board.ChessBoard;
import chess.domain.position.DirectionJudge;
import chess.domain.position.Position;

import static chess.domain.piece.Team.BLACK;
import static chess.domain.piece.Team.WHITE;

public class Pawn extends Piece {
    public static final int FORWARDING_SQUARED_DISTANCE = 1;
    public static final int KILL_PASSING_DISTANCE = 2;
    public static final int FIRST_FORWADING_SQUARED_DISTANCE = 4;

    public Pawn(Team team) {
        super(team);
    }

    @Override
    public boolean canMove(Position start, Position destination, ChessBoard board) {
        return isFowardPassing(start, destination, board)
                || isFirstFowardPassing(start, destination, board)
                || isKillPassing(start, destination, board);
    }

    private boolean isFowardPassing(Position start, Position destination, ChessBoard board) {
        return isForward(start, destination)
                && start.squaredDistanceWith(destination) == FORWARDING_SQUARED_DISTANCE
                && board.positionIsEmpty(destination);
    }

    private boolean isFirstFowardPassing(Position start, Position destination, ChessBoard board) {
        return isForward(start, destination)
                && start.squaredDistanceWith(destination) == FIRST_FORWADING_SQUARED_DISTANCE
                && isInitialPawnRow(start)
                && board.pathIsAllEmpty(start.findPath(destination));
    }

    //TODO: 테스트 구현하기
    private boolean isKillPassing(Position start, Position destination, ChessBoard board) {
        return isForward(start, destination)
                && start.squaredDistanceWith(destination) == KILL_PASSING_DISTANCE
                && isOtherTeam(board.findPieceByPosition(destination));
    }

    private boolean isInitialPawnRow(Position start) {
        if (isBlackTeam()) {
            return BLACK.isInitialPawnRow(start);
        }
        return WHITE.isInitialPawnRow(start);
    }

    private boolean isForward(Position start, Position destination) {
        if (isBlackTeam()) {
            return BLACK.isForward(DirectionJudge.judge(start, destination));
        }
        return WHITE.isForward(DirectionJudge.judge(start, destination));
    }
}
