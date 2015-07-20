/*
 * @author talent_marquis<Ìð²Ëºî¾ô> Email: talent_marquis@163.com Copyright (C) 2007 talent_marquis<Ìð²Ëºî¾ô>
 * All rights reserved.
 */
package logic.action;

import java.io.*;
import java.util.*;
import org.eclipse.jface.action.*;
import org.eclipse.jface.resource.*;
import org.eclipse.jface.viewers.*;
import org.eclipse.swt.*;
import org.eclipse.swt.widgets.MessageBox;

import util.*;

import display.JExplorer;

public class DeleteAction extends Action implements ISelectionChangedListener
{
	private static final String ACTION_DELETE_ERROR_FOLDER_NOT_EMPTY = "The folder is not empty, can not delete it!";
	private static final String ACTION_DELETE_ERROR_FAILED = "Delete file failed!";
	private JExplorer window;

	public DeleteAction( JExplorer w )
	{
		window = w;
		setToolTipText( "Delete Files" );
		setText( "&Delete@Delete" );
		setImageDescriptor( ImageDescriptor.createFromURL( JExplorerUtil.newURL( "file:icons/delete.gif" ) ) );
	}

	public void run()
	{
		IStructuredSelection selection = window.getTableSelection();
		if( selection.isEmpty() )
		{
			return;
		}

		File selected_file = ( File ) selection.getFirstElement();
		MessageBox messageBox = new MessageBox( window.getShell(), SWT.ICON_QUESTION | SWT.YES | SWT.NO );
		messageBox.setText( "Delete" );

		if( selection.size() == 1 )
		{
			// if select one file
			messageBox.setMessage( "Do you really want to delete \"" + selected_file.getName() + "\" ?" );
		}
		else
		{
			// if select multifile
			messageBox.setMessage( "Do you really want to delete these " + selection.size() + " files ?" );
		}

		int response = messageBox.open();

		if( response == SWT.YES )
		{
			for( Iterator i = selection.iterator(); i.hasNext(); )
			{
				try
				{
					File file = ( File ) i.next();
					if( file.isDirectory() && file.listFiles().length != 0 ) 
					{
						//the folder is not empty
						throw new Exception( ACTION_DELETE_ERROR_FOLDER_NOT_EMPTY );
					}
					
					if( !file.delete() )
					{
						throw new Exception( ACTION_DELETE_ERROR_FAILED);
					}
				}
				catch( Exception e )
				{
					MessageBox msgBox = new MessageBox(window.getShell());
					msgBox.setMessage( e.getMessage() );
					msgBox.open();
				}
			}
			window.getTablebViewer().refresh();
			window.getTreeViewer().refresh();
		}
	}

	public void selectionChanged( SelectionChangedEvent event )
	{
		IStructuredSelection selection = window.getTableSelection();
		if( selection.size() == 0 )
		{
			setEnabled( false );
			return;
		}
		else
		{
			setEnabled( true );
			return;
		}

	}
}
