/*
 * @author talent_marquis<Ìð²Ëºî¾ô> Email: talent_marquis@163.com Copyright (C) 2007 talent_marquis<Ìð²Ëºî¾ô>
 * All rights reserved.
 */
package logic.action;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.eclipse.jface.action.*;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.window.*;

import display.dialog.AboutDialog;

import util.*;
import common.*;

public class AboutAction extends Action
{
	private ApplicationWindow window;

	public AboutAction( ApplicationWindow w )
	{
		window = w;
		setText( "&About@Alt+A" );
		setToolTipText( "About jExplorer" );
		setImageDescriptor( ImageDescriptor.createFromURL( JExplorerUtil.newURL( "file:icons/about.gif" ) ) );
	}

	public void run()
	{
		AboutDialog aboutDlg = new AboutDialog( window.getShell() );
		aboutDlg.setAuthor( JExplorerConstant.ABOUT_AUTHOR );
		aboutDlg.setEmail( JExplorerConstant.ABOUT_EMAIL );
		aboutDlg.setVersion( JExplorerConstant.ABOUT_VERSION );
		aboutDlg.setTitle( JExplorerConstant.ABOUT_INFO );
		try
		{
			aboutDlg.setContent( FileUtil.getFileContent( new File( JExplorerConstant.ABOUT_FILE_PATH ) ) );
		}
		catch( FileNotFoundException e )
		{
			e.printStackTrace();
		}
		catch( IOException e )
		{
			e.printStackTrace();
		}
		aboutDlg.open();
	}

}
