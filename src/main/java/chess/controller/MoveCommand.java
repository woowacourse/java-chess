package chess.controller;

import chess.domain.ChessGame;

import java.util.List;

public class MoveCommand extends Command{

    public MoveCommand(ChessGame chessGame, CommandType commandType) {
        super(chessGame, commandType);
    }

    @Override
    public Command execute(List<String> input) {
        return null;
    }
}
