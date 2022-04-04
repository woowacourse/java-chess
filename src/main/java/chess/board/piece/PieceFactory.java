package chess.board.piece;

import chess.board.Team;
import chess.board.piece.position.Position;


public class PieceFactory {

    public static Piece create(final String position, final String team, final String type) {
        Position loadPosition = Position.of(position.charAt(0), position.charAt(1));
        if (type.equals("rook")) {
            return new Rook(loadPosition, Team.from(team));
        }
        if (type.equals("knight")) {
            return new Knight(loadPosition, Team.from(team));
        }
        if (type.equals("bishop")) {
            return new Bishop(loadPosition, Team.from(team));
        }
        if (type.equals("queen")) {
            return new Queen(loadPosition, Team.from(team));
        }
        if (type.equals("king")) {
            return new King(loadPosition, Team.from(team));
        }
        if (type.equals("pawn")) {
            return new Pawn(loadPosition, Team.from(team));
        }
        if (type.equals("empty")) {
            return new Empty(loadPosition);
        }
        throw new IllegalArgumentException("[ERROR] 잘못 입력되었습니다.");
    }


}
