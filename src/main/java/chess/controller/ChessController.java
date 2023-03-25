package chess.controller;

import chess.domain.game.ChessGameFactory;
import chess.dto.inputView.ReadCommandDto;
import chess.dto.outputView.PrintEndMessageDto;
import chess.dto.outputView.PrintErrorMessageDto;
import chess.dto.outputView.PrintInitialMessageDto;
import chess.service.ChessGameService;
import chess.view.IOViewResolver;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static chess.controller.GameCommand.END;
import static chess.controller.GameCommand.INIT;
import static chess.controller.GameCommand.MOVE;
import static chess.controller.GameCommand.START;
import static chess.controller.GameCommand.STATUS;

public final class ChessController {

    private final Map<GameCommand, Function<List<String>, GameCommand>> gameStatusMap;
    private final IOViewResolver ioViewResolver;
    private final ChessGameService chessGameService;

    public ChessController(final IOViewResolver ioViewResolver) {
        this.ioViewResolver = ioViewResolver;
        this.gameStatusMap = new EnumMap<>(GameCommand.class);
        this.chessGameService = new ChessGameService();
        initGameStatusMap();
    }

    private void initGameStatusMap() {
        gameStatusMap.put(START, this::start);
        gameStatusMap.put(MOVE, this::move);
        gameStatusMap.put(STATUS, this::status);
        gameStatusMap.put(END, this::end);
    }

    public void process() {
        ioViewResolver.outputViewResolve(new PrintInitialMessageDto());
        GameCommand gameCommand = INIT;
        while (!gameCommand.isEnd()) {
            gameCommand = play(gameCommand);
        }
    }

    private GameCommand play(final GameCommand gameCommand) {
        try {
            final ReadCommandDto readCommandDto = ioViewResolver.inputViewResolve(ReadCommandDto.class);
            final List<String> input = readCommandDto.getResult();
            final GameCommand newGameCommand = GameCommand.from(input);

            return gameStatusMap.get(newGameCommand).apply(input);
        } catch (IllegalArgumentException | IllegalStateException exception) {
            ioViewResolver.outputViewResolve(new PrintErrorMessageDto(exception.getMessage()));
            return gameCommand;
        }
    }

    private GameCommand start(final List<String> input) {
        chessGameService.checkStart();
        if (chessGameService.hasHistory()) {
            chessGameService.initChessGame();
            ioViewResolver.outputViewResolve(chessGameService.getBoard());
            return MOVE;
        }
        chessGameService.initChessGame(ChessGameFactory.generate());
        ioViewResolver.outputViewResolve(chessGameService.getBoard());
        return MOVE;
    }

    private GameCommand move(final List<String> input) {
        chessGameService.checkMove();
        chessGameService.move(input);

        if (chessGameService.isKingDead()) {
            ioViewResolver.outputViewResolve(chessGameService.getWinner());
            return END;
        }
        ioViewResolver.outputViewResolve(chessGameService.getBoard());
        return MOVE;
    }

    private GameCommand status(final List<String> strings) {
        chessGameService.delete();
        ioViewResolver.outputViewResolve(chessGameService.calculateScore());
        ioViewResolver.outputViewResolve(new PrintEndMessageDto());
        return END;
    }

    private GameCommand end(final List<String> input) {
        ioViewResolver.outputViewResolve(new PrintEndMessageDto());
        chessGameService.save();
        return END;
    }
}
