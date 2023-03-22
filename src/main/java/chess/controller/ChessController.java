package chess.controller;

import chess.domain.Position;
import chess.domain.Team;
import chess.dto.PositionRequest;
import chess.game.ChessGame;
import chess.view.*;

import java.util.List;
import java.util.function.Supplier;

public class ChessController {

    private static final int SOURCE_INDEX = 1;
    private static final int TARGET_INDEX = 2;

    private final ChessGame chessGame;

    public ChessController(ChessGame chessGame) {
        this.chessGame = chessGame;
    }

    public void run() {
        OutputView.printStartMessage();
        GameCommand gameCommand = repeat(InputView::readGameCommand);
        if (gameCommand == GameCommand.START) {
            OutputView.printBoard(chessGame.getBoard());
            while (gameLoop() == GameStatus.CONTINUE) ;
        }
    }

    private GameStatus gameLoop() {
        List<String> commands = repeat(InputView::readMoveCommand);
        MoveCommand moveCommand = MoveCommand.of(commands).orElse(MoveCommand.END);

        if (moveCommand == MoveCommand.MOVE) {
            move(commands);
            return GameStatus.CONTINUE;
        }
        if (moveCommand == MoveCommand.STATUS) {
            double whiteTeamScore = chessGame.calculateScore(Team.WHITE);
            double blackTeamScore = chessGame.calculateScore(Team.BLACK);
            OutputView.printScore(whiteTeamScore, blackTeamScore);
            return GameStatus.CONTINUE;
        }
        return GameStatus.EXIT;
    }

    private void move(List<String> command) {
        try {
            PositionRequest source = PositionMapper.map(command.get(SOURCE_INDEX));
            PositionRequest target = PositionMapper.map(command.get(TARGET_INDEX));
            chessGame.move(convertRequestToPosition(source), convertRequestToPosition(target));
            OutputView.printBoard(chessGame.getBoard());
        } catch (IllegalArgumentException | IllegalStateException e) {
            OutputView.printErrorMessage(e.getMessage());
        }
    }

    private static Position convertRequestToPosition(PositionRequest request) {
        return Position.of(request.getX(), request.getY());
    }

    private <T> T repeat(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return repeat(supplier);
        }
    }
}
