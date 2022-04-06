package chess.model.dto;

import chess.model.Turn;
import chess.model.board.Board;

import java.util.Map;
import java.util.stream.Collectors;

public class WebBoardDto {
    private Turn turn;
    private Map<String, String> webBoard;

    public WebBoardDto(Map<String, String> webBoard) {
        this.webBoard = webBoard;
    }

    public static WebBoardDto from(Board board) {
        Map<String, String> webBoard = board.getBoard()
                .entrySet()
                .stream()
                .collect(Collectors.toMap(entry -> entry.getKey().getPosition(), entry -> entry.getValue().getPieceName()));

        return new WebBoardDto(webBoard);
    }

    public Map<String, String> getWebBoard() {
        return webBoard;
    }
}
