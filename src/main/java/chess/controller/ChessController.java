package chess.controller;

import chess.domain.ChessGame;
import chess.domain.command.Command;
import chess.domain.command.Commands;
import chess.domain.dto.BoardDto;
import chess.domain.dto.PointDto;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    public void run() {
        ChessGame chessGame = new ChessGame();
        OutputView.printCommandInfo();
        while (chessGame.isRunning()) {
            final Commands inputCmd = InputView.inputCommand();
            Command command = Command.of(inputCmd.mainCommand());
            command.apply(chessGame, inputCmd);
            view(chessGame, command); // view 작업을 여기서 해줌.
        }
    }

    private void view(ChessGame chessGame, Command command) {
        if (command.isStart()) {
            OutputView.printBoard(new BoardDto(chessGame.board(), chessGame.turn()));
        }
        if (command.isMove()) {
            OutputView.printBoard(new BoardDto(chessGame.board(), chessGame.turn()));
            confirmKingDead(chessGame);
        }
        if (command.isStatus()) {
            OutputView.printStatus(new PointDto(chessGame.calculatePoint()));
        }
    }

    private void confirmKingDead(ChessGame chessGame) {
        if (chessGame.isEnd()) {
            OutputView.printWinner(chessGame.winner());
        }
    }
//    public static void start(ChessGame chessGame, Commands command) {
//        chessGame.initBoard(BoardInitializer.init());
//        OutputView.printBoard(new BoardDto(chessGame.board(), chessGame.turn()));
//    }
//    public static void move(ChessGame chessGame, Commands command) {
//        if (chessGame.isReady() || chessGame.isEnd()) {
//            throw new IllegalArgumentException("[ERROR] 게임이 초기화되지 않았거나, 종료되지 않았습니다.");
//        }
//        chessGame.move(command);
//        OutputView.printBoard(new BoardDto(chessGame.board(), chessGame.turn()));
//        confirmKingDead(chessGame);
//    }
//
//    public static void end(ChessGame chessGame, Commands command) {
//        chessGame.endGame();
//    }
//
//    public static void status(ChessGame chessGame, Commands command) {
//        if (chessGame.isReady()) {
//            throw new IllegalArgumentException("[ERROR] 게임이 초기화되지 않았습니다.");
//        }
//        OutputView.printStatus(chessGame.calculatePoint());
//    }
//
//    private static void confirmKingDead(ChessGame chessGame) {
//        if (chessGame.isEnd()) {
//            OutputView.printWinner(chessGame.winner());
//        }
//    }
}
