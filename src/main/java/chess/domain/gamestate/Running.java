package chess.domain.gamestate;

import chess.domain.board.Board;
import chess.domain.board.Point;
import chess.domain.board.Team;
import chess.domain.chessgame.PieceMovementRule;
import chess.domain.chessgame.Turn;
import chess.domain.piece.Piece;
import java.util.List;

public class Running implements GameState {

    private static final IllegalArgumentException EXCEPTION =
        new IllegalArgumentException("올바르지 않은 입력입니다.");

    private final Board board;
    private final PieceMovementRule pieceMovementRule;

    public Running(Board board) {
        this.board = board;
        this.pieceMovementRule = new PieceMovementRule(board);
    }

    @Override
    public GameState start() {
        return new Ready(board).start();
    }

    @Override
    public GameState move(Point source, Point destination, Turn turn) {
        if (!pieceMovementRule.canMove(source, destination, turn.now())) {
            throw new IllegalArgumentException("불가능한 이동입니다.");
        }
        boolean isKingDead = board.hasSamePieceTypeAt(destination, Piece.KING);
        Team destinationTeam = board.teamAt(destination);

        board.move(source, destination);
        turn.next();

        return gameStateAfterMovement(isKingDead, destinationTeam);
    }

    private GameState gameStateAfterMovement(boolean isKingDead, Team destinationTeam) {
        if (isKingDead) {
            return new Finished(board, destinationTeam.opposingTeam());
        }
        return this;
    }

    @Override
    public GameState end() {
        return new Finished(board, Team.NONE);
    }

    @Override
    public GameState status() {
        return this;
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public Team winner() {
        throw EXCEPTION;
    }

    @Override
    public List<Point> movablePoints(Point currentPoint, Turn turn) {
        return pieceMovementRule.movablePoints(currentPoint, turn.now());
    }
}
