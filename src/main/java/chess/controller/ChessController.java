package chess.controller;

import chess.domain.BoardFactory;
import chess.domain.ChessGame;
import chess.domain.Command;
import chess.domain.point.File;
import chess.domain.point.Point;
import chess.domain.point.Rank;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    private final InputView inputView;
    private final OutputView outputView;

    public ChessController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printGameStart();

        ChessGame game = new ChessGame(BoardFactory.createEmptyBoard());
        while (true) {
            try {
                String readCommand = inputView.readCommand();
                Command command = Command.from(readCommand);
                if (command.isEnd()) {
                    break;
                }
                if (Command.START == command) {
                    game = new ChessGame(BoardFactory.createInitialChessBoard());
                }
                if (Command.MOVE == command) {
                    pieceMove(readCommand, game);
                }
                outputView.printBoard(game.getBoard());
            } catch (Exception e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private void pieceMove(String readCommand, ChessGame game) {
        Point departure;
        Point destination;
        try {
            String[] splitCommand = readCommand.split(" ");
            departure = parsePoint(splitCommand[1]);
            destination = parsePoint(splitCommand[2]);
        } catch (Exception e) {
            throw new IllegalArgumentException("잘못된 위치를 입력하였습니다.");
        }
        game.move(departure, destination);
    }

    private Point parsePoint(String splitedCommand) {
        File file = File.of(splitedCommand.charAt(0));
        Rank rank = Rank.of(Integer.parseInt(String.valueOf(splitedCommand.charAt(1))));
        return Point.of(file, rank);
    }
}
