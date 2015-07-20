/*
 * @author talent_marquis<Ìð²Ëºî¾ô>
 * Email: talent_marquis@163.com
 * Copyright (C) 2007 talent_marquis<Ìð²Ëºî¾ô>
 * All rights reserved.
 */
package logic.filter;

import java.io.*;
import org.eclipse.jface.viewers.*;

public class AllowOnlyFoldersFilter extends ViewerFilter
{
	public boolean select( Viewer viewer, Object parent, Object element )
	{
		return ( ( File ) element ).isDirectory();
	}
}
