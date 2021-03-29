package chess.db.controller.dto.request;


public class MoveRequestDTOForDB {
    private Long gameId;
    private final String startPosition;
    private final String destination;

    public MoveRequestDTOForDB(Long gameId, String startPosition, String destination) {
        this.gameId = gameId;
        this.startPosition = startPosition;
        this.destination = destination;
    }

    public MoveRequestDTOForDB(String startPosition, String destination) {
        this.startPosition = startPosition;
        this.destination = destination;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
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
