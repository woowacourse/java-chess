package chess.controller.command.strategy;

import chess.controller.ChessState;
import chess.domain.game.ChessGame;
import chess.domain.position.Position;
import chess.view.OutputView;

import java.util.List;

import static chess.controller.ChessState.*;

public class MoveCommand implements StrategyCommand {

    private static final int SOURCE_INDEX = 1;
    private static final int TARGET_INDEX = 2;
    private static final String CANNOT_MOVE_BEFORE_START_ERROR_MESSAGE = "게임을 시작하전에 체스 기물을 이동할 수 없습니다";

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
    public ChessState execute(final ChessState state, final ChessGame chessGame) {
        if (state == START || state == PROGRESS) {
            chessGame.move(source, target);
            OutputView.printBoard(chessGame.getBoard());
            return existOpponentKing(chessGame);
        }

        throw new IllegalArgumentException(CANNOT_MOVE_BEFORE_START_ERROR_MESSAGE);
    }

    private ChessState existOpponentKing(final ChessGame chessGame) {
        if (chessGame.isExistOpponentKing()) {
            chessGame.changeTurn();
            return PROGRESS;
        }
        OutputView.printResultWinning(chessGame.getTurn());
        return END;
    }
}
