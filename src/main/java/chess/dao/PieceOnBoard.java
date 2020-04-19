package chess.dao;

import chess.domain.piece.PieceType;
import chess.domain.piece.Team;
import chess.domain.position.Position;

import java.util.Objects;

public class PieceOnBoard {
    private int pieceId;
    private Position position;
    private PieceType pieceType;
    private Team team;
    private int chessBoardId;

    public PieceOnBoard(Position position, PieceType pieceType, Team team, int chessBoardId) {
        this(0, position, pieceType, team, chessBoardId);
    }

    public PieceOnBoard(int pieceId, Position position, PieceType pieceType, Team team, int chessBoardId) {
        this.pieceId = pieceId;
        this.position = position;
        this.pieceType = pieceType;
        this.team = team;
        this.chessBoardId = chessBoardId;
    }

    public int getPieceId() {
        return this.pieceId;
    }

    public String getPosition() {
        return this.position.toString();
    }

    public String getPieceType() {
        return this.pieceType.getSymbol();
    }

    public String getTeam() {
        return this.team.name();
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
