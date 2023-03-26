package chess.view;

import chess.domain.piece.Empty;
import chess.domain.piece.PieceType;
import chess.domain.piece.Team;

import java.util.Arrays;

public enum TeamMapper {

    WHITE(Team.WHITE, "흰색팀"),
    BLACK(Team.BLACK, "검은팀"),
    EMPTY(Team.EMPTY, "없음");

    private static final String INVALID_TEAM_MESSAGE = "잘못된 팀을 입력했습니다.";
    private final Team team;
    private final String teamView;

    TeamMapper(Team team, String teamView) {
        this.team = team;
        this.teamView = teamView;
    }

    public static TeamMapper from(Team team) {
        return Arrays.stream(values())
                .filter(it -> it.team == team)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_TEAM_MESSAGE));
    }

    public String getTeamView() {
        return teamView;
    }
}
