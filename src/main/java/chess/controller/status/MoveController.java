package chess.controller.status;

import chess.controller.Command;
import chess.domain.chess.CampType;
import chess.domain.chess.ChessGame;
import chess.domain.piece.move.Position;
import chess.domain.piece.move.PositionConverter;

import java.util.List;

public final class MoveController implements Controller {
    private final ChessGame chessGame;
    private final CampType campType;

    MoveController(final ChessGame chessGame, final CampType campType) {
        this.chessGame = chessGame;
        this.campType = campType;
    }

    @Override
    public Controller checkCommand(final Command command, final Runnable runnable) {
        if (command.isStart()) {
            throw new IllegalArgumentException("이미 시작이 완료되었습니다.");
        }
        if (command.isEnd()) {
            return new EndController();
        }
        if (command.isStatus()) {
            return new StatusController(chessGame, campType).getStatus(true);
        }
        return move(command, runnable);
    }

    @Override
    public boolean isRun() {
        return true;
    }

    Controller move(final Command command, final Runnable runnable) {
        validateCommand(command);
        final List<String> commands = command.getCommands();
        final Position source = PositionConverter.convert(commands.get(1));
        final Position target = PositionConverter.convert(commands.get(2));
        boolean isGameRun = chessGame.run(source, target, campType);
        runnable.run();
        if (!isGameRun) {
            return new StatusController(chessGame, campType).getStatus(false);
        }
        return new MoveController(chessGame, campType.changeTurn());
    }

    private void validateCommand(final Command command) {
        if (!command.isCorrectWhenMove()) {
            throw new IllegalArgumentException("'move source위치 target위치 - 예. move b2 b3'와 같은 형태로 입력해 주세요.");
        }
    }
}
