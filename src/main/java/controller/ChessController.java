package controller;

import domain.ChessBoard;
import dto.RouteDto;
import util.BoardMapper;
import view.ChessCommand;
import view.InputView;
import view.OutputView;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Supplier;

public class ChessController {
    private ChessBoard chessBoard;
    private final Map<ChessCommand, Supplier<GameStatus>> commandHandler = initializeHandler();

    private Map<ChessCommand, Supplier<GameStatus>> initializeHandler() {
        final Map<ChessCommand, Supplier<GameStatus>> handler = new EnumMap<>(ChessCommand.class);
        handler.put(ChessCommand.START, this::gameStart);
        handler.put(ChessCommand.MOVE, this::pieceMove);
        handler.put(ChessCommand.END, this::gameEnd);
        return handler;
    }

    public void start() {
        GameStatus gameStatus = GameStatus.GAME_READY;
        while (gameStatus.playAble()) {
            try {
                final ChessCommand chessCommand = InputView.inputChessCommand();
                gameStatus = commandHandler.get(chessCommand)
                                           .get();
                OutputView.printBoard(BoardMapper.toDto(chessBoard));
            } catch (final IllegalArgumentException | IllegalStateException exception) {
                InputView.clear();
                OutputView.printException(exception.getMessage());
            }
        }
    }


    private GameStatus gameStart() {
        if (chessBoard != null) {
            throw new IllegalStateException("이미 체스판이 생성되어 있습니다.");
        }
        chessBoard = ChessBoard.createDefaultBoard();
        return GameStatus.GAME_START;
    }

    private GameStatus pieceMove() {
        if (chessBoard == null) {
            throw new IllegalStateException("체스판이 아직 생성되지 않았습니다.");
        }
        final var source = InputView.inputChessPoint();
        final var destination = InputView.inputChessPoint();
        final var routeDto = new RouteDto(source, destination);
        chessBoard.move(routeDto.getStartPoint(), routeDto.getEndPoint());
        return GameStatus.GAME_PLAY;
    }

    private GameStatus gameEnd() {
        return GameStatus.GAME_END;
    }
}
