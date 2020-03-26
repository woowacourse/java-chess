package domain.commend;

import domain.pieces.Piece;

import java.util.Set;

public interface State {

	State start();

	State status();

	State end();

	State move(String from, String to);

	State pushCommend(String input);

	Set<Piece> getSet();
}
