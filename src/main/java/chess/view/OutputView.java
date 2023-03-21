package chess.view;

import chess.controller.ChessBoardDto;
import chess.controller.PieceDto;

import java.util.List;
import java.util.Objects;

public class OutputView {

    private static final String PROMPT = "> ";

    public void printInitialMessage() {
        printMessageWithPrompt("체스 게임을 시작합니다.");
        printMessageWithPrompt("게임 시작  : start");
        printMessageWithPrompt("게임 종료  : end");
        printMessageWithPrompt("게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    private void printMessageWithPrompt(final String message) {
        System.out.println(PROMPT + message);
    }

    public void printChessBoard(ChessBoardDto chessBoardDto) {
        final List<List<PieceDto>> pieceDtos = chessBoardDto.getPieceDtos();
        System.out.println();
        for (List<PieceDto> rank : pieceDtos) {
            printRank(rank);
        }
        System.out.println();
    }

    public void printInvalidMoveMessage() {
        System.out.println("이동할 수 없습니다.");
    }

    private void printRank(final List<PieceDto> rank) {
        for (PieceDto piece : rank) {
            final String type = piece.getType();
            final String side = piece.getSide();
            printPiece(type, side);
        }
        System.out.println();
    }

    private void printPiece(final String type, final String side) {
        if (Objects.equals(type, "EMPTY_PIECE")) {
            System.out.print(" . ");
        }
        if (Objects.equals(type, "PAWN")) {
            printLetter(" p ", side);
        }
        if (Objects.equals(type, "ROOK")) {
            printLetter(" r ", side);
        }
        if (Objects.equals(type, "KNIGHT")) {
            printLetter(" n ", side);
        }
        if (Objects.equals(type, "BISHOP")) {
            printLetter(" b ", side);
        }
        if (Objects.equals(type, "QUEEN")) {
            printLetter(" q ", side);
        }
        if (Objects.equals(type, "KING")) {
            printLetter(" k ", side);
        }
    }

    private void printLetter(final String letter, final String side) {
        if (Objects.equals(side, "BLACK")) {
            System.out.print(letter.toUpperCase());
            return;
        }
        System.out.print(letter.toLowerCase());
    }
}
