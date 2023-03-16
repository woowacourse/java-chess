package chess.view;

import chess.domain.board.Chessboard;
import chess.domain.board.File;
import chess.domain.board.Rank;
import chess.domain.board.Square;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;

import java.util.Arrays;

public class OutputView {

    public void printChessBoard(Chessboard chessboard) {
        for (Rank rank : Rank.values()) {
            printRankAt(chessboard, rank);
        }
    }

    private void printRankAt(Chessboard chessboard, Rank rank) {
        StringBuilder stringBuilder = new StringBuilder();
        for (File file : File.values()) {
            Piece piece = chessboard.getPieceAt(new Square(file, rank));
            stringBuilder.append(PieceRenderer.getPieceName(piece));
        }
        System.out.println(stringBuilder);
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
}
