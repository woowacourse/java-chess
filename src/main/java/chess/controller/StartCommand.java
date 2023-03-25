package chess.controller;

import chess.domain.ChessGame;
import chess.domain.board.BoardFactory;

import java.util.List;

public class StartCommand extends Command{

    public StartCommand(CommandType commandType) {
        super(null, commandType);
    }

    @Override
    public Command execute(List<String> input) {
        commandType.validate(input);
        return new MoveCommand(new ChessGame(BoardFactory.createBoard()), CommandType.MOVE);
    }

    @Override
    public ChessGame getChessGame() {
        throw new IllegalArgumentException("게임을 시작해야 체스판을 확인할 수 있습니다.");
    }
}
