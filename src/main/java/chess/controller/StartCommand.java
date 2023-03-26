package chess.controller;

import chess.domain.ChessGame;
import chess.domain.board.BoardFactory;
import chess.domain.board.Position;
import chess.domain.piece.Piece;

import java.util.List;
import java.util.Map;

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
    public Map<Position, Piece> getChessGameBoards() {
        throw new IllegalArgumentException("게임을 시작해야 체스판을 확인할 수 있습니다.");
    }
}
