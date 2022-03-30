package chess;

import java.util.List;
import java.util.Map;

import chess.domain.ChessGame;
import chess.domain.Status;
import chess.domain.piece.Color;
import chess.domain.position.Square;
import chess.view.InputView;
import chess.view.OutputView;

public final class ChessGameLauncher {

    private static final int MOVE_SOURCE_INDEX = 0;
    private static final int MOVE_TARGET_INDEX = 1;
    private static final String ERROR_MESSAGE_NOT_KING_DIE = "[ERROR] 아직 게임 안끝났거등? ㅋ";
    private static final String ERROR_MESSAGE_KING_DIE = "[ERROR] 이미 게임 끝났거등? ㅋ";
    private static final String ERROR_MESSAGE_NOT_IN_GAME = "[ERROR] 게임 시작부터 해야지! -3-";
    private static final String ERROR_MESSAGE_IN_GAME = "[ERROR] 당신.. 이미 게임 중인걸..?";

    void run() {
        OutputView.announceStart();
        ChessGame chessGame = null;
        execute(chessGame);
    }

    private void execute(ChessGame chessGame) {
        do {
            Map.Entry<Command, List<Square>> input = InputView.getCommand();
            Command command = input.getKey();
            List<Square> squares = input.getValue();

            if (command == Command.END) {
                System.exit(0);
            }
            if (command == Command.START) {
                chessGame = initGame(chessGame);
            }
            if (command == Command.MOVE) {
                movePiece(chessGame, squares);
            }
            if (command == Command.STATUS && getScore(chessGame)) {
                break;
            }
        } while (true);

    }

    private ChessGame initGame(ChessGame chessGame) {
        try {
            checkInGame(chessGame);
            chessGame = new ChessGame();
            OutputView.showBoard(chessGame.getBoard());
        } catch (IllegalArgumentException e) {
            OutputView.printMessage(e.getMessage());
        }
        return chessGame;
    }

    private void movePiece(ChessGame chessGame, List<Square> squares) {
        try {
            checkStartGame(chessGame);
            checkKingDie(chessGame);
            chessGame.move(squares.get(MOVE_SOURCE_INDEX), squares.get(MOVE_TARGET_INDEX));
            OutputView.showBoard(chessGame.getBoard());
            announceKingDie(chessGame);
        } catch (IllegalArgumentException e) {
            OutputView.printMessage(e.getMessage());
        }
    }

    private void announceKingDie(ChessGame chessGame) {
        if (chessGame.isKingDie()) {
            OutputView.printKingDieMessage();
        }
    }

    private boolean getScore(ChessGame chessGame) {
        try {
            checkStartGame(chessGame);
            checkKingNotDie(chessGame);
            showScore(chessGame);
            return true;
        } catch (IllegalArgumentException e) {
            OutputView.printMessage(e.getMessage());
        }
        return false;
    }

    private void showScore(ChessGame chessGame) {
        Status status = new Status(chessGame.getBoard());
        OutputView.showScore(status, Color.WHITE);
        OutputView.showScore(status, Color.BLACK);
    }

    private void checkKingNotDie(ChessGame chessGame) {
        if (!chessGame.isKingDie()) {
            throw new IllegalArgumentException(ERROR_MESSAGE_NOT_KING_DIE);
        }
    }

    private void checkKingDie(ChessGame chessGame) {
        if (chessGame.isKingDie()) {
            throw new IllegalArgumentException(ERROR_MESSAGE_KING_DIE);
        }
    }

    private void checkStartGame(ChessGame chessGame) {
        if (chessGame == null) {
            throw new IllegalArgumentException(ERROR_MESSAGE_NOT_IN_GAME);
        }
    }

    private void checkInGame(ChessGame chessGame) {
        if (chessGame != null) {
            throw new IllegalArgumentException(ERROR_MESSAGE_IN_GAME);
        }
    }
}
