package chess.controller;

import chess.domain.ChessBoard;
import chess.domain.Square;
import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.info.Team;
import chess.domain.position.Rank;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class ChessBoardDto {

    private final List<String> chessBoardView;

    public ChessBoardDto(ChessBoard chessBoard) {
        chessBoardView = new ArrayList<>();
        List<Square> squares = chessBoard.getSquares();
        StringBuilder tempChessBoard = new StringBuilder();
        for (int index = 0; index < squares.size(); index++) {
            tempChessBoard.append(generateSquareView(squares.get(index)));
            final int chessBoardWidth = Rank.values().length;
            tempChessBoard = addOneFile(tempChessBoard, chessBoardWidth, index);
        }
        chessBoardView.add("");
        chessBoardView.add(Arrays.stream(Rank.values())
            .map(Rank::toString)
            .collect(Collectors.joining("")));
    }

    private StringBuilder addOneFile(StringBuilder tempChessBoard, final int chessBoardWidth,
        final int index) {
        if (index % chessBoardWidth == chessBoardWidth - 1) {
            tempChessBoard.append(" ").append((index + 1) / chessBoardWidth);
            chessBoardView.add(0, tempChessBoard.toString());
            tempChessBoard = new StringBuilder();
        }
        return tempChessBoard;
    }

    private String generateSquareView(final Square square) {
        Piece piece = square.getPiece();
        String view = piece.findType().getSymbol();
        if (piece.getTeam() == Team.BLACK) {
            view = view.toUpperCase(Locale.ROOT);
        }
        return view;
    }

    public List<String> getBoard() {
        return Collections.unmodifiableList(chessBoardView);
    }
}
