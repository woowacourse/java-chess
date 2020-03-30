package chess.domain.piece.blank;

import chess.domain.piece.Piece;
import chess.domain.piece.state.Started;
import chess.domain.piece.team.Team;
import chess.domain.board.Board;
import chess.domain.position.Position;

public class Blank extends Started {

    private Blank(String name, Team team) {
        super(name, team);
    }

    public static Blank of() {
        return new Blank(".", Team.NOT_ASSIGNED);
    }


    @Override
    public Piece move(Position to, Board board) {
        throw new IllegalStateException(String.format("해당 자리 %s가 비어있습니다.", to));
    }

    @Override
    public boolean isNotBlank() {
        return false;
    }

    @Override
    public boolean isBlank() {
        return true;
    }
}
