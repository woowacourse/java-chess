package chess.dto;

import java.util.Map;
import java.util.stream.Collectors;

import chess.domain.Position;
import chess.domain.piece.Piece;

public class PlayerDto {

    private final Map<PositionDto, String> pieces;

    private PlayerDto(final Map<PositionDto, String> pieces) {
        this.pieces = pieces;
    }

    public static PlayerDto toDto(final Map<Position, Piece> pieces) {
        return new PlayerDto(pieces.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        entry -> PositionDto.toDto(entry.getKey()),
                        entry -> entry.getValue().getPieceName()
                ))
        );
    }

    public Map<PositionDto, String> getPieces() {
        return pieces;
    }
}
