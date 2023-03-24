package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.position.Position;
import chess.domain.team.Team;
import chess.domain.board.initial.BoardFactory;

import java.util.HashMap;

public class ChessGame {

    private final Board board;
    private Team turn;

    public ChessGame() {
        this.board = BoardFactory.from(new HashMap<>());
        this.turn = Team.WHITE;
    }

    public void move(final Position source, final Position target) {
        board.move(source, target, turn);
        turn = turn.reverse();
    }

    public Board getBoard() {
        return board;
    }
}
