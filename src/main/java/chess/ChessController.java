package chess;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;
import chess.dto.BoardDTO;
import chess.dto.PiecesDTO;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.Map;

public final class ChessController {

    private static final String newline = System.lineSeparator();
    private static final int CHESS_SIZE = 8;

    public void run() {
        OutputView.printStart();
        final Board board = new Board();
        Command command = new Command();
        while (!command.isExit()) {
            command = new Command(InputView.askCommand());
            commandIsStart(board, command);
            commandIsStatus(board, command);
        }
    }

    private void commandIsStart(final Board board, final Command command) {
        if (command.isStart()) {
            board.init();
            printBoard(board);
            playChess(board);
        }
    }

    private void playChess(final Board board) {
        Command command = new Command();
        while (!board.isFinish() && !command.isEnd()) {
            command = new Command(InputView.askCommand());
            commandIsMove(board, command);
            commandIsStatus(board, command);
            printBoard(board);
        }
    }

    private void commandIsMove(Board board, Command command) {
        if (command.isMove()) {
            movePiece(board, command);
        }
    }

    private void movePiece(Board board, Command command) {
        try {
            board.movePiece(command.sourcePosition(), command.targetPosition());
        } catch (Exception e) {
            OutputView.printErrorMessage(e.getMessage());
        }
    }

    private void printBoard(final Board board) {
        final Map<Position, Piece> pieces = board.pieces();
        final PiecesDTO piecesDTO = new PiecesDTO(piecesToString(pieces));

        OutputView.printBoard(piecesDTO);
    }

    private void commandIsStatus(final Board board, final Command command) {
        if (command.isStatus()) {
            OutputView.printStatus(new BoardDTO(board));
        }
    }

    private String piecesToString(final Map<Position, Piece> pieces) {
        final StringBuilder stringBuilder = new StringBuilder();
        int count = 0;
        for (Piece piece : pieces.values()) {
            stringBuilder.append(piece.symbol());
            count++;
            chessNewLine(stringBuilder, count);
        }
        return stringBuilder.toString();
    }

    private void chessNewLine(final StringBuilder stringBuilder, final int count) {
        if (count != 0 && count % CHESS_SIZE == 0) {
            stringBuilder.append(newline);
        }
    }
}
