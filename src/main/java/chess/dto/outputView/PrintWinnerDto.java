package chess.dto.outputView;

import chess.domain.piece.Team;

public final class PrintWinnerDto {

    private final Team winnerTeam;

    public PrintWinnerDto(final Team winnerTeam) {
        this.winnerTeam = winnerTeam;
    }

    public Team getWinnerTeam() {
        return winnerTeam;
    }
}
