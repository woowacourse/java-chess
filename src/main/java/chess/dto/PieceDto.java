package chess.dto;

import chess.domain.Aliance;

import java.util.Objects;

public class PieceDto {
    private final Aliance aliance;
    private final int gameId;
    private final int kindId;
    private final String position;


    public PieceDto(int teamId, int gameId, int kindId, String position) {
        this.aliance = Aliance.valueOf(teamId);
        this.gameId = gameId;
        this.kindId = kindId;
        this.position = position;
    }

    public Aliance getAliance() {
        return aliance;
    }

    public int getGameId() {
        return gameId;
    }

    public int getKindId() {
        return kindId;
    }

    public String getPosition() {
        return position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PieceDto pieceDto = (PieceDto) o;
        return gameId == pieceDto.gameId &&
                kindId == pieceDto.kindId &&
                aliance == pieceDto.aliance &&
                Objects.equals(position, pieceDto.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(aliance, gameId, kindId, position);
    }
}
