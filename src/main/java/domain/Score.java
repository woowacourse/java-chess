package domain;

import domain.chessgame.ChessBoard;
import domain.piece.Piece;
import domain.position.File;
import domain.position.Position;
import domain.position.Rank;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Score {

    private static final int SEVERAL_PAWN_COUNT = 2;
    private final ChessBoard chessBoard;

    public Score(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }

    public double white() {
        return calculateScoreByPlayer(Player.WHITE);
    }

    public double black() {
        return calculateScoreByPlayer(Player.BLACK);
    }

    private double calculateScoreByPlayer(final Player player) {
        return Arrays.stream(File.values())
            .map(file -> calculateTotalScoreInFile(player, file))
            .mapToDouble(Double::doubleValue)
            .sum();
    }

    private double calculateTotalScoreInFile(final Player player, final File file) {
        List<Piece> pieces = createPiecesInFile(player, file);
        boolean isSeveralPawn = isSeveralPawnInList(pieces);
        return pieces.stream().map(piece -> piece.score(isSeveralPawn))
            .mapToDouble(Double::doubleValue)
            .sum();
    }

    private List<Piece> createPiecesInFile(final Player player, final File file) {
        return Arrays.stream(Rank.values())
            .map(rank -> chessBoard.findPiece(Position.of(file, rank)))
            .filter(piece -> piece.isSamePlayer(player))
            .collect(Collectors.toList());
    }

    private boolean isSeveralPawnInList(final List<Piece> pieces) {
        return pieces.stream()
            .filter(Piece::isPawn)
            .count() >= SEVERAL_PAWN_COUNT;
    }
}
