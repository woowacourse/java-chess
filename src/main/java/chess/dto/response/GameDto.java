package chess.dto.response;

import chess.domain.game.Game;
import chess.domain.game.GameState;
import chess.dto.response.board.RowDto;
import chess.dto.response.board.WebBoardViewDto;
import java.util.List;

public class GameDto {

    private final GameDataDto game;
    private final List<RowDto> board;

    public GameDto(int gameId, Game game) {
        this.game = toModel(gameId, game);
        this.board = toBoard(game);
    }

    private GameDataDto toModel(int gameId, Game game) {
        return new GameDataDto(gameId, game.getState());
    }

    private List<RowDto> toBoard(Game game) {
        WebBoardViewDto webBoardView = game.toBoardWebView();
        return webBoardView.toDisplay();
    }

    public GameDataDto getGame() {
        return game;
    }

    public List<RowDto> getBoard() {
        return board;
    }

    private static class GameDataDto {

        private final int id;
        private final GameState state;

        public GameDataDto(int id, GameState state) {
            this.id = id;
            this.state = state;
        }

        public int getId() {
            return id;
        }

        public GameState getState() {
            return state;
        }
    }
}
