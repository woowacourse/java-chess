package chess.dto.response;

import chess.dto.response.board.RowDto;
import chess.dto.response.board.WebBoardViewDto;
import java.util.List;

public class GameDto {

    private final GameDataDto game;
    private final WebBoardViewDto board;

    public GameDto(GameDataDto game, WebBoardViewDto board) {
        this.game = game;
        this.board = board;
    }

    public GameDataDto getGame() {
        return game;
    }

    public List<RowDto> getBoard() {
        return board.toDisplay();
    }
}
