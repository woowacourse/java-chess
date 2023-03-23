package dto;

import chess.domain.Square;
import chess.domain.chesspiece.Piece;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ChessBoardDto {
    private final List<PieceDto> chessBoardDto;

    private ChessBoardDto(List<PieceDto> pieceDtos) {
        this.chessBoardDto = pieceDtos;
    }

    public static ChessBoardDto of(Map<Square, Piece> pieces) {
        List<PieceDto> pieceDtos = new ArrayList<>();
        for (Square square : pieces.keySet()) {
            pieceDtos.add(PieceDto.from(pieces.get(square), square));
        }
        return new ChessBoardDto(pieceDtos);
    }

    public List<PieceDto> getChessBoardDto() {
        return chessBoardDto;
    }
}
