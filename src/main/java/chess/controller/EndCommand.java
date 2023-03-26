package chess.controller;

import chess.domain.ChessGame;

import java.util.List;

public class EndCommand extends Command{

    protected EndCommand(ChessGame chessGame) {
        super(chessGame, CommandType.END);
    }

    @Override
    public Command execute(List<String> input) {
        return null;
    }
}
