package chess.controller;

import chess.controller.dto.PlayRequest;
import chess.controller.state.GameState;
import chess.controller.state.Ready;
import chess.dao.ChessMovementDao;
import chess.model.game.ChessGame;
import chess.model.piece.Camp;
import chess.model.piece.PieceScore;
import chess.model.position.Position;
import chess.model.position.PositionConverter;
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

    public void start(final ChessGame chessGame, final ChessMovementDao chessMovementDao) {
        outputView.guideGameStart();
        GameState gameState = new Ready(chessGame, chessMovementDao);

        while (gameState.isContinue()) {
            gameState = run(gameState);
            printChessBoard(gameState, chessGame);
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

    private void printChessBoard(final GameState gameState, final ChessGame chessGame) {
        if (isPrintChessBoard(gameState)) {
            printChessBoard(chessGame);
        }
        if (!gameState.isPrintable() && !gameState.isPlay()) {
            outputView.guideEndGame();
        }
        if (isPrintScoreAndGameResult(gameState)) {
            printScoreAndResult(chessGame);
        }
    }

    private boolean isPrintChessBoard(final GameState gameState) {
        return gameState.isPlay() && gameState.isPrintable();
    }

    private void printChessBoard(final ChessGame chessGame) {
        outputView.printCurrentCamp(chessGame.getCurrentCamp());
        outputView.printChessBoard(chessGame.getChessBoard());
    }

    private void printScoreAndResult(final ChessGame chessGame) {
        final ChessGameResultResponse chessGameResultResponse = makeChessGameResultResponse(chessGame);

        outputView.printChessGameResult(chessGameResultResponse);
    }

    private boolean isPrintScoreAndGameResult(final GameState gameState) {
        return !gameState.isPlay() && gameState.isPrintable();
    }

    private ChessGameResultResponse makeChessGameResultResponse(final ChessGame chessGame) {
        final PieceScore blackPieceScore = chessGame.getScoreByCamp(Camp.BLACK);
        final PieceScore whitePieceScore = chessGame.getScoreByCamp(Camp.WHITE);
        final Camp winner = chessGame.getWinnerCamp();

        return new ChessGameResultResponse(blackPieceScore, whitePieceScore, winner);
    }
}
