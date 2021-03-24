package chess.domain.gamestate;

import chess.domain.board.Board;
import chess.domain.board.Point;
import chess.domain.board.Team;
import chess.domain.chessgame.PieceMovementRule;
import chess.domain.chessgame.Turn;
import chess.domain.piece.Piece;

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
        throw EXCEPTION;
    }

    @Override
    public GameState move(Point source, Point destination, Turn turn) {
        if (!pieceMovementRule.canMove(source, destination, turn.now())) {
            throw new IllegalArgumentException("불가능한 이동입니다.");
        }

        return gameStateAfterMove(source, destination, turn);
    }

    private GameState gameStateAfterMove(Point source, Point destination, Turn turn) {

        boolean isKingDead = board.isSamePieceTypeAt(destination, Piece.KING);
        Team destinationTeam = board.teamAt(destination);
        board.move(source, destination);
        turn.next();
        if (isKingDead) {
            return new Finished(destinationTeam.opposingTeam());
        }
        return this;
    }

    @Override
    public GameState end() {
        return new Finished(Team.NONE);
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
}
