package chess.service;

import chess.domain.Board;
import chess.domain.position.Position;
import chess.domain.team.Team;

import java.util.HashMap;

public class ChessService {

    private final Board board;
    private Team turn;

    public ChessService() {
        this.board = Board.create(new HashMap<>());
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
