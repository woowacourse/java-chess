package chess.domain.strategy;

import static java.util.stream.Collectors.toList;

import chess.domain.ChessBoard;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.List;

public class RookMoveStrategy implements MoveStrategy {
    @Override
    public void isMovable(Position source, Position target, ChessBoard chessBoard) {
        if (source.isSameFile(target) && source.isSameRank(target)) {
            throw new IllegalStateException("제자리에 머무를 수 없습니다.");
        }

        if (!source.isSameFile(target) && !source.isSameRank(target)) {
            throw new IllegalStateException("상하좌우로만 움직일 수 있습니다.");
        }

        if (source.isSameFile(target)) {
            Rank sourceRank = source.getRank();
            Rank targetRank = target.getRank();

            List<Rank> ranks = calculateRanks(sourceRank, targetRank);

            List<Position> paths = ranks.stream()
                    .map(rank -> Position.of(source.getFile(), rank))
                    .collect(toList());

            if (chessBoard.isContainPiece(paths)) {
                throw new IllegalStateException();
            }
        }

        if (source.isSameRank(target)) {
            File sourceFile = source.getFile();
            File targetFile = target.getFile();

            List<File> files = calculateFiles(sourceFile, targetFile);

            List<Position> paths = files.stream()
                    .map(file -> Position.of(file, source.getRank()))
                    .collect(toList());

            if (chessBoard.isContainPiece(paths)) {
                throw new IllegalStateException();
            }
        }
    }

    private List<Rank> calculateRanks(Rank sourceRank, Rank targetRank) {
        if (sourceRank.getRank() < targetRank.getRank()) {
            return Rank.getRanks(sourceRank, targetRank);
        }
        return Rank.getRanks(targetRank, sourceRank);
    }

    private List<File> calculateFiles(File sourceFile, File targetFile) {
        if (sourceFile.getFile() < targetFile.getFile()) {
            return File.getFiles(sourceFile, targetFile);
        }
        return File.getFiles(targetFile, sourceFile);
    }
}
