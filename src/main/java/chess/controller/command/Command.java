package chess.controller.command;

import chess.domain.ChessGame;
import chess.domain.board.Position;
import chess.domain.piece.Piece;
import chess.repository.BoardDao;
import chess.view.OutputView;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public abstract class Command {

    protected static final String INVALID_EXECUTE_MESSAGE = "상태를 확인하시려면 status를, 말을 움직이려면 move를, 게임을 종료하려면 end를 입력해주세요.";


    protected final BoardDao boardDao;
    protected final CommandType commandType;
    protected final OutputView outputView;

    protected Command(BoardDao boardDao, CommandType commandType, OutputView outputView) {
        this.boardDao = boardDao;
        this.commandType = commandType;
        this.outputView = outputView;
    }

    public abstract Command execute(List<String> input);

    public boolean isSameType(CommandType commandType) {
        return this.commandType == commandType;
    }

    public boolean isDifferentType(CommandType commandType) {
        return !isSameType(commandType);
    }

    public Map<Position, Piece> getChessGameBoards() {
        ChessGame chessGame = boardDao.selectChessGame();
        return Collections.unmodifiableMap(chessGame.getBoard());
    }
}
