package chess.domain.piece.dto;

public class FindPiecePositionDto {

    private Long gameId;
    private String rank;
    private String file;

    public FindPiecePositionDto(Long gameId, int rank, int file) {
        this.gameId = gameId;
        this.rank = String.valueOf(rank);
        this.file = String.valueOf(file);
    }

    public Long getGameId() {
        return gameId;
    }

    public String getRank() {
        return rank;
    }

    public String getFile() {
        return file;
    }
}
