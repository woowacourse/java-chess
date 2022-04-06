package chess.controller;

import chess.dto.BoardDto;
import chess.dto.ResponseDto;
import chess.dto.ScoreDto;
import chess.model.ConsoleBoard;

public class ChessController {

    private ConsoleBoard consoleBoard;

    public ChessController() {
        this.consoleBoard = new ConsoleBoard();
    }

    public void reStartGame() {
        consoleBoard = new ConsoleBoard();
    }

    public BoardDto getBoard() {
        return BoardDto.of(consoleBoard);
    }

    public ResponseDto move(String source, String target) {
        try {
            consoleBoard.move(source, target);
        } catch (IllegalArgumentException e) {
            return ResponseDto.of(400, e.getMessage(), consoleBoard.isEnd());
        }
        return ResponseDto.of(200, null, consoleBoard.isEnd());
    }

    public ScoreDto score() {
        return ScoreDto.of(consoleBoard.calculateScore());
    }

    public boolean isEnd() {
        return consoleBoard.isEnd();
    }

    public void finishGame() {
        consoleBoard.finishGame();
    }
}
