package chess.service;

import chess.domain.ChessGame;
import chess.domain.command.Commands;
import chess.domain.dto.GameInfoDto;
import chess.domain.utils.BoardInitializer;

import java.util.HashMap;
import java.util.Map;

public class ChessService {
    public static final String MOVE_COMMAND = "move";
    private ChessGame chessGame;

    public Map<String, Object> startResponse() {
        Map<String, Object> model = new HashMap<>();
        return model;
    }

    public void playNewGame() {
        this.chessGame = new ChessGame();
        chessGame.initBoard(BoardInitializer.init());
    }

    public Map<String, Object> initResponse() {
        return makeCommonResponse();
    }

    public void end() {
        chessGame.endGame();
    }

    public void move(String source, String target) {
        chessGame.move(new Commands(String.join(" ", MOVE_COMMAND, source, target)));
    }

    public Map<String, Object> moveResponse() {
        final Map<String, Object> model = makeCommonResponse();
        if (chessGame.isEnd()) { // if king dead,
            model.put("winner", chessGame.winner());
        }
        return model;
    }

    private Map<String, Object> makeCommonResponse() {
        final GameInfoDto gameInfoDto = new GameInfoDto(chessGame);
        Map<String, Object> model = new HashMap<>();
        model.put("squares", gameInfoDto.squares());
        model.put("turn", gameInfoDto.turn());
        model.put("scores", gameInfoDto.scores());
        return model;
    }
}
