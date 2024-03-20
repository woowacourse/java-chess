package chess.view;

import chess.model.ChessPosition;
import chess.model.File;
import chess.model.Rank;

public record MoveArguments(String sourceFile, int sourceRank, String targetFile, int targetRank) {
    public ChessPosition createSourcePosition() {
        return new ChessPosition(File.from(sourceFile), Rank.from(sourceRank));
    }

    public ChessPosition createTargetPosition() {
        return new ChessPosition(File.from(targetFile), Rank.from(targetRank));
    }
}
