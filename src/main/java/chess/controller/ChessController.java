package chess.controller;

import chess.controller.dto.PlayRequest;
import chess.controller.state.GameState;
import chess.controller.state.Ready;
import chess.model.piece.Camp;
import chess.model.piece.score.PieceScore;
import chess.model.position.Position;
import chess.model.position.PositionConverter;
import chess.service.ChessGameService;
import chess.view.InputView;
import chess.view.OutputView;
import chess.view.dto.ChessGameResultResponse;
import java.util.List;
import java.util.stream.Collectors;

public class ChessController {

    private final InputView inputView;
    private final OutputView outputView;

    public ChessController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start(final ChessGameService chessGameService) {
        outputView.guideGameStart();
        GameState gameState = new Ready(chessGameService);

        while (gameState.isContinue()) {
            gameState = run(gameState);
            printChessBoard(gameState, chessGameService);
        }
    }

    private GameState run(final GameState gameState) {
        try {
            return playGame(gameState);
        } catch (IllegalArgumentException e) {
            outputView.printExceptionMessage(e.getMessage());
            return gameState;
        }
    }

    private GameState playGame(final GameState gameState) {
        final PlayRequest playRequest = inputView.readPlayCommand();
        final GameCommand gameCommand = GameCommand.findGameCommand(playRequest.getCommand());
        final List<Position> movePositions = playRequest.getMovePositions().stream()
                .map(PositionConverter::convert)
                .collect(Collectors.toList());

        return gameState.execute(gameCommand, movePositions);
    }

    private void printChessBoard(final GameState gameState, final ChessGameService chessGameService) {
        if (isPrintChessBoard(gameState)) {
            printChessBoard(chessGameService);
        }
        if (!gameState.isPrintable() && !gameState.isPlay()) {
            outputView.guideEndGame();
        }
        if (isPrintScoreAndGameResult(gameState)) {
            printScoreAndResult(chessGameService);
        }
    }

    private boolean isPrintChessBoard(final GameState gameState) {
        return gameState.isPlay() && gameState.isPrintable();
    }

    private void printChessBoard(final ChessGameService chessGameService) {
        outputView.printCurrentCamp(chessGameService.getCurrentCamp());
        outputView.printChessBoard(chessGameService.getChessBoard());
    }

    private boolean isPrintScoreAndGameResult(final GameState gameState) {
        return !gameState.isPlay() && gameState.isPrintable();
    }

    private void printScoreAndResult(final ChessGameService chessGameService) {
        final ChessGameResultResponse chessGameResultResponse = makeChessGameResultResponse(chessGameService);

        outputView.printChessGameResult(chessGameResultResponse);
    }

    private ChessGameResultResponse makeChessGameResultResponse(final ChessGameService chessGameService) {
        final PieceScore blackPieceScore = chessGameService.getScoreByCamp(Camp.BLACK);
        final PieceScore whitePieceScore = chessGameService.getScoreByCamp(Camp.WHITE);
        final Camp winner = chessGameService.getWinnerCamp();

        return new ChessGameResultResponse(blackPieceScore, whitePieceScore, winner);
    }
}
