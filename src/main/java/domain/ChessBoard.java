package domain;

import domain.piece.property.PieceSymbol;
import domain.piece.property.Team;
import domain.piece.unit.Pawn;
import domain.piece.unit.Piece;
import domain.position.Position;
import domain.position.XPosition;
import domain.position.YPosition;
import domain.utils.PieceScore;
import domain.utils.Result;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class ChessBoard {

    private static final int DEFAULT_KING_COUNT = 2;
    private static final int PAWN_DUPLICATE_CONDITION = 2;
    private static final Team START_TEAM = Team.WHITE;

    private Map<Position, Piece> board;
    private Team currentTurn;

    public ChessBoard(final BoardGenerator boardGenerator) {
        this.board = boardGenerator.generate();
        this.currentTurn = START_TEAM;
    }

    public void move(final Position source, final Position target) {
        checkNullSource(source);
        checkCurrentTurn(source);
        checkAvailableTarget(target);
        checkGoThroughPosition(source, target);

        movePiece(source, target);
    }

    private void checkNullSource(final Position source) {
        if (board.get(source) == null) {
            throw new IllegalArgumentException("[ERROR] 선택한 위치는 기물이 존재하지 않습니다.");
        }
    }

    private void checkCurrentTurn(final Position source) {
        if (!board.get(source).checkSameTeam(this.currentTurn)) {
            throw new IllegalArgumentException("[ERROR] 선택한 위치는 자신의 기물의 위치가 아닙니다.");
        }
    }

    private void checkAvailableTarget(final Position target) {
        if (board.get(target) != null && board.get(target).checkSameTeam(currentTurn)) {
            throw new IllegalArgumentException("[ERROR] 자신의 기물이 위치한 곳으로 이동할 수 없습니다.");
        }
    }

    private void checkGoThroughPosition(final Position source, final Position target) {
        Piece piece = board.get(source);
        checkUnavailableMove(source, target, piece);
        if (board.get(source) instanceof Pawn) {
            checkPawnMovement(source, target);
            return;
        }
        checkRoutePositionsNull(target, piece);
    }

    private void checkUnavailableMove(final Position source, final Position target, final Piece piece) {
        if (!piece.availableMove(source, target)) {
            throw new IllegalArgumentException("[ERROR] 선택한 위치로 이동할 수 없습니다.");
        }
    }

    private void checkPawnMovement(final Position source, final Position target) {
        if (checkPawnUpDownDirection(source, target)) {
            checkPawnMoveForward(source, target);
            return;
        }
        checkPawnAttack(target);
    }

    private boolean checkPawnUpDownDirection(final Position source, final Position target) {
        Piece piece = board.get(source);
        return ((Pawn) piece).checkUpDownDirection(source, target);
    }

    private void checkPawnMoveForward(final Position source, final Position target) {
        Piece piece = board.get(source);

        if (((Pawn) piece).checkMoveOneSpace(target)) {
            checkBoardPositionIsNull(target);
            return;
        }
        checkWayPointNullPawn(piece, target);
    }

    private void checkBoardPositionIsNull(final Position position) {
        if (board.get(position) != null) {
            throw new IllegalArgumentException("[ERROR] 선택한 위치로 이동할 수 없습니다.");
        }
    }

    private void checkWayPointNullPawn(final Piece piece, final Position target) {
        List<Position> positions = ((Pawn) piece).calculateForwardRouteByPawn(target);
        boolean checkExistNotNullInPositions = positions.stream()
                .anyMatch(position -> board.get(position) != null);

        if (checkExistNotNullInPositions) {
            throw new IllegalArgumentException("[ERROR] 다른 기물에 의해 선택한 위치로 이동할 수 없습니다.");
        }
    }

    private void checkPawnAttack(final Position target) {
        if (board.get(target) == null) {
            throw new IllegalArgumentException("[ERROR] 선택한 위치에 상대 기물이 없습니다.");
        }
    }

    private void checkRoutePositionsNull(final Position target, final Piece piece) {
        List<Position> routePositions = calculateRoutePositions(target, piece);
        for (Position position : routePositions) {
            checkWayPointNull(position);
        }
    }

    private List<Position> calculateRoutePositions(final Position target, final Piece piece) {
        List<Position> baseRoutePositions = piece.calculateRoute(target);
        if (baseRoutePositions.size() == 0) {
            return null;
        }
        return baseRoutePositions.subList(0, baseRoutePositions.indexOf(target));
    }

    private void checkWayPointNull(final Position position) {
        if (board.get(position) != null) {
            throw new IllegalArgumentException("[ERROR] 다른 기물에 의해 선택한 위치로 이동할 수 없습니다.");
        }
    }

    private void movePiece(final Position source, final Position target) {
        board.put(target, board.get(source));
        board.put(source, null);

        changeTurn();
    }

    private void changeTurn() {
        if (this.currentTurn == Team.BLACK) {
            this.currentTurn = Team.WHITE;
            return;
        }
        this.currentTurn = Team.BLACK;
    }

    public double calculateTeamScore(final Team Team) {
        double sum = 0;
        for (XPosition x : XPosition.values()) {
            List<Piece> pieces = calculateTeamPiecesByXPosition(Team, x);
            sum += calculateXPositionScore(pieces);
        }
        return sum;
    }

    private List<Piece> calculateTeamPiecesByXPosition(final Team Team, final XPosition x) {
        return Arrays.stream(YPosition.values())
                .map(yPosition -> board.get(Position.of(x, yPosition)))
                .filter(piece -> piece != null)
                .filter(piece -> piece.checkSameTeam(Team))
                .collect(Collectors.toList());
    }

    private double calculateXPositionScore(final List<Piece> pieces) {
        List<Double> scores = new ArrayList<>();
        pieces.forEach(piece -> scores.add(PieceScore.createScore(piece.symbol(), checkDuplicatePawn(pieces))));

        return scores.stream()
                .filter(score -> score != null)
                .mapToDouble(Double::doubleValue).sum();
    }

    private boolean checkDuplicatePawn(List<Piece> pieces) {
        return pieces.stream()
                .filter(piece -> piece.symbol().equals(PieceSymbol.Pawn.symbol()))
                .count() >= PAWN_DUPLICATE_CONDITION;
    }

    public Result calculateWinner() {
        double currentTeamScore = calculateTeamScore(currentTurn);
        double opponentTeamScore = calculateTeamScore(getOpponentTeam());

        return competeScore(currentTeamScore, opponentTeamScore);
    }

    private Team getOpponentTeam() {
        if (currentTurn == Team.BLACK) {
            return Team.WHITE;
        }
        return Team.BLACK;
    }

    private Result competeScore(final double currentTeamScore, final double opponentTeamScore) {
        if (currentTeamScore > opponentTeamScore) {
            return Result.WIN;
        }
        if (currentTeamScore < opponentTeamScore) {
            return Result.LOSE;
        }
        return Result.DRAW;
    }

    public boolean checkKingExist() {
        return board.values().stream()
                .filter(piece -> piece != null)
                .filter(piece -> piece.symbol().equals(PieceSymbol.King.symbol()))
                .count() == DEFAULT_KING_COUNT;
    }

    public Team calculateWhoWinner() {
        return getOpponentTeam();
    }

    public Team getCurrentTurn() {
        return currentTurn;
    }

    public String symbol(final Position position) {
        final Piece piece = board.get(position);
        if (piece == null) {
            return ".";
        }
        return piece.getSymbolByTeam();
    }

    @Override
    public String toString() {
        return "ChessBoard{" +
                "board=" + board +
                '}';
    }
}
