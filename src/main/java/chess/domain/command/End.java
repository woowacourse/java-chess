package chess.domain.command;

import java.util.List;

import chess.domain.ChessGame;

public class End extends Command{

    public End(List<String> command) {
        super(command);
    }

    @Override
    public boolean execute(ChessGame chessGame) {
        return false;
    }
}
