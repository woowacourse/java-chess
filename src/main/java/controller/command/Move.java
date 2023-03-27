package controller.command;

import domain.game.File;
import domain.game.Rank;
import util.FileMapper;
import util.RankMapper;

public class Move implements Command {
    private final File sourceFile;
    private final Rank sourceRank;
    private final File targetFile;
    private final Rank targetRank;

    private Move(File sourceFile, Rank sourceRank, File targetFile, Rank targetRank) {
        this.sourceFile = sourceFile;
        this.sourceRank = sourceRank;
        this.targetFile = targetFile;
        this.targetRank = targetRank;
    }

    public static Move of(String sourceText, String targetText) {
        //TODO substring
        String sourceFileText = sourceText.split("")[0];
        String sourceRankText = sourceText.split("")[1];
        String targetFileText = targetText.split("")[0];
        String targetRankText = targetText.split("")[1];
        return new Move(
                FileMapper.convertTextToFile(sourceFileText),
                RankMapper.convertTextToRank(sourceRankText),
                FileMapper.convertTextToFile(targetFileText),
                RankMapper.convertTextToRank(targetRankText));
    }

    @Override
    public boolean isStart() {
        return false;
    }

    @Override
    public boolean isMove() {
        return true;
    }

    @Override
    public boolean isStatus() {
        return false;
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    public File getSourceFile() {
        return sourceFile;
    }

    public Rank getSourceRank() {
        return sourceRank;
    }

    public File getTargetFile() {
        return targetFile;
    }

    public Rank getTargetRank() {
        return targetRank;
    }
}
