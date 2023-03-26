package chess.controller.command;

import chess.domain.ChessGame;
import chess.domain.board.Position;
import chess.domain.piece.Piece;
import chess.view.OutputView;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public abstract class Command {

    protected final ChessGame chessGame;
    protected final CommandType commandType;
    protected final OutputView outputView;

    protected Command(ChessGame chessGame, CommandType commandType, OutputView outputView) {
        this.chessGame = chessGame;
        this.commandType = commandType;
        this.outputView = outputView;
    }

    public abstract Command execute(List<String> input);

    public boolean isSameType(CommandType commandType) {
        return this.commandType == commandType;
    }

    public Map<Position, Piece> getChessGameBoards() {
        return Collections.unmodifiableMap(chessGame.getBoard());
    }
}
