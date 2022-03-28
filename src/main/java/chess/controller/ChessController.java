package chess.controller;

import chess.model.Board;
import chess.model.GameCommand;
import chess.model.piece.Piece;
import chess.state.GameState;
import chess.state.Ready;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.ArrayList;
import java.util.List;

public final class ChessController {

    private final ChessService service;

    public ChessController(final ChessService service) {
        this.service = service;
    }

    public void playGame() {
        OutputView.startGame();
        GameState gameState = new Ready();
        playChess(gameState);
    }

    private void playChess(GameState gameState) {
        while (!gameState.isEnd()) {
            List<String> request = InputView.inputStartOrEndGame();
            GameCommand command = GameCommand.findCommand(request.get(0));
            gameState = gameState.changeStatus(command);
            request.remove(0);
            gameState = gameState.execute(service, request);
            outputBy(gameState);
        }
    }

    private void outputBy(final GameState gameState) {
        if (gameState.isEnd()) {
            return;
        }
        if (gameState.isStatus()) {
            OutputView.printStatus(toScoreDto(service.getScores()));
        }
        OutputView.startGameBoard(toBoardDto(service.getBoard()));
    }

    private BoardDto toBoardDto(final Board board) {
        List<String> boardResult = new ArrayList<>();
        for (Piece piece : board.getBoard()) {
            boardResult.add(toPieceDto(piece));
        }
        return new BoardDto(boardResult);
    }

    private String toPieceDto(final Piece piece) {
        String pieceDto = piece.getLetter();
        if (piece.isBlack()) {
            return pieceDto.toUpperCase();
        }
        return pieceDto;
    }

    private ScoresDto toScoreDto(final ScoreResult scoreResult) {
        String winner = scoreResult.findWinnerName();
        return new ScoresDto(winner, scoreResult);
    }
}
