package chess.domain.state;

import chess.domain.board.ChessBoard;
import java.util.List;

public class Ready implements GameState {
    private final ChessBoard chessBoard;

    public Ready(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }


    @Override
    public GameState play(List<String> inputCommand) {
        String command = inputCommand.get(0);
        if (command.equals("start")) {
            return new Progress(chessBoard);
        }
        if (command.equals("move")) {
            throw new UnsupportedOperationException("게임이 시작되지 않았습니다.");
        }
        if (command.equals("end")) {
            throw new UnsupportedOperationException("게임이 시작되지 않았습니다.");
        }
        throw new IllegalArgumentException("올바르지 않은 command입니다.");
    }

    @Override
    public boolean isEnd() {
        return false;
    }
}
