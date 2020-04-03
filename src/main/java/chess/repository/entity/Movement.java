package chess.repository.entity;

public class Movement extends BaseEntity implements Comparable<Movement> {

    private Long chessId;
    private String sourceKey;
    private String targetKey;

    public Movement(Long autoIncrement, Movement entity) {
        super(autoIncrement);
        this.chessId = entity.getChessId();
        this.sourceKey = entity.sourceKey;
        this.targetKey = entity.targetKey;
    }

    public Movement(Long chessId, String sourceKey, String targetKey) {
        this.chessId = chessId;
        this.sourceKey = sourceKey;
        this.targetKey = targetKey;
    }

    public boolean equalGameId(Long chessId) {
        return this.getChessId().equals(chessId);
    }

    public Long getChessId() {
        return chessId;
    }

    public String getSourceKey() {
        return sourceKey;
    }

    public String getTargetKey() {
        return targetKey;
    }

    @Override
    public int compareTo(Movement o) {
        return super.getCreatedTime().compareTo(o.getCreatedTime());
    }
}
