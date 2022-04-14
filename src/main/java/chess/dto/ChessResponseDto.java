package chess.dto;

import java.util.List;

public class ChessResponseDto {

    private final List<PieceDto> pieces;
    private final String turn;
    private final String status;

    public ChessResponseDto(List<PieceDto> pieces, String turn, String status) {
        this.pieces = pieces;
        this.turn = turn;
        this.status = status;
    }

    public static ChessResponseDto from(List<PieceDto> pieces, GameDto gameDto) {
        return new ChessResponseDto(pieces, gameDto.getTurn(), gameDto.getStatus());
    }

    public List<PieceDto> getPieces() {
        return pieces;
    }

    public String getTurn() {
        return turn;
    }

    public String getStatus() {
        return status;
    }
}
