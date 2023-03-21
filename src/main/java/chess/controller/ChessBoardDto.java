package chess.controller;


import static chess.controller.ChessBoardDto.ChessViewGenerator.generateSquareView;

import chess.domain.ChessBoard;
import chess.domain.Square;
import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.info.Team;
import java.util.ArrayList;
import java.util.List;

public class ChessBoardDto {

    private static final int CHESS_BOARD_WIDTH = 8;

    private final List<String> chessBoardView;

    public ChessBoardDto(ChessBoard chessBoard) {
        chessBoardView = new ArrayList<>();
        List<Square> squares = chessBoard.getSquares();
        StringBuilder tempChessBoard = new StringBuilder();
        for (int index = 0; index < squares.size(); index++) {
            tempChessBoard.append(generateSquareView(squares.get(index)));
            addOneLine(tempChessBoard, index);
        }
    }

    private void addOneLine(final StringBuilder tempChessBoard, final int index) {
        if (index % CHESS_BOARD_WIDTH == CHESS_BOARD_WIDTH - 1) {
            chessBoardView.add(0, tempChessBoard.toString());
            tempChessBoard.delete(0, tempChessBoard.length());
        }
    }

    public List<String> getBoard() {
        return chessBoardView;
    }

    static class ChessViewGenerator {

        public static String generateSquareView(final Square square) {
            Piece piece = square.getPiece();
            String view = generatePieceView(piece);
            if (piece.getTeam() == Team.BLACK) {
                view = view.toUpperCase();
            }
            return view;
        }

        private static String generatePieceView(final Piece piece) {
            if (piece.getClass() == Pawn.class) {
                return "p";
            }
            if (piece.getClass() == Rook.class) {
                return "r";
            }
            if (piece.getClass() == Bishop.class) {
                return "b";
            }
            if (piece.getClass() == Knight.class) {
                return "n";
            }
            if (piece.getClass() == King.class) {
                return "k";
            }
            if (piece.getClass() == Queen.class) {
                return "q";
            }
            return ".";
        }
    }
}
