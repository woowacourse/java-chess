package chess.dto;

import chess.domain.ChessBoardPosition;
import chess.domain.piece.ChessPiece;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class ChessBoardDto {
    private final Map<ChessBoardPosition, Character> boardDto;

    private ChessBoardDto(Map<ChessBoardPosition, Character> boardDto) {
        this.boardDto = boardDto;
    }

    public static ChessBoardDto of(Map<ChessBoardPosition, ChessPiece> board) {
        Map<ChessBoardPosition, Character> boardDto = new HashMap<>();
        for (Entry<ChessBoardPosition, ChessPiece> boardBlock : board.entrySet()) {
            boardDto.put(boardBlock.getKey(), ChessPieceSymbol.convertToSymbol(boardBlock.getValue()));
        }
        return new ChessBoardDto(boardDto);
    }

    public Map<ChessBoardPosition, Character> getBoardDto() {
        return boardDto;
    }
}
