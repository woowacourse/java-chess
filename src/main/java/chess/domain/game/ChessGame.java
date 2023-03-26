package chess.domain.game;

import chess.dao.BoardDao;
import chess.domain.board.Board;
import chess.domain.piece.King;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import chess.domain.team.Team;
import chess.view.TeamName;

public class ChessGame {

    private static final int INITIAL_SCORE = 0;
    private static final int SAME_TEAM_PAWN = 1;
    private static final int NOT_PAWN = 0;
    private static final int INITIAL_COUNT = 0;
    private static final double PENALTY_SCORE_DUPLICATE_PAWN_IN_FILE = 0.5;
    private static final int EXIST_OPPONENT_KING = 1;
    private static final int NOT_EXIST_OPPONENT_KING = 0;

    private final int id;
    private final Board board;
    private Team turn;

    private ChessGame(final int id, final int boardId, final Team turn) {
        this.id = id;
        this.board = BoardDao.findById(boardId);
        this.turn = turn;
    }

    public static ChessGame of(final int id, final int boardId) {
        return new ChessGame(id, boardId, Team.WHITE);
    }

    public static ChessGame of(final int id, final int boardId, final String team) {
        final Team turn = TeamName.findByName(team);
        return new ChessGame(id, boardId, turn);
    }

    public void move(final Position source, final Position target) {
        board.move(source, target, turn);
    }

    public void changeTurn() {
        turn = turn.reverse();
    }

    public Score calculateScore(final Team team) {
        Score score = Score.from(INITIAL_SCORE);

        for (final File file : File.values()) {
            score = score.add(calculateScoreEachFile(file, team));
        }

        return score;
    }

    private Score calculateScoreEachFile(final File file, final Team team) {
        Score score = Score.from(INITIAL_SCORE);

        for (final Rank rank : Rank.values()) {
            score = score.add(calculateScoreEachPosition(Position.of(file, rank), team));
        }
        score = score.minus(calculatePawnScoreByCountEachFile(file, team));

        return score;
    }

    private Score calculateScoreEachPosition(final Position position, final Team team) {
        final Score score = Score.from(INITIAL_SCORE);
        final Piece piece = board.getPiece(position);

        return score.add(PieceScore.findByPiece(piece, team));
    }

    private Score calculatePawnScoreByCountEachFile(final File file, final Team team) {
        int pawnCount = INITIAL_COUNT;

        for (final Rank rank : Rank.values()) {
            pawnCount += hasPawn(Position.of(file, rank), team);
        }

        if (pawnCount <= 1) {
            return Score.from(INITIAL_SCORE);
        }
        return Score.from(PENALTY_SCORE_DUPLICATE_PAWN_IN_FILE * pawnCount);
    }

    private int hasPawn(final Position position, final Team team) {
        if (board.getPiece(position).equals(new Pawn(team))) {
            return SAME_TEAM_PAWN;
        }
        return NOT_PAWN;
    }

    public boolean isExistOpponentKing() {
        int countOpponentKing = INITIAL_COUNT;

        for (final File file : File.values()) {
            countOpponentKing += getCountOpponentKingEachFile(file);
        }

        return countOpponentKing == EXIST_OPPONENT_KING;
    }

    private int getCountOpponentKingEachFile(final File file) {
        int countOpponentKing = INITIAL_COUNT;

        for (final Rank rank : Rank.values()) {
            countOpponentKing += getCountOpponentKingEachPosition(file, rank);
        }

        return countOpponentKing;
    }

    private int getCountOpponentKingEachPosition(final File file, final Rank rank) {
        final Piece piece = board.getPiece(Position.of(file, rank));

        if (piece.equals(new King(turn.reverse()))) {
            return EXIST_OPPONENT_KING;
        }
        return NOT_EXIST_OPPONENT_KING;
    }

    public int getId() {
        return id;
    }

    public Team getTurn() {
        return turn;
    }

    public Board getBoard() {
        return board;
    }
}
