package chess.webController;

import chess.service.SetChessGameService;

import java.util.Map;

public class ChessUrlController {

    private SetChessGameService setChessGameService;

    public ChessUrlController() {
        this.setChessGameService = new SetChessGameService();
    }

    public Map<String, Object> gameSetting() {
        return setChessGameService.settingChessBoard();
    }
}
