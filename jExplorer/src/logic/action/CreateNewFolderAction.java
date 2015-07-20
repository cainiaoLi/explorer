/*
 * @author talent_marquis<Ìð²Ëºî¾ô> Email: talent_marquis@163.com Copyright (C) 2007 talent_marquis<Ìð²Ëºî¾ô>
 * All rights reserved.
 */
package logic.action;

import java.io.File;

import org.eclipse.jface.action.*;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.resource.*;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.MessageBox;

import util.*;

import display.JExplorer;

public class CreateNewFolderAction extends Action
{
	private JExplorer window;
	private static final String ACTION_CREATE_NEW_FOLDER_INPUTDIALOG_TITLE = "Create new folder";
	private static final String ACTION_CREATE_NEW_FOLDER_INPUTDIALOG_INFO = "Please input folder's name";
	private static final String ACTION_CREATE_NEW_FOLDER_INFO_FAILED = "Create new folder operation failed!";
	private static final String ACTION_CREATE_NEW_FOLDER_INFO_FOLDER_NAME_EXIST = "The folder has existed!";
	private static final String ACTION_CREATE_NEW_FOLDER_DEFAULT_NAME = "new folder";

	public CreateNewFolderAction( JExplorer w )
	{
		window = w;
		// setEnabled( false );
		setText( "Create new folder@Ctrl+N" );
		setToolTipText( "Create a new folder" );
		setImageDescriptor( ImageDescriptor.createFromURL( JExplorerUtil.newURL( "file:icons/createnewfolder.gif" ) ) );
	}

	public void run()
	{
		IStructuredSelection selection = window.getTableSelection();
		File file_path = null;
		if( selection.isEmpty() )
		{
			selection = ( IStructuredSelection ) window.getTreeViewer().getSelection();
			file_path = ( File ) selection.getFirstElement();
			if( file_path == null )
			{
				file_path = window.getCurrentRoot();
			}
		}
		else
		{
			file_path = ( ( File ) selection.getFirstElement() ).getParentFile();
		}

		// TODO need write a class InputValidator implements IInputValidator
		InputDialog inputDlg = new InputDialog( window.getShell(), ACTION_CREATE_NEW_FOLDER_INPUTDIALOG_TITLE,
				ACTION_CREATE_NEW_FOLDER_INPUTDIALOG_INFO, ACTION_CREATE_NEW_FOLDER_DEFAULT_NAME, null );

		if( inputDlg.open() == InputDialog.OK )
		{
			String folderName = inputDlg.getValue();
			File newFolder = new File( file_path.getAbsolutePath() + File.separator + folderName );

			try
			{
				if( FileUtil.isFileExist( newFolder, file_path ) )
				{
					throw new Exception( ACTION_CREATE_NEW_FOLDER_INFO_FOLDER_NAME_EXIST );
				}
				if( newFolder.mkdir() )
				{
					window.refresh();
					StructuredSelection new_selection = new StructuredSelection( newFolder );
					//window.getTreeViewer().setSelection( new_selection );
					window.getTreeViewer().expandToLevel( new_selection, 1 );
				}
				else
				{
					throw new Exception( ACTION_CREATE_NEW_FOLDER_INFO_FAILED );
				}
			}
			catch( Exception e )
			{
				MessageBox msgBox = new MessageBox( window.getShell() );
				msgBox.setMessage( e.getMessage() );
				msgBox.open();
				//e.printStackTrace();
			}
		}

	}
}
