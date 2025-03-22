package chess.view;

import chess.domain.piece.*;
import chess.domain.pieces.Color;
import chess.domain.position.Column;
import chess.domain.position.Position;

import java.util.List;

public class OutputView {

    public void outputBoard(
            final List<ChessPiece> blackPieces,
            final List<ChessPiece> whitePieces
    ) {
        String[][] board = new String[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board[i][j] = ".";
            }
        }

        for (int i = 1; i < 9; i++) {
            board[i][0] = "\033[32m" + (9 - i) + "\033[0m";
        }

        for (Column value : Column.values()) {
            board[0][value.ordinal() + 1] = "\033[32m" + value.name() + "\033[0m";
        }

        for (ChessPiece piece : blackPieces) {
            Position pos = piece.getPosition();
            int row = pos.row().ordinal() + 1;
            int col = pos.column().ordinal() + 1;
            board[row][col] = "\033[30m" + getPieceSymbol(piece) + "\033[0m";
        }

        for (ChessPiece piece : whitePieces) {
            Position pos = piece.getPosition();
            int row = pos.row().ordinal() + 1;
            int col = pos.column().ordinal() + 1;
            board[row][col] = "\033[37m" + getPieceSymbol(piece) + "\033[0m";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sb.append(board[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private char getPieceSymbol(final ChessPiece piece) {
        if (piece instanceof King) return 'K';
        if (piece instanceof Queen) return 'Q';
        if (piece instanceof Rook) return 'R';
        if (piece instanceof Bishop) return 'B';
        if (piece instanceof Knight) return 'N';
        if (piece instanceof Pawn) return 'P';
        throw new IllegalStateException();
    }

    public void outputWinner(final Color winnerColor) {
        if (winnerColor == Color.BLACK) {
            System.out.println("검정 승리!");
        }
        if (winnerColor == Color.WHITE) {
            System.out.println("하양 승리!");
        }
        throw new IllegalStateException();
    }
}
