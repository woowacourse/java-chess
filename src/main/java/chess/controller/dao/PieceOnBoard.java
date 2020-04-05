package chess.controller.dao;

import java.util.Objects;

public class PieceOnBoard {
    private int pieceId;
    private String position;
    private String pieceImageUrl;
    private int chessBoardId;

    public PieceOnBoard(int pieceId, String position, String pieceImageUrl, int chessBoardId) {
        this.pieceId = pieceId;
        this.position = position;
        this.pieceImageUrl = pieceImageUrl;
        this.chessBoardId = chessBoardId;
    }

    public int getPieceId() {
        return pieceId;
    }

    public String getPosition() {
        return position;
    }

    public String getPieceImageUrl() {
        return this.pieceImageUrl;
    }

    public int getChessBoardId() {
        return chessBoardId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PieceOnBoard pieceOnBoard = (PieceOnBoard) o;
        return Objects.equals(pieceId, pieceOnBoard.pieceId)
                && Objects.equals(position, pieceOnBoard.position)
                && Objects.equals(pieceImageUrl, pieceOnBoard.pieceImageUrl)
                && Objects.equals(chessBoardId, pieceOnBoard.chessBoardId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieceId, position, pieceImageUrl, chessBoardId);
    }
}
