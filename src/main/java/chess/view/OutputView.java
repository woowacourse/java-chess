package chess.view;

import chess.domain.piece.Piece;
import chess.domain.piece.Position;
import chess.domain.piece.type.Bishop;
import chess.domain.piece.type.King;
import chess.domain.piece.type.Night;
import chess.domain.piece.type.Pawn;
import chess.domain.piece.type.Queen;
import chess.domain.piece.type.Rook;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class OutputView {

    public void printChessBoard(final Set<Piece> pieces) {
        List<List<String>> borad = sortByBoardOrder(pieces);

        borad.forEach(row -> printChessRow(row));
    }

    private void printChessRow(final List<String> row) {
        row.forEach(System.out::print);
        System.out.println();
    }

    private List<List<String>> sortByBoardOrder(final Set<Piece> pieces) {
        List<List<String>> board = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            board.add(new ArrayList<>(List.of(".", ".", ".", ".", ".", ".", ".", ".")));
        }

        for (Piece piece : pieces) {
            Position position = piece.getPosition();
            int fileIndex = position.getFile().getIndex() - 1;
            int rankIndex = position.getRank().getNumber() - 1;
            List<String> marks = board.get(rankIndex);
            marks.set(fileIndex, convertToMark(piece));
        }

        return board;
    }

    private String convertToMark(final Piece piece) {
        if (piece instanceof Rook && piece.isBlack()) {
            return "R";
        }
        if (piece instanceof Rook && piece.isWhite()) {
            return "r";
        }
        if (piece instanceof Night && piece.isBlack()) {
            return "N";
        }
        if (piece instanceof Night && piece.isWhite()) {
            return "n";
        }
        if (piece instanceof Bishop && piece.isBlack()) {
            return "B";
        }
        if (piece instanceof Bishop && piece.isWhite()) {
            return "b";
        }
        if (piece instanceof Queen && piece.isBlack()) {
            return "Q";
        }
        if (piece instanceof Queen && piece.isWhite()) {
            return "q";
        }
        if (piece instanceof King && piece.isBlack()) {
            return "K";
        }
        if (piece instanceof King && piece.isWhite()) {
            return "k";
        }
        if (piece instanceof Pawn && piece.isBlack()) {
            return "P";
        }
        if (piece instanceof Pawn && piece.isWhite()) {
            return "p";
        }
        return "";
    }
}
