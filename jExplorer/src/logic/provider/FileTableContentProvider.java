/*
 * @author talent_marquis<Ìð²Ëºî¾ô>
 * Email: talent_marquis@163.com
 * Copyright (C) 2007 talent_marquis<Ìð²Ëºî¾ô>
 * All rights reserved.
 */
package logic.provider;

import java.io.*;
import org.eclipse.jface.viewers.*;

public class FileTableContentProvider implements IStructuredContentProvider
{
	public Object[] getElements( Object element )
	{
		Object[] kids = null;
		kids = ( ( File ) element ).listFiles();
		return kids == null ? new Object[ 0 ] : kids;
	}

	public void dispose()
	{
	}

	public void inputChanged( Viewer viewer, Object old_object, Object new_object )
	{
	}
}
