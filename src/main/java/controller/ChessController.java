package controller;

import controller.command.Command;
import controller.command.End;
import controller.command.Move;
import controller.mapper.GameStatusMapper;
import controller.mapper.PieceMapper;
import domain.game.Game;
import domain.game.GameStatus;
import domain.game.Position;
import domain.game.Score;
import domain.piece.Piece;
import java.util.LinkedHashMap;
import java.util.Map;
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
            printChessBoardOf(game);
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
        printGameResult(game);
    }

    private Command moveByUserCommand(Game game) {
        Command command = this.inputView.requestUserCommand();
        if (command.isEnd()) {
            return command;
        }
        moveByPositionsOfMoveCommand(game, command);
        if (isInProgress(game)) {
            return new End();
        }
        printChessBoardOf(game);
        return command;
    }

    private static boolean isInProgress(Game game) {
        return !game.checkStatus().equals(GameStatus.IN_PROGRESS);
    }

    private void printChessBoardOf(Game game) {
        Map<Position, Piece> chessBoard = game.getChessBoard();
        Map<Position, String> chessBoardOfForPrint = new LinkedHashMap<>();
        for (Map.Entry<Position, Piece> positionPieceEntry : chessBoard.entrySet()) {
            chessBoardOfForPrint.put(
                    positionPieceEntry.getKey(),
                    PieceMapper.convertPieceCategoryToText(positionPieceEntry.getValue().getCategory()));
        }
        this.outputView.printChessBoard(chessBoardOfForPrint);
    }

    private static void moveByPositionsOfMoveCommand(Game game, Command command) {
        Move moveCommand = (Move) command;
        Position sourcePosition = Position.of(moveCommand.getSourceFile(), moveCommand.getSourceRank());
        Position targetPosition = Position.of(moveCommand.getTargetFile(), moveCommand.getTargetRank());
        game.move(sourcePosition, targetPosition);
    }

    private void printGameResult(Game game) {
        String gameStatusText = GameStatusMapper.convertGameStatusToText(game.checkStatus());
        Score whiteScore = game.calculateWhiteScore();
        Score blackScore = game.calculateBlackScore();
        this.outputView.printGameResult(gameStatusText, whiteScore, blackScore);
    }

    private <T> T repeat(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (IllegalStateException e) {
            System.out.println("Log: " + e.getMessage()); // log 기록
            this.outputView.printServerErrorMessage();
            return repeat(supplier);
        } catch (RuntimeException e) {
            this.outputView.printErrorMessage(e.getMessage());
            return repeat(supplier);
        }
    }
}
