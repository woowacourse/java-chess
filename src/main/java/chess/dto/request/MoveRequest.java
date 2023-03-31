package chess.dto.request;

import chess.domain.Color;
import chess.domain.Position;
import chess.dto.PositionDto;

import java.util.List;

public class MoveRequest {

    private final List<Position> positions;
    private final Color movablePieceColor;
    private final PositionDto movablePieceDto;
    private final PositionDto targetPositionDto;

    private MoveRequest(
            final List<Position> positions,
            final Color movablePieceColor,
            final PositionDto movablePieceDto,
            final PositionDto targetPosition
    ) {
        this.positions = positions;
        this.movablePieceColor = movablePieceColor;
        this.movablePieceDto = movablePieceDto;
        this.targetPositionDto = targetPosition;
    }

    public static MoveRequest from(
            final List<Position> positions,
            final Color movablePieceColor,
            final PositionDto movablePieceDto,
            final PositionDto targetPosition
    ) {
        return new MoveRequest(positions, movablePieceColor, movablePieceDto, targetPosition);
    }

    public List<Position> getPositions() {
        return positions;
    }

    public PositionDto getMovablePieceDto() {
        return movablePieceDto;
    }

    public PositionDto getTargetPositionDto() {
        return targetPositionDto;
    }

    public Color getMovablePieceColor() {
        return movablePieceColor;
    }

}
