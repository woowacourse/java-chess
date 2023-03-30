package chess.controller;

import chess.domain.Square;
import chess.domain.Team;
import chess.domain.piece.Piece;
import chess.domain.piece.Role;
import java.util.ArrayList;
import java.util.List;

public class ChessViewMapper {

    private static final int CHESS_BOARD_WIDTH = 8;

    public static List<String> generateChessView(List<Square> squares) {
        final StringBuilder tempChessBoard = new StringBuilder();
        final List<String> chessBoardView = new ArrayList<>();
        for (int index = 0; index < squares.size(); index++) {
            tempChessBoard.append(ChessViewMapper.generateSquareView(squares.get(index)));
            addOneLine(chessBoardView, tempChessBoard, index);
        }
        return chessBoardView;
    }

    private static String generateSquareView(final Square square) {
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

    private static void addOneLine(final List<String> chessBoardView, final StringBuilder tempChessBoard,
                                   final int index) {
        if (index % CHESS_BOARD_WIDTH == CHESS_BOARD_WIDTH - 1) {
            chessBoardView.add(0, tempChessBoard.toString());
            tempChessBoard.delete(0, tempChessBoard.length());
        }
    }
}
