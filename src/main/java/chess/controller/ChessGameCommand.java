package chess.controller;

import chess.domain.board.Square;
import java.util.List;

public class ChessGameCommand {

    private final ChessExecuteCommand chessExecuteCommand;
    private final Square source;
    private final Square destination;

    private ChessGameCommand(final ChessExecuteCommand chessExecuteCommand) {
        this(chessExecuteCommand, null, null);
    }

    private ChessGameCommand(
            final ChessExecuteCommand chessExecuteCommand,
            final Square source,
            final Square destination
    ) {
        this.chessExecuteCommand = chessExecuteCommand;
        this.source = source;
        this.destination = destination;
    }

    public static ChessGameCommand from(final List<String> inputs) {
        validateEmpty(inputs);
        final ChessExecuteCommand chessExecuteCommand = ChessExecuteCommand.from(inputs.get(0));
        if (chessExecuteCommand == ChessExecuteCommand.STOP) {
            return getEndCommand(inputs, chessExecuteCommand);
        }
        return getMoveCommand(inputs, chessExecuteCommand);
    }

    private static void validateEmpty(final List<String> inputs) {
        if (inputs.isEmpty()) {
            throw new IllegalArgumentException("move랑 end 중 입력하세요.");
        }
    }

    private static ChessGameCommand getEndCommand(
            final List<String> inputs,
            final ChessExecuteCommand chessExecuteCommand
    ) {
        validateEnd(inputs);
        return new ChessGameCommand(chessExecuteCommand);
    }

    private static void validateEnd(final List<String> inputs) {
        if (inputs.size() != 1) {
            throw new IllegalArgumentException("end만 입력해야 합니다.");
        }
    }

    private static ChessGameCommand getMoveCommand(
            final List<String> inputs,
            final ChessExecuteCommand chessExecuteCommand
    ) {
        validateMove(inputs);
        return new ChessGameCommand(chessExecuteCommand, Square.from(inputs.get(1)), Square.from(inputs.get(2)));
    }

    private static void validateMove(final List<String> inputs) {
        if (inputs.size() != 3) {
            throw new IllegalArgumentException("(예. move b2 b3)와 같이 입력해야 합니다.");
        }
    }

    public ChessExecuteCommand getChessExecuteCommand() {
        return chessExecuteCommand;
    }

    public Square getSource() {
        return source;
    }

    public Square getDestination() {
        return destination;
    }
}
