package chess.controller.state;

import chess.controller.Command;
import chess.domain.chess.ChessGame;
import chess.domain.piece.TeamColor;
import chess.domain.position.Position;
import chess.domain.position.PositionConverter;

import java.util.List;

public final class Move implements State {
    public static final int SOURCE_INDEX = 1;
    public static final int TARGET_INDEX = 2;

    private final ChessGame chessGame;
    private final TeamColor teamColor;

    public Move(final ChessGame chessGame, final TeamColor teamColor) {
        this.chessGame = chessGame;
        this.teamColor = teamColor;
    }

    @Override
    public State checkCommand(final Command command) {
        if (command.isStart()) {
            throw new IllegalArgumentException("이미 시작이 완료되었습니다.");
        }
        if (command.isEnd()) {
            return new End().run(chessGame);
        }
        if (command.isStatus()) {
            return new Status(chessGame, teamColor).run();
        }
        validateCommand(command);
        return move(command);
    }

    private State move(final Command command) {
        final List<String> commands = command.getCommands();
        final Position source = PositionConverter.convert(commands.get(SOURCE_INDEX));
        final Position target = PositionConverter.convert(commands.get(TARGET_INDEX));
        chessGame.setUp(source, target, teamColor);
        if (chessGame.isEnd()) {
            return new End().run(chessGame);
        }
        return new Move(chessGame, teamColor.changeTurn());
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
