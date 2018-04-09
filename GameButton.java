// David Eppstein, UC Irvine, 11 Jun 1997
//
// Buttons to change the game state

import java.awt.*;
import java.util.*;
import gui.*;

public abstract class GameButton extends ThreeStateButton implements Observer {
	Game game;
	public GameButton(Game g, String s) {
		super(s);
		game = g;
		g.addObserver(this);
		enabled = active();
	}
	
	public void update(Observable g, Object o) {
		if (enabled != active()) {
			if (active()) enable();
			else disable();
		}
	}
	
	public boolean mouseEnter(Event e, int x, int y) {
		if (!enabled) return false;
		Fanorona.showMessage(this, status(), false);
		return true;
	}

	public abstract boolean active();
	public abstract String status();

	static void buttonsPanel(Panel buttons, Game game, Color bgc, Color fgc, Color disc) {
		buttons.setLayout(new StackLayout(StackLayout.HORIZONTAL));
		new UndoButton(game).colorize(bgc, fgc, disc, buttons);
		new ResetButton(game).colorize(bgc, fgc, disc, buttons);
		new PassButton(game).colorize(bgc, fgc, disc, buttons);
	}
}


