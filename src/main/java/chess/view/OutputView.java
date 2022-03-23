package chess.view;

import chess.domain.Abscissa;
import chess.domain.Board;
import chess.domain.Ordinate;
import chess.domain.Position;
import chess.domain.piece.Piece;
import java.util.Map;
import java.util.Optional;

public class OutputView {

    private static final String NONE_PIECE = ".";

    public static void printInitialChessBoard(Board board) {
        for (Ordinate ordinate : Ordinate.values()) {
            printBoardRowLine(ordinate, board);
        }
    }

    public static void printBoardRowLine(Ordinate ordinate, Board board) {
        Map<Position, Piece> chessBoard = board.getBoard();
        for (Abscissa value : Abscissa.values()) {
            Optional<Piece> pieceOptional = Optional.ofNullable(
                chessBoard.get(Position.valueOf(value, ordinate)));
            String printFormat = pieceOptional.map(PieceMapper::from).orElse(NONE_PIECE);
            System.out.print(printFormat);
        }
        System.out.println();
    }
}
