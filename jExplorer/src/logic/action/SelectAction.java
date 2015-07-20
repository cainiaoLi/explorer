/*
 * @author talent_marquis<Ìð²Ëºî¾ô>
 * Email: talent_marquis@163.com
 * Copyright (C) 2007 talent_marquis<Ìð²Ëºî¾ô>
 * All rights reserved.
 */
package logic.action;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.action.*;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;

import display.JExplorer;

import util.JExplorerUtil;

public class SelectAction extends Action
{
	public static final int SELECT_TYPE_ALL = 1;
	public static final int SELECT_TYPE_FILE = 2;
	public static final int SELECT_TYPE_FOLDER = 3;
	
	private JExplorer window;
	private int type;

	/**
	 * 
	 * @param w
	 * @param type all = 1, file = 2, folder = 3
	 */
	public SelectAction( JExplorer w, int type )
	{
		
		window = w;
		this.type = type;
		String text = "";
		String toolTipText = "";
		switch( type )
		{
			case SELECT_TYPE_ALL:
				text = "Select &all@Ctrl+A";
				toolTipText = "Select all";
				break;
			case SELECT_TYPE_FILE:
				text = "Select all &files@Ctrl+F";
				toolTipText = "Select all files";
				break;
			case SELECT_TYPE_FOLDER:
				text = "Select all fol&ders@Ctrl+D";
				toolTipText = "Select all folders";
				break;
		}
		
		setText( text );
		setToolTipText( toolTipText );
		setImageDescriptor( ImageDescriptor.createFromURL( JExplorerUtil.newURL( "file:icons/selectall.gif" ) ) );
	}

	public void run()
	{
		window.getTablebViewer().getTable().selectAll();
		IStructuredSelection selection = window.getTableSelection();
		List< Object > selectionList = new ArrayList< Object >();
		if( type == SELECT_TYPE_ALL )
		{
			return;
		}
		else if ( type == SELECT_TYPE_FILE )
		{	
			for( Iterator i = selection.iterator(); i.hasNext(); )
			{
				File file = ( File ) i.next();
				if( file.isFile() )
				{
					selectionList.add( file );
				}
			}
			window.getTablebViewer().setSelection( new StructuredSelection(selectionList.toArray()) );
			return;
		}
		else if( type == SELECT_TYPE_FOLDER )
		{
			for( Iterator i = selection.iterator(); i.hasNext(); )
			{
				File file = ( File ) i.next();
				if( file.isDirectory() )
				{
					selectionList.add( file );
				}
			}
			window.getTablebViewer().setSelection( new StructuredSelection(selectionList.toArray()) );
			return;
		}
		else
		{
			//throw new Exception("Wrong Pramater!");
		}

	}
}
