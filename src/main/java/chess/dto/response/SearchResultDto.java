package chess.dto.response;

public class SearchResultDto {

    private final int gameId;
    private final boolean gameFound;

    public SearchResultDto(int gameId, boolean gameFound) {
        this.gameId = gameId;
        this.gameFound = gameFound;
    }

    public String toJson() {
        return "{\"id\":" + gameId + ",\"found\":" + gameFound + "}";
    }
}
