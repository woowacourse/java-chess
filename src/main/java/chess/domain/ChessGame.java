package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import chess.domain.scorecalculator.ChessScoreCalculator;
import chess.domain.scorecalculator.ScoreCalculator;
import chess.exception.InvalidTurnException;
import chess.exception.PieceNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public final class ChessGame {

    private static final int BOARD_SIZE = 8;
    private static final int START_LINE = 0;
    private static final int END_LINE = 7;

    private final List<Piece> pieces;
    private final ScoreCalculator scoreCalculator;
    private TeamColor currentColor;

    private ChessGame(List<Piece> pieces, TeamColor teamColor, ScoreCalculator scoreCalculator) {
        this.pieces = new ArrayList<>(pieces);
        currentColor = teamColor;
        this.scoreCalculator = scoreCalculator;
    }

    public static ChessGame initialGame() {
        return from(
            PieceFactory.initialPieces(BOARD_SIZE, START_LINE, END_LINE),
            TeamColor.WHITE
        );
    }

    public static ChessGame from(List<Piece> pieces, TeamColor teamColor) {
        ChessGame chessGame = new ChessGame(pieces, teamColor, new ChessScoreCalculator());
        chessGame.updateMovablePositions();
        return chessGame;
    }

    private void updateMovablePositions() {
        pieces.forEach(piece ->
            piece.updateMovablePositions(
                existPiecePositions(),
                existPiecePositionsByColor(piece.enemyColor())
            )
        );
    }

    private List<Position> existPiecePositions() {
        return pieces.stream()
            .map(Piece::currentPosition)
            .collect(Collectors.toList());
    }

    private List<Position> existPiecePositionsByColor(TeamColor teamColor) {
        return pieces.stream()
            .filter(piece -> piece.isSameColor(teamColor))
            .map(Piece::currentPosition)
            .collect(Collectors.toList());
    }

    public void movePiece(Position currentPosition, Position targetPosition) {
        Piece currentPiece = pieceByPosition(currentPosition)
            .orElseThrow(PieceNotFoundException::new);
        ;
        if (!currentPiece.isSameColor(currentColor)) {
            throw new InvalidTurnException(currentColor);
        }
        Optional<Piece> targetPiece = pieceByPosition(targetPosition);

        currentPiece.move(targetPosition);
        targetPiece.ifPresent(pieces::remove);
        updateMovablePositions();
        currentColor = currentColor.reverse();
    }

    public Optional<Piece> pieceByPosition(Position currentPosition) {
        return pieces.stream()
            .filter(piece -> piece.samePosition(currentPosition))
            .findAny();
    }

    public GameResult gameResult() {
        return new GameResult(scoreByTeamColor(TeamColor.WHITE), scoreByTeamColor(TeamColor.BLACK));
    }

    private Score scoreByTeamColor(TeamColor teamColor) {
        List<Piece> pieces = piecesByTeamColor(teamColor);
        return scoreCalculator.calculate(pieces);
    }

    private List<Piece> piecesByTeamColor(TeamColor teamColor) {
        return pieces.stream()
            .filter(piece -> piece.isSameColor(teamColor))
            .collect(Collectors.toList());
    }
}
