package chess.model.state.running;

import chess.model.Command;
import chess.model.Team;
import chess.model.board.Board;
import chess.model.position.Position;
import chess.model.state.finished.End;
import chess.model.state.State;
import java.util.List;

public class BlackTurn extends Running {

    private final static Team BLACK = Team.BLACK;

    public BlackTurn(Board board) {
        super(board);
    }

    @Override
    public State proceed(List<String> inputs) {
        Command command = Command.of(inputs.get(0));
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
        checkBlackPieceAt(Position.from(command.get(1)));
        board.move(Position.from(command.get(1)), Position.from(command.get(2)));
    }

    private void checkBlackPieceAt(Position source) {
        board.checkSameTeam(BLACK, source);
    }
}
