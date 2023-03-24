package chess.controller;

import chess.domain.game.ChessGame;
import chess.view.OutputView;
import java.util.List;

public class MoveController implements Controller {
    private static final int SOURCE_INDEX = 0;
    private static final int TARGET_INDEX = 1;
    private static final int POSITION_SIZE = 2;

    private final List<String> parameters;

    public MoveController(List<String> parameters) {
        validateSize(parameters);
        this.parameters = parameters;
    }

    private void validateSize(List<String> parameters) {
        if (parameters.size() != POSITION_SIZE) {
            throw new IllegalArgumentException("잘못된 명령어를 입력했습니다.");
        }
    }

    @Override
    public void execute(ChessGame chessGame, OutputView outputView) {
        String source = parameters.get(SOURCE_INDEX);
        String target = parameters.get(TARGET_INDEX);
        chessGame.move(GameCommand.createPosition(source), GameCommand.createPosition(target));
        outputView.printBoard(chessGame.getBoard());
    }
}
