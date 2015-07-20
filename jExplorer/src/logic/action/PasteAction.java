/*
 * @author talent_marquis<甜菜侯爵> Email: talent_marquis@163.com Copyright (C) 2007 talent_marquis<甜菜侯爵>
 * All rights reserved.
 */
package logic.action;

import java.io.*;
import org.eclipse.jface.action.*;
import org.eclipse.jface.resource.*;
import org.eclipse.jface.viewers.*;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.*;
import org.eclipse.swt.widgets.MessageBox;

import common.JExplorerConstant;

import util.FileUtil;
import util.JExplorerUtil;

import display.JExplorer;

public class PasteAction extends Action
{
	private JExplorer window;

	private static final int FILE_IO_BUFFER_SIZE = JExplorerConstant.FILE_IO_BUFFER_SIZE;

	public PasteAction( JExplorer w )
	{
		window = w;
		// setEnabled( false );
		setToolTipText( "Paste" );
		setText( "Paste@Ctrl+V" );
		setImageDescriptor( ImageDescriptor.createFromURL( JExplorerUtil.newURL( "file:icons/paste.gif" ) ) );
	}

	public void run()
	{
		Clipboard clipboard = JExplorerUtil.getClipboard();
		TextTransfer text_transfer = TextTransfer.getInstance();

		String filesStr = ( String ) clipboard.getContents( text_transfer );

		if( "".equals( filesStr ) || filesStr == null )
		{
			return;
		}

		String[] sourceFiles = filesStr.split( JExplorerConstant.FILE_COPY_SEPARATOR );

		IStructuredSelection selection = window.getTableSelection();
		
		// if the selection in tableViewer is empty, choose selection from treeViewer
		if( selection.isEmpty() )
		{
			selection = ( IStructuredSelection ) window.getTreeViewer().getSelection();
		}

		File selected_file = ( File ) selection.getFirstElement();
		File target_folder = null;
		// get the target folder which file will be pasted in
		if( selected_file.isFile() )
		{
			target_folder = selected_file.getParentFile();
		}
		else
		{
			target_folder = selected_file;
		}

		// check whether the same name file exist in target folder
		for( String str : sourceFiles )
		{
			File source_file = new File( str );

			//if( !source_file.exists() )
			//{
			//	return;
			//}
			//TODO 暂不对目录进行任何操作
			if( source_file.isDirectory() )
			{
				continue;
			}

			if( FileUtil.isFileExist( source_file, target_folder ) )
			{
				MessageBox msgBox = new MessageBox( window.getShell(), SWT.YES | SWT.NO );
				msgBox.setMessage( "\"" + source_file.getName() + "\" has existed, cover it?" );
				if( SWT.NO == msgBox.open() )
				{
					//skip this file
					continue;
				}
			}

			try
			{
				window.setStatus( "copy " + source_file.getPath() + " to " + target_folder.getPath() + "..." );
				FileUtil.doPaste( source_file, target_folder, FILE_IO_BUFFER_SIZE );
				//if cut then delete source target
				if( window.isCut() )
				{
					source_file.delete();
				}
			}
			catch( FileNotFoundException e )
			{
				MessageBox msgBox = new MessageBox( window.getShell(), SWT.YES );
				msgBox.setMessage( e.getMessage() );
				msgBox.open();
				e.printStackTrace();
			}
			catch( IOException e )
			{
				MessageBox msgBox = new MessageBox( window.getShell(), SWT.YES );
				msgBox.setMessage( e.getMessage() );
				msgBox.open();
				e.printStackTrace();
			}
			
		}
		window.refresh();
		
	}
}
