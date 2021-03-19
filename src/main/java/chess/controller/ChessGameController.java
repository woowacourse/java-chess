package chess.controller;

import chess.ChessGame;
import chess.domain.Team;
import chess.domain.position.Col;
import chess.domain.position.Position;
import chess.domain.position.Row;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessGameController {
    private final ChessGame chessGame;
    private boolean gameFlag = true;
    private String command;

    public ChessGameController(final ChessGame chessGame) {
        this.chessGame = chessGame;
    }


    public void run() {
        OutputView.printStartMessage();

        while (gameFlag) {
            command = InputView.getCommand();
            if ("end".equals(command)) {
                gameFlag = false;
            }

            if ("start".equals(command)) {
                initSetting();
            }

            if ("move".equals(command)) {
                try {
                    move();
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                    command = InputView.getCommand();
                    move();
                }
            }
        }
    }

    private void move() {
        String startPointStr = InputView.getPoint();
        String endPointStr = InputView.getPoint();

        System.out.println(startPointStr);
        System.out.println(endPointStr);

        Position startPoint = new Position(Row.getLocation(startPointStr.charAt(1) + ""), Col.getLocation(startPointStr.charAt(0) + ""));
        Position endPoint = new Position(Row.getLocation(endPointStr.charAt(1) + ""), Col.getLocation(endPointStr.charAt(0) + ""));

        chessGame.move(startPoint, endPoint, Team.WHITE);
        printBoard();
    }

    private void initSetting() {
        chessGame.initSetting();
        printBoard();
    }

    private void printBoard() {
        OutputView.printBoard(chessGame.getBoard());
    }
}
