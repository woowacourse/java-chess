package chess.view;

import chess.domain.piece.*;
import chess.domain.pieces.Color;
import chess.domain.position.Position;

import java.util.List;

public class OutputView {

    public void outputBoard(final List<ChessPiece> pieces) {
        char[][] board = new char[8][8];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = '.';
            }
        }

        for (ChessPiece piece : pieces) {
            Position pos = piece.getPosition();
            int row = 8 - pos.row().ordinal();
            int col = pos.column().ordinal() - 1;
            board[row][col] = getPieceSymbol(piece);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
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
