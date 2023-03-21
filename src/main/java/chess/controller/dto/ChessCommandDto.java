package chess.controller.dto;

import chess.domain.game.ChessCommandType;
import chess.domain.game.ChessGameCommand;
import chess.domain.position.Position;
import chess.view.input.ViewFile;
import chess.view.input.ViewRank;

public class ChessCommandDto {

    private static final int FILE_INDEX = 0;
    private static final int RANK_INDEX = 1;

    private final ChessCommandType gameCommand;
    private final Position fromPosition;
    private final Position toPosition;

    public ChessCommandDto(ChessCommandType gameCommand, Position fromPosition, Position toPosition) {
        this.gameCommand = gameCommand;
        this.fromPosition = fromPosition;
        this.toPosition = toPosition;
    }

    public static ChessCommandDto of(ChessCommandType gameCommand) {
        return new ChessCommandDto(gameCommand, null, null);
    }

    public static ChessCommandDto of(ChessCommandType gameCommand, String from, String to) {
        return new ChessCommandDto(gameCommand, toPosition(from), toPosition(to));
    }

    private static Position toPosition(String positionInput) {
        String fileInput = String.valueOf(positionInput.charAt(FILE_INDEX));
        String rankInput = String.valueOf(positionInput.charAt(RANK_INDEX));

        return Position.of(ViewFile.from(fileInput), ViewRank.from(rankInput));
    }

    public ChessGameCommand toChessGameCommand() {
        if (gameCommand != ChessCommandType.MOVE) {
            return new ChessGameCommand(gameCommand);
        }

        return new ChessGameCommand(
                gameCommand,
                fromPosition,
                toPosition
        );
    }
}
