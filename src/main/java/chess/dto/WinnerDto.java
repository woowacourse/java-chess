package chess.dto;

public class WinnerDto {
    private final String winnerMsg;

    public WinnerDto(String winnerMsg) {
        this.winnerMsg = winnerMsg;
    }

    public String getWinnerMsg() {
        return "alert(\"" + winnerMsg+ "이 승리했습니다." + "\")";
    }
}
