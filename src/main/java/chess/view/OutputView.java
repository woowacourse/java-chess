package chess.view;

import chess.domain.piece.Piece;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import chess.dto.ChessBoardDto;

import java.util.Map;

public class OutputView {
    private static final String ERROR_HEADER = "[ERROR] ";
    private static final String NEW_LINE = System.lineSeparator();

    public void printStartMessage() {
        System.out.println(
                "> 체스 게임을 시작합니다." + NEW_LINE
                        + "> 게임 시작 : start" + NEW_LINE
                        + "> 게임 종료 : end" + NEW_LINE
                        + "> 게임 이동 : move source위치 target위치 - 예. move b2 b3"
        );
    }

    public void printChessBoard(ChessBoardDto chessBoardDto) {
        Map<Position, Piece> chessBoard = chessBoardDto.chessBoard();

        for (Rank rank : Rank.values()) {
            printEachRank(chessBoard, rank);
            System.out.println();
        }
        System.out.println();
    }

    public void printErrorMessage(String message) {
        System.out.println(ERROR_HEADER + message);
    }

    private void printEachRank(Map<Position, Piece> chessBoard, Rank rank) {
        for (File file : File.values()) {
            printEachPiece(chessBoard, rank, file);
        }
    }

    private void printEachPiece(Map<Position, Piece> chessBoard, Rank rank, File file) {
        Position position = Position.of(file, rank);
        if (chessBoard.containsKey(position)) {
            Piece piece = chessBoard.get(position);
            System.out.print(PieceSymbol.convertToSymbol(piece));
            return;
        }
        System.out.print(PieceSymbol.printEmptySymbol());
    }
}
