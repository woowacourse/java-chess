package chess.service;

import chess.domain.ChessGame;
import chess.domain.command.Commands;
import chess.domain.dto.GameInfoDto;
import chess.domain.dto.ScoresDto;
import chess.domain.utils.BoardInitializer;
import chess.view.OutputView;

import java.util.HashMap;
import java.util.Map;

public class ChessService {
    private ChessGame chessGame;

    public void start() {
        this.chessGame = new ChessGame();
    }

    public Map<String, Object> startResponse() {
        Map<String, Object> model = new HashMap<>();
        return model;
    }

    public void playNewGame() {
        chessGame.initBoard(BoardInitializer.init()); //보드 초기화
    }

    public Map<String, Object> initResponse() {
        return makeCommonResponse();
    }

    public void end() {
        chessGame.endGame();
    }

    public void continuedGame() {
    }

    public void move(String source, String target) {
        chessGame.move(new Commands(String.join(" ", "move", source, target)));
    }

    public Map<String, Object> moveResponse() {
        return makeCommonResponse();
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
