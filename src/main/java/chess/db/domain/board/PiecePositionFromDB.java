package chess.db.domain.board;

import chess.beforedb.domain.piece.type.PieceType;
import chess.beforedb.domain.player.type.TeamColor;
import chess.beforedb.domain.position.type.File;
import chess.beforedb.domain.position.type.Rank;

public class PiecePositionFromDB {
    private final PieceType pieceType;
    private final TeamColor teamColor;
    private final File file;
    private final Rank rank;

    public PiecePositionFromDB(String pieceName, String pieceColor, String fileValue,
        String rankValue) {

        pieceType = PieceType.valueOf(pieceName);
        teamColor = TeamColor.of(pieceColor);
        file = File.of(fileValue);
        rank = Rank.of(rankValue);
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    public TeamColor getTeamColor() {
        return teamColor;
    }

    public File getFile() {
        return file;
    }

    public Rank getRank() {
        return rank;
    }
}
