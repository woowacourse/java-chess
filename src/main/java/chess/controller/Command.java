package chess.controller;

import chess.domain.ChessGame;
import chess.domain.board.Position;
import chess.domain.piece.Piece;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public abstract class Command {

    protected final ChessGame chessGame;
    protected final CommandType commandType;

    protected Command(ChessGame chessGame, CommandType commandType) {
        this.chessGame = chessGame;
        this.commandType = commandType;
    }

    public abstract Command execute(List<String> input);

    public boolean isSameType(CommandType commandType) {
        return this.commandType == commandType;
    }

    public Map<Position, Piece> getChessGameBoards() {
        return Collections.unmodifiableMap(chessGame.getBoard());
    }
}
