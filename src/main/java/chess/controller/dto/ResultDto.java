package chess.controller.dto;

public class ResultDto {

    private final String winTeam;

    public ResultDto(String winTeam) {
        this.winTeam = winTeam;
    }

    public String getWinTeam() {
        return winTeam;
    }
}
