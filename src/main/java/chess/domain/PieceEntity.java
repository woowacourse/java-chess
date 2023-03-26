package chess.domain;

public class PieceEntity {
    private final String position;
    private final String pieceType;
    private final String camp;
    private final Long boardId;

    public PieceEntity(String position, String pieceType, String camp, Long boardId) {
        this.position = position;
        this.pieceType = pieceType;
        this.camp = camp;
        this.boardId = boardId;
    }

    public String getPosition() {
        return position;
    }

    public String getPieceType() {
        return pieceType;
    }

    public String getCamp() {
        return camp;
    }

    public Long getBoardId() {
        return boardId;
    }
}
