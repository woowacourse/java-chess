package chess;

import chess.domain.*;
import chess.view.InputView;
import chess.view.OutputVIew;

import java.util.HashSet;

import static chess.domain.Team.BLACK;
import static chess.domain.Team.WHITE;

public class CUIChessApp {
    private static ChessGame chessGame;

    public static void main(String[] args) {
        OutputVIew.printInputGuide();
        String input;
        GameResult result = GameResult.KEEP;
        while (result == GameResult.KEEP) {
            input = InputView.promptUserSelection();
            result = handleUserSelection(input);
        }

    }

    private static GameResult handleUserSelection(String input) {
        String[] tokens = input.split(" ");
        if (tokens[0].equals("start")) {
            initBoard();
            OutputVIew.printBoardState(chessGame.getBoardState());
            return GameResult.KEEP;
        }

        if (tokens[0].equals("end")) {
            return GameResult.judgeScore(chessGame.getBoardState());
        }

        if (tokens[0].equals("status")) {
            assertStarted();

            ScoreCounter scoreCount = new ScoreCounter(chessGame.getBoardState());
            OutputVIew.printScore(scoreCount.getScore(WHITE), scoreCount.getScore(BLACK));
            return GameResult.KEEP;
        }

        if (tokens[0].equals("move")) {
            assertStarted();
            handleMoveCommand(tokens);
            OutputVIew.printBoardState(chessGame.getBoardState());
            return checkResult();
        }

        throw new IllegalArgumentException("알 수 없는 명령입니다");
    }


    private static void initBoard() {
        chessGame = new ChessGame(new BoardStateFactory());
    }

    private static void assertStarted() {
        if (chessGame == null) {
            throw new IllegalArgumentException("start를 먼저 해주세요");
        }
    }

    private static void handleMoveCommand(String[] tokens) {
        try {
            chessGame.move(
                CoordinatePair.from(tokens[1])
                    .orElseThrow(() -> new IllegalArgumentException("올바르지 않은 좌표입니다.")),
                CoordinatePair.from(tokens[2])
                    .orElseThrow(() -> new IllegalArgumentException("올바르지 않은 좌표입니다."))
            );

        } catch (IllegalArgumentException e) {
            OutputVIew.printError(e.getMessage());
        }
    }

    private static GameResult checkResult() {
        return GameResult.judge(new HashSet<>(chessGame.getBoardState().values()));
    }
}
