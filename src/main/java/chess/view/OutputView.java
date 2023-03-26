package chess.view;

import chess.domain.ChessGame;
import chess.domain.board.Chessboard;
import chess.domain.board.File;
import chess.domain.board.Rank;
import chess.domain.board.Square;
import chess.domain.piece.Camp;
import chess.domain.piece.Piece;
import chess.dto.PieceRenderer;

public class OutputView {
    private static final String WHITE = "WHITE";
    private static final String BLACK = "BLACK";
    private static final String ERROR = "[ERROR] : %s";

    public void printError(String message) {
        System.out.println(String.format(ERROR, message));
    }

    public void printStartMessage() {
        System.out.println(Message.GAME_START.value);
        System.out.println(Message.START_COMMAND.value);
        System.out.println(Message.END_COMMAND.value);
        System.out.println(Message.MOVE_COMMAND.value);
        System.out.println(Message.STATUS_COMMAND.value);
    }

    public void printScoreMessage(ChessGame chessGame) {
        double whiteScore = chessGame.calculateScoreOf(Camp.WHITE);
        double blackScore = chessGame.calculateScoreOf(Camp.BLACK);

        printGameResult(chessGame);
        System.out.println(String.format(Message.SCORE.value, WHITE, whiteScore));
        System.out.println(String.format(Message.SCORE.value, BLACK, blackScore));
        printScoreWinnerMessage(whiteScore, blackScore);
    }

    private void printGameResult(ChessGame chessGame) {
        if (chessGame.isBothKingAlive()) {
            return;
        }

        System.out.println(System.lineSeparator() + Message.GAME_FINISH.value);
        printGameWinner(chessGame);
    }

    private void printGameWinner(ChessGame chessGame) {
        if (chessGame.isWhiteKingAlive()) {
            System.out.println(String.format(Message.GAME_WINNER.value, WHITE));
            return;
        }

        System.out.println(String.format(Message.GAME_WINNER.value, BLACK));
    }

    private void printScoreWinnerMessage(double whiteScore, double blackScore) {
        String message = Message.SCORE_DRAW.value;

        if (whiteScore > blackScore) {
            message = String.format(Message.SCORE_WINNER.value, WHITE);
        }

        if (whiteScore < blackScore) {
            message = String.format(Message.SCORE_WINNER.value, BLACK);
        }

        System.out.println(message);
    }

    public void printChessBoard(Chessboard chessboard) {
        System.out.println();
        for (Rank rank : Rank.values()) {
            printRankAt(chessboard, rank);
        }
    }

    private void printRankAt(Chessboard chessboard, Rank rank) {
        StringBuilder stringBuilder = new StringBuilder();

        for (File file : File.values()) {
            Piece piece = chessboard.getPieceAt(Square.getInstanceOf(file, rank));
            stringBuilder.append(PieceRenderer.render(piece));
        }

        System.out.println(stringBuilder);
    }

    private enum Message {
        GAME_START("> 체스 게임을 시작합니다."),
        START_COMMAND("> 게임 시작 : start"),
        END_COMMAND("> 게임 종료 : end"),
        MOVE_COMMAND("> 게임 이동 : move source위치 target위치 - 예. move b2 b3"),
        STATUS_COMMAND("> 게임 점수 출력 : status"),
        GAME_FINISH("> King이 죽었기 때문에 게임을 종료합니다."),
        GAME_WINNER("> %s가 승리하였습니다."),
        SCORE("> %s의 점수는 %.1f입니다."),
        SCORE_WINNER("> %s의 점수가 더 높습니다."),
        SCORE_DRAW("> 동점입니다.");

        private final String value;

        Message(String value) {
            this.value = value;
        }
    }
}
