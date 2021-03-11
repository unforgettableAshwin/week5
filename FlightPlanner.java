package week5;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import acm.program.ConsoleProgram;

public class FlightPlanner extends ConsoleProgram
{
	public void run()
	{
		String line;
		String cityStarting;
		String connectingOrigin;
		String destinationSelected;

		ArrayList< String > route = new ArrayList< String >();

		try
		{
			BufferedReader rd = new BufferedReader( new FileReader( FILE_NAME ) );
			while( true )
			{
				line = rd.readLine();
				if( line == null )
					break;
				else
					parse( line );
			}

			rd.close();
		}
		catch( FileNotFoundException e )
		{
			e.printStackTrace();
		}
		catch( IOException e )
		{
			e.printStackTrace();
		}

		println( "Welcome to Flight Planner!" );
		do
		{
			println( "Here's a list of all the cities in our database:" );
			for( String s: flights.keySet() )
				println( "\t" + s );

			println( "Let's plan a round-trip route!" );
			cityStarting = readLine( "Enter the starting city: " ).trim();
		}
		while( !flights.containsKey( cityStarting ) );

		route.add( cityStarting );
		connectingOrigin = new String( cityStarting );
		boolean listAgain;
		do
		{
			listAgain = true;
			println( "From " + connectingOrigin + " you can fly directly to:" );
			listDestinations( connectingOrigin );
			destinationSelected = readLine( "Where do you want to go from " + connectingOrigin + "? " ).trim();
			// Is the destination listed?
			if( !destinationSelected.equals( "" ) )
			{
				ArrayList< String > al = flights.get( connectingOrigin );
				listAgain = !al.contains( destinationSelected );
			}

			// If we don't have to repeat the list...:
			if( !listAgain ) // ...check if origin city selected:
				if( destinationSelected.equals( cityStarting ) )
				{
					route.add( cityStarting );
					break;
				}
				else // ...or update our origin:
				{
					route.add( destinationSelected );
					connectingOrigin = destinationSelected;
				}
		}
		while( true );

		println( "The route you've chosen is:" );
		StringBuilder sb = new StringBuilder( cityStarting );
		boolean first = true;
		for( String s: route )
			if( first )
				first = false;
			else
				sb.append( " -> " + s );

		println( sb.toString() );
	}

	private void listDestinations(
			String origin
	)
	{
		ArrayList< String > al = flights.get( origin );
		for( String s: al )
			println( "\t" + s );
	}

	private void parse(
			String line
	)
	{
		String[] s = line.split( "->" );
		if( s.length > 1 )
		{
			String origin = s[ 0 ].trim();
			String destination = s[ 1 ].trim();
			ArrayList< String > al;
			if( flights.containsKey( origin ) )
				al = flights.get( origin );
			else
				al = new ArrayList< String >();

			al.add( destination );
			flights.put( origin, al );
		}
	}

	private static final long serialVersionUID = 1L;

	private static final String FILE_NAME = "flights.txt";

	private HashMap< String, ArrayList< String > > flights = new HashMap< String, ArrayList< String > >();
}