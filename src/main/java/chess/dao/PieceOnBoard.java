package chess.dao;

import java.util.Objects;

public class PieceOnBoard {
    private int pieceId;
    private String position;
    private String pieceType;
    private String team;
    private int chessBoardId;

    public PieceOnBoard(int pieceId, String position, String pieceType, String team, int chessBoardId) {
        this.pieceId = pieceId;
        this.position = position;
        this.pieceType = pieceType;
        this.team = team;
        this.chessBoardId = chessBoardId;
    }

    public int getPieceId() {
        return pieceId;
    }

    public String getPosition() {
        return position;
    }

    public String getPieceType() {
        return this.pieceType;
    }

    public String getTeam() {
        return this.team;
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
                && Objects.equals(pieceType, pieceOnBoard.pieceType)
                && Objects.equals(team, pieceOnBoard.team)
                && Objects.equals(chessBoardId, pieceOnBoard.chessBoardId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieceId, position, pieceType, team, chessBoardId);
    }
}
