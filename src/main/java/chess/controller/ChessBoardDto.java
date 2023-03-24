package chess.controller;


import static chess.controller.ChessBoardDto.ChessViewGenerator.generateSquareView;

import chess.domain.ChessBoard;
import chess.domain.Square;
import chess.domain.Team;
import chess.domain.piece.Piece;
import chess.domain.piece.Role;
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
            if (piece.isSameRole(Role.PAWN)) {
                return "p";
            }
            if (piece.isSameRole(Role.ROOK)) {
                return "r";
            }
            if (piece.isSameRole(Role.BISHOP)) {
                return "b";
            }
            if (piece.isSameRole(Role.KNIGHT)) {
                return "n";
            }
            if (piece.isSameRole(Role.KING)) {
                return "k";
            }
            if (piece.isSameRole(Role.QUEEN)) {
                return "q";
            }
            return ".";
        }
    }
}
