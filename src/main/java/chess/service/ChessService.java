package chess.service;

import chess.domain.ChessGame;
import chess.domain.dto.BoardDto;
import chess.domain.dto.GameInfoDto;
import chess.domain.utils.BoardInitializer;

import java.util.HashMap;
import java.util.Map;

public class ChessService {
    private ChessGame chessGame;

    public void start() {
        this.chessGame = new ChessGame();
    }

    public Map<String, Object> startRequest() {
        Map<String, Object> model = new HashMap<>();
        return model;
    }

    public void playNewGame() {
        chessGame.initBoard(BoardInitializer.init()); //보드 초기화
    }

    public Map<String, Object> initRequest() {
        GameInfoDto gameInfoDto = new GameInfoDto(chessGame);
        Map<String, Object> model = new HashMap<>();
        model.put("squares", gameInfoDto.getSquares());
        model.put("turn", gameInfoDto.getTurn());
        return model;
    }

    public void end() {
        chessGame.endGame();
    }

    public void continuedGame() {
    }

    public Map<String, Object> moveRequest() {
        Map<String, Object> model = new HashMap<>();
        return model;
    }
}
