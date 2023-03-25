package chess.view;

import chess.controller.ChessBoardDto;
import chess.controller.PieceDto;

import java.util.List;

public class OutputView {

    private static final String PROMPT = "> ";

    public void printInstructions() {
        printMessageWithPrompt("체스 게임을 시작합니다.");
        printMessageWithPrompt("게임 종료 : end");
        printMessageWithPrompt("게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    private void printMessageWithPrompt(final String message) {
        System.out.println(PROMPT + message);
    }

    public void printChessBoard(ChessBoardDto chessBoardDto) {
        final List<List<PieceDto>> pieceDtos = chessBoardDto.getPieceDtos();

        printBlankLine();

        for (List<PieceDto> rank : pieceDtos) {
            printOneRank(rank);
        }
    }

    private void printOneRank(final List<PieceDto> rank) {
        for (PieceDto pieceDto : rank) {
            printPiece(pieceDto);
        }

        printBlankLine();
    }

    private void printPiece(final PieceDto pieceDto) {
        System.out.print(pieceDto.getOutputFormat());
    }

    public void printInvalidMoveMessage() {
        System.out.println("이동할 수 없습니다.");
    }

    private void printBlankLine() {
        System.out.println();
    }
}
