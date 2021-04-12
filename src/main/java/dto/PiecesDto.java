package dto;

import domain.piece.objects.Piece;
import domain.piece.position.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PiecesDto {
    private List<PieceDto> pieces = new ArrayList<>();
    private double blackScore;
    private double whiteScore;
    private boolean turn;
    private boolean isEnd;

    public PiecesDto(Map<Position, Piece> pieces, StatusDto statusDto, boolean isEnd, boolean turn) {
        for (Map.Entry<Position, Piece> entry : pieces.entrySet()) {
            this.pieces.add(new PieceDto(entry.getKey().toString(), entry.getValue().toString()));
        }
        this.blackScore = statusDto.getBlackScore();
        this.whiteScore = statusDto.getWhiteScore();
        this.isEnd = isEnd;
        this.turn = turn;
    }

    public List<PieceDto> getPieces() {
        return pieces;
    }

    public double getBlackScore() {
        return blackScore;
    }

    public double getWhiteScore() {
        return whiteScore;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public boolean isTurn() {
        return turn;
    }
}
