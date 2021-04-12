package chess.controller.command;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;
import chess.dto.PiecesDTO;
import chess.view.OutputView;
import java.util.Map;
import java.util.Objects;

public final class Play extends AbstractCommand {

    private static final String NEW_LINE = System.lineSeparator();
    private static final int CHESS_SIZE = 8;
    private static final int MOVE_COMMAND_SIZE = 3;
    private static final int TARGET_INDEX = 2;
    private static final int SOURCE_INDEX = 1;
    private static final String CHESS_COLUMN = "abcdefgh";

    public Play(final Board board) {
        super(board);
        board.init();
        printBoard();
    }

    @Override
    public Command execute(final String command) {
        if ("end".equals(command)) {
            return new Finish(board);
        }
        if (commandIsStatus(command)) {
            return this;
        }
        final String[] commands = command.split(" ");
        if (command.startsWith("move") && validateMove(commands)) {
            pieceMove(commands);
            printBoard();
            return this;
        }
        printErrorMessage("end, status, move");
        return this;
    }

    private void pieceMove(final String[] commands) {
        try {
            board.movePiece(commands[SOURCE_INDEX], commands[TARGET_INDEX]);
        } catch (Exception e) {
            OutputView.printErrorMessage(e.getMessage());
        }
    }

    private boolean validateMove(final String[] commands) {
        return commands.length == MOVE_COMMAND_SIZE;
    }

    private void printBoard() {
        final Map<Position, Piece> pieces = board.pieces();
        final PiecesDTO piecesDTO = new PiecesDTO(piecesToString(pieces));

        OutputView.printBoard(piecesDTO);
    }

    private String piecesToString(final Map<Position, Piece> pieces) {
        final StringBuilder stringBuilder = new StringBuilder();
        int count = 0;
        for (Piece piece : pieces.values()) {
            addSymbol(stringBuilder, piece);
            count++;
            chessNewLine(stringBuilder, count);
        }
        stringBuilder.append(NEW_LINE);
        stringBuilder.append(CHESS_COLUMN);
        return stringBuilder.toString();
    }

    private void addSymbol(final StringBuilder stringBuilder, final Piece piece) {
        if (Objects.isNull(piece)) {
            stringBuilder.append(".");
            return;
        }
        stringBuilder.append(piece.symbol());
    }

    private void chessNewLine(final StringBuilder stringBuilder, final int count) {
        if (count != 0 && count % CHESS_SIZE == 0) {
            stringBuilder.append("  ");
            stringBuilder.append(chessRow(count));
            stringBuilder.append(NEW_LINE);
        }
    }

    private int chessRow(final int count) {
        return (CHESS_SIZE + 1) - (count / CHESS_SIZE);
    }
}
