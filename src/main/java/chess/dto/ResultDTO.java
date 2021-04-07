package chess.dto;

public final class ResultDTO implements Comparable {
    private final String nickname;
    private final int winCount;
    private final int loseCount;

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
