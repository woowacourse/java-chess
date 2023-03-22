package chess.domain;

import chess.domain.piece.Bishop;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {

    private static final int BLACK_PAWN_INITIAL_ROW = 6;
    private static final int WHITE_PAWN_INITIAL_ROW = 1;

    private static final int BLACK_EDGE_ROW = 7;
    private static final int WHITE_EDGE_ROW = 0;
    private static final int EMPTY_START_ROW = 2;
    private static final int EMPTY_END_ROW = 5;

    private static final int LINE_SIZE = 8;

    private final Map<Position, Piece> board;

    private Board(Map<Position, Piece> board) {
        this.board = board;
    }

    public static Board create() {
        HashMap<Position, Piece> board = new HashMap<>();
        initializePawns(board, Team.WHITE, WHITE_PAWN_INITIAL_ROW);
        initializePawns(board, Team.BLACK, BLACK_PAWN_INITIAL_ROW);
        initializePiecesOfEdgeLine(board, Team.WHITE, WHITE_EDGE_ROW);
        initializePiecesOfEdgeLine(board, Team.BLACK, BLACK_EDGE_ROW);
        initializeEmptyPieces(board);
        return new Board(board);
    }

    private static void initializeEmptyPieces(HashMap<Position, Piece> board) {
        for (int i = 0; i < LINE_SIZE; i++) {
            for (int j = EMPTY_START_ROW; j <= EMPTY_END_ROW; j++) {
                board.put(new Position(i, j), new EmptyPiece());
            }
        }
    }

    private static void initializePiecesOfEdgeLine(HashMap<Position, Piece> board, Team team, int row) {
        List<Piece> pieces = new ArrayList<>(
                List.of(Rook.from(team), Knight.from(team), Bishop.from(team), Queen.from(team),
                        King.from(team), Bishop.from(team), Knight.from(team), Rook.from(team))
        );
        for (int i = 0; i < LINE_SIZE; i++) {
            board.put(new Position(i, row), pieces.get(i));
        }
    }

    private static void initializePawns(HashMap<Position, Piece> board, Team team, int row) {
        for (int i = 0; i < LINE_SIZE; i++) {
            board.put(new Position(i, row), Pawn.from(team));
        }
    }

    public void movePiece(Position source, Position target) {
        //source가 비어있으면 예외
        //target이 비어있지 않다면, 같은 팀인지 판단

        //경로에 장애물이 있으면 예외

        MoveValidator.validate(board, source, target);

        Piece piece = board.get(source);
        board.put(target, piece);
        board.put(source, new EmptyPiece());
    }

    private List<List<Piece>> sortBoard() {
        List<Position> positions = sortPosition();
        List<List<Piece>> sortedBoard = new ArrayList<>();
        for (int i = 0; i < LINE_SIZE; i++) {
            List<Piece> line = sortLine(positions, i);
            sortedBoard.add(line);
        }
        return sortedBoard;
    }

    private List<Piece> sortLine(List<Position> positions, int i) {
        List<Piece> line = new ArrayList<>();
        for (int j = 0; j < LINE_SIZE; j++) {
            Piece piece = board.get(positions.get(j + LINE_SIZE * i));
            line.add(piece);
        }
        return line;
    }

    private List<Position> sortPosition() {
        List<Position> positions = new ArrayList<>(board.keySet());
        positions.sort((p1, p2) -> {
            if (p1.getRow() == p2.getRow()) {
                return p1.getColumn() - p2.getColumn();
            }
            return p2.getRow() - p1.getRow();
        });
        return positions;
    }

    public List<List<Piece>> getBoard() {
        return sortBoard();
    }

}
