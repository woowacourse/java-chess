package chess.controller.command.command;

import chess.controller.ChessState;
import chess.dao.MoveLogDao;
import chess.domain.board.Board;
import chess.domain.board.initial.BoardFactory;
import chess.domain.game.ChessGame;
import chess.dto.ChessGameDto;
import chess.view.OutputView;

import java.util.Set;

import static chess.controller.ChessState.INIT;
import static chess.controller.ChessState.START;

public class StartCommand implements Command {

    private static final String CANNOT_START_AFTER_START_ERROR_MESSAGE = "게임 진행 도중에 다시 초기화할 수 없습니다.";

    private StartCommand() {
    }

    public static StartCommand create() {
        return new StartCommand();
    }

    @Override
    public ChessState execute(final ChessState state, final ChessGameDto chessGameDto) {
        if (!Set.of(INIT).contains(state)) {
            throw new IllegalArgumentException(CANNOT_START_AFTER_START_ERROR_MESSAGE);
        }
        final Board board = BoardFactory.create();
        final ChessGame chessGame = ChessGame.of(MoveLogDao.load(chessGameDto, board), chessGameDto.getTurn());

        OutputView.printBoard(chessGame.getBoard());
        return START;
    }
}
