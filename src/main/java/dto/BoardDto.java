package dto;

import domain.board.Position;
import domain.piece.Piece;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public record BoardDto(Map<PositionDto, PieceDto> value) {

    public static BoardDto from(Map<Position, Piece> piecePositions) {
        Map<PositionDto, PieceDto> value = new HashMap<>();
        piecePositions.forEach(((position, piece) ->
                value.put(PositionDto.from(position), PieceDto.from(piece))));

        return new BoardDto(Collections.unmodifiableMap(value));
    }
}
