package chess.controller;

import chess.controller.state.Start;
import chess.controller.state.State;
import chess.dao.ChessGameDao;
import chess.dao.ChessGameLoader;
import chess.domain.game.ChessGame;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;
import java.util.Map;

public class ChessController {
    private final ChessGameDao chessGameDao;

    public ChessController(final ChessGameDao chessGameDao) {
        this.chessGameDao = chessGameDao;
    }

    public void run() {
        OutputView.printStartMessage();
        ChessGame chessGame = ChessGameLoader.load(chessGameDao);
        State gameStatus = new Start(chessGame);
        play(chessGame, gameStatus);
    }

    private void play(ChessGame chessGame, State gameStatus) {
        while (!chessGame.isEnd() && gameStatus.isRun()) {
            gameStatus = getStatus(gameStatus);
            chessGameDao.update(chessGame);
            printChessBoard(gameStatus, chessGame.getChessBoard());
        }
        if (chessGame.isEnd()) {
            chessGameDao.init(chessGame);
        }
    }

    private State getStatus(State gameStatus) {
        try {
            List<String> commands = InputView.getCommand();
            final Command command = Command.findCommand(commands);
            return gameStatus.checkCommand(command);
        } catch (IllegalArgumentException | IllegalStateException e) {
            OutputView.print(e.getMessage());
            return getStatus(gameStatus);
        }
    }

    private void printChessBoard(State gameStatus, Map<Position, Piece> board) {
        if (gameStatus.isRun()) {
            ChessBoardDto chessBoardDTO = new ChessBoardDto(board);
            OutputView.print(chessBoardDTO.getBoardMessage().toString());
        }
    }
}
