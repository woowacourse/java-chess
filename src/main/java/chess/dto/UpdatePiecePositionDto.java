package chess.dto;

import chess.domain.position.XAxis;
import chess.domain.position.YAxis;

public class UpdatePiecePositionDto {
    private final String gameId;
    private final XAxis fromXAxis;
    private final YAxis fromYAxis;
    private final XAxis toXAxis;
    private final YAxis toYAxis;

    private UpdatePiecePositionDto(String gameId, XAxis fromXAxis, YAxis fromYAxis, XAxis toXAxis,
                                   YAxis toYAxis) {
        this.gameId = gameId;
        this.fromXAxis = fromXAxis;
        this.fromYAxis = fromYAxis;
        this.toXAxis = toXAxis;
        this.toYAxis = toYAxis;
    }

    public static UpdatePiecePositionDto of(String gameId, XAxis fromXAxis, YAxis fromYAxis, XAxis toXAxis,
                                            YAxis toYAxis) {
        return new UpdatePiecePositionDto(gameId, fromXAxis, fromYAxis, toXAxis, toYAxis);
    }

//    public static UpdatePiecePositionDto of(String gameId, Position from, Position to) {
//        XAxis fromXAxis = from.getXAxis();
//        YAxis fromYAxis = from.getYAxis();
//        XAxis toXAxis = to.getXAxis();
//        YAxis toYAxis = to.getYAxis();
//
//        return new UpdatePiecePositionDto(gameId, fromXAxis, fromYAxis, toXAxis, toYAxis);
//    }

    public String getGameId() {
        return gameId;
    }

    public String getFromXAxisValueAsString() {
        return fromXAxis.getValueAsString();
    }

    public String getFromYAxisValueAsString() {
        return fromYAxis.getValueAsString();
    }

    public String getToXAxisValueAsString() {
        return toXAxis.getValueAsString();
    }

    public String getToYAxisValueAsString() {
        return toYAxis.getValueAsString();
    }

    @Override
    public String toString() {
        return "UpdatePiecePositionDto{" +
                "gameId='" + gameId + '\'' +
                ", fromXAxis=" + fromXAxis +
                ", fromYAxis=" + fromYAxis +
                ", toXAxis=" + toXAxis +
                ", toYAxis=" + toYAxis +
                '}';
    }
}
