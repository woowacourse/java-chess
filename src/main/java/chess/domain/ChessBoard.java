package chess.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import chess.domain.chesspiece.Blank;
import chess.domain.chesspiece.ChessPiece;

public class ChessBoard {

	private static final String CANNOT_MOVE_PATH = "이동할 수 없는 경로 입니다.";
	private static final String SAME_TEAM_MESSAGE = "같은 팀입니다.";
	private static final String NOT_CHESS_PIECE_MESSAGE = "체스 말이 아닙니다.";

	private List<Row> board;

	public ChessBoard(List<Row> board) {
		this.board = new ArrayList<>(board);
	}

	public List<Row> getBoard() {
		return board;
	}

	public void move(Position startPosition, Position targetPosition) {
		ChessPiece startPiece = findByPosition(startPosition);
		ChessPiece targetPiece = findByPosition(targetPosition);

		checkTeam(startPiece, targetPiece);
		validateCanMove(startPiece, targetPiece);

		replace(startPosition, new Blank(startPosition));
		replace(targetPosition, startPiece);
		startPiece.changePosition(targetPosition);
	}

	public ChessPiece findByPosition(Position position) {
		return board.stream()
			.filter(row -> row.contains(position))
			.map(row -> row.findByPosition(position))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException(CANNOT_MOVE_PATH));
	}

	private void checkTeam(ChessPiece startPiece, ChessPiece targetPiece) {
		validateNotBlank(startPiece);
		validateOtherTeam(startPiece, targetPiece);
	}

	private void validateNotBlank(ChessPiece startPiece) {
		if (startPiece.isMatchTeam(Team.BLANK)) {
			throw new IllegalArgumentException(NOT_CHESS_PIECE_MESSAGE);
		}
	}

	private void validateOtherTeam(ChessPiece startPiece, ChessPiece targetPiece) {
		if (startPiece.isSameTeam(targetPiece)) {
			throw new IllegalArgumentException(SAME_TEAM_MESSAGE);
		}
	}

	private void validateCanMove(ChessPiece startPiece, ChessPiece targetPiece) {
		if (startPiece.isNeedCheckPath()) {
			validatePath(startPiece.makePath(targetPiece));
		}
		if (startPiece.isNeedCheckPath() == false) {
			startPiece.validateMove(targetPiece);
		}
	}

	private void replace(Position targetPosition, ChessPiece startPiece) {
		Row row = getRow(targetPosition);
		row.replace(targetPosition, startPiece);
	}

	private Row getRow(Position targetPosition) {
		return board.stream()
			.filter(row -> row.contains(targetPosition))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException(CANNOT_MOVE_PATH));
	}

	private void validatePath(List<Position> positions) {
		if (containsNotBlankTeam(positions)) {
			throw new IllegalArgumentException(CANNOT_MOVE_PATH);
		}
	}

	private boolean containsNotBlankTeam(List<Position> positions) {
		return positions.stream()
			.map(this::findByPosition)
			.anyMatch(chessPiece -> chessPiece.isNotMatchTeam(Team.BLANK));
	}
/*
    public double getWinScore() {
        double blackTeamScore = sumScore(Team.BLACK);
        double whiteTeamScore = sumScore(Team.WHITE);
        if (blackTeamScore > whiteTeamScore) {
            return blackTeamScore;
        }
        return whiteTeamScore;
    }

    public double sumScore(Team team) {
        double score = 0;
        int cnt = 0;
        for (int i = 0; i < 8; i++) {
            	int pawnCnt = 0;
            for (int j = 0; j < 8; j++) {
                ChessPiece chessPiece = board.get(j).get(i);

                if (chessPiece.getTeam() == team) {
					if (chessPiece.getClass() == Pawn.class) {
						pawnCnt++;
					}
                //    score += board.get(j).get(i).getScore();
                }
            }
            if (pawnCnt >= 2) {
            	cnt += pawnCnt;
			}
        }
        return score - cnt * 0.5;
    }

 */
}
