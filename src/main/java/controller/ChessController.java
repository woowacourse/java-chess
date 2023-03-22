package controller;

import controller.command.Command;
import controller.command.Move;
import domain.game.Game;
import domain.piece.Position;
import java.util.function.Supplier;
import view.InputView;
import view.OutputView;

public class ChessController {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void run() {
        this.outputView.printGameGuideMessage();
        Game game = repeat(this::startGame);
        proceed(game);
    }

    private Game startGame() {
        Command command = this.inputView.requestUserCommand();
        if (command.isStart()) {
            Game game = Game.create();
            this.outputView.printChessBoard(game.getChessBoard());
            return game;
        }
        throw new IllegalArgumentException("게임 시작하려면 먼저 start를 입력하세요.");
    }

    private void proceed(Game game) {
        Command command;
        do {
            this.outputView.printSideOfTurn(game.getSideOfTurn());
            command = repeat(() -> moveByUserCommand(game));
        } while (command.isMove());
    }

    private Command moveByUserCommand(Game game) {
        Command command = this.inputView.requestUserCommand();
        if (command.isEnd()) {
            return command;
        }
        Move moveCommand = (Move) command;
        Position sourcePosition = new Position(moveCommand.getSourceFile(), moveCommand.getSourceRank());
        Position targetPosition = new Position(moveCommand.getTargetFile(), moveCommand.getTargetRank());
        game.move(sourcePosition, targetPosition);
        this.outputView.printChessBoard(game.getChessBoard());
        return command;
    }

    private <T> T repeat(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (RuntimeException e) {
            this.outputView.printErrorMessage(e.getMessage());
            return repeat(supplier);
        }
    }
}
