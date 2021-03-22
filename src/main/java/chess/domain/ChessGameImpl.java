package chess.domain;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.toSet;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import chess.domain.scorecalculator.ChessScoreCalculator;
import chess.domain.scorecalculator.ScoreCalculator;
import chess.exception.InvalidTurnException;
import chess.exception.PieceNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public final class ChessGameImpl implements ChessGame {

    private static final int BOARD_SIZE = 8;
    private static final int START_LINE = 0;
    private static final int END_LINE = 7;

    private final List<Piece> pieces;
    private final ScoreCalculator scoreCalculator;
    private TeamColor currentColor;

    private ChessGameImpl(List<Piece> pieces, TeamColor teamColor,
        ScoreCalculator scoreCalculator) {
        this.pieces = new ArrayList<>(pieces);
        currentColor = teamColor;
        this.scoreCalculator = scoreCalculator;
    }

    public static ChessGameImpl initialGame() {
        return from(
            PieceFactory.initialPieces(BOARD_SIZE, START_LINE, END_LINE),
            TeamColor.WHITE
        );
    }

    public static ChessGameImpl emptyGame() {
        return from(new ArrayList<>(), TeamColor.WHITE);
    }

    public static ChessGameImpl from(List<Piece> pieces, TeamColor teamColor) {
        ChessGameImpl chessGame = new ChessGameImpl(pieces, teamColor, new ChessScoreCalculator());
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
            .collect(toList());
    }

    private List<Position> existPiecePositionsByColor(TeamColor teamColor) {
        return pieces.stream()
            .filter(piece -> piece.isSameColor(teamColor))
            .map(Piece::currentPosition)
            .collect(toList());
    }

    @Override
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

    @Override
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
            .collect(toList());
    }

    @Override
    public boolean isKingDead() {
        return kingByColor(currentColor).isEmpty();
    }

    @Override
    public boolean isChecked() {
        Set<Position> enemiesAttackPositions = attackPositionsByColor(currentColor.reverse());

        return enemiesAttackPositions.contains(
            kingByColor(currentColor)
                .orElseThrow(PieceNotFoundException::new)
                .currentPosition()
        );
    }

    private Set<Position> attackPositionsByColor(TeamColor teamColor) {
        return piecesByTeamColor(teamColor)
            .stream()
            .flatMap(piece -> piece.movablePositions().stream())
            .collect(toSet());
    }

    @Override
    public boolean isCheckmate() {
        Set<Position> attackPositions = attackPositionsByColor(currentColor.reverse());
        Piece king = kingByColor(currentColor).orElseThrow(PieceNotFoundException::new);
        List<Position> movablePositions = king.movablePositions();
        movablePositions.add(king.currentPosition());
        return attackPositions.containsAll(movablePositions);
    }

    private Optional<Piece> kingByColor(TeamColor teamColor) {
        return piecesByTeamColor(teamColor)
            .stream()
            .filter(Piece::isKing)
            .findAny();
    }

    @Override
    public Map<Position, String> nameGroupingByPosition() {
        return pieces.stream()
            .collect(toMap(
                Piece::currentPosition,
                Piece::name
            ));
    }

    @Override
    public int boardSize() {
        return BOARD_SIZE;
    }

    @Override
    public TeamColor currentColor() {
        return currentColor;
    }
}
