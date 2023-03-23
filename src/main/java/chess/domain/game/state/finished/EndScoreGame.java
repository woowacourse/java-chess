package chess.domain.game.state.finished;

import chess.domain.piece.Camp;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.position.ChessBoard;
import chess.domain.position.File;
import chess.domain.position.Position;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EndScoreGame extends FinishedGame {

    private final double whiteCampScore;
    private final double blackCampScore;

    public EndScoreGame(ChessBoard chessBoard) {
        super(chessBoard);
        this.whiteCampScore = calculateChessScore(chessBoard, Camp.WHITE);
        this.blackCampScore = calculateChessScore(chessBoard, Camp.BLACK);
    }

    private double calculateChessScore(ChessBoard chessBoard, Camp camp) {
        boolean hasSamePawnInSameFile = isPawnReduction(chessBoard, camp);

        List<Piece> collect = chessBoard.getPiecesOfCamp(camp);

        double totalScore = 0d;
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
    public Map<Position, Piece> getPiecesPosition() {
        return null;
    }
}
