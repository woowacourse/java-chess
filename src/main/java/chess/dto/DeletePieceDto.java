package chess.dto;

import chess.domain.position.XAxis;
import chess.domain.position.YAxis;

public class DeletePieceDto {
    private final String gameId;
    private final XAxis xAxis;
    private final YAxis yAxis;

    private DeletePieceDto(String gameId, XAxis xAxis, YAxis yAxis) {
        this.gameId = gameId;
        this.xAxis = xAxis;
        this.yAxis = yAxis;
    }

    public static DeletePieceDto of(String gameId, XAxis xAxis, YAxis yAxis) {
        return new DeletePieceDto(gameId, xAxis, yAxis);
    }

    public String getGameId() {
        return gameId;
    }

    public String getXAxisValueAsString() {
        return xAxis.getValueAsString();
    }

    public String getYAxisValueAsString() {
        return yAxis.getValueAsString();
    }

    @Override
    public String toString() {
        return "DeletePieceDto{" +
                "gameId='" + gameId + '\'' +
                ", xAxis=" + xAxis +
                ", yAxis=" + yAxis +
                '}';
    }
}
