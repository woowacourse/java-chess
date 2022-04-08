package chess.dto;

import chess.domain.board.Position;

public class BoardUpdateRequest {

    private final Position nextPosition;
    private final Position previousPosition;
    private final int gameNumber;

    public BoardUpdateRequest(Position nextPosition, Position previousPosition, int gameNumber) {
        this.nextPosition = nextPosition;
        this.previousPosition = previousPosition;
        this.gameNumber = gameNumber;
    }

    public Position getNextPosition() {
        return nextPosition;
    }

    public Position getPreviousPosition() {
        return previousPosition;
    }

    public int getGameNumber() {
        return gameNumber;
    }
}
