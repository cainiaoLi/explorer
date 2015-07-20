/*
 * @author talent_marquis<Ìð²Ëºî¾ô> Email: talent_marquis@163.com Copyright (C) 2007 talent_marquis<Ìð²Ëºî¾ô>
 * All rights reserved.
 */
package util;

import java.io.File;
import java.net.*;
import java.util.List;

import org.eclipse.jface.resource.*;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.widgets.Display;

public class JExplorerUtil
{
	private static ImageRegistry image_registry;

	private static Clipboard clipboard;

	private static final String ICON_PATH = "icons/";
	private static final String ICON_FILETYPE_PATH = "icons/filetype/";

	public static URL newURL( String url_name )
	{
		try
		{
			return new URL( url_name );
		}
		catch( MalformedURLException e )
		{
			throw new RuntimeException( "Malformed URL " + url_name, e );
		}
	}

	private static void executeImageRigister( File folder, String fileType )
	{
		
		if( image_registry == null )
		{
			image_registry = new ImageRegistry();
		}
		
		List< File > fileList = FileUtil.getFileList( folder, fileType );

		for( File file : fileList )
		{
			try
			{
				image_registry.put( FileUtil.getFileName( file ), ImageDescriptor.createFromURL( file.toURL() ) );
			}
			catch( MalformedURLException e )
			{
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 
	 * @return imageRegistry which has loaded images
	 */
	public static ImageRegistry getImageRegistry()
	{
		if( image_registry == null )
		{
			image_registry = new ImageRegistry();
			
			File folder = new File( ICON_PATH );
			executeImageRigister( folder, "gif" );
			
			folder = new File( ICON_FILETYPE_PATH );
			executeImageRigister( folder, "png" );
			
		}
		return image_registry;
	}

	public static Clipboard getClipboard()
	{
		if( clipboard == null )
		{
			clipboard = new Clipboard( Display.getCurrent() );
		}
		return clipboard;
	}
}
