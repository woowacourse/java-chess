package chess.domain.piece;

import chess.config.BoardConfig;
import chess.domain.piece.position.Position;
import chess.domain.piece.team.Team;

import java.util.HashMap;
import java.util.Map;

//public class TestSquaresState extends PiecesState {
//    private static final int BLACK_PAWN_ROW = 7;
//    private static final int WHITE_PAWN_ROW = 2;
//    private static final int BLANK_START_INDEX = 3;
//    private static final int BLANK_END_INDEX = 6;
//
//    TestSquaresState(Map<Position, Piece> pieces) {
//        super(pieces);
//    }
//
//    public static TestSquaresState initialize() {
//        Map<Position, Piece> pieces = new HashMap<>();
//        initializeBlackTeam(pieces);
//        initializeBlanks(pieces);
//        initializeWhiteTeam(pieces);
//        return new TestSquaresState(pieces);
//    }
//
//    private static void initializeBlackTeam(Map<Position, Piece> pieces) {
//        initializeEdge(pieces, Team.BLACK, BoardConfig.LINE_END);
//        initializePawns(BLACK_PAWN_ROW, Team.BLACK, pieces);
//
//    }
//
//    private static void initializeWhiteTeam(Map<Position, Piece> pieces) {
//        initializeEdge(pieces, Team.WHITE, BoardConfig.LINE_START);
//        initializePawns(WHITE_PAWN_ROW, Team.WHITE, pieces);
//    }
//
//    private static void initializeEdge(Map<Position, Piece> pieces, Team team, int edgeRow) {
//        for (int x = BoardConfig.LINE_START; x <= BoardConfig.LINE_END; x++) {
//            Position position = Position.of(x, edgeRow);
//            //todo: refac
//            Piece piece = null;
//            pieces.put(position, piece);
//        }
//    }
//
//    private static void initializePawns(int row, Team team, Map<Position, Piece> pieces) {
//        for (int x = BoardConfig.LINE_START; x <= BoardConfig.LINE_END; x++) {
//            Position position = Position.of(x, row);
//            //todo: refac
//            Piece initializedPawn = null;
//            pieces.put(position, initializedPawn);
//        }
//    }
//
//    private static void initializeBlanks(Map<Position, Piece> pieces) {
//        for (int y = BLANK_START_INDEX; y <= BLANK_END_INDEX; y++) {
//            initializeBlanks(pieces, y);
//        }
//    }
//
//    private static void initializeBlanks(Map<Position, Piece> pieces, int y) {
//        for (int x = BoardConfig.LINE_START; x <= BoardConfig.LINE_END; x++) {
//            Position position = Position.of(x, y);
//            Blank blank = Blank.of();
//            pieces.put(position, blank);
//        }
//    }


//}
