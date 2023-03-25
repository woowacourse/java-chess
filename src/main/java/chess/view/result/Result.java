package chess.view.result;

import chess.domain.piece.Team;

public enum Result {
    BLACK_WIN("블랙 팀이 이기고 있습니다"),
    WHITE_WIN("화이트 팀이 이기고 있습니다"),
    DRAW("동점입니다");

    private final String result;

    Result(String result) {
        this.result = result;
    }

    public static String of(Team team) {
        if (Team.BLACK.equals(team)) {
            return BLACK_WIN.result;
        }
        if (Team.WHITE.equals(team)) {
            return WHITE_WIN.result;
        }
        return DRAW.result;
    }
}
