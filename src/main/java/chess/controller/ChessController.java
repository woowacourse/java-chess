package chess.controller;

import chess.model.Board;
import chess.model.GameStartCommand;
import chess.model.piece.Piece;
import chess.state.GameState;
import chess.state.Ready;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class ChessController {

    private final ChessService service;

    public ChessController(ChessService service) {
        this.service = service;
    }

    public void playGame() {
        OutputView.startGame();
        GameState gameState = new Ready();
        while (!gameState.isEnd()) {
            List<String> request = InputView.inputStartOrEndGame();
            GameStartCommand command = GameStartCommand.findCommand(request.get(0));
            gameState = gameState.changeStatus(command);
            request.remove(0);
            gameState = gameState.execute(service, request);
            outputBy(gameState);
        }
    }

    private void outputBy(GameState gameState) {
        if (gameState.isEnd()) {
            return;
        }
        if (gameState.isStatus()) {
            OutputView.printStatus(toDto(service.getScores()));
        }
        OutputView.startGameBoard(new BoardDto(toDto(service.getBoard())));
    }

    private List<String> toDto(final Board board) {
        List<String> boardDto = new ArrayList<>();
        for (Piece piece : board.getBoard()) {
            boardDto.add(toDto(piece));
        }
        return boardDto;
    }

    private String toDto(final Piece piece) {
        String pieceDto = piece.getLetter();
        if (piece.isBlack()) {
            return pieceDto.toUpperCase();
        }
        return pieceDto;
    }

    private ScoresDto toDto(final Map<String, Double> scores) {
        // 메소드로 따로 빼도 될거같아요
        String winner = findWinnerName(scores);
        return new ScoresDto(winner, scores);
    }

    private String findWinnerName(Map<String, Double> scores) {
        if (scores.get("BLACK").equals(scores.get("WHITE"))) {
            return "무승부";
        }
        if (scores.get("BLACK") > scores.get("WHITE")) {
            return "BLACK 승";
        }
        return "WHITE 승";
    }
}
