package chess.dto;

import chess.domain.team.Team;
import chess.view.TeamName;

public class ChessGameDto {

    private final int id;
    private final Team turn;

    private ChessGameDto(final int id, final Team turn) {
        this.id = id;
        this.turn = turn;
    }

    public static ChessGameDto of(final int id, final String turnName) {
        final Team turn = TeamName.findByName(turnName);

        return new ChessGameDto(id, turn);
    }

    public int getId() {
        return id;
    }

    public Team getTurn() {
        return turn;
    }
}
