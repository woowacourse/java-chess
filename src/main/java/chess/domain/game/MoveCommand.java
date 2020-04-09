package chess.domain.game;

/**
 * MoveCommand 클래스는 DB에 저장되는 값을 모아둔 클래스이다.
 * DB에서 HistoryDao 클래스가 값을 가져오면, 그 값은 MoveCommand 클래스로 가공된다.
 * DB에는 움직임에 대한 명령만 기록하기 때문에, 시작값과 끝값 외의 값은 없다.
 */
public class MoveCommand {
    private final String first;
    private final String second;

    public MoveCommand(String start, String end) {
        this.first = start;
        this.second = end;
    }

    public String getFirstCommand() {
        return first;
    }

    public String getSecondCommand() {
        return second;
    }
}