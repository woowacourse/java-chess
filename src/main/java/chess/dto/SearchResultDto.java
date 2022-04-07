package chess.dto;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SearchResultDto that = (SearchResultDto) o;
        return gameId == that.gameId
                && gameFound == that.gameFound;
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameId, gameFound);
    }

    @Override
    public String toString() {
        return "SearchResultDto{" + "gameId=" + gameId + ", gameFound=" + gameFound + '}';
    }
}
