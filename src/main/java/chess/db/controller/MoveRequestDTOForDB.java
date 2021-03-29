package chess.db.controller;


public class MoveRequestDTOForDB {
    private final Long gameId;
    private final String startPosition;
    private final String destination;

    public MoveRequestDTOForDB(Long gameId, String startPosition, String destination) {
        this.gameId = gameId;
        this.startPosition = startPosition;
        this.destination = destination;
    }

    public Long getGameId() {
        return gameId;
    }

    public String getStartPosition() {
        return startPosition;
    }

    public String getDestination() {
        return destination;
    }
}
