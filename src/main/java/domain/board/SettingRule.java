package domain.board;

import domain.piece.Color;
import domain.piece.Pawn;
import domain.piece.Piece;
import domain.piece.PieceType;
import domain.position.File;
import domain.position.Rank;

public class SettingRule {

    public Piece findPieceByPosition(Rank rank, File file) {
        PieceType pieceType = InitialPiecePosition.find(rank, file);
        Color color = InitialColorPosition.find(rank);
        if (pieceType.isPawn()) {
            return new Pawn(pieceType, color);
        }
        return new Piece(pieceType, color);
    }
}
