package chessgame.controller;

import java.util.Optional;

import chessgame.db.BoardDao;
import chessgame.db.BoardService;
import chessgame.domain.Board;
import chessgame.domain.Game;
import chessgame.view.InputView;
import chessgame.view.OutputView;

public class ChessController {
    private final InputView inputView;
    private final OutputView outputView;

    public ChessController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run(BoardDao boardDao) {
        BoardService boardService = new BoardService(boardDao);
        Game game = loadGame(boardService);
        playGame(game, boardService);
    }

    private Game loadGame(BoardService boardService) {
        Optional<Board> board;
        int boardNo;
        do {
            boardNo = inputView.readBoardNo(boardService.getRunningBoards());
            board = getBoard(boardService, boardNo);
        } while (board.isEmpty());
        return new Game(board.get(), boardService.getState(boardNo));
    }

    private Optional<Board> getBoard(BoardService boardService, int boardNo) {
        try {
            Board board = boardService.getBoard(boardNo);
            return Optional.of(board);
        } catch (IllegalArgumentException e) {
            outputView.printErrorMsg(e.getMessage());
            return Optional.empty();
        }
    }

    private void playGame(Game game, BoardService boardService) {
        outputView.printStartMessage();
        do {
            eachTurn(game);
            boardService.updateBoard(game);
        } while (game.isNotEnd());
    }

    private void eachTurn(Game game) {
        try {
            Command command = Command.of(inputView.readCommand());
            game.setFrom(command);
            printChessBoard(command, game);
        } catch (IllegalArgumentException e) {
            outputView.printErrorMsg(e.getMessage());
        }
    }

    private void printChessBoard(Command command, Game game) {
        if (game.isRunning()) {
            outputView.printChessBoard(game.board());
        }
        if (command.isStatus()) {
            outputView.printScore(game.score());
        }
        if (game.isEnd()) {
            outputView.printWinner(game.winner());
        }
    }
}
