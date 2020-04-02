package chess.controller;

import chess.domain.board.ChessBoard;
import chess.domain.GameState;
import chess.domain.board.Square;
import chess.domain.TeamScore;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChessController {

    private static final String BLANK = " ";
    ChessBoard chessBoard = new ChessBoard();

    public void run() {
        OutputView.printGameInformation();
        if (!initGame()) {
            return;
        }
        proceed();
        OutputView.printWinner(TeamScore.getWinners(chessBoard.getChessBoard()));
    }

    private void proceed() {
        while (!isEndState(stateInput())) {
            OutputView.printChessBoard(chessBoard.getChessBoard());
        }
    }

    private List<String> stateInput() {
        return Arrays.asList(InputView.inputState().split(BLANK));
    }

    private boolean isEndState(List<String> stateInput) {
        GameState gameState;
        stateInput = getStateInput(stateInput);
        gameState = GameState.of(stateInput.get(0));
        if(isStartRepeat(gameState)){
            return false;
        }
        if(isInputStateStatus(gameState)){
            OutputView.printStatus(TeamScore.calculateTeamScore(chessBoard.getChessBoard()));
            OutputView.printWinner(TeamScore.getWinners(chessBoard.getChessBoard()));
            return false;
        }
        if(isInputStateEnd(gameState)){
            OutputView.printStatus(TeamScore.calculateTeamScore(this.chessBoard.getChessBoard()));
            return true;
        }
        if (isInputStateMove(gameState) && isRightMoveInput(stateInput)) {
            move(getMoveSquare(stateInput));
            return this.chessBoard.isKingCaptured();
        }
        return false;
    }

    private List<String> getStateInput(List<String> stateInput) {
        while(!validateGameState(stateInput.get(0))) {
            stateInput = stateInput();
        }
        return stateInput;
    }

    private boolean isInputStateEnd(GameState gameState) {
        return GameState.END.equals(gameState);
    }

    private boolean isInputStateStatus(GameState gameState) {
        return GameState.STATUS.equals(gameState);
    }

    private boolean validateGameState(String gameState) {
        if(gameState == null || gameState.isEmpty()){
            throw new IllegalArgumentException("입력이 존재하지 않습니다.");
        }
        return !GameState.of(gameState).equals(GameState.ERROR);
    }

    private boolean isRightMoveInput(List<String> stateInput) {
        if (stateInput.size() != 3) {
            return false;
        }
        try {
            Square.of(stateInput.get(1));
            Square.of(stateInput.get(2));
        } catch (IllegalArgumentException e) {
            OutputView.printInputError();
            return false;
        }
        return true;
    }

    private void move(List<Square> moveSquares) {
        if (!chessBoard.movePiece(moveSquares)) {
            OutputView.printCanNotMoveMessage();
        }
    }

    private static boolean isInputStateMove(GameState gameState) {
        return GameState.MOVE.equals(gameState);
    }


    private static List<Square> getMoveSquare(List<String> input) {
        List<Square> moveSquares = new ArrayList<>();
        moveSquares.add(Square.of(input.get(1)));
        moveSquares.add(Square.of(input.get(2)));
        return moveSquares;
    }

    private static boolean isStartRepeat(GameState gameState) {
        if (gameState.equals(GameState.START)) {
            OutputView.printAlreadyGameStartedMessage();
            return true;
        }
        return false;
    }

    private boolean initGame() {
        if (!startChessGame()) {
            return false;
        }
        OutputView.printChessBoard(this.chessBoard.getChessBoard());
        return true;
    }

    private static boolean startChessGame() {
        String input = InputView.inputState();
        while (!GameState.START.equals(GameState.of(input))) {
            if (GameState.END.equals((GameState.of(input)))) {
                return false;
            }
            OutputView.printStartGameFirstMessage();
            input = InputView.inputState();
        }
        return true;
    }
}
