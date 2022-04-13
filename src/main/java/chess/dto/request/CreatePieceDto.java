package chess.dto.request;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.piece.PieceType;
import chess.domain.position.Position;
import chess.domain.position.XAxis;
import chess.domain.position.YAxis;

public class CreatePieceDto {
    private final String gameId;
    private final XAxis xAxis;
    private final YAxis yAxis;
    private final PieceType pieceType;
    private final PieceColor pieceColor;

    private CreatePieceDto(String gameId, XAxis xAxis, YAxis yAxis, PieceType pieceType, PieceColor pieceColor) {
        this.gameId = gameId;
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        this.pieceType = pieceType;
        this.pieceColor = pieceColor;
    }

    public static CreatePieceDto of(String gameId, Position position, Piece piece) {
        return new CreatePieceDto(gameId, position.getXAxis(), position.getYAxis(), piece.getPieceType(),
                piece.getPieceColor());
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

    public String getPieceTypeName() {
        return pieceType.name();
    }

    public String getPieceColorName() {
        return pieceColor.name();
    }

    @Override
    public String toString() {
        return "CreatePieceDto{" +
                "xAxis=" + xAxis +
                ", yAxis=" + yAxis +
                ", pieceType=" + pieceType +
                ", pieceColor=" + pieceColor +
                '}';
    }
}
