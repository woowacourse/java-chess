package chess.consolecontroller;

import chess.board.BoardGenerator;
import chess.manager.ChessManager;

import java.util.Objects;

public class ChessContext {
    private ChessManager chessManager;
    private boolean isNotEnd = true;

    private ChessContext() {
    }

    public static ChessContext empty() {
        return new ChessContext();
    }

    public void create() {
        if (Objects.nonNull(this.chessManager)) {
            return;
        }
        this.chessManager = new ChessManager(BoardGenerator.create());
    }

    public ChessManager getChessManager() {
        return chessManager;
    }

    public boolean isNotEnd() {
        if (isNotEnd && Objects.isNull(chessManager)) {
            return true;
        }
        return isNotEnd && chessManager.isKingAlive();
    }

    public void end() {
        this.isNotEnd = false;
    }
}
