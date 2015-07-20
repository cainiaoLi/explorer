/*
 * @author talent_marquis<Ìð²Ëºî¾ô>
 * Email: talent_marquis@163.com
 * Copyright (C) 2007 talent_marquis<Ìð²Ëºî¾ô>
 * All rights reserved.
 */
package logic.action;

import org.eclipse.jface.action.*;
import org.eclipse.jface.resource.*;

import util.JExplorerUtil;

import display.JExplorer;

public class RefreshAction extends Action
{
	JExplorer window;

	public RefreshAction( JExplorer w )
	{
		window = w;
		setToolTipText( "Refresh jExplorer" );
		setText( "&Refresh@F5" );
		setImageDescriptor( ImageDescriptor.createFromURL( JExplorerUtil.newURL( "file:icons/refresh.gif" ) ) );
	}

	public void run()
	{
		window.refresh();
	}
}
