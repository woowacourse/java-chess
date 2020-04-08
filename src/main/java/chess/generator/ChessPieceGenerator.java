package chess.generator;

import chess.domain.chesspiece.*;
import chess.domain.game.Team;
import chess.domain.move.Position;

import static chess.domain.chesspiece.ChessPieceInfo.*;

public class ChessPieceGenerator {
    private ChessPieceGenerator() {
    }

    public static ChessPiece generateChessPiece(String pieceName, String teamName, Position position) {
        String lowerPieceName = pieceName.toLowerCase();

        if (lowerPieceName.equals(KING.getName())) {
            return new King(Team.of(teamName));
        }
        if (lowerPieceName.equals(QUEEN.getName())) {
            return new Queen(Team.of(teamName));
        }
        if (lowerPieceName.equals(ROOK.getName())) {
            return new Rook(Team.of(teamName));
        }
        if (lowerPieceName.equals(BISHOP.getName())) {
            return new Bishop(Team.of(teamName));
        }
        if (lowerPieceName.equals(KNIGHT.getName())) {
            return new Knight(Team.of(teamName));
        }
        if (lowerPieceName.equals(PAWN.getName())) {
            Pawn pawn = new Pawn(Team.of(teamName));

            pawn.updateFirstMove(teamName, position);
            return pawn;
        }
        return new Blank();
    }
}
