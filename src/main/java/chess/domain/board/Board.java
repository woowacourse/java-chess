package chess.domain.board;

import static chess.domain.board.File.A;
import static chess.domain.board.File.B;
import static chess.domain.board.File.C;
import static chess.domain.board.File.D;
import static chess.domain.board.File.E;
import static chess.domain.board.File.F;
import static chess.domain.board.File.G;
import static chess.domain.board.File.H;
import static chess.domain.board.Rank.EIGHT;
import static chess.domain.board.Rank.ONE;
import static chess.domain.board.Rank.SEVEN;
import static chess.domain.board.Rank.TWO;
import static chess.domain.piece.vo.TeamColor.BLACK;
import static chess.domain.piece.vo.TeamColor.WHITE;

import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.vo.TeamColor;
import chess.game.TotalScore;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class Board {

    private static final Map<TeamColor, Rank> RANK_BY_TEAM_COLOR = Map.of(
            WHITE, ONE,
            BLACK, EIGHT
    );

    private static final Map<TeamColor, Rank> RANK_BY_TEAM_COLOR_PAWN = Map.of(
            WHITE, TWO,
            BLACK, SEVEN
    );

    private static final Map<File, BiFunction<TeamColor, Position, Piece>> PIECE_BY_FILE = Map.of(
            A, Rook::new, B, Knight::new, C, Bishop::new, D, Queen::new,
            E, King::new, F, Bishop::new, G, Knight::new, H, Rook::new
    );

    private final List<Piece> pieces;
    private TeamColor currentTurnTeamColor;

    private Board(final List<Piece> pieces, final TeamColor currentTurnTeamColor) {
        this.pieces = pieces;
        this.currentTurnTeamColor = currentTurnTeamColor;
    }

    public Board() {
        List<Piece> pieces = new ArrayList<>();
        pieces.addAll(initTeamPieces(BLACK));
        pieces.addAll(initTeamPieces(WHITE));
        this.pieces = pieces;
        currentTurnTeamColor = WHITE;
    }

    private List<Piece> initTeamPieces(TeamColor teamColor) {
        List<Piece> pieces = initPiecesExceptPawn(teamColor);
        pieces.addAll(initPawns(teamColor));
        return pieces;
    }

    private List<Piece> initPiecesExceptPawn(TeamColor teamColor) {
        List<Piece> pieces = new ArrayList<>();
        Rank rank = RANK_BY_TEAM_COLOR.get(teamColor);
        for (File file : File.values()) {
            pieces.add(PIECE_BY_FILE.get(file).apply(teamColor, Position.of(file, rank)));
        }
        return pieces;
    }

    private List<Piece> initPawns(TeamColor teamColor) {
        List<Piece> pieces = new ArrayList<>();
        Rank rank = RANK_BY_TEAM_COLOR_PAWN.get(teamColor);
        for(File file : File.values()) {
            pieces.add(new Pawn(teamColor, Position.of(file, rank)));
        }
        return pieces;
    }

    public Board movePiece(final Position sourcePosition, final Position targetPosition) {
        final Piece sourcePiece = findPieceInPosition(sourcePosition);
        validateTurn(sourcePiece);
        final List<Piece> otherPieces = getOtherPieces(sourcePiece);
        final Piece movedPiece = sourcePiece.move(otherPieces, targetPosition);

        if (hasPieceInPosition(targetPosition)) {
            removeTargetPositionPiece(findPieceInPosition(targetPosition), movedPiece);
        }
        pieces.set(pieces.indexOf(sourcePiece), movedPiece);
        currentTurnTeamColor = currentTurnTeamColor.nextTurn();
        return new Board(pieces, currentTurnTeamColor);
    }

    public Piece findPieceInPosition(final Position position) {
        return pieces.stream()
                .filter(piece -> piece.hasPosition(position))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("해당 위치에 기물이 없습니다."));
    }

    public boolean hasPieceInPosition(final Position position) {
        return pieces.stream()
                .anyMatch(piece -> piece.hasPosition(position));
    }

    private void validateTurn(final Piece sourcePiece) {
        if (!sourcePiece.isTeamOf(currentTurnTeamColor)) {
            throw new IllegalArgumentException("다른 팀 기물은 이동시킬 수 없습니다.");
        }
    }

    private List<Piece> getOtherPieces(final Piece sourcePiece) {
        return pieces.stream()
                .filter(piece -> !sourcePiece.equals(piece))
                .collect(Collectors.toUnmodifiableList());
    }

    private void removeTargetPositionPiece(final Piece targetPositionPiece, final Piece movedPiece) {
        validateSameTeamTargetPositionPiece(movedPiece, targetPositionPiece);
        pieces.remove(targetPositionPiece);
    }

    private void validateSameTeamTargetPositionPiece(final Piece movedPiece, final Piece targetPositionPiece) {
        if (movedPiece.isSameTeam(targetPositionPiece)) {
            throw new IllegalArgumentException("이동하려는 위치에 같은 팀 기물이 있습니다.");
        }
    }

    public boolean hasOneKing() {
        return pieces.stream()
                .filter(piece -> piece.isTypeOf(King.class))
                .count() == 1;
    }

    public double getTotalPoint(TeamColor teamColor) {
        final List<Piece> teamPieces = pieces.stream()
                .filter(piece -> piece.isTeamOf(teamColor))
                .collect(Collectors.toUnmodifiableList());
        return TotalScore.getTotalScore(teamPieces);
    }

    public TeamColor getCurrentTurnTeamColor() {
        return currentTurnTeamColor;
    }
}
