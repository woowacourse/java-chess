package chess.controller;

import chess.domain.chessgame.ChessGame;
import chess.domain.chessgame.KingDiedGame;
import chess.domain.side.Color;
import chess.view.OutputView;

import java.util.List;

public class MoveCommand implements Command {
    private static final int SOURCE_INDEX_OF_MOVE_COMMAND = 1;
    private static final int TARGET_INDEX_OF_MOVE_COMMAND = 2;

    @Override
    public ChessGame execute(final ChessGame chessGame, final List<String> input, final OutputView outputView) {
        validateMoveCommand(input);
        String sourceSquare = input.get(SOURCE_INDEX_OF_MOVE_COMMAND);
        String targetSquare = input.get(TARGET_INDEX_OF_MOVE_COMMAND);
        ChessGame afterMoveChessGame = chessGame.move(sourceSquare, targetSquare);
        printMoveResult(afterMoveChessGame, outputView);
        return afterMoveChessGame;
    }

    private void printMoveResult(ChessGame chessGame, OutputView outputView) {
        outputView.printBoard(chessGame.findChessBoard());
        if (chessGame instanceof KingDiedGame) {
            Color winnerColor = chessGame.findWinner();
            outputView.printHigherScoreSide(winnerColor);
        }
    }

    private void validateMoveCommand(final List<String> input) {
        if (input.size() != 3) {
            throw new IllegalArgumentException("\"move 시작위치 도착위치\"의 형태로 입력해주세요.");
        }
    }
}
