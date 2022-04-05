package chess.web.dto;

import chess.domain.board.Piece;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ChessDto {

    private final Map<PositionDto, PieceDto> board;

    public ChessDto(Map<PositionDto, PieceDto> board) {
        this.board = board;
    }


    public static ChessDto from(Map<Position, Piece> value) {
        Map<PositionDto, PieceDto> result = new HashMap<>();
        Set<Position> positions = value.keySet();
        for (Position position : positions) {
            Piece piece = value.get(position);
            result.put(PositionDto.from(position), PieceDto.from(piece));
        }
        return new ChessDto(result);
    }

    public Map<PositionDto, PieceDto> getBoard() {
        return board;
    }
}
