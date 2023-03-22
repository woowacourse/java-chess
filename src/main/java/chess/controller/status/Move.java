package chess.controller.status;

import chess.controller.Command;
import chess.domain.camp.CampType;
import chess.domain.chess.ChessGame;
import chess.domain.piece.Position;
import chess.domain.piece.PositionConverter;

import java.util.List;

public final class Move implements Status {
    public static final int SOURCE_INDEX = 1;
    public static final int TARGET_INDEX = 2;

    private final ChessGame chessGame;
    private final CampType campType;

    public Move(final ChessGame chessGame, final CampType campType) {
        this.chessGame = chessGame;
        this.campType = campType;
    }

    @Override
    public Status checkCommand(final Command command) {
        if (command.isStart()) {
            throw new IllegalArgumentException("이미 시작이 완료되었습니다.");
        }
        if (command.isEnd()) {
            return new End();
        }
        validateCommand(command);
        return move(command);
    }

    private Status move(final Command command) {
        final List<String> commands = command.getCommands();
        final Position source = PositionConverter.convert(commands.get(SOURCE_INDEX));
        final Position target = PositionConverter.convert(commands.get(TARGET_INDEX));
        chessGame.setUp(source, target, campType);
        return new Move(chessGame, campType.changeTurn());
    }

    private void validateCommand(final Command command) {
        if (!command.isCorrectWhenMove()) {
            throw new IllegalArgumentException("'move source위치 target위치 - 예. move b2 b3'와 같은 형태로 입력해 주세요.");
        }
    }

    @Override
    public boolean isRun() {
        return true;
    }
}
