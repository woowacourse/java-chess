package chess.dto.response;

import chess.domain.game.Game;
import chess.dto.response.board.RowDto;
import chess.dto.response.board.WebBoardViewDto;
import java.util.List;

public class FullGameModel {

    private final GameModel game;
    private final List<RowDto> board;

    public FullGameModel(int gameId, Game game) {
        this.game = toModel(gameId, game);
        this.board = toBoard(game);
    }

    private GameModel toModel(int gameId, Game game) {
        return new GameModel(gameId, game.getState());
    }

    private List<RowDto> toBoard(Game game) {
        WebBoardViewDto webBoardView = game.toBoardWebView();
        return webBoardView.toDisplay();
    }

    public GameModel getGame() {
        return game;
    }

    public List<RowDto> getBoard() {
        return board;
    }
}
