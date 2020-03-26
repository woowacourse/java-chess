package chess.domain.piece.blank;

import chess.domain.piece.Piece;
import chess.domain.piece.state.Initialized;
import chess.domain.piece.state.Started;
import chess.domain.piece.team.Team;
import chess.domain.board.Board;
import chess.domain.position.Position;

public class Blank extends Started {

    public Blank(String name, Position position, Team team) {
        super(name, position, team);
    }

    @Override
    public Piece move(Position to, Board board) {
        return new Blank(name, to, team);
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
