package chess.view;

import static chess.view.InputView.END_COMMAND;
import static chess.view.InputView.START_COMMAND;

import chess.domain.board.Position;
import chess.domain.piece.Piece;
import java.util.Map;

public class OutputView {
    private static final String GAME_START_MESSAGE = "체스 게임을 시작합니다.";
    private static final String GAME_START_COMMAND_FORMAT = "게임 시작은 %s, 종료는 %s 명령을 입력하세요.%n";

    public void printStartMessage() {
        System.out.println(GAME_START_MESSAGE);
        System.out.printf(GAME_START_COMMAND_FORMAT, START_COMMAND, END_COMMAND);
    }

    public void printBoard(Map<Position, Piece> board) {
        int count = 0;
        for (Position position : board.keySet()) {
            String content = makeBoardContentString(board.get(position));
            System.out.print(content);
            count++;
            if (count % 8 == 0) {
                System.out.println();
            }
        }
    }

    private String makeBoardContentString(Piece piece) {
        if (piece == null) {
            return ".";
        }
        return decideCaseByCamp(piece);
    }

    private String decideCaseByCamp(Piece piece) {
        String convertedString = convertPieceToString(piece);
        if (piece.isBlack()) {
            return convertedString.toUpperCase();
        }
        return convertedString;
    }

    private String convertPieceToString(Piece piece) {
        if (piece.isBishop()) {
            return "b";
        }
        if (piece.isKing()) {
            return "k";
        }
        if (piece.isKnight()) {
            return "n";
        }
        if (piece.isPawn()) {
            return "p";
        }
        if (piece.isQueen()) {
            return "q";
        }
        if (piece.isRook()) {
            return "r";
        }
        throw new IllegalArgumentException("존재하지 않는 기물입니다.");
    }
}
