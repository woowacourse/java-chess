package chess.domain.board.state;

import chess.domain.board.Rank;
import chess.domain.piece.Piece;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class GameStarted implements BoardState {

    protected final Map<Integer, Rank> ranks;

    public GameStarted(Map<Integer, Rank> ranks) {
        this.ranks = ranks;
    }

    public Rank getRank(int rankLine) {
        return ranks.get(rankLine);
    }


    @Override
    public double calculateBlackScore() {
        double totalScore = 0;

        for (int i = 0; i < 8; i++) {
            List<Piece> file = makeBlackFile(i);
            List<Piece> pawns = getPawns(file);
            List<Piece> noPawns = getNoPawns(file);
            totalScore += calculateFileScore(pawns, noPawns);
        }

        return totalScore;
    }

    private List<Piece> makeBlackFile(int index) {
        List<Piece> file = new ArrayList<>();

        for (Rank rank : ranks.values()) {
            file.add(rank.getPieces().get(index));
        }

        return file.stream()
                .filter(Piece::isBlack)
                .collect(Collectors.toList());
    }

    @Override
    public double calculateWhiteScore() {
        double totalScore = 0;

        for (int i = 0; i < 8; i++) {
            List<Piece> file = makeWhiteFile(i);
            List<Piece> pawns = getPawns(file);
            List<Piece> noPawns = getNoPawns(file);
            totalScore += calculateFileScore(pawns, noPawns);
        }

        return totalScore;
    }

    private List<Piece> makeWhiteFile(int index) {
        List<Piece> file = new ArrayList<>();

        for (Rank rank : ranks.values()) {
            file.add(rank.getPieces().get(index));
        }

        return file.stream()
                .filter(piece -> !piece.isBlack() && !piece.isBlank())
                .collect(Collectors.toList());
    }

    private List<Piece> getPawns(List<Piece> file) {
        return file.stream()
                .filter(Piece::isPawn)
                .collect(Collectors.toList());
    }

    private List<Piece> getNoPawns(List<Piece> file) {
        return file.stream()
                .filter(piece -> !piece.isPawn())
                .collect(Collectors.toList());
    }

    private double calculateFileScore(List<Piece> pawns, List<Piece> noPawns) {
        double pawnsScore = calculatePiecesScore(pawns);
        double noPawnsScore = calculatePiecesScore(noPawns);

        if (pawns.size() >= 2) {
            pawnsScore *= 0.5;
        }

        return pawnsScore + noPawnsScore;
    }

    private double calculatePiecesScore(List<Piece> pieces) {
        return pieces.stream()
                .mapToDouble(Piece::getScore)
                .sum();
    }
}
