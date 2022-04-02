package chess;

import chess.Controller.ChessController;
import chess.Controller.command.Command;
import chess.Controller.command.ParsedCommand;
import chess.Controller.dto.PiecesDto;
import chess.Controller.dto.ScoreDto;
import chess.Controller.dto.StateDto;
import chess.console.view.InputView;
import chess.console.view.OutputView;
import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.board.strategy.CreateCompleteBoardStrategy;
import chess.domain.piece.Piece;
import java.util.Map;

public class ConsoleApplication {

    public static void main(String[] args) {
        final Map<Position, Piece> pieces = (new CreateCompleteBoardStrategy()).createPieces();
        final ChessController chess = new ChessController(new Board(pieces));
        OutputView.printStartMessage();
        StateDto state = chess.getCurrentStatus();
        while (!isEnd(state)) {
            repeatTurn(chess);
            state = chess.getCurrentStatus();
        }
    }

    private static boolean isEnd(final StateDto stateDto) {
        return stateDto.getState().equals("END");
    }

    private static void repeatTurn(final ChessController chess) {
        try {
            operateOnce(chess);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            repeatTurn(chess);
        }
    }

    private static void operateOnce(final ChessController chessController) {
        final ParsedCommand parsedCommand = new ParsedCommand(InputView.input());
        if (isCommandRelatedToScore(parsedCommand)) {
            final ScoreDto scoreDto = chessController.doActionAboutScore(parsedCommand);
            OutputView.printStatus(scoreDto);
            return;
        }
        final PiecesDto piecesDto = chessController.doActionAboutPieces(parsedCommand);
        OutputView.printBoard(piecesDto);
    }

    private static boolean isCommandRelatedToScore(final ParsedCommand parsedCommand) {
        final Command command = parsedCommand.getCommand();
        return command == Command.END || command == Command.STATUS;
    }
}
