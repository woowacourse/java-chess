package chess.domain.game.state;

import chess.domain.game.result.GameResult;
import chess.domain.game.result.MatchResult;
import chess.domain.piece.Camp;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceScore;
import chess.domain.position.ChessBoard;
import chess.domain.position.File;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EndScoreGame extends FinishedGame {

    private final PieceScore whiteCampScore;
    private final PieceScore blackCampScore;

    protected EndScoreGame(ChessBoard chessBoard) {
        super(chessBoard);
        this.whiteCampScore = calculateChessScore(chessBoard, Camp.WHITE);
        this.blackCampScore = calculateChessScore(chessBoard, Camp.BLACK);
    }

    private PieceScore calculateChessScore(ChessBoard chessBoard, Camp camp) {
        boolean hasSamePawnInSameFile = isPawnReduction(chessBoard, camp);

        List<Piece> collect = chessBoard.getPiecesOfCamp(camp);

        PieceScore totalScore = PieceScore.getZero();
        for (Piece piece : collect) {
            totalScore = piece.appendPieceScore(totalScore, hasSamePawnInSameFile);
        }
        return totalScore;
    }

    private boolean isPawnReduction(ChessBoard chessBoard, Camp camp) {
        return Stream.of(File.values())
                .map(chessBoard::getPiecesInFile)
                .map(pieces -> hasSamePawnInFile(pieces, camp))
                .collect(Collectors.toList())
                .contains(Boolean.TRUE);
    }

    private boolean hasSamePawnInFile(List<Piece> pieces, Camp camp) {
        return pieces.stream()
                .filter(piece -> piece.equals(new Pawn(camp)))
                .count() > 1;
    }

    @Override
    public GameResult calculateResult() {
        Map<Camp, PieceScore> scoreByCamp = new ConcurrentHashMap<>();
        MatchResult matchResult = matchByScore();

        scoreByCamp.put(Camp.WHITE, whiteCampScore);
        scoreByCamp.put(Camp.BLACK, blackCampScore);
        return new GameResult(matchResult, scoreByCamp);
    }

    private MatchResult matchByScore() {
        if (whiteCampScore.isGreaterThan(blackCampScore)) {
            return MatchResult.WHITE_WIN;
        }

        if (blackCampScore.isGreaterThan(whiteCampScore)) {
            return MatchResult.BLACK_WIN;
        }

        return MatchResult.DRAW;
    }
}
