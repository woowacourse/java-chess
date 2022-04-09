package chess.web.dto;

import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.Map;

public class ChessBoardDto {

    private final Map<PositionDto, PieceDto> cells;

    public ChessBoardDto(Map<PositionDto, PieceDto> cells) {
        this.cells = cells;
    }

    public static ChessBoardDto from(Map<Position, Piece> cells) {
        Map<PositionDto, PieceDto> chessBoardDto = new HashMap<>();

        for (Position position : cells.keySet()) {
            Piece piece = cells.get(position);
            changeToDto(chessBoardDto, position, piece);
        }

        return new ChessBoardDto(chessBoardDto);
    }

    private static void changeToDto(Map<PositionDto, PieceDto> chessBoardDto, Position position, Piece piece) {
        PositionDto positionDto = PositionDto.from(position);
        PieceDto pieceDto = new PieceDto(piece);

        chessBoardDto.put(positionDto, pieceDto);
    }

    public Map<PositionDto, PieceDto> getCells() {
        return cells;
    }
}
