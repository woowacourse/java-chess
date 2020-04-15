package chess.model.dto;

import chess.model.domain.board.BoardSquare;
import chess.model.domain.board.ChessGame;
import chess.model.domain.board.TeamScore;
import chess.model.domain.piece.Bishop;
import chess.model.domain.piece.Color;
import chess.model.domain.piece.King;
import chess.model.domain.piece.Knight;
import chess.model.domain.piece.Pawn;
import chess.model.domain.piece.Piece;
import chess.model.domain.piece.Queen;
import chess.model.domain.piece.Rook;
import chess.model.domain.state.MoveState;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import util.NullChecker;

public class ChessGameDto {

    private static final Map<Piece, String> PIECES_LETTER;
    private static final Map<Color, String> DEFAULT_NAMES;

    private final List<String> pieces;
    private final String turn;
    private final String state;
    private final String blackScore;
    private final String whiteScore;
    private final String blackName;
    private final String whiteName;
    private final String winner;

    static {
        Map<Piece, String> piecesLetter = new HashMap<>();
        piecesLetter.put(Pawn.getPieceInstance(Color.BLACK), "♟");
        piecesLetter.put(Pawn.getPieceInstance(Color.WHITE), "♙");
        piecesLetter.put(Rook.getPieceInstance(Color.BLACK), "♜");
        piecesLetter.put(Rook.getPieceInstance(Color.WHITE), "♖");
        piecesLetter.put(Knight.getPieceInstance(Color.BLACK), "♞");
        piecesLetter.put(Knight.getPieceInstance(Color.WHITE), "♘");
        piecesLetter.put(Bishop.getPieceInstance(Color.BLACK), "♝");
        piecesLetter.put(Bishop.getPieceInstance(Color.WHITE), "♗");
        piecesLetter.put(Queen.getPieceInstance(Color.BLACK), "♛");
        piecesLetter.put(Queen.getPieceInstance(Color.WHITE), "♕");
        piecesLetter.put(King.getPieceInstance(Color.BLACK), "♚");
        piecesLetter.put(King.getPieceInstance(Color.WHITE), "♔");
        PIECES_LETTER = Collections.unmodifiableMap(piecesLetter);

        Map<Color, String> defaultNames = new HashMap<>();
        defaultNames.put(Color.BLACK, "BLACK");
        defaultNames.put(Color.WHITE, "WHITE");
        DEFAULT_NAMES = Collections.unmodifiableMap(defaultNames);
    }

    public ChessGameDto(ChessGame chessGame) {
        this(chessGame, MoveState.EMPTY, chessGame.getTeamScore(), DEFAULT_NAMES);
    }

    public ChessGameDto(ChessGame chessGame, Map<Color, String> names) {
        this(chessGame, MoveState.EMPTY, chessGame.getTeamScore(), names);
    }

    public ChessGameDto(ChessGame chessGame, MoveState moveState, TeamScore teamScore,
        Map<Color, String> names) {
        NullChecker.validateNotNull(chessGame, moveState);
        Map<BoardSquare, Piece> board = chessGame.getChessBoard();
        List<String> pieces = new ArrayList<>();
        for (int rank = BoardSquare.MAX_FILE_AND_RANK_COUNT;
            rank >= BoardSquare.MIN_FILE_AND_RANK_COUNT; rank--) {
            printRankRaw(pieces, board, rank);
        }
        this.pieces = pieces;
        this.turn = chessGame.getGameTurn().getName();
        this.state = moveState.getMessage();
        this.blackScore = String.valueOf(teamScore.get(Color.BLACK));
        this.whiteScore = String.valueOf(teamScore.get(Color.WHITE));
        this.blackName = names.get(Color.BLACK);
        this.whiteName = names.get(Color.WHITE);
        this.winner = teamScore.getWinners().stream()
            .map(names::get)
            .collect(Collectors.joining(", "));
    }

    public ChessGameDto(TeamScore teamScore, Map<Color, String> names) {
        this.pieces = IntStream.rangeClosed(BoardSquare.MIN_FILE_AND_RANK_COUNT,
            BoardSquare.MAX_FILE_AND_RANK_COUNT * BoardSquare.MAX_FILE_AND_RANK_COUNT)
            .mapToObj(number -> "")
            .collect(Collectors.toList());
        this.turn = null;
        this.state = null;
        this.blackScore = String.valueOf(teamScore.get(Color.BLACK));
        this.whiteScore = String.valueOf(teamScore.get(Color.WHITE));
        this.blackName = names.get(Color.BLACK);
        this.whiteName = names.get(Color.WHITE);
        this.winner = teamScore.getWinners().stream()
            .map(names::get)
            .collect(Collectors.joining(", "));
    }

    private static void printRankRaw(List<String> pieces, Map<BoardSquare, Piece> board, int rank) {
        for (char file = 'a'; file <= 'h'; file++) {
            pieces.add(getLetterByFileColumn(board, rank, file));
        }
    }

    private static String getLetterByFileColumn(Map<BoardSquare, Piece> gameBoard, int rank,
        char file) {
        if (gameBoard.containsKey(BoardSquare.of(String.valueOf(file) + rank))) {
            return PIECES_LETTER.get(gameBoard.get(BoardSquare.of(String.valueOf(file) + rank)));
        }
        return "";
    }

    public String getTurn() {
        return turn;
    }

    public String getState() {
        return state;
    }

    public List<String> getPieces() {
        return pieces;
    }

    public String getBlackScore() {
        return blackScore;
    }

    public String getWhiteScore() {
        return whiteScore;
    }
}
