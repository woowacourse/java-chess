package chess.model.state.finished;

import chess.model.board.Board;
import chess.model.board.GameResult;
import chess.model.piece.Piece;
import java.util.HashMap;
import java.util.Map;

public final class End extends Finished {

    public End(Board board) {
        super(board);
    }

    @Override
    public boolean isStatus() {
        return false;
    }

    @Override
    public Map<String, Piece> getBoardForWeb() {
        return board.getBoardForWeb();
    }

    @Override
    public GameResult getScore() {
        throw new IllegalArgumentException("[ERROR] 게임이 종료되어 점수를 계산 할 수 없습니다.");
    }
}
