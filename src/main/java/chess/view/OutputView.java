package chess.view;

import chess.domain.ChessBoard;
import chess.domain.Column;
import chess.domain.Position;
import chess.domain.Row;
import chess.domain.piece.ChessPiece;
import java.util.Map;

public class OutputView {
    public void printChessBoard(ChessBoard chessBoard) {

        for (Row row : Row.values()) {
            for (Column column : Column.values()) {
                ChessPiece piece = chessBoard.getPieceOfPosition(new Position(row, column));
                System.out.print(piece.name());
            }
            System.out.println();
        }
    }
}
