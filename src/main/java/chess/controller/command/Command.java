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

    public Map<Position, Piece> getChessGameBoards() {
        ChessGame chessGame = boardDao.selectChessGame();
        return Collections.unmodifiableMap(chessGame.getBoard());
    }
}
