package chess.domain.piece;

import chess.domain.board.Position;

public class Empty extends Piece {

    private static final String INVALID_PIECE_MESSAGE = "빈칸은 움직일 수 없습니다.";

    private Empty(Team team, Position position) {
        super(team, position);
    }

    public static Piece create(Position position) {
        return new Empty(Team.EMPTY, position);
    }

    @Override
    public boolean canMove(Position targetPosition, Team team) {
        throw new UnsupportedOperationException(INVALID_PIECE_MESSAGE);
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public Piece move(Position targetPosition, Team nowPlayingTeam, Team targetTeam) {
        throw new UnsupportedOperationException(INVALID_PIECE_MESSAGE);
    }
}
