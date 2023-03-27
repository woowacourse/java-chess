package chess.domain;

import chess.db.dao.BoardDao;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.square.File;
import chess.domain.square.Rank;
import chess.domain.square.Square;
import chess.domain.square.Squares;

import java.util.Arrays;
import java.util.List;

import static chess.domain.Team.*;

public class ChessGame {

    private static final int SQUARE_INPUT_LENGTH = 2;

    private final Board board;
    private Team team;

    public ChessGame(final Board board, final Team team) {
        this.board = board;
        this.team = team;
    }

    public void movePiece(final String source, final String destination) {
        Square src = convertSquare(source);
        Square dst = convertSquare(destination);
        validateSameTeam(src);
        Piece origin = board.move(src, dst);
        BoardDao boardDao = new BoardDao();
        boardDao.update(src, dst);
        if (origin.getPieceType() != PieceType.EMPTY) {
            boardDao.deleteBySquare(src);
        }
        changeTeam();
    }

    private Square convertSquare(final String input) {
        List<String> squareInput = Arrays.asList(input.split(""));
        validateSquareInput(squareInput);
        return Squares.getSquare(File.findFileBy(squareInput.get(0)), Rank.findRankBy(squareInput.get(1)));
    }

    private void validateSquareInput(final List<String> squareInput) {
        if (squareInput.size() != SQUARE_INPUT_LENGTH) {
            throw new IllegalArgumentException("source위치, target 위치는 알파벳(a~h)과 숫자(1~8)로 입력해주세요. 예) a1");
        }
    }

    private void validateSameTeam(final Square src) {
        if (!board.isSameTeam(src, team)) {
            throw new IllegalArgumentException("다른 팀 말을 움직여 주세요.");
        }
    }

    private void changeTeam() {
        team = Team.change(team);
    }

    public double calculateTeamScore(final Team team) {
        return board.calculateTeamScore(team);
    }

    public Team calculateWinnerTeam() {
        double whiteScore = calculateTeamScore(WHITE);
        double blackScore = calculateTeamScore(BLACK);
        if (whiteScore < blackScore || board.isKingDead(WHITE)) {
            return BLACK;
        }
        if (blackScore < whiteScore || board.isKingDead(BLACK)) {
            return WHITE;
        }
        return EMPTY;
    }

    public boolean isGameEnd() {
        return board.isKingDead(team);
    }

    public Board getBoard() {
        return board;
    }
}
