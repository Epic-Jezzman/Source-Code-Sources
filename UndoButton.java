// David Eppstein, UC Irvine, 11 Jun 1997
//
// Buttons to change the game state

import java.awt.*;
import java.util.*;
import gui.*;

class UndoButton extends GameButton {
	public UndoButton(Game g) { super(g, "Undo"); }
	public static Board retro(Game g) {
		Board b = g.getBoard();
		if (b == null) return null;
		for (;;) {
			b = b.previousPosition;
			if (b == null) return null;
			if (g.humanToMove(b) && !g.mustPass(b)) return b;
		}
	}

	public boolean active() { return retro(game) != null; }
	public void action() { game.setBoard(retro(game)); }
	public String status() { return "Return to the position before your most recent move"; }
}

