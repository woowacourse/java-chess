package chess.domain.view;

import chess.domain.Team;
import chess.domain.board.Board;
import chess.domain.pieces.Piece;
import chess.domain.position.PositionManager;

public class WebOutputView {
    public static String printBoard(Board board) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 8; i >= 1 ; i--) {
            stringBuilder.append("<div class=\"blank\"></div>\n");
            for (int j = 1; j <= 8; j++) {
                Piece piece = board.findPiece(PositionManager.getMatchPosition(j, i));
                stringBuilder.append(printPiece(piece, j, i));
            }
            stringBuilder.append("<div class=\"blank\"></div>\n");
        }
        return stringBuilder.toString();
    }

    private static String printPiece(Piece piece, int x, int y) {
        String tileColor = "";

        if ((x + y) % 2 == 0) {
            tileColor = " BLACK ";
        }
        if ((x + y) % 2 != 0) {
            tileColor = " WHITE ";
        }

        if (piece.getTeam() == Team.WHITE) {
            return "<div class=\"" + x + "" + y + tileColor + "WHITE" + piece + "\"></div>\n";
        }
        if (piece.getTeam() == Team.BLACK) {
            return "<div class=\"" + x + "" + y + tileColor + "BLACK" + piece + "\"></div>\n";
        }
        return "<div class=\"" + x + "" + y + tileColor + piece + "\"></div>\n";
    }

    public static String printTurn(Team team) {
        if (team == Team.WHITE) {
            return "WHITE";
        }
        return "BLACK";
    }
}
