package chess.dto.web;

import chess.domain.ChessBoard;
import chess.domain.ChessBoardPosition;
import chess.domain.piece.ChessPiece;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class ChessBoardDto {
    private final Map<String, ChessPieceDto> boardDto;

    private ChessBoardDto(Map<String, ChessPieceDto> boardDto) {
        this.boardDto = boardDto;
    }

    public static ChessBoardDto of(ChessBoard board) {
        Map<String, ChessPieceDto> boardDto = new HashMap<>();
        for (Entry<ChessBoardPosition, ChessPiece> boardBlock : board.getBoard().entrySet()) {
            boardDto.put(toKey(boardBlock.getKey()), ChessPieceDto.of(boardBlock.getValue()));
        }
        return new ChessBoardDto(boardDto);
    }

    private static String toKey(ChessBoardPosition chessBoardPosition) {
        return String.valueOf(chessBoardPosition.getColumn()) + chessBoardPosition.getRow();
    }

    public static ChessBoardDto empty() {
        return new ChessBoardDto(new HashMap<>());
    }

    public Map<String, ChessPieceDto> getBoardDto() {
        return boardDto;
    }
}
