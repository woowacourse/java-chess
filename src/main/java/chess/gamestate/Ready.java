package chess.gamestate;

import chess.PieceOperator;
import chess.Turn;
import chess.command.Command;
import java.util.List;

public class Ready implements GameState {

    private final PieceOperator pieceOperator;

    public Ready(PieceOperator pieceOperator) {
        this.pieceOperator = pieceOperator;

    }

    @Override
    public GameState operateCommand(Command command, List<String> arguments) {
        if (command == Command.START) {
            pieceOperator.initialize();
            return new Running(pieceOperator, new Turn());
        }
        throw new IllegalArgumentException("start 이외의 명령은 입력할 수 없습니다.");
    }

    @Override
    public boolean isRunning() {
        return false;
    }
}
