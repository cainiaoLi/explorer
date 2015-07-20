/*
 * @author talent_marquis<Ìð²Ëºî¾ô>
 * Email: talent_marquis@163.com
 * Copyright (C) 2007 talent_marquis<Ìð²Ëºî¾ô>
 * All rights reserved.
 */
package logic.provider;

import java.io.File;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import util.JExplorerUtil;

public class FileTreeLabelProvider extends LabelProvider
{
	public String getText( Object element )
	{
		return ( ( File ) element ).getName();
	}

	public Image getImage( Object element )
	{
		if( ( ( File ) element ).isDirectory() )
		{
			return JExplorerUtil.getImageRegistry().get( "folder" );
		}
		else
		{
			return JExplorerUtil.getImageRegistry().get( "file" );
		}
	}

}
