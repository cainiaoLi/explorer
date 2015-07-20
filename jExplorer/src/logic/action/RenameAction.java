/*
 * @author talent_marquis<Ìð²Ëºî¾ô>
 * Email: talent_marquis@163.com
 * Copyright (C) 2007 talent_marquis<Ìð²Ëºî¾ô>
 * All rights reserved.
 */
package logic.action;

import java.io.File;

import org.eclipse.jface.action.*;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.resource.*;
import org.eclipse.jface.viewers.IStructuredSelection;

import util.JExplorerUtil;

import display.JExplorer;

public class RenameAction extends Action
{
	private JExplorer window;
	private static final String ACTION_RENAME_INPUTDIALOG_TITLE = "Rename";
	private static final String ACTION_RENAME_INPUTDIALOG_INFO = "Please input new name";
	private static final String ACTION_RENAME_INFO_FAILED = "Rename operation failed!";

	public RenameAction( JExplorer w )
	{
		window = w;
		// setEnabled( false );
		setToolTipText( "Rename file" );
		setText( "Rename@F2" );
		setImageDescriptor( ImageDescriptor.createFromURL( JExplorerUtil.newURL( "file:icons/rename.gif" ) ) );
	}

	public void run()
	{
		IStructuredSelection selection = window.getTableSelection();
		if( selection.isEmpty() )
		{
			return;
		}

		if( selection.size() > 1 )
		{
			// TODO implement batch rename
			return;
		}

		
		File selected_file = ( File ) selection.getFirstElement();
		String oldFileName = selected_file.getName();
		// TODO need write a class InputValidator implements IInputValidator
		InputDialog inputDlg = new InputDialog( window.getShell(), ACTION_RENAME_INPUTDIALOG_TITLE,
				ACTION_RENAME_INPUTDIALOG_INFO, oldFileName, null );

		if( inputDlg.open() == InputDialog.OK )
		{
			String newName = inputDlg.getValue();
			File newNameFile = new File( selected_file.getParent() + File.separator + newName );
			// TODO need check name duplicated and rename failed
			if( selected_file.renameTo( newNameFile ) )
			{
				window.refresh();
			}
			else
			{
				window.setStatus( ACTION_RENAME_INFO_FAILED );
			}
		}

	}
}
