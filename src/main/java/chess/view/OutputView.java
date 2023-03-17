package chess.view;

import chess.domain.board.Chessboard;
import chess.domain.board.File;
import chess.domain.board.Rank;
import chess.domain.board.Square;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;

import java.util.Arrays;

public class OutputView {
    private static final String ERROR = "[ERROR] : %s";

    public void printStartMessage() {
        System.out.println(Message.GAME_START.value);
        System.out.println(Message.START_COMMAND.value);
        System.out.println(Message.END_COMMAND.value);
        System.out.println(Message.MOVE_COMMAND.value);
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
            stringBuilder.append(PieceRenderer.getPieceName(piece));
        }
        System.out.println(stringBuilder);
    }

    public void printError(String message) {
        System.out.println(String.format(ERROR, message));
    }

    private enum PieceRenderer {
        PAWN("p"),
        ROOK("r"),
        KNIGHT("n"),
        BISHOP("b"),
        QUEEN("q"),
        KING("k"),
        EMPTY(".");

        private final String value;

        PieceRenderer(String value) {
            this.value = value;
        }

        public static String getPieceName(Piece piece) {
            PieceRenderer renderedPiece = renderPiece(piece.getPieceType());

            if (piece.isWhite()) {
                return renderedPiece.value;
            }

            return renderedPiece.value.toUpperCase();
        }

        private static PieceRenderer renderPiece(PieceType pieceType) {
            return Arrays.stream(values())
                    .filter(value -> value.name().equals(pieceType.name()))
                    .findFirst()
                    .orElseThrow(IllegalArgumentException::new);
        }
    }

    private enum Message {
        GAME_START("> 체스 게임을 시작합니다."),
        START_COMMAND("> 게임 시작 : start"),
        END_COMMAND("> 게임 종료 : end"),
        MOVE_COMMAND("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");

        private final String value;

        Message(String value) {
            this.value = value;
        }
    }
}
