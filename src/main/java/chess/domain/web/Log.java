package chess.domain.web;

/**
 * Log 클래스는 DB에 저장되는 값을 모아둔 클래스이다.
 * DB에서 LogDao 클래스가 값을 가져오면, 그 값은 Log 클래스로 가공된다.
 * DB에는 움직임에 대한 명령만 기록하기 때문에, 시작값과 끝값 외의 값은 없다.
 */
public class Log {
    private final String start;
    private final String end;

    public Log(String start, String end) {
        this.start = start;
        this.end = end;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }
}