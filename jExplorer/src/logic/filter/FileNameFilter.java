/*
 * @author talent_marquis<Ìð²Ëºî¾ô>
 * Email: talent_marquis@163.com
 * Copyright (C) 2007 talent_marquis<Ìð²Ëºî¾ô>
 * All rights reserved.
 */
package logic.filter;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.jface.viewers.*;

public class FileNameFilter extends ViewerFilter
{
	private String regex = "";
	public FileNameFilter( String name )
	{
		super();
		regex = name;
	}
	public boolean select( Viewer viewer, Object parent, Object element )
	{
		if( "".equals( regex ) || regex == null )
		{
			//display all files
			return true;
		}
		else
		{
			//display the files including the letters in regex, and ignore low/up case
			String name = ((File)element).getName();
			Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE );
			Matcher m = p.matcher(name);

			boolean isFinded = m.find();
			return isFinded;
		}
	}
}
