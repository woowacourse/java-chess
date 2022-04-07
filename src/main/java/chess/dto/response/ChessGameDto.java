package chess.dto.response;

import chess.domain.piece.PieceColor;

public class ChessGameDto {
    private final String gameId;
    private final PieceColorDto pieceColorDto;

    public ChessGameDto(String gameId, PieceColorDto pieceColorDto) {
        this.gameId = gameId;
        this.pieceColorDto = pieceColorDto;
    }

    public static ChessGameDto from(String gameId, String turn) {
        return new ChessGameDto(gameId, PieceColorDto.from(turn)); // TODO: null 위험 존재
    }

    public PieceColor getCurrentTurnAsPieceColor() {
        return pieceColorDto.toPieceColor();
    }

    @Override
    public String toString() {
        return "GameDto{" +
                "gameId='" + gameId + '\'' +
                ", currentTurnDto=" + pieceColorDto +
                '}';
    }
}
