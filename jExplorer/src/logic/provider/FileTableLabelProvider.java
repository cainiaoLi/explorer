/*
 * @author talent_marquis<Ìð²Ëºî¾ô> Email: talent_marquis@163.com Copyright (C) 2007 talent_marquis<Ìð²Ëºî¾ô>
 * All rights reserved.
 */
package logic.provider;

import java.io.File;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

import util.FileUtil;
import util.JExplorerUtil;

public class FileTableLabelProvider implements ITableLabelProvider
{

	public String getColumnText( Object element, int column_index )
	{
		if( column_index == 0 )
		{
			return ( ( File ) element ).getName();
		}
		if( column_index == 1 )
		{
			if( ( ( File ) element ).isDirectory() )
			{
				return "";
			}
			else
			{
				return FileUtil.getFileSize( ( File ) element );
			}
		}
		if( column_index == 2 )
		{
			return "" + FileUtil.getFileType( ( File ) element );
		}
		if( column_index == 3 )
		{
			return FileUtil.getFileLastModified( ( File ) element );
		}
		if( column_index == 4 )
		{
			return FileUtil.getFileStatus( ( File ) element );
		}
		return "";
	}

	public void addListener( ILabelProviderListener ilabelproviderlistener )
	{
	}

	public void dispose()
	{
	}

	public boolean isLabelProperty( Object obj, String s )
	{
		return false;
	}

	public void removeListener( ILabelProviderListener ilabelproviderlistener )
	{
	}

	public Image getColumnImage( Object element, int column_index )
	{
		if( column_index != 0 )
		{
			return null;
		}
		if( ( ( File ) element ).isDirectory() )
		{
			return JExplorerUtil.getImageRegistry().get( "folder" );
		}
		else
		{
			File file = ( File ) element;
			String fileType = FileUtil.getFileType( file );
			Image fileImage = JExplorerUtil.getImageRegistry().get( fileType );
			if( fileImage == null )
			{
				return JExplorerUtil.getImageRegistry().get( "file" );
			}
			else
			{
				return fileImage;
			}

			// if( "exe".equals( FileUtil.getFileType( ( File ) element ) ) )
			// {
			// return JExplorerUtil.getImageRegistry().get( "exe" );
			//
			// }
			// else if( "jpg".equals( FileUtil.getFileType( ( File ) element ) ) )
			// {
			// return JExplorerUtil.getImageRegistry().get( "jpg" );
			//
			// }
			// else if( "txt".equals( FileUtil.getFileType( ( File ) element ) ) )
			// {
			// return JExplorerUtil.getImageRegistry().get( "txt" );
			//
			// }
			// else
			// {
			// return JExplorerUtil.getImageRegistry().get( "file" );
			// }

		}
	}
}
