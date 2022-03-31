package chess.controller;

import chess.domain.game.ChessGame;
import chess.domain.game.score.ScoreResult;
import chess.domain.position.Position;
import chess.view.InputView;
import chess.view.OutputView;
import chess.view.dto.BoardDto;
import chess.view.dto.ConsoleCommandDto;

public class ChessController {

    public void run() {
        ChessGame chessGame = new ChessGame();

        OutputView.printCommandGuide();

        // TODO: depth 줄이기
        // TODO: while 대신 재귀 고려하기
        // TODO: 메소드 길이를 줄이기 위해 메소드 추출하기
        while (true) {
            try {
                ConsoleCommandDto commandDto = InputView.inputCommand();

                if (commandDto.isStart()) {
                    chessGame.startGame();
                    OutputView.printBoard(BoardDto.from(chessGame.getBoard()));
                }

                if (commandDto.isMove()) {
                    Position fromPosition = Position.from(commandDto.getArgumentByIndex(0));
                    Position toPosition = Position.from(commandDto.getArgumentByIndex(1));

                    chessGame.movePiece(fromPosition, toPosition);
                    OutputView.printBoard(BoardDto.from(chessGame.getBoard()));
                }

                if (commandDto.isStatus()) {
                    ScoreResult scoreResult = chessGame.showStatus();
                    OutputView.printScore(scoreResult);
                }

                if (commandDto.isEnd()) {
                    break;
                }
            } catch (IllegalArgumentException e) {
                OutputView.printException(e);
            }
        }
    }
}
