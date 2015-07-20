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

import util.FileUtil;

public class FileTypeFilter extends ViewerFilter
{
	private String regex = "";

	public FileTypeFilter( String type )
	{
		super();
		regex = type;
	}

	public boolean select( Viewer viewer, Object parent, Object element )
	{
		if( FileUtil.FILE_TYPE_ALL.equals( regex ) || "".equals( regex ) || regex == null )
		{
			// display all files
			return true;
		}
		else
		{
			// display the files including the letters in regex, and ignore
			// low/up case
			String type = FileUtil.getFileType( ( File ) element );
			Pattern p = Pattern.compile( regex, Pattern.CASE_INSENSITIVE );
			Matcher m = p.matcher( type );

			boolean isFinded = m.find();
			return isFinded;
		}
	}
}
