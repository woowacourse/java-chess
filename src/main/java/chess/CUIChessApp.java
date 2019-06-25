package chess;

import chess.domain.*;
import chess.view.InputView;
import chess.view.OutputVIew;

import java.util.*;
import java.util.stream.Collectors;

import static chess.domain.Team.*;

public class CUIChessApp {
    private static ChessGame chessGame;

    public static void main(String[] args) {
        OutputVIew.printInputGuide();
        String input = InputView.promptUserSelection();

        while (!handleUserSelection(input)) {
            input = InputView.promptUserSelection();

        }
    }

    private static boolean handleUserSelection(String input) {
        String[] tokens = input.split(" ");
        if (tokens[0].equals("start")) {
            initBoard();
            OutputVIew.printBoardState(chessGame.getBoard().getBoardState());
            return false;
        }

        if (tokens[0].equals("end")) {
            System.exit(0);
            return true;
        }

        if (tokens[0].equals("status")) {
            assertStarted();
            ChessScoreCount scoreCount = new ChessScoreCount(chessGame.getBoard());
            OutputVIew.printScore(scoreCount.getScore(WHITE), scoreCount.getScore(BLACK));
            return false;
        }

        if (tokens[0].equals("move")) {
            assertStarted();

            handleMoveCommand(tokens);
            OutputVIew.printBoardState(chessGame.getBoard().getBoardState());
            return checkResult();
        }

        throw new IllegalArgumentException("알 수 없는 명령입니다");
    }

    private static void initBoard() {
        AbstractChessPieceFactory factory = new ChessPieceFactory();
        ChessPiece empty = factory.create(PieceType.NONE);

        chessGame = new ChessGame(new StateInitiatorFactory(), Turn.firstTurn());
    }

    private static boolean checkResult() {
        ChessResult result = ChessResult.judge(chessGame.getBoard());
        if (result == ChessResult.KEEP) {
            return false;
        }
        return true;
    }

    private static void handleMoveCommand(String[] tokens) {
        try {
            chessGame.move(ChessCoordinate.valueOf(tokens[1]), ChessCoordinate.valueOf(tokens[2]));
        } catch (IllegalArgumentException e) {
            OutputVIew.printError(e.getMessage());
        }
    }

    private static void assertStarted() {
        if (chessGame == null) {
            throw new IllegalArgumentException("start를 먼저 해주세요");
        }
    }
}
