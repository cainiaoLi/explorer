/*
 * @author talent_marquis<Ìð²Ëºî¾ô>
 * Email: talent_marquis@163.com
 * Copyright (C) 2007 talent_marquis<Ìð²Ëºî¾ô>
 * All rights reserved.
 */
package logic.action;

import java.io.*;
import java.util.*;

import org.eclipse.jface.action.*;
import org.eclipse.jface.resource.*;
import org.eclipse.jface.viewers.*;
import org.eclipse.swt.dnd.*;

import util.JExplorerUtil;

import display.JExplorer;
import common.JExplorerConstant;

public class CutAction extends Action
{
	JExplorer window;

	public CutAction( JExplorer w )
	{
		window = w;
		//setEnabled( false );
		setToolTipText( "Cut files" );
		setText( "Cut@Ctrl+X" );
		setImageDescriptor( ImageDescriptor.createFromURL( JExplorerUtil.newURL( "file:icons/cut.gif" ) ) );
	}

	public void run()
	{
		//TODO implements the function of cut
		Clipboard clipboard = JExplorerUtil.getClipboard();
		TextTransfer text_transfer = TextTransfer.getInstance();

		IStructuredSelection selection = window.getTableSelection();
		if( selection.isEmpty() )
		{
			return;
		}
		StringBuffer string_buffer = new StringBuffer();
		for( Iterator i = selection.iterator(); i.hasNext(); )
		{
			File file = ( File ) i.next();
			if( string_buffer.length() == 0 )
			{
				string_buffer.append( file.getAbsolutePath() );
			}
			else
			{
				string_buffer.append( JExplorerConstant.FILE_COPY_SEPARATOR );
				string_buffer.append( file.getAbsolutePath() );
			}
		}
		clipboard.setContents( 
				new Object[]{ string_buffer.toString() }, 
				new Transfer[]{ text_transfer } );
		
		window.setStatus( "cut " + selection.size() + " files" );
		window.setCut( true );
	}
}
