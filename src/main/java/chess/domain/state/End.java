package chess.domain.state;

import chess.domain.board.ChessBoard;
import java.util.List;

public class End implements GameState {
    private static final String START_COMMAND = "start";
    private static final String MOVE_COMMAND = "move";
    private static final String END_COMMAND = "end";

    private final ChessBoard chessBoard;

    public End(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }

    @Override
    public GameState play(List<String> inputCommand) {
        throw new UnsupportedOperationException("종료한 게임은 진행할 수 없습니다.");
    }

    @Override
    public boolean isEnd() {
        return true;
    }
}
