package chess.controller.command;

import chess.dao.ChessGameDao;
import chess.domain.game.ChessGame;
import chess.domain.piece.property.Color;
import chess.domain.position.Position;
import chess.view.OutputView;
import chess.view.PositionParser;

import java.util.List;

public final class MoveCommand implements Command {

    private static final int MOVE_PARAMETERS_SIZE = 2;
    private static final int POSITION_STRING_SIZE = 2;
    private static final int SOURCE_INDEX = 0;
    private static final int TARGET_INDEX = 1;

    private final OutputView outputView = new OutputView();
    private final ChessGameDao chessGameDao = new ChessGameDao();

    private final List<String> parameters;

    public MoveCommand(List<String> parameters) {
        validateTwoPosition(parameters);
        validatePositionLength(parameters);
        this.parameters = parameters;
    }

    private void validateTwoPosition(final List<String> parameters) {
        if (parameters.size() != MOVE_PARAMETERS_SIZE) {
            throw new IllegalArgumentException("이동 명령은 두개의 포지션을 입력해야 합니다.");
        }
    }

    private void validatePositionLength(final List<String> parameters) {
        boolean isNotLegalPositionCommand = parameters.stream()
                .map(String::length)
                .anyMatch(length -> length != POSITION_STRING_SIZE);
        if (isNotLegalPositionCommand) {
            throw new IllegalArgumentException("포지션은 두글자로 입력해야 합니다.");
        }
    }

    @Override
    public void execute(ChessGame chessGame) {
        Position source = PositionParser.parse(parameters.get(SOURCE_INDEX));
        Position target = PositionParser.parse(parameters.get(TARGET_INDEX));
        chessGame.playTurn(source, target);
        chessGameDao.updateGame(chessGame, source, target);
        outputView.printBoard(chessGame.getBoard());
        if (chessGame.computeWinner() != Color.NONE) {
            outputView.printWinner(chessGame.computeWinner());
            chessGameDao.deleteGameById(chessGame.getGameId());
            chessGame.end();
        }
    }
}
