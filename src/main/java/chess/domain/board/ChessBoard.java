package chess.domain.board;

import chess.domain.UserInterface;
import chess.domain.piece.Piece;
import chess.domain.piece.blank.Blank;
import chess.domain.piece.factory.PieceFactory;
import chess.domain.piece.pawn.InitializedPawn;
import chess.domain.piece.team.Team;
import chess.domain.position.MovingFlow;
import chess.domain.position.Position;

import java.util.*;
import java.util.stream.Collectors;

public class ChessBoard implements Board {
    private static final int SIZE = 64;
    private static final int LINE_START_INDEX = 1;
    private static final int LINE_END_INDEX = 8;
    private static final int BLACK_PAWN_ROW = 7;
    private static final int WHITE_PAWN_ROW = 2;
    private static final int BLANK_START_INDEX = 3;
    private static final int BLANK_END_INDEX = 6;

    private final Map<Position, Piece> pieces;
    private final UserInterface userInterface;

    private ChessBoard(Map<Position, Piece> pieces, UserInterface userInterface) {
        this.pieces = pieces;
        this.userInterface = userInterface;
    }

    public static ChessBoard initiaize(UserInterface userInterface) {
        Map<Position, Piece> pieces = new HashMap<>();
        initializeBlackTeam(pieces);
        initializeBlanks(pieces);
        initializeWhiteTeam(pieces);
        return new ChessBoard(pieces, userInterface);
    }


    @Override
    public Board movePiece() {
        MovingFlow movingFlow = userInterface.inputMovingFlow();
        return MoveExceptionHandler.handle(this::move, movingFlow, userInterface, this);
    }

    private Board move(Position from, Position to, Board board) {
        Map<Position, Piece> pieces = clonePieces(this.pieces);
        Piece piece = board.getPiece(from);
        piece = piece.move(to, board);
        pieces.put(from, Blank.of());
        pieces.put(to, piece);
        return new ChessBoard(pieces, userInterface);
    }

    @Override
    public Piece getPiece(Position position) {
        return pieces.get(position);
    }

    @Override
    public Map<Position, Piece> getPieces() {
        return pieces;
    }

    private Map<Position, Piece> clonePieces(Map<Position, Piece> board) {
        return board.entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                        Map.Entry::getValue));
    }

    private static void initializeBlackTeam(Map<Position, Piece> pieces) {
        initializeEdge(pieces, Team.BLACK, LINE_END_INDEX);
        initializePawns(BLACK_PAWN_ROW, Team.BLACK, pieces);

    }

    private static void initializeWhiteTeam(Map<Position, Piece> pieces) {
        initializeEdge(pieces, Team.WHITE, LINE_START_INDEX);
        initializePawns(WHITE_PAWN_ROW, Team.WHITE, pieces);
    }

    private static void initializeEdge(Map<Position, Piece> pieces, Team team, int edgeRow) {
        for (int x = LINE_START_INDEX; x <= LINE_END_INDEX; x++) {
            Position position = Position.of(x, edgeRow);
            Piece piece = PieceFactory.createPieceWithInitialColumn(x, position, team);
            pieces.put(position, piece);
        }
    }

    private static void initializePawns(int row, Team team, Map<Position, Piece> pieces) {
        for (int y = row; y <= row; y++) {
            for (int x = 1; x <= 8; x++) {
                Position position = Position.of(x, y);
                Piece initializedPawn = PieceFactory.createPiece(InitializedPawn.class, position, team);
                pieces.put(position, initializedPawn);

            }
        }
    }

    private static void initializeBlanks(Map<Position, Piece> pieces) {
        for (int y = BLANK_START_INDEX; y <= BLANK_END_INDEX; y++) {
            for (int x = LINE_START_INDEX; x <= LINE_END_INDEX; x++) {
                Position position = Position.of(x, y);
                Blank blank = Blank.of();
                pieces.put(position, blank);
            }
        }
    }
}
