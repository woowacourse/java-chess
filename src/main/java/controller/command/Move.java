package controller.command;

public class Move implements Command {
    private final String sourceFile;
    private final String sourceRank;
    private final String targetFile;
    private final String targetRank;

    private Move(String sourceFile, String sourceRank, String targetFile, String targetRank) {
        this.sourceFile = sourceFile;
        this.sourceRank = sourceRank;
        this.targetFile = targetFile;
        this.targetRank = targetRank;
    }

    public static Move of(String sourceText, String targetText) {
        //TODO substring
        String sourceFile = sourceText.split("")[0];
        String sourceRank = sourceText.split("")[1];
        String targetFile = targetText.split("")[0];
        String targetRank = targetText.split("")[1];
        return new Move(sourceFile, sourceRank, targetFile, targetRank);
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
    public boolean isEnd() {
        return false;
    }

    public String getSourceFile() {
        return sourceFile;
    }

    public String getSourceRank() {
        return sourceRank;
    }

    public String getTargetFile() {
        return targetFile;
    }

    public String getTargetRank() {
        return targetRank;
    }
}
