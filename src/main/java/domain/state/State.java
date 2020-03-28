package domain.state;

import domain.pieces.Piece;
import domain.pieces.Pieces;

import java.util.Set;

public interface State {

	State start();

	State status();

	State end();

	State move(String from, String to);

	State pushCommend(String input);

	Set<Piece> getSet();

	Pieces getPieces();

	boolean isStatus();

	boolean isPlaying();
}
