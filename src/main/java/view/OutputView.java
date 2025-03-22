package view;

import chess.Color;
import chess.Column;
import chess.Position;
import chess.Row;
import chess.piece.Piece;
import chess.piece.PieceType;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class OutputView {

    public void printWinner(Color color){
        System.out.printf("축하합니다! %s의 승리입니다!\n",color.getName());
    }

    public void printBoard(List<Piece> pieces){
        String columnInfo = Arrays.stream(Column.values())
                .map(Column::getName)
                .map(String::valueOf)
                .collect(Collectors.joining(" "));
        System.out.println("    "+columnInfo);
        Row[] rows = Row.values();
        Column[] columns = Column.values();
        for (int row = 0; row < rows.length; row++) {
            printRowInfo(rows[row]);
            for (int col = 0; col < columns.length; col++) {
                Position position = new Position(rows[row],columns[col]);
                Optional<Piece> findPiece = getFindPiece(pieces, position);
                printPositionState(findPiece.orElse(null));
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }

    public void printExceptionMessage(String message){
        System.out.println(message);
    }

    private void printRowInfo(Row row) {
        System.out.printf("%2s  ", row.getName());
    }

    private void printPositionState(Piece findPiece) {
        if (findPiece == null) {
            System.out.print("- ");
            return;
        }
        System.out.print(getDescription(findPiece) + " ");
    }

    private Optional<Piece> getFindPiece(List<Piece> pieces, Position position) {
        return pieces.stream().filter(piece -> piece.isSamePosition(position))
                .findAny();
    }

    private String getDescription(Piece piece){
        Color color = piece.getColor();
        PieceType pieceType = piece.getPieceType();
        if (color.isWhite()){
            return pieceType.getName();
        }
        return pieceType.getName().toLowerCase();
    }
}
