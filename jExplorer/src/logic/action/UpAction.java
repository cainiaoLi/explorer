/*
 * @author talent_marquis<Ìð²Ëºî¾ô>
 * Email: talent_marquis@163.com
 * Copyright (C) 2007 talent_marquis<Ìð²Ëºî¾ô>
 * All rights reserved.
 */
package logic.action;

import java.io.File;

import org.eclipse.jface.action.*;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TreeViewer;

import display.JExplorer;

import util.JExplorerUtil;

public class UpAction extends Action
{
	private JExplorer window;

	public UpAction( JExplorer w )
	{
		window = w;
		setText( "&Up@Alt+U" );
		setToolTipText( "Return to the previous folder" );
		setImageDescriptor( ImageDescriptor.createFromURL( JExplorerUtil.newURL( "file:icons/up.gif" ) ) );
	}

	public void run()
	{
		TreeViewer tv = window.getTreeViewer();
		TableViewer tbv = window.getTablebViewer();
		IStructuredSelection selection = ( IStructuredSelection ) tv.getSelection();
		Object selected_file = selection.getFirstElement();
		if( selected_file == null )
		{
			return;
		}
		else
		{
			Object parent = ( ( File ) selected_file ).getParentFile();
			if( parent == null )
			{
				return;
			}
			else if( ( ( File ) parent ).getParent() == null )
			{
				tbv.setInput( parent );
				tv.setInput( parent );
			}
			else
			{
				tbv.setInput( parent );
				selection = ( IStructuredSelection ) tbv.getSelection();
				tv.setExpandedState( parent, false );
			}
		}
	}
}
