package chess.controller;

import chess.domain.ChessGame;
import chess.domain.board.Board;
import chess.domain.command.Command;
import chess.dto.BoardDto;
import chess.dto.BoardStatusDto;
import chess.exception.GameIsNotStartException;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;

public class ChessController {
    private static final String END_TRUE = "true";

    private ChessGame chessGame;

    public void run(ChessGame chessGame) {
        OutputView.printManual();
        while (chessGame.isBeforeEnd()) {
            playGame(chessGame);
        }
    }

    public void playGame(ChessGame chessGame) {
        try {
            String inputCommand = InputView.inputCommand();
            Command.findCommand(inputCommand).execute(chessGame, inputCommand);
            checkGameStart(chessGame);
            OutputView.printBoard(chessGame.getBoard());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void checkGameStart(ChessGame chessGame) {
        if (chessGame.isBeforeStart()) {
            throw new GameIsNotStartException();
        }
    }

    public BoardDto start(List<String> commands) {
        chessGame = new ChessGame();
        chessGame.settingBoard();

        for (String command : commands) {
            chessGame.move(command);
        }

        Board board = chessGame.getBoard();
        return new BoardDto(board, chessGame.turn());
    }

    public BoardDto move(String target, String destination) {
        chessGame.move(target, destination);
        if (chessGame.isBeforeEnd()) {
            return new BoardDto(chessGame.getBoard(), chessGame.turn());
        }
        return new BoardDto(chessGame.getBoard(), chessGame.turn().name(), END_TRUE);
    }

    public List<String> movablePosition(String target) {
        return chessGame.findMovablePosition(target);
    }

    public BoardDto boardDto() {
        return new BoardDto(chessGame.getBoard(), chessGame.turn());
    }

    public BoardStatusDto boardStatusDto() {
        return new BoardStatusDto(chessGame.boardStatus());
    }
}