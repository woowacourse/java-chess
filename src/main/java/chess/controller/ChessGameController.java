package chess.controller;

import chess.domain.board.Board;
import chess.domain.board.InitBoardGenerator;
import chess.domain.board.LineDto;
import chess.domain.command.Command;
import chess.domain.command.Commands;
import chess.domain.game.ChessGame;
import chess.utils.DtoAssembler;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;

public class ChessGameController {
    public void run() {
        ChessGame chessGame = new ChessGame(new Board(InitBoardGenerator.initLines()));
        Commands commands = Commands.initCommands(chessGame);
        OutputView.printCommandNotice();
        while (chessGame.isNotFinished()) {
            playChess(chessGame, commands);
        }
        // 게임 종료 후 3단계 승패코드
    }

    private void playChess(ChessGame chessGame, Commands commands) {
        try {
            String input = InputView.command();
            Command command = commands.matchedCommand(input);
            command.execution(input);
            printBoardStatus(chessGame);
        } catch (IllegalStateException | IllegalArgumentException e) {
            OutputView.printErrorException(e.getMessage());
        }
    }

    private void printBoardStatus(ChessGame chessGame) {
        List<LineDto> boardDto = DtoAssembler.assemble(chessGame.board());
        OutputView.printBoard(boardDto);
    }
}
