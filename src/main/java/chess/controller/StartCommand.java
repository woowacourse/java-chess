package chess.controller;

import chess.domain.ChessGame;
import chess.domain.board.BoardFactory;

import java.util.List;

public class StartCommand extends Command{

    public StartCommand() {
        super(null, CommandType.START);
    }

    @Override
    public Command execute(List<String> input) {
        CommandType inputCommandType = CommandType.from(input);
        if (inputCommandType != CommandType.START) {
            throw new IllegalArgumentException("잘못된 명령어를 입력했습니다.");
        }
        return new MoveCommand(new ChessGame(BoardFactory.createBoard()));
    }

    @Override
    public ChessGame getChessGame() {
        throw new IllegalArgumentException("게임을 시작해야 체스판을 확인할 수 있습니다.");
    }
}
