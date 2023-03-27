package chess.dto;

import chess.domain.position.File;
import chess.domain.position.Rank;

public class ChessPositionDto {

    private final int id;
    private final File file;
    private final Rank rank;
    private final String pieceName;
    private final String teamName;

    private ChessPositionDto(final int id, final File file, final Rank rank, final String pieceName, final String teamName) {
        this.id = id;
        this.file = file;
        this.rank = rank;
        this.pieceName = pieceName;
        this.teamName = teamName;
    }

    public static ChessPositionDto of(final int id, final String file, final String rank, final String pieceName, final String teamName) {
        return new ChessPositionDto(id, File.of(file.charAt(0)), Rank.of(rank.charAt(0)), pieceName, teamName);
    }

    public int getId() {
        return id;
    }

    public File getFile() {
        return file;
    }

    public Rank getRank() {
        return rank;
    }

    public String getPieceName() {
        return pieceName;
    }

    public String getTeamName() {
        return teamName;
    }
}
