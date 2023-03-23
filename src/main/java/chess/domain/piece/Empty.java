package chess.domain.piece;

import chess.domain.board.Position;

public class Empty extends Piece {

    private static final String INVALID_PIECE_MESSAGE = "빈칸은 움직일 수 없습니다.";

    private Empty(Team team) {
        super(team);
    }

    public static Piece create() {
        return new Empty(Team.EMPTY);
    }

    @Override
    public boolean canMove(Position sourcePosition, Position targetPosition, Team team) {
        throw new UnsupportedOperationException(INVALID_PIECE_MESSAGE);
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public Piece move(Position sourcePosition, Position targetPosition, Team nowPlayingTeam, Team targetTeam) {
        throw new UnsupportedOperationException(INVALID_PIECE_MESSAGE);
    }
}
