package chess.dto;

public class ResultDTO implements Comparable {
    private String nickname;
    private int winCount;
    private int loseCount;

    public ResultDTO(String nickname, int winCount, int loseCount) {
        this.nickname = nickname;
        this.winCount = winCount;
        this.loseCount = loseCount;
    }

    public String getNickname() {
        return nickname;
    }

    public int getWinCount() {
        return winCount;
    }

    public int getLoseCount() {
        return loseCount;
    }

    @Override
    public int compareTo(Object o) {
        ResultDTO resultDTO = (ResultDTO) o;
        return Integer.compare(resultDTO.winCount, this.winCount);
    }
}
