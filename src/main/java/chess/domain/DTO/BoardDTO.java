package chess.domain.DTO;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Piece;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class BoardDTO {
    private Map<String, String> boardInfo = new HashMap<>();

    private BoardDTO(Map<String, String> boardInfo) {
        this.boardInfo = boardInfo;
    }

    public static BoardDTO of(Board board) {
        Map<String, String> boardInfo = new HashMap<>();
        for (Map.Entry<Position, Piece> info : board.getBoard().entrySet()) {
            if (Objects.equals(info.getValue(), null)) {
                boardInfo.put(info.getKey().convertToString(), "");
            } else {
                boardInfo.put(info.getKey().convertToString(), info.getValue().getUnicode());
            }
        }
        return new BoardDTO(boardInfo);
    }

    public static BoardDTO of(Map<String, String> boardInfo) {
        return new BoardDTO(boardInfo);
    }

    public Map<String, String> getBoardInfo() {
        return boardInfo;
    }
}
