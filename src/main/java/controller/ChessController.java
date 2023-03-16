package controller;

import domain.game.Game;
import domain.piece.Position;
import domain.piece.Side;
import view.GameCommand;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class ChessController {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void run() {
        Game game = Game.create();
        this.outputView.printGameGuideMessage();
        repeatByRunnable(this::getStartCommand);

        Side side = Side.WHITE;
        this.outputView.printChessBoard(game.getChessBoard());

        GameCommand gameCommand;
        do {
            Side finalSide = side;
            this.outputView.printSide(side);
            gameCommand = repeatBySupplier(() -> moveByUserInput(game, finalSide));
            this.outputView.printChessBoard(game.getChessBoard());
            side = changeSide(side);
        } while (!gameCommand.equals(GameCommand.END));
    }

    private GameCommand moveByUserInput(Game game, Side side) {
        String userInput;
        GameCommand gameCommand;
        userInput = this.inputView.requestUserInput();
        if (userInput.equals("end")) {
            return GameCommand.END;
        }
        gameCommand = convertUserInputToGameCommandAfterStart(userInput);
        List<Position> positions = convertUserInputToPositions(userInput);
        Position sourcePosition = positions.get(0);
        Position targetPosition = positions.get(1);
        game.move(side, sourcePosition, targetPosition);
        return gameCommand;
    }

    private List<Position> convertUserInputToPositions(String userInput) {
        List<String> inputs = Arrays.asList(userInput.split(" "));
        String sourceString = inputs.get(1);
        String targetString = inputs.get(2);
        List<String> sourceFileAndRank = Arrays.asList(sourceString.split(""));
        List<String> targetFileAndRank = Arrays.asList(targetString.split(""));
        List<Position> positions = new ArrayList<>();
        positions.add(Position.of(sourceFileAndRank.get(0), sourceFileAndRank.get(1)));
        positions.add(Position.of(targetFileAndRank.get(0), targetFileAndRank.get(1)));
        return positions;
    }

    private void getStartCommand() {
        String startCommand = this.inputView.requestUserInput();
        if (!GameCommand.from(startCommand).equals(GameCommand.START)) {
            throw new IllegalArgumentException("게임 시작하려면 먼저 start를 입력하세요.");
        }
    }

    private GameCommand convertUserInputToGameCommandAfterStart(String userInput) {
        List<String> inputs = Arrays.asList(userInput.split(" "));
        if (inputs.size() != 3) {
            throw new IllegalArgumentException("올바르지 않은 움직임입니다. (예: move b2 b3)");
        }
        GameCommand command = GameCommand.from(inputs.get(0));

        if (command.equals(GameCommand.START)) {
            throw new IllegalArgumentException("게임이 이미 실행중입니다.");
        }
        return command;
    }

    private Side changeSide(Side side) {
        if (side.equals(Side.WHITE)) {
            return Side.BLACK;

        }
        return Side.WHITE;
    }

    private Runnable repeatByRunnable(Runnable runnable) {
        try {
            runnable.run();
            return runnable;
        } catch (RuntimeException e) {
            this.outputView.printErrorMessage(e.getMessage());
            return repeatByRunnable(runnable);
        }
    }

    private GameCommand repeatBySupplier(Supplier<GameCommand> supplier) {
        try {
            return supplier.get();
        } catch (RuntimeException e) {
            this.outputView.printErrorMessage(e.getMessage());
            return repeatBySupplier(supplier);
        }
    }
}
