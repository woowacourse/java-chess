package chess.view.input.command;

import chess.domain.position.Position;
import chess.view.input.ViewFile;
import chess.view.input.ViewRank;

public class ChessCommandDto {

    private static final int FILE_INDEX = 0;
    private static final int RANK_INDEX = 1;

    private final ChessCommand chessCommand;
    private final Position fromPosition;
    private final Position toPosition;

    public ChessCommandDto(ChessCommand chessCommand, Position fromPosition, Position toPosition) {
        this.chessCommand = chessCommand;
        this.fromPosition = fromPosition;
        this.toPosition = toPosition;
    }

    public static ChessCommandDto of(ChessCommand chessCommand) {
        return new ChessCommandDto(chessCommand, null, null);
    }

    public static ChessCommandDto of(ChessCommand chessCommand, String from, String to) {
        return new ChessCommandDto(chessCommand, toPosition(from), toPosition(to));
    }

    private static Position toPosition(String positionInput) {
        String fileInput = String.valueOf(positionInput.charAt(FILE_INDEX));
        String rankInput = String.valueOf(positionInput.charAt(RANK_INDEX));

        return Position.of(ViewFile.from(fileInput), ViewRank.from(rankInput));
    }

    public boolean isEnd() {
        return this.chessCommand.isEnd();
    }

    public ChessCommand getChessCommand() {
        return chessCommand;
    }

    public Position getFromPosition() {
        validateCommandIsMove();

        return fromPosition;
    }

    public Position getToPosition() {
        validateCommandIsMove();

        return toPosition;
    }

    private void validateCommandIsMove() {
        if (chessCommand != ChessCommand.MOVE) {
            throw new IllegalStateException();
        }
    }
}
