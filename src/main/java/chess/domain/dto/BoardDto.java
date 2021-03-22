package chess.domain.dto;

import chess.domain.board.Board;
import chess.domain.board.Team;

public class BoardDto {
    private final Board board;
    private final Team team;

    public BoardDto(final Board board, final Team team) {
        this.board = board;
        this.team = team;
    }

    public Board board() {
        return board;
    }

    public String team() {
        return team.team();
    }
}
