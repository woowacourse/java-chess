package chess.controller;

import chess.controller.command.CommandHandler;
import chess.controller.command.command.Command;
import chess.dao.ChessGameDao;
import chess.dao.ChessRoomDao;
import chess.dto.ChessGameDto;
import chess.dto.ChessRoomDto;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessGameController {

    private final ChessRoomDao chessRoomDao = new ChessRoomDao();
    private final ChessGameDao chessGameDao = new ChessGameDao();

    public void run(final ChessRoomDto chessRoomDto) {
        ChessState state = chessRoomDto.getState();

        OutputView.printStart();
        while (state != ChessState.END) {
            final ChessGameDto chessGameDto = chessGameDao.findByChessRoom(chessRoomDto);
            state = play(state, chessGameDto);
            chessRoomDao.updateState(chessRoomDto, state);
        }
    }

    private ChessState play(final ChessState state, final ChessGameDto chessGameDto) {
        try {
            final Command command = CommandHandler.bind(InputView.readCommand());
            return command.execute(state, chessGameDto);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e);
            return play(state, chessGameDto);
        }
    }
}
