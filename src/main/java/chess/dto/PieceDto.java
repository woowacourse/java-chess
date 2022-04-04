package chess.dto;

import chess.domain.piece.Piece;
import chess.domain.position.Position;

public class PieceDto {
    private final String team;
    private final String type;
    private final String position;

    public PieceDto(Piece piece, Position position) {
        this(piece.getTeam().name().toLowerCase(),
                piece.getInfo().getType(),
                position.getName());
    }

    public PieceDto(String team, String type, String position) {
        this.team = team;
        this.type = type;
        this.position = position;
    }

    public String getTeam() {
        return team;
    }

    public String getType() {
        return type;
    }

    public String getPosition() {
        return position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PieceDto)) {
            return false;
        }

        PieceDto pieceDto = (PieceDto) o;

        if (team != null ? !team.equals(pieceDto.team) : pieceDto.team != null) {
            return false;
        }
        if (type != null ? !type.equals(pieceDto.type) : pieceDto.type != null) {
            return false;
        }
        return position != null ? position.equals(pieceDto.position) : pieceDto.position == null;
    }

    @Override
    public int hashCode() {
        int result = team != null ? team.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (position != null ? position.hashCode() : 0);
        return result;
    }
}
