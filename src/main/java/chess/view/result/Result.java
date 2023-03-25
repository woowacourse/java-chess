package chess.view.result;

import chess.domain.piece.Team;

public enum Result {
    BLACK_WIN("블랙 승"),
    WHITE_WIN("화이트 승"),
    DRAW("동점");

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
