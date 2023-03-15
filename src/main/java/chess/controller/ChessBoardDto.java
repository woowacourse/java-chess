package chess.controller;

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
import chess.domain.position.Rank;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class ChessBoardDto {

    private final List<String> chessBoardView;

    public ChessBoardDto(ChessBoard chessBoard) {
        this.chessBoardView = new ArrayList<>();
        List<Square> squares = chessBoard.getSquares();

        StringBuilder tempChessBoard = new StringBuilder();
        for (int i = 0; i < squares.size(); i++) {
            tempChessBoard.append(generateSquareView(squares.get(i)));
            final int chessBoardWidth = Rank.values().length;
            if (i % chessBoardWidth == chessBoardWidth - 1) {
                this.chessBoardView.add(0, tempChessBoard.toString());
                tempChessBoard = new StringBuilder();
            }
        }
    }

    private String generateSquareView(final Square square) {
        Piece piece = square.getPiece();
        String view = generatePieceView(piece);
        if (piece.getTeam() == Team.BLACK) {
            view = view.toUpperCase(Locale.ROOT);
        }
        return view;
    }

    private String generatePieceView(final Piece piece) {
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

    public List<String> getBoard() {
        return Collections.unmodifiableList(chessBoardView);
    }
}
