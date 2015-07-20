/*
 * @author talent_marquis<Ìð²Ëºî¾ô>
 * Email: talent_marquis@163.com
 * Copyright (C) 2007 talent_marquis<Ìð²Ëºî¾ô>
 * All rights reserved.
 */
package logic.action;

import java.io.File;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.program.Program;

import util.JExplorerUtil;

import display.JExplorer;

public class OpenAction extends Action implements ISelectionChangedListener, IDoubleClickListener

{
	JExplorer window;

	public OpenAction( JExplorer w )
	{
		window = w;
		setText( "&Open@ALT+O" );
		setToolTipText( "Open the file" );
		setImageDescriptor( ImageDescriptor.createFromURL( JExplorerUtil.newURL( "file:icons/run.gif" ) ) );
	}

	public void run()
	{
		IStructuredSelection selection = window.getTableSelection();
		if( selection.size() != 1 )
		{
			return;
		}
		File selected_file = ( File ) selection.getFirstElement();
		if( selected_file.isFile() )
		{
			Program.launch( selected_file.getAbsolutePath() );
		}
		else if( selected_file.isDirectory() )
		{
			window.openFolder( selected_file );
			// window.getTreeViewer().setSelection( selection );
			// window.getTreeViewer().setExpandedState( selected_file, true );
			// window.getTablebViewer().setInput( selected_file );
		}
	}

	public void selectionChanged( SelectionChangedEvent event )
	{
		setText( "Run" );
		setToolTipText( "Run the associated program on a file" );
		IStructuredSelection selection = window.getTableSelection();
		if( selection.size() != 1 )
		{
			setEnabled( false );
			setToolTipText( getToolTipText() + " (Only enabled when exactly one item is selected)" );
			return;
		}
		File file = ( File ) selection.getFirstElement();
		if( file.isFile() )
		{
			setEnabled( true );
			setText( "Run " + file.getName() );
			setToolTipText( "Run " + file.getName() );
		}
		else if( file.isDirectory() )
		{
			setEnabled( true );
			setText( "Open folder " + file.getName() );
			setToolTipText( "Open folder  " + file.getName() );
		}
	}

	public void doubleClick( DoubleClickEvent event )
	{
		IStructuredSelection selection = window.getTableSelection();
		if( selection.size() != 1 )
		{
			return;
		}
		File selected_file = ( File ) selection.getFirstElement();
		if( selected_file.isFile() )
		{
			Program.launch( selected_file.getAbsolutePath() );
		}
		else if( selected_file.isDirectory() )
		{
			window.openFolder( selected_file );
			// window.getTreeViewer().setSelection( selection );
			// window.getTreeViewer().setExpandedState( selected_file, true );
			// window.getTablebViewer().setInput( selected_file );
		}
	}

}
