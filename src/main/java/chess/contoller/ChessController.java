package chess.contoller;

import chess.domain.board.Board;
import chess.domain.board.Team;
import chess.domain.chessgame.ChessGame;
import chess.domain.chessgame.Turn;
import chess.domain.command.Command;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    public void play() {
        Board board = new Board();
        ChessGame chessGame = new ChessGame(board, new Turn(Team.WHITE));
        OutputView.printStartInfo();
        proceed(chessGame, board);
        printGameResult(chessGame);
    }

    private void proceed(ChessGame chessGame, Board board) {
        while (chessGame.isContinue()) {
            inputCommandAndExecute(chessGame);
            OutputView.printCurrentTurn(chessGame.currentTurn());
            OutputView.printChessBoard(board.boardDto());
        }
    }

    private void inputCommandAndExecute(ChessGame chessGame) {
        String input = InputView.InputString();
        Command command = Command.commandByInput(input);
        command.execute(chessGame, Command.arguments(input));
        printScoreIfStatus(chessGame, command);
    }

    private void printScoreIfStatus(ChessGame chessGame, Command command) {
        if (command == Command.STATUS) {
            OutputView.printTeamScore(chessGame.score(Team.WHITE), Team.WHITE);
            OutputView.printTeamScore(chessGame.score(Team.BLACK), Team.BLACK);
        }
    }

    private void printGameResult(ChessGame chessGame) {
        OutputView.printWinner(chessGame.winner());
    }
}
