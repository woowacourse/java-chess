package chess.controller.command.strategy;

import chess.controller.ChessState;
import chess.domain.Board;
import chess.domain.position.Position;
import chess.domain.team.Team;
import chess.view.OutputView;

import java.util.List;

public class MoveCommand implements StrategyCommand {

    private static final int SOURCE_INDEX = 1;
    private static final int TARGET_INDEX = 2;

    private final Position source;
    private final Position target;

    private MoveCommand(final Position source, final Position target) {
        this.source = source;
        this.target = target;
    }

    public static MoveCommand from(final List<String> inputs) {
        final Position source = Position.from(inputs.get(SOURCE_INDEX));
        final Position target = Position.from(inputs.get(TARGET_INDEX));

        return new MoveCommand(source, target);
    }

    @Override
    public ChessState execute(final ChessState state, final Board board, final Team team) {
        if (state == ChessState.START || state == ChessState.PROGRESS) {
            board.move(source, target, team);
            OutputView.printBoard(board);
            return ChessState.PROGRESS;
        }

        throw new IllegalArgumentException("게임을 시작하전에 체스 기물을 이동할 수 없습니다");
    }
}
