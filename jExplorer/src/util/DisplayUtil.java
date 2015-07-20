/*
 * @author talent_marquis<Ìð²Ëºî¾ô>
 * Email: talent_marquis@163.com
 * Copyright (C) 2007 talent_marquis<Ìð²Ëºî¾ô>
 * All rights reserved.
 */
package util;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;

public class DisplayUtil
{
	/**
	 * make the control display at the mouse's location
	 * 
	 * @param control
	 */
	public static void showOnAtMouseLocation( Control control )
	{
		int mouseX = Display.getDefault().getCursorLocation().x;
		int mouseY = Display.getDefault().getCursorLocation().y;
		control.setLocation( mouseX - control.getSize().x / 2, mouseY - control.getSize().y / 2 );
	}

	/**
	 * make the control display at the middle of the screen
	 * 
	 * @param control
	 */
	public static void showOnAtScreenMiddle( Control control )
	{
		int screenWidth = Display.getDefault().getPrimaryMonitor().getBounds().width;
		int screenHeight = Display.getDefault().getPrimaryMonitor().getBounds().height;

		control.setLocation( ( screenWidth - control.getSize().x ) / 2, ( screenHeight - control.getSize().y ) / 2 );

	}

	public static void main( String args[] )
	{

		// DisplayUtil.setShowOnInTheMiddle();
	}
	
	/**
	 * 
	 * @param fontData the file style in SWT
	 * @return the file style in SWING
	 */
	public static java.awt.Font getAWTFont( FontData fontData )
	{
		String fontName = fontData.getName();
		int fontSize = fontData.getHeight();
		int fontStyle = fontData.getStyle();

		if( fontStyle == SWT.BOLD )
		{
			fontStyle = java.awt.Font.BOLD;
		}
		else if( fontStyle == SWT.ITALIC )
		{
			fontStyle = java.awt.Font.ITALIC;
		}
		else if( fontStyle == ( SWT.BOLD | SWT.ITALIC ) )
		{
			fontStyle = java.awt.Font.BOLD + java.awt.Font.ITALIC;
		}
		else
		{
			fontStyle = java.awt.Font.PLAIN;
		}

		return new java.awt.Font( fontName, fontStyle, fontSize );

	}

	/**
	 * 
	 * @param rgb the color style in SWT
	 * @return the color style in AWT
	 */
	public static java.awt.Color getAWTColor( RGB rgb )
	{
		return new java.awt.Color( rgb.red, rgb.green, rgb.blue );
	}
}
