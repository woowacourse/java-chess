package chess.piece;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import chess.board.File;
import chess.board.Position;
import chess.board.Rank;

public abstract class Piece {

    protected final Team team;
    private final PieceType type;

    protected Piece(final Team team, final PieceType type) {
        this.team = team;
        this.type = type;
    }

    public boolean isWhite() {
        return team == Team.WHITE;
    }

    public boolean isBlack() {
        return team == Team.BLACK;
    }

    public boolean isEmpty() {
        return team == Team.EMPTY;
    }

    public boolean isSameTeam(final Team team) {
        return this.team == team;
    }

    public boolean isSameType(final PieceType type) {
        return this.type == type;
    }

    public void validateMove(final Position from, final Position to, final Map<Position, Piece> board) {
        validateStay(from, to);
        validateDestination(board.get(to)); // TODO Null 처리
        validatePathByType(from, to, board);
    }

    private void validateStay(final Position from, final Position to) {
        if (from.equals(to)) {
            throw new IllegalArgumentException("제자리로는 움직일 수 없습니다.");
        }
    }

    private void validateDestination(final Piece toPiece) {
        if (this.team == toPiece.team) {
            throw new IllegalArgumentException("목적지에 같은 색의 말이 존재하여 이동할 수 없습니다.");
        }
    }

    protected abstract void validatePathByType(final Position from, final Position to, final Map<Position, Piece> board);

    protected void validateDiagonal(final Position from, final Position to, final Map<Position, Piece> board) {
        final List<File> files = File.sliceBetween(from.getFile(), to.getFile());
        final List<Rank> ranks = Rank.sliceBetween(from.getRank(), to.getRank());

        if (files.size() != ranks.size()) {
            return;
        }

        for (final Position position : makeDiagonal(sliceFile(from, to, files), sliceRank(from, to, ranks))) {
            validateBlockedRoute(board.get(position));
        }
    }

    private List<Position> makeDiagonal(final List<File> slicedFiles, final List<Rank> slicedRanks) {
        return IntStream.range(0, slicedFiles.size())
                .mapToObj(index -> new Position(slicedFiles.get(index), slicedRanks.get(index)))
                .collect(Collectors.toList());
    }

    private List<File> sliceFile(final Position from, final Position to, final List<File> files) {
        final List<File> slicedFiles = IntStream.range(1, files.size() - 1)
                .mapToObj(files::get)
                .collect(Collectors.toList());
        if (from.getFile().getIndex() > to.getFile().getIndex()) {
            Collections.reverse(slicedFiles);
        }
        return slicedFiles;
    }

    private List<Rank> sliceRank(final Position from, final Position to, final List<Rank> ranks) {
        final List<Rank> slicedRanks = IntStream.range(1, ranks.size() - 1)
                .mapToObj(ranks::get)
                .collect(Collectors.toList());
        if (from.getRank().getIndex() > to.getRank().getIndex()) {
            Collections.reverse(slicedRanks);
        }
        return slicedRanks;
    }

    protected void validateStraight(final Position from, final Position to, final Map<Position, Piece> board) {
        try {
            validateVertical(from, to, board);
        } catch (Exception e) {
            validateHorizontal(from, to, board);
        }
    }

    protected void validateVertical(final Position from, final Position to, final Map<Position, Piece> board) {
        if ((from.getFile() != to.getFile())) {
            throw new IllegalArgumentException("같은 File이 아니면 움직일 수 없습니다.");
        }
        final Rank fromRank = from.getRank();
        final Rank toRank = to.getRank();
        final int min = Math.min(fromRank.getIndex(), toRank.getIndex()) + 1;
        final int max = Math.max(fromRank.getIndex(), toRank.getIndex()) - 1;

        for (int i = min; i <= max; i++) {
            final Piece validationPiece = board.get(new Position(from.getFile(), Rank.from(i)));
            validateBlockedRoute(validationPiece);
        }
    }

    private void validateHorizontal(final Position from, final Position to, final Map<Position, Piece> board) {
        if (from.getRank() != to.getRank()) {
            throw new IllegalArgumentException("같은 Rank가 아니면 움직일 수 없습니다.");
        }
        final File fromFile = from.getFile();
        final File toFile = to.getFile();
        final int min = Math.min(fromFile.getIndex(), toFile.getIndex()) + 1;
        final int max = Math.max(fromFile.getIndex(), toFile.getIndex()) - 1;

        for (int i = min; i <= max; i++) {
            final Piece validationPiece = board.get(new Position(File.from(i), from.getRank()));
            validateBlockedRoute(validationPiece);
        }
    }

    private void validateBlockedRoute(final Piece validationPiece) {
        if (!validationPiece.isEmpty()) {
            throw new IllegalArgumentException("말이 이동경로에 존재하여 이동할 수 없습니다.");
        }
    }

    public Team getTeam() {
        return team;
    }

    public PieceType getType() {
        return type;
    }
}
