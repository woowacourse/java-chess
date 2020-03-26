package chess.domain.Piece.blank;

import chess.domain.Piece.Piece;
import chess.domain.Piece.state.Initialized;
import chess.domain.Piece.team.Team;
import chess.domain.board.Board;
import chess.domain.position.Position;

public class Blank extends Initialized {

    public Blank(String name, Position position, Team team) {
        super(name, position, team);
    }

    @Override
    public Piece move(Position to, Board board) {
        return new Blank(name, to, team);
    }

    @Override
    protected boolean canNotMove(Position to, Board board) {
        return false;
    }
}
