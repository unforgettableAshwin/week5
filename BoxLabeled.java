package week5;

import acm.graphics.GCompound;
import acm.graphics.GLabel;
import acm.graphics.GRect;

public class BoxLabeled extends GCompound
{
	public BoxLabeled(
			String name
	)
	{
		box = new GRect( BOX_WIDTH, BOX_HEIGHT );
		label = new GLabel( name );
		add( box );
		add( label );
		label.move( ( box.getWidth() - label.getWidth() ) / 2, ( box.getHeight() - label.getHeight() ) / 2 + LABEL_VERTICAL_OFFSET );
	}

	private static final double BOX_WIDTH = 120;
	private static final double BOX_HEIGHT = 50;

	// Required to center the label vertically:
	private static final double LABEL_VERTICAL_OFFSET = 12;

	private GRect box;
	private GLabel label;
}
