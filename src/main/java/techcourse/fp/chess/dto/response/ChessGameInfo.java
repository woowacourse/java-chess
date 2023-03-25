package techcourse.fp.chess.dto.response;

import java.time.LocalDateTime;

public class ChessGameInfo {


    private final long id;
    private final String name;
    private final String turn;
    private final LocalDateTime createTime;


    public ChessGameInfo(final long id, final String name, final String turn, final LocalDateTime createTime) {
        this.id = id;
        this.name = name;
        this.turn = turn;
        this.createTime = createTime;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTurn() {
        return turn;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }
}
