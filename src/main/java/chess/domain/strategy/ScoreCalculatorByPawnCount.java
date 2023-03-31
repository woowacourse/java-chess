package chess.domain.strategy;

import chess.domain.Score;
import chess.domain.Square;
import chess.domain.piece.PieceType;
import chess.domain.piece.info.Team;
import chess.domain.position.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ScoreCalculatorByPawnCount implements ScoreCalculator {

    private final Score specialPawn = new Score(0.5);

    public Score calculateByTeam(List<Square> squares, Team team) {
        return Arrays.stream(File.values())
            .map((file) -> calculateByFileAndTeam(squares, file, team))
            .reduce(Score.ZERO, Score::add);
    }

    private Score calculateByFileAndTeam(List<Square> squares, File file, Team team) {
        Map<PieceType, Long> pieceCountBoard = squares.stream()
            .filter((square) -> square.isSameFileAndTeam(file, team))
            .collect(Collectors.groupingBy(Square::findPieceType, Collectors.counting()));

        return squares.stream()
            .filter((square) -> square.isSameFileAndTeam(file, team))
            .map((square) -> findPieceScore(square, pieceCountBoard))
            .reduce(Score.ZERO, Score::add);
    }

    private Score findPieceScore(Square square, Map<PieceType, Long> pieceCountBoard) {
        if (square.findPieceType().equals(PieceType.PAWN)
            && pieceCountBoard.getOrDefault(PieceType.PAWN, 0L) > 1L) {
            return specialPawn;
        }
        return square.findPieceScore();
    }

}
