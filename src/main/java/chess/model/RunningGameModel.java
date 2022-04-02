package chess.model;

import chess.domain.game.Game;
import chess.dto.response.RowDto;
import chess.dto.response.WebBoardViewDto;
import java.util.List;

public class RunningGameModel {

    private final GameModel game;
    private final List<RowDto> board;

    public RunningGameModel(int gameId, Game game) {
        this.game = GameModel.ofRunning(gameId, game);
        this.board = toBoard(game);
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
