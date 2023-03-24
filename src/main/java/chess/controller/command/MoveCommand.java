package chess.controller.command;

import chess.domain.board.Position;
import chess.domain.game.ChessGame;
import chess.view.OutputView;
import chess.view.PositionParser;

import java.util.List;

public final class MoveCommand implements Command {

    private final OutputView outputView = new OutputView();

    private final List<String> parameters;

    public MoveCommand(List<String> parameters) {
        validateTwoPosition(parameters);
        validatePositionLength(parameters);
        this.parameters = parameters;
    }

    private void validateTwoPosition(final List<String> parameters) {
        if (parameters.size() != 2) {
            throw new IllegalArgumentException("이동 명령은 두개의 포지션을 입력해야 합니다.");
        }
    }

    private void validatePositionLength(final List<String> parameters) {
        boolean isNotLegalPositionCommand = parameters.stream()
                .map(String::length)
                .anyMatch(length -> length != 2);
        if (isNotLegalPositionCommand) {
            throw new IllegalArgumentException("포지션은 두글자로 입력해야 합니다.");
        }
    }

    @Override
    public void execute(ChessGame chessGame) {
        Position source = PositionParser.parse(parameters.get(0));
        Position target = PositionParser.parse(parameters.get(1));
        chessGame.playTurn(source, target);
        outputView.printBoard(chessGame.getBoard());
    }
}
