package chess.view;

import chess.domain.Board;
import chess.domain.location.Location;
import chess.domain.piece.Piece;
import java.util.LinkedHashMap;
import java.util.Map;

public class BoardDto {

    private final Map<String, PieceDto> boardData;

    private BoardDto(Map<String, PieceDto> boardData) {
        this.boardData = boardData;
    }

    public static BoardDto of(Board board) {
        Map<String, PieceDto> boardDto = new LinkedHashMap<>();
        Map<Location, Piece> boardData = board.getBoard();
        boardData.keySet()
                .forEach(key -> boardDto.put(key.toString(), PieceDto.of(boardData.get(key))));
        return new BoardDto(boardDto);
    }

    public Map<String, PieceDto> getBoardData() {
        return boardData;
    }
}
