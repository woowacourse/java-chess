package chess.database.dto;

import java.util.Map;
import java.util.stream.Collectors;

import chess.domain.board.Point;
import chess.domain.piece.Piece;

public class BoardDto {

    private final Map<PointDto, PieceDto> pointPieceDto;

    public BoardDto(Map<PointDto, PieceDto> pointPieceDto) {
        this.pointPieceDto = pointPieceDto;
    }

    public static BoardDto of(Map<Point, Piece> pointPieces) {
        return new BoardDto(
            pointPieces.entrySet()
                .stream()
                .collect(Collectors.toMap(
                    pointPieceEntry -> PointDto.of(pointPieceEntry.getKey()),
                    pointPieceEntry -> PieceDto.of(pointPieceEntry.getValue())
                ))
        );
    }

    public Map<PointDto, PieceDto> getPointPieces() {
        return ignoreEmpty();
    }

    private Map<PointDto, PieceDto> ignoreEmpty() {
        return pointPieceDto.entrySet()
            .stream()
            .filter(entry -> !entry.getValue().isEmpty())
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
