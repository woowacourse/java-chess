package chess.domain.dto;

import chess.domain.Position;
import chess.domain.board.Board;
import chess.domain.piece.Piece;

import java.util.HashMap;
import java.util.Map;

public class BoardSaveDto {

    private final Map<String, HashMap<String, PieceDto>> data;
    private final String turn;

    private BoardSaveDto(Map<String, HashMap<String, PieceDto>> data, String turn) {
        this.data = data;
        this.turn = turn;
    }

    public static BoardSaveDto from(Board board) {
        Map<Position, Piece> boardData = board.getBoardData();
        Map<String, HashMap<String, PieceDto>> result = new HashMap<>();
        for (Position position : boardData.keySet()) {
            HashMap<String, PieceDto> rankAndPieceDto = result.getOrDefault(position.getFile().name(), new HashMap<>());
            rankAndPieceDto.put(position.getRank().name(), PieceDto.from(boardData.get(position)));
            result.putIfAbsent(position.getFile().name(), rankAndPieceDto);
        }
        return new BoardSaveDto(result, board.getTurn().name());
    }

    public Map<String, HashMap<String, PieceDto>> getData() {
        return data;
    }

    public String getTurn() {
        return turn;
    }
}
