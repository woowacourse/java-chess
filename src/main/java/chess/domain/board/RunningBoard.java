package chess.domain.board;

import chess.config.BoardConfig;
import chess.domain.piece.Blank;
import chess.domain.piece.Piece;
import chess.domain.piece.PiecesState;
import chess.domain.piece.factory.PieceFactory;
import chess.domain.piece.factory.PieceType;
import chess.domain.piece.position.MovingFlow;
import chess.domain.piece.position.Position;
import chess.domain.piece.service.PieceService;
import chess.domain.piece.team.Team;

import java.util.HashMap;
import java.util.Map;

public class RunningBoard implements Board {
    private static final int BLACK_PAWN_ROW = 7;
    private static final int WHITE_PAWN_ROW = 2;
    private static final int BLANK_START_INDEX = 3;
    private static final int BLANK_END_INDEX = 6;

    private final Map<Position, Piece> pieces;

    private RunningBoard(Map<Position, Piece> pieces) {
        this.pieces = pieces;
    }

    public static RunningBoard initiaize() {
        Map<Position, Piece> pieces = new HashMap<>();
        initializeBlackTeam(pieces);
        initializeBlanks(pieces);
        initializeWhiteTeam(pieces);
        return new RunningBoard(pieces);
    }

    @Override
    public Board movePiece(MovingFlow movingFlow) {
        Position from = movingFlow.getFrom();
        Position to = movingFlow.getTo();
        PiecesState piecesState = BoardToPiecesStateTranslator.translate(this);
        Piece piece = PieceService.movePiece(from, to, piecesState);
        Map<Position, Piece> pieces = updatePieces(from, to, piece);
        return updateBoard(pieces, piece);
    }

    @Override
    public Piece getPiece(Position position) {
        return pieces.get(position);
    }

    @Override
    public Map<Position, Piece> getPieces() {
        return pieces;
    }

    @Override
    public boolean isNotFinished() {
        return true;
    }

    @Override
    public Result concludeResult() {
        throw new IllegalStateException("게임이 끝나지 않아, 승패를 결정할 수 없습니다.");
    }

    private static void initializeBlackTeam(Map<Position, Piece> pieces) {
        initializeEdge(pieces, Team.BLACK, BoardConfig.LINE_END);
        initializePawns(BLACK_PAWN_ROW, Team.BLACK, pieces);

    }

    private static void initializeWhiteTeam(Map<Position, Piece> pieces) {
        initializeEdge(pieces, Team.WHITE, BoardConfig.LINE_START);
        initializePawns(WHITE_PAWN_ROW, Team.WHITE, pieces);
    }

    private static void initializeEdge(Map<Position, Piece> pieces, Team team, int edgeRow) {
        for (int x = BoardConfig.LINE_START; x <= BoardConfig.LINE_END; x++) {
            Position position = Position.of(x, edgeRow);
            Piece piece = PieceFactory.createInitializedPieceWithInitialColumn(x, position, team);
            pieces.put(position, piece);
        }
    }

    private static void initializePawns(int row, Team team, Map<Position, Piece> pieces) {
        for (int x = BoardConfig.LINE_START; x <= BoardConfig.LINE_END; x++) {
            Position position = Position.of(x, row);
            Piece initializedPawn = PieceFactory.createInitializedPiece(PieceType.INITIALIZED_PAWN, position, team);
            pieces.put(position, initializedPawn);
        }
    }

    private static void initializeBlanks(Map<Position, Piece> pieces) {
        for (int y = BLANK_START_INDEX; y <= BLANK_END_INDEX; y++) {
            initializeBlanks(pieces, y);
        }
    }

    private static void initializeBlanks(Map<Position, Piece> pieces, int y) {
        for (int x = BoardConfig.LINE_START; x <= BoardConfig.LINE_END; x++) {
            Position position = Position.of(x, y);
            Blank blank = Blank.of();
            pieces.put(position, blank);
        }
    }



    private Board updateBoard(Map<Position, Piece> pieces, Piece piece) {
        if (piece.attackedKing()) {
            return new FinishedBoard(pieces);
        }
        return new RunningBoard(pieces);
    }

    private Map<Position, Piece> updatePieces(Position from, Position to, Piece piece) {
        Map<Position, Piece> pieces = clonePieces(this.pieces);
        pieces.put(from, Blank.of());
        pieces.put(to, piece);
        return pieces;
    }

    private Map<Position, Piece> clonePieces(Map<Position, Piece> board) {
        return new HashMap<>(board);
    }
}
