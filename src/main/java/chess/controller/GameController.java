package chess.controller;

import static chess.domain.Command.END;
import static chess.domain.Command.MOVE;
import static chess.domain.Command.START;
import static chess.domain.Team.WHITE;

import chess.ChessGame;
import chess.domain.Board;
import chess.domain.Command;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;

public class GameController {

    private final InputView inputView;
    private final OutputView outputView;

    public GameController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        startGame();
        ChessGame chessGame = new ChessGame(new Board(), WHITE);
        Board board = chessGame.getBoard();
        outputView.printChessBoard(board.getPieces());
        progressGame(chessGame);
    }

    private void startGame() {
        outputView.printGameStart();
        List<String> gameCommand = inputView.readGameCommand();
        Command command = Command.from(gameCommand.get(0));
        try {
            checkCorrectCommand(command, START);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            startGame();
        }
    }

    private void progressGame(ChessGame chessGame) {
        while (true) {
            List<String> gameCommand = inputView.readGameCommand();
            Command command = Command.from(gameCommand.get(0));
            if (command == END) {
                break;
            }
            checkCorrectCommand(command, MOVE);
            validateMoveCommandFormat(gameCommand);

            chessGame.movePiece(gameCommand.get(1), gameCommand.get(2));
            outputView.printChessBoard(chessGame.getBoard().getPieces());
        }
    }

    private void checkCorrectCommand(final Command userCommand, final Command expected) {
        if (userCommand != expected) {
            throw new IllegalArgumentException("올바른 command를 입력해주세요. 게임 시작은 start로, 게임 진행은 move로, 게임 종료는 end로 할 수 있습니다.");
        }
    }

    private void validateMoveCommandFormat(final List<String> gameCommand) {
        if (gameCommand.size() != 3) {
            throw new IllegalArgumentException("move source위치 target위치 형태로 입력해주세요. 예) move a2 a3");
        }
    }
}
