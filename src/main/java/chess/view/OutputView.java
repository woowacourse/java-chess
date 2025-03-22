package chess.view;

import chess.domain.ChessBoard;
import chess.domain.Column;
import chess.domain.Position;
import chess.domain.Row;
import chess.domain.TeamColor;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;

public class OutputView {
    private static final String defaultColor = "\033[0m";

    public void displayBoard(ChessBoard board) {
        StringBuilder sb = new StringBuilder();
        sb.append("\033[36m" + "   a b c d e f g h \n");
        for(Row row: Row.values()) {
            sb.append("\033[36m" + row.intValue() + "| ");
            for(Column column : Column.values()) {
                Piece piece = board.findPieceBy(new Position(row, column));
                sb.append(colorString(piece.getTeamColor()));
                PieceType pieceType = piece.getPieceType();
                PieceTypeView pieceTypeView = PieceTypeView.from(pieceType);
                sb.append(pieceTypeView.getPieceNameBy(pieceType) + " ");
            }
            sb.append(System.lineSeparator());
        }

        sb.append("\033[36m" + "   a b c d e f g h \n " + defaultColor);
        System.out.println(sb);

    }

    public String colorString(TeamColor teamColor) {
        if(teamColor == TeamColor.BLACK) {
            return "\033[30m";
        }
        if(teamColor == TeamColor.WHITE) {
            return "\033[97m";
        }

        return "\033[33m";
    }
}
