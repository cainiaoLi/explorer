/*
 * @author talent_marquis<Ìð²Ëºî¾ô>
 * Email: talent_marquis@163.com
 * Copyright (C) 2007 talent_marquis<Ìð²Ëºî¾ô>
 * All rights reserved.
 */
package logic.action;


import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

import util.JExplorerUtil;
import display.JExplorer;

public class FilterAction extends Action implements SelectionListener
{
	JExplorer window;

	public FilterAction( JExplorer w )
	{
		window = w;
		setText( "&Filter@Ctrl+Shift+F" );
		setToolTipText( "Filter current folder" );
		setImageDescriptor( ImageDescriptor.createFromURL( JExplorerUtil.newURL( "file:icons/filter.gif" ) ) );
	}

	public void run()
	{
		//TODO become can input prarmet in the future
		window.doFilter();
	}

	public void widgetDefaultSelected( SelectionEvent e )
	{
		//do nothing
	}

	public void widgetSelected( SelectionEvent e )
	{
		window.doFilter();
		
	}
}
