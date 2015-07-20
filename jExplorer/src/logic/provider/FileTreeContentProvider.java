/*
 * @author talent_marquis<Ìð²Ëºî¾ô>
 * Email: talent_marquis@163.com
 * Copyright (C) 2007 talent_marquis<Ìð²Ëºî¾ô>
 * All rights reserved.
 */
package logic.provider;

import java.io.*;
import org.eclipse.jface.viewers.*;

public class FileTreeContentProvider implements ITreeContentProvider
{
	public Object[] getChildren( Object element )
	{
		Object[] kids = ( ( File ) element ).listFiles();
		return kids == null ? new Object[ 0 ] : kids;
	}

	public Object[] getElements( Object element )
	{
		return getChildren( element );
	}

	public boolean hasChildren( Object element )
	{
		return getChildren( element ).length > 0;
	}

	public Object getParent( Object element )
	{
		//Object parent = new File( element.toString() ).getParent();
		/*
		Object parent = null;
		File file = null;
		try
		{
			file = (File)element;
			parent = file.getParent();
		}
		catch(ClassCastException cce )
		{
			cce.printStackTrace();
		}
		*/
		String parent = null;
		try
		{
			parent = new File( element.toString() ).getParent();
			
		}
		catch(ClassCastException cce)
		{
			cce.printStackTrace();
		}
		return parent;
		//return parent == null ? element : parent;
	}

	public void dispose()
	{
	}

	public void inputChanged( Viewer viewer, Object old_input, Object new_input )
	{
	}
}
