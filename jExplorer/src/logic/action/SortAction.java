/*
 * @author talent_marquis<Ìð²Ëºî¾ô>
 * Email: talent_marquis@163.com
 * Copyright (C) 2007 talent_marquis<Ìð²Ëºî¾ô>
 * All rights reserved.
 */
package logic.action;


import java.util.Map;

import org.eclipse.jface.action.Action;

import common.JExplorerConstant;

import display.JExplorer;

public class SortAction extends Action

{
	private String sortType;
	private JExplorer window;
	private Map<String, String> sortTypeMap = JExplorerConstant.getSortTypeMap();

	public SortAction( String sortTypeKey, JExplorer w )
	{
		window = w;
		this.sortType = sortTypeMap.get( sortTypeKey );
		setText( sortTypeKey );
		setToolTipText( "Sort" );
		//setImageDescriptor( ImageDescriptor.createFromURL( JExplorerUtil.newURL( "file:icons/sort.gif" ) ) );
	}

	
	public void run()
	{
		window.doSort( sortType );
	}

}
