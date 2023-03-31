package chess.controller.status;

import chess.controller.Command;
import chess.domain.chess.ChessGame;
import chess.domain.piece.move.Position;
import chess.domain.piece.move.PositionConverter;
import chess.service.ChessGameService;

import java.util.List;
import java.util.Optional;

public final class MoveController implements Controller {

    private final long userId;
    private final ChessGameService chessGameService;

    MoveController(final long userId, final ChessGameService chessGameService) {
        this.userId = userId;
        this.chessGameService = chessGameService;
    }

    @Override
    public Controller checkCommand(final Command command) {
        if (command.isStart()) {
            throw new IllegalArgumentException("이미 시작이 완료되었습니다.");
        }
        if (command.isEnd()) {
            return new EndController();
        }
        if (command.isStatus()) {
            return new StatusController(userId, chessGameService).getStatus(true);
        }
        return move(command);
    }

    @Override
    public boolean isRun() {
        return true;
    }

    @Override
    public Optional<ChessGame> findGame() {
        final ChessGame chessGame = chessGameService.getOrCreateChessGame(userId);
        return Optional.of(chessGame);
    }

    Controller move(final Command command) {
        validateCommand(command);
        playChessGame(command);
        final ChessGame chessGame = chessGameService.getOrCreateChessGame(userId);
        if (!chessGame.isKingAlive()) {
            return new StatusController(userId, chessGameService).getStatus(false);
        }
        return new MoveController(userId, chessGameService);
    }

    private void validateCommand(final Command command) {
        if (!command.isCorrectWhenMove()) {
            throw new IllegalArgumentException("'move source위치 target위치 - 예. move b2 b3'와 같은 형태로 입력해 주세요.");
        }
    }

    private void playChessGame(final Command command) {
        final List<String> commands = command.getCommands();
        final Position source = PositionConverter.convert(commands.get(1));
        final Position target = PositionConverter.convert(commands.get(2));
        chessGameService.play(userId, source, target);
    }

}
