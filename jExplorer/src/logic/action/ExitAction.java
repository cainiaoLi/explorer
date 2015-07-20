/*
 * @author talent_marquis<Ìð²Ëºî¾ô> Email: talent_marquis@163.com Copyright (C) 2007 talent_marquis<Ìð²Ëºî¾ô>
 * All rights reserved.
 */
package logic.action;

import org.eclipse.jface.action.*;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.window.*;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;

import util.JExplorerUtil;

public class ExitAction extends Action
{
	ApplicationWindow window;

	public ExitAction( ApplicationWindow w )
	{
		window = w;
		setText( "E&xit@Alt+X" );
		setToolTipText( "Exit jExplorer" );
		setImageDescriptor( ImageDescriptor.createFromURL( JExplorerUtil.newURL( "file:icons/close.gif" ) ) );
	}

	public void run()
	{
		MessageBox messageBox = new MessageBox( window.getShell(),SWT.YES  | SWT.NO );
		messageBox.setMessage( "Exit?" );
		if( SWT.YES == messageBox.open() )
		{
			window.close();
		}
	}
}
