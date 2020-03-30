package chess.domain.board;

import chess.domain.GameResult;
import chess.domain.command.MoveCommand;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.exception.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static chess.domain.piece.Pawn.PAWN_HALF_SCORE;
import static chess.domain.piece.Pawn.PAWN_SCORE;
import static chess.util.NullValidator.validateNull;

public class Board {
    public static final int ONLY_ONE_PAWN_IN_XPOINT = 1;

    public PieceColor team = PieceColor.WHITE;

    private final Map<Position, Piece> board = BoardFactory.getBoard();

    public Piece findPiece(Position sourcePosition, PieceColor team) {
        validateNull(sourcePosition);

        Piece sourcePiece = board.get(sourcePosition);
        if (sourcePiece.isNone()) {
            throw new PieceNotFoundException("움직일 수 있는 체스말이 없습니다.");
        }
        if (sourcePiece.isDifferentColor(team)) {
            throw new AnotherTeamPieceException("다른 색의 말을 움직일 수 없습니다.");
        }
        return board.get(sourcePosition);
    }

    private void checkPath(Piece piece, Position targetPosition) {
        if (piece instanceof Pawn) {
            checkPawnPath(piece, targetPosition);
            return;
        }
        List<Position> path = piece.getPathTo(targetPosition);
        if (havePieceBeforeTargetPosition(path)) {
            throw new OtherPieceInPathException("이동 경로 중에 다른 체스말이 있기 때문에 지정한 위치로 이동할 수 없습니다.");
        }
        if (cannotMoveToTargetPosition(piece, targetPosition)) {
            throw new SameTeamPieceException("지정한 위치에 같은 색의 체스말이 있기 때문에 이동할 수 없습니다.");
        }
    }

    private boolean havePieceBeforeTargetPosition(List<Position> path) {
        path.remove(path.size() - 1);
        return path.stream()
                .map(board::get)
                .noneMatch(Piece::isNone);
    }

    private boolean cannotMoveToTargetPosition(Piece piece, Position targetPosition) {
        Piece targetPiece = board.get(targetPosition);
        if (!targetPiece.isNone()) {
            return piece.isSameColor(targetPiece);
        }
        return false;
    }

    private void checkPawnPath(Piece piece, Position targetPosition) {
        List<Position> path = piece.getPathTo(targetPosition);
        if ((piece.getDirection(targetPosition).isSouth() || piece.getDirection(targetPosition).isNorth()) && havePieceIn(path)) {
            throw new OtherPieceInPathException("이동 경로 중에 다른 체스말이 있기 때문에 지정한 위치로 이동할 수 없습니다.");
        } else if (!piece.getDirection(targetPosition).isSouth() && !piece.getDirection(targetPosition).isNorth() && cannotMovePawnToTargetPosition(piece, targetPosition)) {
            throw new PawnNotAttackableException("지정한 위치에 다른 색의 체스말이 없기 때문에 이동할 수 없습니다.");
        }
    }

    private boolean havePieceIn(List<Position> path) {
        return !path.stream()
                .map(board::get)
                .allMatch(Piece::isNone);
    }

    private boolean cannotMovePawnToTargetPosition(Piece piece, Position targetPosition) {
        Piece targetPiece = board.get(targetPosition);
        if (targetPiece.isNone()) {
            return true;
        }
        return piece.isSameColor(targetPiece);
    }

    public void move(MoveCommand moveCommand) {
        validateNull(moveCommand);

        Piece piece = findPiece(moveCommand.getSourcePosition(), team);
        Position targetPosition = moveCommand.getTargetPosition();
        checkPath(piece, targetPosition);

        board.put(piece.getPosition(), new EmptyPiece(PieceColor.NONE, piece.getPosition()));
        board.put(targetPosition, piece);
        piece.changeTo(targetPosition);
        team = team.change();
    }

    public boolean isBlackKingKilled() {
        return board.values().stream()
                .map(Piece::getName)
                .noneMatch("K"::equals);
    }

    public boolean isWhiteKingKilled() {
        return board.values().stream()
                .map(Piece::getName)
                .noneMatch("k"::equals);
    }

    private double getAlivePieceScoreSumBy(PieceColor pieceColor) {
        double scoreSum = board.values().stream()
                .filter(piece -> !(piece instanceof Pawn) && piece.isSameColor(pieceColor))
                .mapToDouble(Piece::getScore)
                .sum();

        return scoreSum + getAlivePawnScoreSumBy(pieceColor);
    }

    private double getAlivePawnScoreSumBy(PieceColor pieceColor) {
        double scoreSum = 0;

        for (char x = 'a'; x <= 'h'; x++) {
            int pawnInXPointCount = 0;
            for (char y = '1'; y <= '8'; y++) {
                Piece piece = board.get(PositionFactory.of(x, y));
                if (piece instanceof Pawn && piece.isSameColor(pieceColor)) {
                    pawnInXPointCount++;
                }
            }
            scoreSum += getPawnScoreSumInXPoint(pawnInXPointCount);
        }

        return scoreSum;
    }

    private double getPawnScoreSumInXPoint(int pawnInXPointCount) {
        double scoreSum = 0;

        if (ONLY_ONE_PAWN_IN_XPOINT < pawnInXPointCount) {
            scoreSum += pawnInXPointCount * PAWN_HALF_SCORE;
        } else {
            scoreSum += pawnInXPointCount * PAWN_SCORE;
        }

        return scoreSum;
    }

    public GameResult createGameResult() {
        return new GameResult(isBlackKingKilled(), isWhiteKingKilled(), getAlivePieceScoreSumBy(PieceColor.BLACK),
                              getAlivePieceScoreSumBy(PieceColor.WHITE));
    }

    public boolean isGameOver() {
        return isWhiteKingKilled() || isBlackKingKilled();
    }

    public PieceColor getTeam() {
        return team;
    }

    public Map<Position, Piece> get() {
        return Collections.unmodifiableMap(board);
    }
}
