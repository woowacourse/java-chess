package domain.board;

import domain.piece.Color;
import domain.piece.Piece;
import domain.piece.PieceType;
import domain.position.File;
import domain.position.Rank;

public class SettingRule {

    public Piece findPieceByPosition(Rank rank, File file) {
        Color color = InitialColorPosition.find(rank);
        PieceType pieceType = InitialPiecePosition.find(rank, file);
        return new Piece(pieceType, color);
    }
}
