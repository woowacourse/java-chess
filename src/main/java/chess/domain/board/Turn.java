package chess.domain.board;

import chess.domain.Team;
import chess.domain.pieces.Piece;

public final class Turn {

    static final String INVALID_CONSECUTIVE_MOVE = "다른 팀의 말을 움직일 수 없습니다.";
    static final String INVALID_EMPTY_PIECE_MOVE = "기물을 움직여주세요.";

    private final Team turn;

    public Turn(final Team team) {
        this.turn = team;
    }

    public void validateTurn(final Piece currentPositionPiece) {
        if (currentPositionPiece.isNeutrality()) {
            throw new IllegalArgumentException(INVALID_EMPTY_PIECE_MOVE);
        }
        if (currentPositionPiece.isAlly(this.turn)) {
            return;
        }
        throw new IllegalArgumentException(INVALID_CONSECUTIVE_MOVE);
    }

    public Turn changeTurn() {
        if (this.turn.isWhite()) {
            return new Turn(Team.BLACK);
        }
        return new Turn(Team.WHITE);
    }

    public Team getTurn() {
        return turn;
    }
}
