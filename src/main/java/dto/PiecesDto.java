package dto;

import domain.piece.objects.Piece;
import domain.piece.position.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PiecesDto {
    private List<PieceDto> pieces = new ArrayList<>();
    private Map<String, Double> status;
    private boolean turn;
    private boolean isEnd;

    public PiecesDto(Map<Position, Piece> pieces, StatusDto statusDto, boolean isEnd, boolean turn) {
        for (Map.Entry<Position, Piece> entry : pieces.entrySet()) {
            this.pieces.add(new PieceDto(entry.getKey().toString(), entry.getValue().toString()));
        }
        this.status = statusDto.getStatusResult();
        this.isEnd = isEnd;
        this.turn = turn;
    }

    public List<PieceDto> getPieces() {
        return pieces;
    }

    public Map<String, Double> getStatus() {
        return status;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public boolean isTurn() {
        return turn;
    }
}
