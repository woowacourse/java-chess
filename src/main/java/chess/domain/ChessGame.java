package chess.domain;

import chess.domain.piece.*;
import chess.exception.ImpossibleMoveException;
import chess.exception.PieceNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static chess.domain.TeamColor.BLACK;
import static chess.domain.TeamColor.WHITE;
import static java.util.stream.Collectors.toMap;

public class ChessGame {

    public static final int BOARD_SIZE = 8;

    private final List<Piece> pieces = new ArrayList<>();
    private TeamColor currentColor = WHITE;

    public ChessGame() {
        init();
    }

    private void init() {
        initPieces(WHITE, 0);
        initPawns(WHITE, 1);

        initPawns(BLACK, 6);
        initPieces(BLACK, 7);
        updateMovablePositions();
    }

    private void initPieces(TeamColor teamColor, int y) {
        pieces.add(new Rook(teamColor, Position.of(0, y)));
        pieces.add(new Knight(teamColor, Position.of(1, y)));
        pieces.add(new Bishop(teamColor, Position.of(2, y)));
        pieces.add(new Queen(teamColor, Position.of(3, y)));
        pieces.add(new King(teamColor, Position.of(4, y)));
        pieces.add(new Bishop(teamColor, Position.of(5, y)));
        pieces.add(new Knight(teamColor, Position.of(6, y)));
        pieces.add(new Rook(teamColor, Position.of(7, y)));
    }

    private void initPawns(TeamColor teamColor, int y) {
        for (int x = 0; x < BOARD_SIZE; x++) {
            pieces.add(new Pawn(teamColor, Position.of(x, y)));
        }
    }

    public void updateMovablePositions() {
        pieces.forEach(piece ->
                piece.addMovablePositions(existPiecePositions(),
                        positionsByTeamColor(piece.enemyColor())
                )
        );
    }

    public boolean isKingDead() {
        return piecesByTeamColor(currentColor)
                .stream()
                .noneMatch(Piece::isKing);
    }

    public List<Piece> piecesByTeamColor(TeamColor teamColor) {
        return pieces.stream()
                .filter(piece -> piece.sameColor(teamColor))
                .collect(Collectors.toList());
    }

    public Score totalScoreByTeamColor(TeamColor teamColor) {
        List<Piece> pieces = piecesByTeamColor(teamColor);

        int pawnCount = (int) pieces.stream()
                .filter(Piece::isPawn)
                .count();

        int sameColumnPawnCount = (int) (pawnCount - pieces.stream()
                .filter(Piece::isPawn)
                .map(Piece::row)
                .distinct()
                .count());

        return pieces.stream()
                .map(Piece::score)
                .reduce(Score.from(0), Score::addedScore)
                .minusPawnCount(sameColumnPawnCount);
    }

    private List<Position> existPiecePositions() {
        return pieces.stream()
                .map(Piece::currentPosition)
                .collect(Collectors.toList());
    }

    private List<Position> positionsByTeamColor(TeamColor teamColor) {
        return piecesByTeamColor(teamColor).stream()
                .map(Piece::currentPosition)
                .collect(Collectors.toList());
    }

    public void move(Position currentPosition, Position targetPosition) throws PieceNotFoundException, ImpossibleMoveException {
        Piece currentPiece = piece(currentPosition).orElseThrow(PieceNotFoundException::new);
        if (currentPiece.isNotSameColor(currentColor)) {
            throw new ImpossibleMoveException(currentColor + "팀 차례 입니다.");
        }

        Optional<Piece> targetPiece = piece(targetPosition);

        currentPiece.changePosition(targetPosition);
        targetPiece.ifPresent(pieces::remove);
        updateMovablePositions();
        currentColor = currentColor.reverse();
    }

    public Map<Position, String> nameGroupingByPosition() {
        return pieces.stream()
                .collect(toMap(
                        Piece::currentPosition,
                        Piece::name
                ));
    }

    public Optional<Piece> piece(Position position) {
        return pieces.stream()
                .filter(piece -> piece.isSamePosition(position))
                .findAny();
    }

    public TeamColor enemyColor() {
        return currentColor.reverse();
    }
}
