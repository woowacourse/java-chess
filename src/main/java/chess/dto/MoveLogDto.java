package chess.dto;

public class MoveLogDto {

    private final int id;
    private final int chessGameId;
    private final int sourcePositionId;
    private final int targetPositionId;

    private MoveLogDto(final int id, final int chessGameId, final int sourcePositionId, final int targetPositionId) {
        this.id = id;
        this.chessGameId = chessGameId;
        this.sourcePositionId = sourcePositionId;
        this.targetPositionId = targetPositionId;
    }

    public static MoveLogDto of(final int id, final int chessGameId, final int sourcePositionId, final int targetPositionId) {
        return new MoveLogDto(id, chessGameId, sourcePositionId, targetPositionId);
    }

    public int getId() {
        return id;
    }

    public int getChessGameId() {
        return chessGameId;
    }

    public int getSourcePositionId() {
        return sourcePositionId;
    }

    public int getTargetPositionId() {
        return targetPositionId;
    }
}
