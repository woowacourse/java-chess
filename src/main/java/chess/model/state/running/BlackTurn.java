package chess.model.state.running;

import static chess.model.Team.BLACK;

import chess.model.board.Board;
import chess.model.position.Position;
import chess.model.state.Command;
import chess.model.state.State;
import chess.model.state.finished.End;
import chess.model.state.finished.Status;
import java.util.List;

public final class BlackTurn extends Running {

    public BlackTurn(Board board) {
        super(board);
    }

    @Override
    public boolean isWhiteTurn() {
        return false;
    }

    @Override
    public boolean isBlackTurn() {
        return true;
    }

    @Override
    public State proceed(List<String> inputs) {
        Command command = Command.of(inputs.get(COMMAND_INDEX));
        if (command.isStatus()) {
            return new Status(board);
        }
        if (command.isEnd()) {
            return new End(board);
        }
        if (command.isMove()) {
            movePieceFrom(inputs);
            return createStateByBoard();
        }
        throw new IllegalArgumentException("[ERROR] 게임을 진행하기 위한 명령어가 아닙니다.");
    }

    private void movePieceFrom(List<String> command) {
        board.checkSameTeam(BLACK, Position.from(command.get(SOURCE_OPTION_INDEX)));
        board.move(Position.from(command.get(SOURCE_OPTION_INDEX)), Position.from(command.get(TARGET_OPTION_INDEX)));
    }

    private State createStateByBoard() {
        if (board.isKingDead()) {
            return new End(board);
        }
        return new WhiteTurn(board);
    }
}
