package model;

public class SquareInfo {

    private final BoardInfo boardInfo;
    private final Camp camp;

    public SquareInfo(BoardInfo boardInfo, Camp camp) {
        this.boardInfo = boardInfo;
        this.camp = camp;
    }

    public BoardInfo getBoardInfo() {
        return boardInfo;
    }

    public Camp getCamp() {
        return camp;
    }

    @Override
    public String toString() {
        if (boardInfo == BoardInfo.BLANK) {
            return ".";
        }
        return boardInfo.toString();
    }
}
