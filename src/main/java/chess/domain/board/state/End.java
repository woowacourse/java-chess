package chess.domain.board.state;

import chess.domain.board.Rank;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class End extends GameStarted {

    public End(Map<Integer, Rank> ranks) {
        super(ranks);
    }

    @Override
    public boolean isEnd() {
        return true;
    }

    @Override
    public boolean isBlackTurn() {
        throw new IllegalStateException("게임이 끝나서 턴이 없습니다.");
    }

    @Override
    public BoardState move(Position start, Position target) {
        throw new IllegalStateException("게임이 끝나서 말을 움직일 수 없습니다.");
    }

    @Override
    public BoardState terminate() {
        throw new IllegalStateException("이미 게임이 종료되었습니다.");
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

    private List<Piece> makeBlackFile(int index) {
        List<Piece> file = new ArrayList<>();

        for (Rank rank : ranks.values()) {
            file.add(rank.getPieces().get(index));
        }

        return file.stream()
                .filter(Piece::isBlack)
                .collect(Collectors.toList());
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
}
