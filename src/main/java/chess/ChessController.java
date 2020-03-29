package chess;

import chess.domain.GameResult;
import chess.domain.board.Board;
import chess.domain.command.Command;
import chess.domain.command.MoveCommand;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {
    public static void run() {
        Board board = new Board();
        OutputView.printBoard(board);

        PieceColor team = PieceColor.WHITE;

        while (!(board.isWhiteKingKilled() || board.isBlackKingKilled())) {
            OutputView.printTurn(team);

            try {
                MoveCommand moveCommand = new MoveCommand(InputView.requestMoveCommand());
                Piece piece = board.findPiece(moveCommand.getSourcePosition(), team);
                board.checkPath(piece, moveCommand.getTargetPosition());
                board.move(piece, moveCommand.getTargetPosition());
                OutputView.printBoard(board);
                team = team.change();
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e.getMessage());
            }
        }

        Command statusCommand;
        do {
            statusCommand = resolveCommand();
        } while (statusCommand == null);

        GameResult gameResult = board.createGameResult();
        OutputView.printResult(gameResult);
    }

    private static Command resolveCommand() {
        try {
            return Command.of(InputView.requestStatusCommand());
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return null;
        }
    }
}
