package controller;

import domain.game.Game;
import domain.piece.Position;
import domain.piece.Side;
import view.GameCommand;
import view.InputView;
import view.OutputView;

import java.util.Arrays;
import java.util.List;

public class ChessController {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void run() {
        Game game = Game.create();
        this.outputView.printGameGuideMessage();
        repeat(this::getStartCommand);

        Side side = Side.WHITE;
        this.outputView.printChessBoard(game.getChessBoard());

        String userInput = this.inputView.requestUserInput();
        GameCommand gameCommand = convertUserInputToGameCommandAfterStart(userInput);
        while (!gameCommand.equals(GameCommand.END)) {

            Position sourcePosition = convertUserInputToSourcePosition(userInput);
            Position targetPosition = convertUserInputToTargetPosition(userInput);
            game.move(side, sourcePosition, targetPosition);

            this.outputView.printChessBoard(game.getChessBoard());
            side = changeSide(side);

            userInput = this.inputView.requestUserInput();
            gameCommand = convertUserInputToGameCommandAfterStart(userInput);
        }
    }

    private void getStartCommand() {
        String startCommand = this.inputView.requestUserInput();
        if (!GameCommand.from(startCommand).equals(GameCommand.START)) {
            throw new IllegalArgumentException("게임 시작하려면 먼저 start를 입력하세요.");
        }
    }

    private static void validateStartUserInput(String startCommand) {

    }

    private Position convertUserInputToSourcePosition(String userInput) {
        List<String> inputs = Arrays.asList(userInput.split(" "));

        String sourceString = inputs.get(1);

        List<String> fileAndRank = Arrays.asList(sourceString.split(""));
        return Position.of(fileAndRank.get(0), fileAndRank.get(1));
    }

    private Position convertUserInputToTargetPosition(String userInput) {
        List<String> inputs = Arrays.asList(userInput.split(" "));

        String sourceString = inputs.get(2);

        List<String> fileAndRank = Arrays.asList(sourceString.split(""));
        return Position.of(fileAndRank.get(0), fileAndRank.get(1));
    }

    private GameCommand convertUserInputToGameCommandAfterStart(String userInput) {
        List<String> inputs = Arrays.asList(userInput.split(" "));
        GameCommand command = GameCommand.from(inputs.get(0));

        if (command.equals(GameCommand.START)) {
            throw new IllegalArgumentException("게임이 이미 실행중입니다.");
        }
        return command;
    }

    private GameCommand getGameCommandAfterStart() {
        GameCommand gameCommand = GameCommand.from(this.inputView.requestUserInput());
        if (gameCommand.equals(GameCommand.START)) {
            throw new IllegalArgumentException("이미 게임이 실행 중입니다.");
        }
        return gameCommand;
    }

    private Side changeSide(Side side) {
        if (side.equals(Side.WHITE)) {
            return Side.BLACK;

        }
        return Side.WHITE;
    }

    private Runnable repeat(Runnable runnable) {
        try {
            runnable.run();
            return runnable;
        } catch (RuntimeException e) {
            this.outputView.printErrorMessage(e.getMessage());
            return repeat(runnable);
        }
    }
}
