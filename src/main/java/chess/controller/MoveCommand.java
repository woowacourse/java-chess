package chess.controller;

import chess.domain.ChessGame;
import chess.view.PositionMapper;

import java.util.List;

public class MoveCommand extends Command {

    private static final int SOURCE_POSITION_INDEX = 1;
    private static final int TARGET_POSITION_INDEX = 2;

    protected MoveCommand(ChessGame chessGame) {
        super(chessGame, CommandType.MOVE);
    }

    @Override
    public Command execute(List<String> input) {
        CommandType inputCommandType = CommandType.from(input);
        if (inputCommandType == CommandType.MOVE) {
            chessGame.movePiece(PositionMapper.from(input.get(SOURCE_POSITION_INDEX)),
                    PositionMapper.from(input.get(TARGET_POSITION_INDEX)));
            return new MoveCommand(getChessGame());
        }
        if (inputCommandType == CommandType.END) {
            return new EndCommand(chessGame);
        }
        throw new IllegalArgumentException("잘못된 명령어를 입력하셨습니다.");
    }
}
