package chess.controller.command;

import chess.controller.action.RoomAction;
import chess.domain.game.ChessGame;
import chess.service.ChessService;
import java.util.Arrays;
import java.util.List;

public enum RoomCommand {

    NEW("new", (chessService, ignored) -> newGame(chessService)),
    ENTER("enter", RoomCommand::enterGame);

    private static final int CHESS_GAME_ID_INDEX = 1;

    private final String symbol;
    private final RoomAction roomAction;

    RoomCommand(final String symbol, final RoomAction roomAction) {
        this.symbol = symbol;
        this.roomAction = roomAction;
    }

    public static RoomCommand from(final String symbol) {
        return Arrays.stream(values())
                .filter(roomCommand -> roomCommand.symbol.equals(symbol))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 명령어입니다."));
    }

    private static ChessGame newGame(final ChessService chessService) {
        return chessService.newGame();
    }

    private static ChessGame enterGame(final ChessService chessService, final List<String> roomCommand) {
        if (roomCommand.size() != 2) {
            throw new IllegalArgumentException("잘못된 명령어입니다.");
        }

        final int chessGameId = Integer.parseInt(roomCommand.get(CHESS_GAME_ID_INDEX));
        return chessService.enterGame(chessGameId);
    }

    public ChessGame execute(final ChessService chessService, final List<String> roomCommand) {
        return this.roomAction.execute(chessService, roomCommand);
    }
}
