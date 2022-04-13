package chess.dto;

import java.util.List;

public class ChessGameUpdateDto {

    private final String turn;
    private final List<PieceDto> whitePieces;
    private final List<PieceDto> blackPieces;

    public ChessGameUpdateDto(String turn, List<PieceDto> whitePieces, List<PieceDto> blackPieces) {
        this.turn = turn;
        this.whitePieces = whitePieces;
        this.blackPieces = blackPieces;
    }

    public String getTurn() {
        return turn;
    }

    public List<PieceDto> getWhitePieces() {
        return whitePieces;
    }

    public List<PieceDto> getBlackPieces() {
        return blackPieces;
    }
}
