package controller;

import controller.command.Command;
import controller.command.End;
import controller.command.Move;
import controller.mapper.PieceMapper;
import domain.game.Game;
import domain.game.Position;
import domain.piece.Piece;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;
import view.InputView;
import view.OutputView;

<<<<<<< HEAD
=======
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

>>>>>>> a58948a (refactor: 게임 로직 구현)
public class ChessController {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void run() {
        this.outputView.printGameGuideMessage();
<<<<<<< HEAD
        Game game = repeat(this::startGame);
        proceed(game);
    }

    private Game startGame() {
        Command command = this.inputView.requestUserCommand();
        if (command.isStart()) {
            Game game = Game.create();
            printChessBoardOf(game);
            return game;
        }
        throw new IllegalArgumentException("게임 시작하려면 먼저 start를 입력하세요.");
=======
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
>>>>>>> a58948a (refactor: 게임 로직 구현)
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
<<<<<<< HEAD
        moveByPositionsOfMoveCommand(game, command);
        if (game.isEnd()) {
            return new End();
=======
    }

    private GameCommand convertUserInputToGameCommandAfterStart(String userInput) {
        List<String> inputs = Arrays.asList(userInput.split(" "));
        if (inputs.size() != 3) {
            throw new IllegalArgumentException("올바르지 않은 움직임입니다. (예: move b2 b3)");
        }
        GameCommand command = GameCommand.from(inputs.get(0));

        if (command.equals(GameCommand.START)) {
            throw new IllegalArgumentException("게임이 이미 실행중입니다.");
>>>>>>> a58948a (refactor: 게임 로직 구현)
        }
        printChessBoardOf(game);
        return command;
    }

<<<<<<< HEAD
    private static void moveByPositionsOfMoveCommand(Game game, Command command) {
        Move moveCommand = (Move) command;
        Position sourcePosition = Position.of(moveCommand.getSourceFile(), moveCommand.getSourceRank());
        Position targetPosition = Position.of(moveCommand.getTargetFile(), moveCommand.getTargetRank());
        game.move(sourcePosition, targetPosition);
    }

    private void printChessBoardOf(Game game) {
        Map<Position, Piece> chessBoard = game.getChessBoard();
        Map<Position, String> chessBoardOfForPrint = new LinkedHashMap<>();
        for (Map.Entry<Position, Piece> positionPieceEntry : chessBoard.entrySet()) {
            chessBoardOfForPrint.put(
                    positionPieceEntry.getKey(),
                    PieceMapper.convertPieceCategoryToText(positionPieceEntry.getValue().getCategory()));
=======
    private Side changeSide(Side side) {
        if (side.equals(Side.WHITE)) {
            return Side.BLACK;

>>>>>>> a58948a (refactor: 게임 로직 구현)
        }
        this.outputView.printChessBoard(chessBoardOfForPrint);
    }

<<<<<<< HEAD
    private <T> T repeat(Supplier<T> supplier) {
=======
    private Runnable repeatByRunnable(Runnable runnable) {
>>>>>>> a58948a (refactor: 게임 로직 구현)
        try {
            return supplier.get();
        } catch (IllegalStateException e) {
            System.out.println("Log: " + e.getMessage()); // log 기록
            this.outputView.printServerErrorMessage();
            return repeat(supplier);
        } catch (RuntimeException e) {
            this.outputView.printErrorMessage(e.getMessage());
<<<<<<< HEAD
            return repeat(supplier);
=======
            return repeatByRunnable(runnable);
        }
    }

    private GameCommand repeatBySupplier(Supplier<GameCommand> supplier) {
        try {
            return supplier.get();
        } catch (RuntimeException e) {
            this.outputView.printErrorMessage(e.getMessage());
            return repeatBySupplier(supplier);
>>>>>>> a58948a (refactor: 게임 로직 구현)
        }
    }
}
