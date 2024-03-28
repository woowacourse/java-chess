package chess.controller;

import chess.dto.BoardDTO;
import chess.dto.ScoreDTO;
import chess.model.board.Board;
import chess.model.board.InitialBoardGenerator;
import chess.model.piece.Color;
import chess.model.position.Movement;
import chess.model.position.Position;
import chess.model.score.ScoreGenerator;
import chess.service.BoardService;
import chess.view.Command;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.function.Supplier;

public class ChessGameController {
    private final OutputView outputView;
    private final InputView inputView;
    private final BoardService boardService;

    public ChessGameController(OutputView outputView, InputView inputView, BoardService boardService) {
        this.outputView = outputView;
        this.inputView = inputView;
        this.boardService = boardService;
    }

    public void run() {
        outputView.printGameIntro();
        while (getValidCommand() != Command.START) {
            outputView.printException("게임을 시작하려면 start를 입력하세요.");
        }
        start();
    }

    private void start() {
        Board board = getOrCreateBoard();
        GameStatus gameStatus = new GameStatus();
        showBoard(board);
        while (gameStatus.isRunning()) {
            retryOnException(() -> playTurn(gameStatus, board));
        }
        boardService.saveBoard(board);
    }

    private Board getOrCreateBoard() {
        if (boardService.isBoardExist()) {
            return boardService.getBoard();
        }
        return new InitialBoardGenerator().create();
    }

    private void showBoard(Board board) {
        BoardDTO boardDTO = BoardDTO.from(board);
        outputView.printBoard(boardDTO);
    }

    private void playTurn(GameStatus gameStatus, Board board) {
        Command command = inputView.askCommand();
        validateCommandNotStart(command);
        if (command == Command.MOVE) {
            moveAndShowResult(gameStatus, board);
            return;
        }
        if (command == Command.END) {
            gameStatus.stop();
            return;
        }
        showResult(board);
    }

    private void validateCommandNotStart(Command command) {
        if (command == Command.START) {
            throw new IllegalArgumentException("게임이 이미 시작되었습니다.");
        }
    }

    private void moveAndShowResult(GameStatus gameStatus, Board board) {
        move(board);
        showBoard(board);
        Color winnerColor = board.getWinnerColor();
        if (winnerColor == Color.NONE) {
            return;
        }
        gameStatus.stop();
        outputView.printWinner(winnerColor.name());
    }

    private void move(Board board) {
        Position source = inputView.askPosition();
        Position destination = inputView.askPosition();
        Movement movement = new Movement(source, destination);
        board.move(movement);
    }

    private void showResult(Board board) {
        ScoreGenerator scoreGenerator = new ScoreGenerator(board);
        ScoreDTO scoreDTO = scoreGenerator.calculateScore();
        outputView.printScore(scoreDTO);
    }

    private Command getValidCommand() {
        return retryOnException(inputView::askCommand);
    }

    private void retryOnException(Runnable action) {
        try {
            action.run();
        } catch (IllegalArgumentException e) {
            outputView.printException(e.getMessage());
            retryOnException(action);
        }
    }

    private <T> T retryOnException(Supplier<T> retryOperation) {
        try {
            return retryOperation.get();
        } catch (IllegalArgumentException e) {
            outputView.printException(e.getMessage());
            return retryOnException(retryOperation);
        }
    }
}
