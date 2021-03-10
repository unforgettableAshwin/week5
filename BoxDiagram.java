package week5;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.HashMap;

import acm.graphics.GObject;
import acm.graphics.GPoint;
import acm.program.GraphicsProgram;
import acmx.export.javax.swing.JButton;
import acmx.export.javax.swing.JLabel;
import acmx.export.javax.swing.JTextField;

public class BoxDiagram extends GraphicsProgram
{
	public void init()
	{
		labelName = new JLabel( "Name:" );
		textName = new JTextField( 10 );
		buttonAdd = new JButton( "Add" );
		buttonRemove = new JButton( "Remove" );
		buttonClear = new JButton( "Clear" );
		add( labelName, SOUTH );
		add( textName, SOUTH );
		add( buttonAdd, SOUTH );
		add( buttonRemove, SOUTH );
		add( buttonClear, SOUTH );
		buttonAdd.addActionListener( this );
		buttonRemove.addActionListener( this );
		buttonClear.addActionListener( this );
		boxes = new HashMap< String, GObject >();
		addMouseListeners();
	}

	public void actionPerformed(
			ActionEvent e
	)
	{
		String s = e.getActionCommand();
		switch( s )
		{
		case "Add":
			addBox();
			break;
		case "Remove":
			s = textName.getText();
			if( boxes.containsKey( s ) )
			{
				remove( boxes.get( s ) );
				boxes.remove( s );
			}

			break;
		case "Clear":
			while( boxes.keySet().size() > 0 )
				for( String k: boxes.keySet() )
				{
					remove( boxes.get( k ) );
					boxes.remove( k );
					break;
				}

			break;
		}
	}

	public void addBox()
	{
		String s = textName.getText();
		if( !s.equals( "" ) && !boxes.containsKey( s ) )
		{
			BoxLabeled bl = new BoxLabeled( s );
			boxes.put( s, bl );
			add( bl, ( getWidth() - bl.getWidth() ) / 2, ( getHeight() - bl.getHeight() ) / 2 );
		}
	}

	// Called on mouse press to record the coordinates of the click */
	public void mousePressed(
			MouseEvent e
	)
	{
		// GPoint has X and Y coordinate
		last = new GPoint( e.getPoint() );
		gObj = getElementAt( last );
	}

	// Called on mouse drag to reposition the object
	public void mouseDragged(
			MouseEvent e
	)
	{
		if( gObj != null )
		{
			gObj.move( e.getX() - last.getX(), e.getY() - last.getY() );
			last = new GPoint( e.getPoint() );
		}
	}

	private static final long serialVersionUID = 1L;

	private HashMap< String, GObject > boxes;

	private GPoint last;

	private GObject gObj;

	private JLabel labelName;

	private JTextField textName;

	private JButton buttonAdd;
	private JButton buttonRemove;
	private JButton buttonClear;
}