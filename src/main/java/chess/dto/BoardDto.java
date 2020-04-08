package chess.dto;

import chess.domain.piece.Piece;

import java.util.List;
import java.util.stream.Collectors;

public class BoardDto {
    List<PieceDto> pieceDtos;

    public BoardDto(List<Piece> pieces) {

        this.pieceDtos = pieces.stream()
                .map(PieceDto::new)
                .collect(Collectors.toList());
    }

    public List<PieceDto> getPieceDtos() {
        return pieceDtos;
    }

    public void setPieceDtos(List<PieceDto> pieceDtos) {
        this.pieceDtos = pieceDtos;
    }
}
