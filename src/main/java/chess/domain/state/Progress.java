package chess.domain.state;

import chess.domain.board.ChessBoard;
import chess.domain.position.Position;
import chess.domain.position.Positions;
import java.util.List;

public class Progress implements GameState {
    private final ChessBoard chessBoard;

    public Progress(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }

    @Override
    public GameState play(List<String> inputCommand) {
        String command = inputCommand.get(0);
        if (command.equals("start")) {
            throw new UnsupportedOperationException("이미 시작한 게임은 다시 시작할 수 없습니다.");
        }
        if (command.equals("move")) {
            Position source = Positions.findByInput(inputCommand.get(1));
            Position target = Positions.findByInput(inputCommand.get(2));

            chessBoard.move(source, target);
            return new Progress(chessBoard);
        }
        if (command.equals("end")) {
            return new End(chessBoard);
        }
        throw new IllegalArgumentException("올바르지 않은 command입니다.");
    }

    @Override
    public boolean isEnd() {
        return false;
    }
}
