package chess.model.state.running;

import static chess.model.Team.BLACK;

import chess.model.Team;
import chess.model.board.Board;
import chess.model.position.Position;
import chess.model.state.Command;
import chess.model.state.State;
import chess.model.state.finished.End;
import java.util.List;

public class BlackTurn extends Running {

    private static final int COMMAND_INDEX = 0;
    private static final int SOURCE_OPTION_INDEX = 1;
    private static final int TARGET_OPTION_INDEX = 2;

    public BlackTurn(Board board) {
        super(board);
    }

    @Override
    public State proceed(List<String> inputs) {
        Command command = Command.of(inputs.get(COMMAND_INDEX));
        if (command.isEnd()) {
            return new End();
        }
        if (command.isMove()) {
            movePieceFrom(inputs);
            return new WhiteTurn(board);
        }
        throw new IllegalArgumentException("[ERROR] 게임을 진행하기 위한 명령어가 아닙니다.");
    }

    private void movePieceFrom(List<String> command) {
        board.checkSameTeam(BLACK, Position.from(command.get(SOURCE_OPTION_INDEX)));
        board.move(Position.from(command.get(SOURCE_OPTION_INDEX)), Position.from(command.get(TARGET_OPTION_INDEX)));
    }
}
